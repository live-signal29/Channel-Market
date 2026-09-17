package com.example.data

import com.example.model.ItemCategory
import com.example.model.Platform
import com.example.model.TonAdsStatus
import kotlin.math.min

object PriceAdjuster {

    fun adjust(
        category: ItemCategory,
        platform: Platform,
        tonAdsStatus: TonAdsStatus,
        isVerified: Boolean,
        isPremium: Boolean,
        rawPrice: Double,
        membersCount: Int
    ): Double {
        return rawPrice
    }
}
