package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.ChatMessageEntity
import com.example.data.DealChatEntity
import com.example.data.ListingEntity
import com.example.data.ListingRepository
import com.example.model.ItemCategory
import com.example.model.ListingFilter
import com.example.model.Platform
import com.example.model.PrivacyType
import com.example.model.SortOption
import com.example.model.TonAdsStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class MarketUiState(
    val listings: List<ListingEntity> = emptyList(),
    val allListingsForAdmin: List<ListingEntity> = emptyList(),
    val favoriteListings: List<ListingEntity> = emptyList(),
    val dealChats: List<DealChatEntity> = emptyList(),
    val unreadNoticesCount: Int = 0,
    val currentFilter: ListingFilter = ListingFilter(),
    val isAdminUnlocked: Boolean = false,
    val isLoading: Boolean = false,
    val totalVolumeUsd: Double = 0.0,
    val pendingCount: Int = 0
)

class MarketViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ListingRepository

    private val _filter = MutableStateFlow(ListingFilter())
    val filter: StateFlow<ListingFilter> = _filter

    private val _isAdminUnlocked = MutableStateFlow(false)
    val isAdminUnlocked: StateFlow<Boolean> = _isAdminUnlocked

    val uiState: StateFlow<MarketUiState>

    init {
        val database = AppDatabase.getDatabase(application)
        repository = ListingRepository(database.listingDao(), database.dealDao())

        viewModelScope.launch {
            repository.seedInitialDataIfNeeded()
        }

        uiState = combine(
            repository.approvedListings,
            repository.allListings,
            repository.favoriteListings,
            repository.allDealChats,
            repository.unreadNoticesCount,
            _filter,
            _isAdminUnlocked
        ) { args: Array<Any> ->
            @Suppress("UNCHECKED_CAST")
            val approved = args[0] as List<ListingEntity>
            @Suppress("UNCHECKED_CAST")
            val all = args[1] as List<ListingEntity>
            @Suppress("UNCHECKED_CAST")
            val favorites = args[2] as List<ListingEntity>
            @Suppress("UNCHECKED_CAST")
            val deals = args[3] as List<DealChatEntity>
            val unreadNotices = args[4] as Int
            val currentFilter = args[5] as ListingFilter
            val adminUnlocked = args[6] as Boolean

            // Apply filter to approved listings
            val filtered = approved.filter { item ->
                // Platform filter
                val matchesPlatform = currentFilter.platform == null ||
                        item.platform.equals(currentFilter.platform.name, ignoreCase = true)

                // Category filter
                val matchesCategory = currentFilter.category == null ||
                        item.category.equals(currentFilter.category.name, ignoreCase = true)

                // Privacy filter
                val matchesPrivacy = currentFilter.privacy == null ||
                        item.privacy.equals(currentFilter.privacy.name, ignoreCase = true)

                // TON Ads filter
                val matchesTonAds = currentFilter.tonAdsStatus == null ||
                        item.tonAdsStatus.equals(currentFilter.tonAdsStatus.name, ignoreCase = true)

                // Verified filter
                val matchesVerified = !currentFilter.verifiedOnly || item.isVerified

                // Premium filter
                val matchesPremium = !currentFilter.premiumOnly || item.isPremium

                // Search query
                val matchesSearch = currentFilter.searchQuery.isBlank() ||
                        item.title.contains(currentFilter.searchQuery, ignoreCase = true) ||
                        item.handleOrLink.contains(currentFilter.searchQuery, ignoreCase = true) ||
                        item.niche.contains(currentFilter.searchQuery, ignoreCase = true) ||
                        item.description.contains(currentFilter.searchQuery, ignoreCase = true)

                matchesPlatform && matchesCategory && matchesPrivacy &&
                        matchesTonAds && matchesVerified && matchesPremium && matchesSearch
            }.sortedWith { a, b ->
                when (currentFilter.sortBy) {
                    SortOption.NEWEST -> {
                        if (a.isFeatured != b.isFeatured) b.isFeatured.compareTo(a.isFeatured)
                        else b.createdAt.compareTo(a.createdAt)
                    }
                    SortOption.PRICE_LOW_HIGH -> a.price.compareTo(b.price)
                    SortOption.PRICE_HIGH_LOW -> b.price.compareTo(a.price)
                    SortOption.MEMBERS_HIGH_LOW -> b.membersCount.compareTo(a.membersCount)
                }
            }

            val totalVolume = all.sumOf { it.price }
            val pending = all.count { !it.isApproved }

            MarketUiState(
                listings = filtered,
                allListingsForAdmin = all,
                favoriteListings = favorites,
                dealChats = deals,
                unreadNoticesCount = unreadNotices,
                currentFilter = currentFilter,
                isAdminUnlocked = adminUnlocked,
                isLoading = false,
                totalVolumeUsd = totalVolume,
                pendingCount = pending
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MarketUiState(isLoading = true)
        )
    }

    fun selectPlatform(platform: Platform?) {
        _filter.value = _filter.value.copy(
            platform = platform,
            category = null,
            tonAdsStatus = null
        )
    }

    fun selectCategory(category: ItemCategory?) {
        _filter.value = _filter.value.copy(category = category)
    }

    fun selectPrivacy(privacy: PrivacyType?) {
        _filter.value = _filter.value.copy(privacy = privacy)
    }

    fun selectTonAds(status: TonAdsStatus?) {
        _filter.value = _filter.value.copy(tonAdsStatus = status)
    }

    fun toggleVerifiedOnly() {
        _filter.value = _filter.value.copy(verifiedOnly = !_filter.value.verifiedOnly)
    }

    fun togglePremiumOnly() {
        _filter.value = _filter.value.copy(premiumOnly = !_filter.value.premiumOnly)
    }

    fun setSortBy(sort: SortOption) {
        _filter.value = _filter.value.copy(sortBy = sort)
    }

    fun setSearchQuery(query: String) {
        _filter.value = _filter.value.copy(searchQuery = query)
    }

    fun resetFilters() {
        _filter.value = ListingFilter()
    }

    fun toggleFavorite(listingId: Long, currentIsFavorite: Boolean) {
        viewModelScope.launch {
            repository.toggleFavorite(listingId, !currentIsFavorite)
        }
    }

    fun setAdminUnlocked(unlocked: Boolean) {
        _isAdminUnlocked.value = unlocked
    }

    fun adminSetApproval(listingId: Long, isApproved: Boolean) {
        viewModelScope.launch {
            repository.setApproved(listingId, isApproved)
        }
    }

    fun adminToggleFeatured(listingId: Long, currentFeatured: Boolean) {
        viewModelScope.launch {
            repository.setFeatured(listingId, !currentFeatured)
        }
    }

    fun adminToggleSold(listingId: Long, currentSold: Boolean) {
        viewModelScope.launch {
            repository.setSold(listingId, !currentSold)
        }
    }

    fun adminDeleteListing(listingId: Long) {
        viewModelScope.launch {
            repository.deleteListing(listingId)
        }
    }

    // ==========================================
    // P2P ESCROW DEAL CHAT ACTIONS
    // ==========================================
    fun openOrCreateDealChat(listing: ListingEntity, onChatReady: (Long) -> Unit) {
        viewModelScope.launch {
            val chatId = repository.createOrGetDealChat(listing)
            onChatReady(chatId)
        }
    }

    fun getDealChat(dealChatId: Long): Flow<DealChatEntity?> {
        return repository.getDealChatById(dealChatId)
    }

    fun getMessagesForDeal(dealChatId: Long): Flow<List<ChatMessageEntity>> {
        return repository.getMessagesForDeal(dealChatId)
    }

    fun sendChatMessage(
        dealChatId: Long,
        senderRole: String,
        senderName: String,
        text: String
    ) {
        if (text.isBlank()) return
        viewModelScope.launch {
            val notifyAdmin = senderRole != "ADMIN"
            repository.sendMessage(dealChatId, senderRole, senderName, text.trim(), notifyAdmin)
        }
    }

    fun adminApproveDeal(dealChatId: Long) {
        viewModelScope.launch {
            repository.approveDeal(dealChatId)
        }
    }

    fun adminCompleteDeal(dealChatId: Long, listingId: Long) {
        viewModelScope.launch {
            repository.completeDeal(dealChatId, listingId)
        }
    }

    fun markNoticeRead(dealChatId: Long) {
        viewModelScope.launch {
            repository.markNoticeAsRead(dealChatId)
        }
    }

    fun createListing(
        title: String,
        platform: Platform,
        category: ItemCategory,
        privacy: PrivacyType,
        tonAdsStatus: TonAdsStatus,
        isVerified: Boolean,
        isPremium: Boolean,
        handleOrLink: String,
        membersCount: Int,
        price: Double,
        currency: String,
        description: String,
        niche: String,
        monthlyIncome: String,
        sellerTelegram: String,
        sellerWhatsApp: String,
        onComplete: (Long) -> Unit
    ) {
        viewModelScope.launch {
            val entity = ListingEntity(
                title = title.trim(),
                platform = platform.name,
                category = category.name,
                privacy = privacy.name,
                tonAdsStatus = tonAdsStatus.name,
                isVerified = isVerified,
                isPremium = isPremium,
                handleOrLink = handleOrLink.trim(),
                membersCount = membersCount,
                price = price,
                currency = currency,
                description = description.trim(),
                niche = niche.ifBlank { "General" },
                monthlyIncome = monthlyIncome.ifBlank { "$0" },
                sellerTelegram = sellerTelegram.trim(),
                sellerWhatsApp = sellerWhatsApp.trim(),
                isFeatured = false,
                isApproved = true,
                isSold = false,
                isFavorite = false,
                viewsCount = 1
            )
            val newId = repository.insertListing(entity)
            onComplete(newId)
        }
    }
}
