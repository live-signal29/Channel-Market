package com.example.ui.components

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.example.ui.theme.FeaturedPink
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightSurface
import com.example.ui.theme.LightSurfaceVariant
import com.example.ui.theme.LightTextMuted
import com.example.ui.theme.LightTextPrimary
import com.example.ui.theme.LightTextSecondary
import com.example.ui.theme.MarketPrimary
import com.example.ui.theme.PriceGreen
import com.example.ui.theme.TelegramCyan
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
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .testTag("listing_card_${listing.id}"),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // 1. Top row: Platform icon, Title & Handle, Favorite bookmark
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PlatformIconBadge(platform = platform, size = 40.dp)

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = listing.title,
                        fontSize = 15.sp,
                        color = LightTextPrimary,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = listing.handleOrLink,
                            fontSize = 12.sp,
                            color = MarketPrimary,
                            fontWeight = FontWeight.Medium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f, fill = false)
                        )

                        if (listing.isFeatured) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFFEF2F2), RoundedCornerShape(4.dp))
                                    .border(1.dp, Color(0xFFFECACA), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = "★ Featured",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFDC2626)
                                )
                            }
                        }
                    }
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

            // 2. Badges FlowRow (Clean and relevant tags)
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                TagChip(
                    text = category.displayName,
                    textColor = LightTextSecondary,
                    bgColor = LightSurfaceVariant,
                    borderColor = LightBorder
                )

                if (platform == Platform.TELEGRAM && category != ItemCategory.USER_ACCOUNT && tonAds != TonAdsStatus.NONE) {
                    TonAdsBadge(status = tonAds)
                }

                if (listing.isVerified) {
                    VerifiedBadge()
                }

                if (listing.isPremium) {
                    PremiumBadge()
                }

                EscrowGuaranteedBadge()

                if (listing.isSold) {
                    SoldBadge()
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // 3. Metrics row: Members count + Monthly Revenue
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.People,
                        contentDescription = "Members",
                        tint = LightTextMuted,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = formatMembers(listing.membersCount, category),
                        fontSize = 12.sp,
                        color = LightTextSecondary,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                if (listing.monthlyIncome.isNotBlank() && listing.monthlyIncome != "$0" && listing.monthlyIncome != "N/A") {
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(
                        modifier = Modifier
                            .background(Color(0xFFECFDF5), RoundedCornerShape(4.dp))
                            .border(1.dp, Color(0xFFA7F3D0), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.TrendingUp,
                            contentDescription = null,
                            tint = PriceGreen,
                            modifier = Modifier.size(11.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = listing.monthlyIncome,
                            fontSize = 11.sp,
                            color = PriceGreen,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 7.dp),
                thickness = 0.8.dp,
                color = LightBorder.copy(alpha = 0.7f)
            )

            // 4. Dedicated Bottom Action Row: Clear Price (Left) + Buy Button (Right)
            // Price is locked to horizontal layout and will NEVER wrap character-by-character
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Price: Horizontal, high contrast, clean
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    Text(
                        text = "$${NumberFormat.getNumberInstance(Locale.US).format(listing.price.toInt())}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        color = PriceGreen,
                        maxLines = 1,
                        softWrap = false
                    )
                    Spacer(modifier = Modifier.width(4.dp))
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

                // Buy Now Action Button
                Button(
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (listing.isSold) Color(0xFF64748B) else TelegramCyan
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                    modifier = Modifier.height(34.dp)
                ) {
                    Text(
                        text = if (listing.isSold) "Sold Out" else "Buy Now",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (listing.isSold) Color.White else Color.Black,
                        maxLines = 1,
                        softWrap = false
                    )
                }
            }
        }
    }
}

fun formatMembers(count: Int, category: ItemCategory): String {
    if (category == ItemCategory.USER_ACCOUNT) {
        return "1 Account"
    }
    return when {
        count >= 1_000_000 -> String.format(Locale.US, "%.1fM", count / 1_000_000.0)
        count >= 1_000 -> String.format(Locale.US, "%.1fK", count / 1_000.0)
        else -> NumberFormat.getNumberInstance(Locale.US).format(count)
    } + if (category == ItemCategory.CHANNEL) " Subs" else " Members"
}

