package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.ui.theme.FeaturedPink
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightSurface
import com.example.ui.theme.LightSurfaceVariant
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
                width = if (listing.isFeatured) 1.5.dp else 1.dp,
                color = if (listing.isFeatured) Color(0xFFDB2777) else LightBorder,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .testTag("listing_card_${listing.id}"),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box {
            Column(modifier = Modifier.padding(16.dp)) {
                // Top row: Platform icon, Title & Handle, Favorite button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    PlatformIconBadge(platform = platform, size = 46.dp)

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = listing.title,
                                fontSize = 16.sp,
                                color = LightTextPrimary,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f, fill = false)
                            )
                            if (listing.isFeatured) {
                                Box(
                                    modifier = Modifier
                                        .background(FeaturedPink, RoundedCornerShape(10.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "Featured",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            text = listing.handleOrLink,
                            fontSize = 12.sp,
                            color = MarketPrimary,
                            fontWeight = FontWeight.Medium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    IconButton(
                        onClick = onToggleFavorite,
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("favorite_btn_${listing.id}")
                    ) {
                        Icon(
                            imageVector = if (listing.isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (listing.isFavorite) TonGold else LightTextMuted,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Badges row: category, privacy, TON Ads, verified, premium
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Category chip
                    TagChip(
                        text = category.displayName,
                        textColor = LightTextSecondary,
                        bgColor = LightSurfaceVariant,
                        borderColor = LightBorder
                    )

                    // Privacy badge
                    PrivacyBadge(privacy = privacy)

                    // Telegram specific TON Ads tag
                    if (platform == Platform.TELEGRAM && category != ItemCategory.USER_ACCOUNT) {
                        TonAdsBadge(status = tonAds)
                    }

                    // Verified badge
                    if (listing.isVerified) {
                        VerifiedBadge()
                    }

                    // Premium badge
                    if (listing.isPremium) {
                        PremiumBadge()
                    }

                    // Escrow guarantee
                    EscrowGuaranteedBadge()

                    if (listing.isSold) {
                        SoldBadge()
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Bottom row: Metrics (subscribers/members) + Price & Buy Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.People,
                            contentDescription = "Members",
                            tint = LightTextMuted,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = formatMembers(listing.membersCount, category),
                            fontSize = 13.sp,
                            color = LightTextSecondary,
                            fontWeight = FontWeight.SemiBold
                        )

                        if (listing.monthlyIncome.isNotBlank() && listing.monthlyIncome != "$0" && listing.monthlyIncome != "N/A") {
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .background(Color(0x1910B981), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = listing.monthlyIncome,
                                    fontSize = 11.sp,
                                    color = PriceGreen,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                    // Price & Buy Now button like the image
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier.padding(end = 10.dp)
                        ) {
                            Text(
                                text = "$${listing.price.toInt()}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black,
                                color = PriceGreen
                            )
                            Text(
                                text = listing.currency,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = LightTextMuted
                            )
                        }
                        Button(
                            onClick = onClick,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (listing.isSold) Color(0xFF64748B) else Color(0xFF0284C7)
                            ),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 14.dp, vertical = 7.dp),
                            modifier = Modifier.height(36.dp)
                        ) {
                            Text(
                                text = if (listing.isSold) "Sold" else "Buy Now",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        }
                    }
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

