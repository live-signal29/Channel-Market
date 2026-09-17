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
        return when (category) {
            ItemCategory.USER_ACCOUNT -> {
                // Rule 1: Account rate price: Max 100$ se ziyaada na ho, her category me!
                val base = when {
                    isPremium && isVerified -> 92.0
                    isPremium -> 85.0
                    isVerified -> 75.0
                    membersCount > 50000 -> 60.0
                    membersCount > 20000 -> 45.0
                    membersCount > 5000 -> 35.0
                    else -> 25.0
                }
                val dynamic = (base + ((rawPrice.toInt() % 12))).coerceIn(15.0, 98.0)
                min(dynamic, 98.0)
            }

            ItemCategory.CHANNEL -> {
                // Rule 2: Channel rate price: minimum 30$ se 2000$ tak
                // Rule 3: but TON active, TON not active se kam ho!
                when (tonAdsStatus) {
                    TonAdsStatus.ACTIVE -> {
                        // TON Ads Active: strictly LOWER than Not Active ($35 - $190)
                        val base = when {
                            isPremium && isVerified -> 170.0
                            isPremium -> 140.0
                            isVerified -> 115.0
                            membersCount > 200000 -> 90.0
                            membersCount > 100000 -> 70.0
                            membersCount > 50000 -> 50.0
                            else -> 38.0
                        }
                        (base + (rawPrice.toInt() % 15)).coerceIn(35.0, 190.0)
                    }

                    TonAdsStatus.NOT_ACTIVE -> {
                        // TON Ads Not Active: strictly HIGHER than Active ($260 - $1850)
                        val base = when {
                            isPremium && isVerified -> 1450.0
                            isPremium -> 980.0
                            isVerified -> 720.0
                            membersCount > 200000 -> 580.0
                            membersCount > 100000 -> 420.0
                            membersCount > 50000 -> 320.0
                            else -> 260.0
                        }
                        (base + (rawPrice.toInt() % 40)).coerceIn(260.0, 1850.0)
                    }

                    TonAdsStatus.NONE -> {
                        // Other platforms channels (YouTube, WhatsApp, Instagram, TikTok)
                        // Strictly between 30.0 and 2000.0
                        if (platform == Platform.YOUTUBE) {
                            // YouTube monetized channels
                            val base = if (isPremium || isVerified) 1200.0 else 580.0
                            (base + (rawPrice.toInt() % 250)).coerceIn(90.0, 1950.0)
                        } else {
                            val base = when {
                                isPremium && isVerified -> 480.0
                                isPremium -> 360.0
                                isVerified -> 260.0
                                membersCount > 200000 -> 180.0
                                membersCount > 50000 -> 110.0
                                else -> 45.0
                            }
                            (base + (rawPrice.toInt() % 35)).coerceIn(30.0, 1100.0)
                        }
                    }
                }
            }

            ItemCategory.PAGE -> {
                // Facebook / Instagram Pages (Broadcast pages: $35 - $650)
                val base = when {
                    isPremium && isVerified -> 520.0
                    isPremium -> 380.0
                    isVerified -> 260.0
                    membersCount > 200000 -> 190.0
                    membersCount > 50000 -> 120.0
                    else -> 45.0
                }
                (base + (rawPrice.toInt() % 30)).coerceIn(30.0, 950.0)
            }

            ItemCategory.GROUP -> {
                when (tonAdsStatus) {
                    TonAdsStatus.ACTIVE -> {
                        // TON Ads Active group: lower price ($25 - $80)
                        val base = if (isPremium || isVerified) 70.0 else 40.0
                        (base + (rawPrice.toInt() % 12)).coerceIn(25.0, 80.0)
                    }
                    TonAdsStatus.NOT_ACTIVE -> {
                        // TON Ads Not Active group: higher price ($95 - $350)
                        val base = if (isPremium || isVerified) 260.0 else 140.0
                        (base + (rawPrice.toInt() % 25)).coerceIn(95.0, 350.0)
                    }
                    TonAdsStatus.NONE -> {
                        // WhatsApp, Facebook groups
                        val base = if (isPremium || isVerified) 190.0 else 75.0
                        (base + (rawPrice.toInt() % 20)).coerceIn(30.0, 380.0)
                    }
                }
            }

            ItemCategory.SERVER -> {
                // Discord Servers ($35 - $380)
                val base = if (isPremium || isVerified) 240.0 else 95.0
                (base + (rawPrice.toInt() % 25)).coerceIn(35.0, 390.0)
            }

            ItemCategory.BOT -> {
                // Telegram / Discord bots ($25 - $95)
                val base = if (isPremium || isVerified) 70.0 else 40.0
                (base + (rawPrice.toInt() % 15)).coerceIn(25.0, 95.0)
            }
        }
    }
}
