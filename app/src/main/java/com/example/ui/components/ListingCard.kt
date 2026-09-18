package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ListingEntity
import com.example.model.ItemCategory
import com.example.model.Platform
import com.example.model.TonAdsStatus
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightTextMuted
import com.example.ui.theme.LightTextPrimary
import com.example.ui.theme.LightTextSecondary
import com.example.ui.theme.MarketPrimary
import com.example.ui.theme.PriceGreen
import com.example.ui.theme.TonGold
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListingCard(
    listing: ListingEntity,
    onClick: () -> Unit,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    val platform = listing.getPlatformEnum()
    val category = listing.getCategoryEnum()
    val privacy = listing.getPrivacyEnum()
    val tonAds = listing.getTonAdsEnum()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (listing.isFeatured) Color(0xFFFCD34D) else LightBorder,
                shape = RoundedCornerShape(14.dp)
            )
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .testTag("listing_card_${listing.id}"),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            // 1. Top row: Platform Logo, Asset Name, Handle, Favorite button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PlatformIconBadge(platform = platform, size = 38.dp)

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = listing.title,
                        fontSize = 14.5.sp,
                        color = LightTextPrimary,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(1.dp))

                    Text(
                        text = listing.handleOrLink,
                        fontSize = 11.5.sp,
                        color = MarketPrimary,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier
                        .size(32.dp)
                        .testTag("favorite_btn_${listing.id}")
                ) {
                    Icon(
                        imageVector = if (listing.isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (listing.isFavorite) TonGold else LightTextMuted,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // 2. Badges FlowRow (High clarity, unambiguous status)
            // Priority: ASSET TYPE -> PUBLIC/PRIVATE -> TON ADS ACTIVE/NOT ACTIVE -> VERIFIED/NOT VERIFIED
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Asset Type Badge (e.g. CHANNEL, GROUP, BOT, USER ACCOUNT, etc.)
                AssetTypeBadge(category = category, platform = platform)

                // Public / Private Badge
                PrivacyBadge(privacy = privacy)

                // TON Ads status (for Telegram channels & groups)
                if (platform == Platform.TELEGRAM && category != ItemCategory.USER_ACCOUNT && tonAds != TonAdsStatus.NONE) {
                    TonAdsBadge(status = tonAds)
                }

                // Verification Badge
                VerificationBadge(isVerified = listing.isVerified)

                // Premium badge (for User Accounts: show PREMIUM / NON-PREMIUM; for others show when active)
                if (category == ItemCategory.USER_ACCOUNT) {
                    PremiumStatusBadge(isPremium = listing.isPremium)
                } else if (listing.isPremium) {
                    PremiumBadge()
                }

                // Escrow protection guarantee
                EscrowGuaranteedBadge()

                // Sold status
                if (listing.isSold) {
                    SoldBadge()
                }
            }

            Spacer(modifier = Modifier.height(7.dp))

            // 3. Real Asset Metrics: Audience size + Views + Category (NO MONETIZATION/ADS INCOME DATA)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Audience / Subscribers / Members count
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.People,
                        contentDescription = "Audience",
                        tint = LightTextMuted,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = formatAudienceLabel(listing.membersCount, category, platform),
                        fontSize = 11.5.sp,
                        color = LightTextSecondary,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                // Views Metric (channels, groups, pages)
                if (listing.viewsCount > 0 && category != ItemCategory.USER_ACCOUNT) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "Views",
                            tint = LightTextMuted,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "${formatCount(listing.viewsCount)} Avg Views",
                            fontSize = 11.5.sp,
                            color = LightTextSecondary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Niche / Category
                if (listing.niche.isNotBlank() && listing.niche != "General") {
                    Spacer(modifier = Modifier.width(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f, fill = false)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Category,
                            contentDescription = "Category",
                            tint = LightTextMuted,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = listing.niche,
                            fontSize = 11.sp,
                            color = LightTextMuted,
                            fontWeight = FontWeight.Medium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 7.dp),
                thickness = 0.8.dp,
                color = LightBorder.copy(alpha = 0.7f)
            )

            // 4. Dedicated Bottom Action Row: Horizontal Price (Left) + [Details] & [Buy Now] Buttons (Right)
            // Price + USDT strictly stays on one line and never wraps
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Price: Single horizontal line, bold, high contrast
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    Text(
                        text = "$${NumberFormat.getNumberInstance(Locale.US).format(listing.price.toInt())}",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Black,
                        color = PriceGreen,
                        maxLines = 1,
                        softWrap = false
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = listing.currency.ifBlank { "USDT" },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = LightTextMuted,
                        maxLines = 1,
                        softWrap = false
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Action Buttons: Details and Buy Now
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    OutlinedButton(
                        onClick = onClick,
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
                        modifier = Modifier
                            .height(32.dp)
                            .testTag("details_btn_${listing.id}"),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = LightTextPrimary
                        ),
                        border = BorderStroke(1.dp, LightBorder)
                    ) {
                        Text(
                            text = "Details",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            softWrap = false
                        )
                    }

                    Button(
                        onClick = onClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (listing.isSold) Color(0xFF64748B) else MarketPrimary
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                        modifier = Modifier
                            .height(32.dp)
                            .testTag("buy_now_btn_${listing.id}")
                    ) {
                        Text(
                            text = if (listing.isSold) "Sold Out" else "Buy Now",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            maxLines = 1,
                            softWrap = false
                        )
                    }
                }
            }
        }
    }
}

fun formatCount(count: Int): String {
    return when {
        count >= 1_000_000 -> String.format(Locale.US, "%.1fM", count / 1_000_000.0)
        count >= 1_000 -> String.format(Locale.US, "%.1fK", count / 1_000.0)
        else -> NumberFormat.getNumberInstance(Locale.US).format(count)
    }
}

fun formatAudienceLabel(count: Int, category: ItemCategory, platform: Platform): String {
    if (category == ItemCategory.USER_ACCOUNT) {
        return if (count <= 1) "1 Account" else "${formatCount(count)} Followers"
    }
    val formatted = formatCount(count)
    return when (category) {
        ItemCategory.CHANNEL -> "$formatted Subscribers"
        ItemCategory.GROUP -> "$formatted Members"
        ItemCategory.BOT -> "$formatted Users"
        ItemCategory.PAGE -> "$formatted Followers"
        ItemCategory.SERVER -> "$formatted Members"
        else -> "$formatted Members"
    }
}

// Backward compatibility helper for any other screen calling formatMembers
fun formatMembers(count: Int, category: ItemCategory): String {
    return formatAudienceLabel(count, category, Platform.TELEGRAM)
}
