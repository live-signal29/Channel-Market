package com.example.model

enum class Platform(
    val displayName: String,
    val brandColorHex: Long,
    val shortCode: String
) {
    TELEGRAM("Telegram", 0xFF26A5E4, "TG"),
    WHATSAPP("WhatsApp", 0xFF25D366, "WA"),
    TIKTOK("TikTok", 0xFFEE1D52, "TT"),
    INSTAGRAM("Instagram", 0xFFE1306C, "IG"),
    FACEBOOK("Facebook", 0xFF1877F2, "FB"),
    YOUTUBE("YouTube", 0xFFFF0000, "YT"),
    X_TWITTER("X (Twitter)", 0xFFFFFFFF, "X"),
    DISCORD("Discord", 0xFF5865F2, "DC"),
    OTHER("Other", 0xFF9E9E9E, "OT");

    companion object {
        fun fromString(value: String): Platform {
            return entries.firstOrNull { it.name.equals(value, ignoreCase = true) } ?: TELEGRAM
        }
    }
}

enum class ItemCategory(val displayName: String) {
    CHANNEL("Channel"),
    GROUP("Group"),
    BOT("Bot"),
    USER_ACCOUNT("User Account"),
    PAGE("Page"),
    SERVER("Server");

    companion object {
        fun fromString(value: String): ItemCategory {
            return entries.firstOrNull { it.name.equals(value, ignoreCase = true) } ?: CHANNEL
        }
    }
}

enum class PrivacyType(val displayName: String) {
    PUBLIC("Public"),
    PRIVATE("Private");

    companion object {
        fun fromString(value: String): PrivacyType {
            return entries.firstOrNull { it.name.equals(value, ignoreCase = true) } ?: PUBLIC
        }
    }
}

enum class TonAdsStatus(val displayName: String) {
    ACTIVE("TON Ads Active"),
    NOT_ACTIVE("TON Ads Not Active"),
    NONE("Not Applicable");

    companion object {
        fun fromString(value: String): TonAdsStatus {
            return entries.firstOrNull { it.name.equals(value, ignoreCase = true) } ?: NONE
        }
    }
}

data class ListingFilter(
    val platform: Platform? = null,
    val category: ItemCategory? = null,
    val privacy: PrivacyType? = null,
    val tonAdsStatus: TonAdsStatus? = null,
    val verifiedOnly: Boolean = false,
    val premiumOnly: Boolean = false,
    val searchQuery: String = "",
    val minPrice: Double? = null,
    val maxPrice: Double? = null,
    val sortBy: SortOption = SortOption.NEWEST
)

enum class SortOption(val displayName: String) {
    NEWEST("Newest First"),
    PRICE_LOW_HIGH("Price: Low to High"),
    PRICE_HIGH_LOW("Price: High to Low"),
    MEMBERS_HIGH_LOW("Most Members / Subs")
}
