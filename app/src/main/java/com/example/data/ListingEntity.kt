package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.ItemCategory
import com.example.model.Platform
import com.example.model.PrivacyType
import com.example.model.TonAdsStatus

@Entity(tableName = "listings")
data class ListingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val platform: String, // Platform enum name
    val category: String, // ItemCategory enum name
    val privacy: String, // PrivacyType enum name
    val tonAdsStatus: String, // TonAdsStatus enum name
    val isVerified: Boolean = false,
    val isPremium: Boolean = false,
    val handleOrLink: String,
    val membersCount: Int,
    val price: Double,
    val currency: String = "USDT",
    val description: String,
    val niche: String = "General",
    val monthlyIncome: String = "$0",
    val sellerTelegram: String,
    val sellerWhatsApp: String,
    val isFeatured: Boolean = false,
    val isApproved: Boolean = true,
    val isSold: Boolean = false,
    val isFavorite: Boolean = false,
    val viewsCount: Int = 12,
    val createdAt: Long = System.currentTimeMillis()
) {
    fun getPlatformEnum(): Platform = Platform.fromString(platform)
    fun getCategoryEnum(): ItemCategory = ItemCategory.fromString(category)
    fun getPrivacyEnum(): PrivacyType = PrivacyType.fromString(privacy)
    fun getTonAdsEnum(): TonAdsStatus = TonAdsStatus.fromString(tonAdsStatus)
}
