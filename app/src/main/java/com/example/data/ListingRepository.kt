package com.example.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ListingRepository(
    private val listingDao: ListingDao,
    private val dealDao: DealDao
) {

    val allListings: Flow<List<ListingEntity>> = listingDao.getAllListings()
    val approvedListings: Flow<List<ListingEntity>> = listingDao.getApprovedListings()
    val favoriteListings: Flow<List<ListingEntity>> = listingDao.getFavoriteListings()

    fun getListingById(id: Long): Flow<ListingEntity?> {
        return listingDao.getListingById(id)
    }

    suspend fun insertListing(listing: ListingEntity): Long = withContext(Dispatchers.IO) {
        listingDao.insertListing(listing)
    }

    suspend fun updateListing(listing: ListingEntity) = withContext(Dispatchers.IO) {
        listingDao.updateListing(listing)
    }

    suspend fun deleteListing(id: Long) = withContext(Dispatchers.IO) {
        listingDao.deleteListingById(id)
    }

    suspend fun toggleFavorite(id: Long, isFavorite: Boolean) = withContext(Dispatchers.IO) {
        listingDao.toggleFavorite(id, isFavorite)
    }

    suspend fun setSold(id: Long, isSold: Boolean) = withContext(Dispatchers.IO) {
        listingDao.setSoldStatus(id, isSold)
    }

    suspend fun setApproved(id: Long, isApproved: Boolean) = withContext(Dispatchers.IO) {
        listingDao.setApprovalStatus(id, isApproved)
    }

    suspend fun setFeatured(id: Long, isFeatured: Boolean) = withContext(Dispatchers.IO) {
        listingDao.setFeaturedStatus(id, isFeatured)
    }

    suspend fun seedInitialDataIfNeeded() = withContext(Dispatchers.IO) {
        if (listingDao.getCount() == 0) {
            val initialList = InitialData.getInitialListings()
            listingDao.insertAll(initialList)
        }
    }

    // ==========================================
    // P2P ESCROW DEALS & CHATS
    // ==========================================
    val allDealChats: Flow<List<DealChatEntity>> = dealDao.getAllDealChats()
    val unreadNoticesCount: Flow<Int> = dealDao.getUnreadAdminNoticesCount()

    fun getDealChatByListingId(listingId: Long): Flow<DealChatEntity?> {
        return dealDao.getDealChatByListingId(listingId)
    }

    fun getDealChatById(id: Long): Flow<DealChatEntity?> {
        return dealDao.getDealChatById(id)
    }

    fun getMessagesForDeal(dealChatId: Long): Flow<List<ChatMessageEntity>> {
        return dealDao.getMessagesForDeal(dealChatId)
    }

    suspend fun createOrGetDealChat(listing: ListingEntity, buyerName: String = "Buyer"): Long = withContext(Dispatchers.IO) {
        val chat = DealChatEntity(
            listingId = listing.id,
            listingTitle = listing.title,
            platform = listing.platform,
            price = listing.price,
            currency = listing.currency,
            buyerName = buyerName,
            sellerName = "Owner (${listing.sellerTelegram.ifBlank { "@Seller" }})",
            sellerTelegram = listing.sellerTelegram,
            sellerWhatsApp = listing.sellerWhatsApp,
            status = "PENDING_APPROVAL",
            adminApproved = false,
            lastMessage = "Deal chat initiated. Escrow Admin has received an alert for order inspection.",
            lastMessageTime = System.currentTimeMillis(),
            hasAdminNotice = true
        )
        val chatId = dealDao.insertDealChat(chat)

        // Add initial system message
        dealDao.insertMessage(
            ChatMessageEntity(
                dealChatId = chatId,
                senderRole = "SYSTEM",
                senderName = "Shield Escrow Bot",
                text = "🛡️ OFFICIAL ADMIN ESCROW SESSION INITIALIZED\nOrder #ESC-${chatId} for: ${listing.title}\nAmount: ${listing.price} ${listing.currency}\n\nNotice has been sent to Admin Panel. Outside unapproved deals are forbidden to protect funds. Admin will review credentials before transfer approval."
            )
        )
        chatId
    }

    suspend fun sendMessage(
        dealChatId: Long,
        senderRole: String,
        senderName: String,
        text: String,
        notifyAdmin: Boolean = true
    ) = withContext(Dispatchers.IO) {
        dealDao.insertMessage(
            ChatMessageEntity(
                dealChatId = dealChatId,
                senderRole = senderRole,
                senderName = senderName,
                text = text
            )
        )
        dealDao.updateLastMessage(
            id = dealChatId,
            message = "$senderName: $text",
            time = System.currentTimeMillis(),
            hasNotice = notifyAdmin
        )
    }

    suspend fun approveDeal(dealChatId: Long) = withContext(Dispatchers.IO) {
        dealDao.setDealApproval(dealChatId, approved = true, status = "ADMIN_APPROVED")
        dealDao.insertMessage(
            ChatMessageEntity(
                dealChatId = dealChatId,
                senderRole = "ADMIN",
                senderName = "👑 Marketplace Admin",
                text = "✅ DEAL APPROVED BY ADMIN! The buyer may now safely proceed with escrow payment, and seller can prepare ownership credentials. Admin is actively supervising this deal."
            )
        )
        dealDao.markNoticeAsRead(dealChatId)
    }

    suspend fun completeDeal(dealChatId: Long, listingId: Long) = withContext(Dispatchers.IO) {
        dealDao.setDealApproval(dealChatId, approved = true, status = "COMPLETED")
        dealDao.insertMessage(
            ChatMessageEntity(
                dealChatId = dealChatId,
                senderRole = "ADMIN",
                senderName = "👑 Marketplace Admin",
                text = "🎉 DEAL FULLY COMPLETED & FUNDS RELEASED! Channel ownership successfully transferred to Buyer. Escrow order closed."
            )
        )
        listingDao.setSoldStatus(listingId, true)
        dealDao.markNoticeAsRead(dealChatId)
    }

    suspend fun markNoticeAsRead(dealChatId: Long) = withContext(Dispatchers.IO) {
        dealDao.markNoticeAsRead(dealChatId)
    }
}
