package com.example.data

import com.example.model.ItemCategory
import com.example.model.Platform
import com.example.model.PrivacyType
import com.example.model.TonAdsStatus

object SeedHelper {
    fun createItem(
        id: Long,
        title: String,
        platform: Platform,
        category: ItemCategory,
        privacy: PrivacyType = PrivacyType.PUBLIC,
        tonAdsStatus: TonAdsStatus = TonAdsStatus.NONE,
        isVerified: Boolean = false,
        isPremium: Boolean = false,
        handleOrLink: String,
        membersCount: Int,
        price: Double,
        currency: String = "USDT",
        niche: String,
        monthlyIncome: String = "",
        description: String,
        sellerTelegram: String = "@MarketSeller_TG",
        sellerWhatsApp: String = "+13125550144",
        isFeatured: Boolean = false,
        viewsCount: Int = 300,
        hoursAgo: Long = 1
    ): ListingEntity {
        val now = System.currentTimeMillis()
        val finalPrice = PriceAdjuster.adjust(
            category = category,
            platform = platform,
            tonAdsStatus = tonAdsStatus,
            isVerified = isVerified,
            isPremium = isPremium,
            rawPrice = price,
            membersCount = membersCount
        )
        return ListingEntity(
            id = id,
            title = title,
            platform = platform.name,
            category = category.name,
            privacy = privacy.name,
            tonAdsStatus = tonAdsStatus.name,
            isVerified = isVerified,
            isPremium = isPremium,
            handleOrLink = handleOrLink,
            membersCount = membersCount,
            price = finalPrice,
            currency = currency,
            description = description,
            niche = niche,
            monthlyIncome = monthlyIncome,
            sellerTelegram = sellerTelegram,
            sellerWhatsApp = sellerWhatsApp,
            isFeatured = isFeatured,
            isApproved = true,
            isSold = false,
            viewsCount = viewsCount,
            createdAt = now - (hoursAgo * 3600_000L)
        )
    }
}
