package com.example.ui.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ListingEntity
import com.example.model.ItemCategory
import com.example.model.Platform
import com.example.model.PrivacyType
import com.example.model.TonAdsStatus
import com.example.ui.MarketViewModel
import com.example.ui.components.EscrowGuaranteedBadge
import com.example.ui.components.PlatformIconBadge
import com.example.ui.components.PremiumBadge
import com.example.ui.components.PrivacyBadge
import com.example.ui.components.SoldBadge
import com.example.ui.components.TagChip
import com.example.ui.components.TonAdsBadge
import com.example.ui.components.VerifiedBadge
import com.example.ui.components.formatMembers
import com.example.ui.theme.DangerRed
import com.example.ui.theme.DarkBg
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.DarkTextMuted
import com.example.ui.theme.DarkTextPrimary
import com.example.ui.theme.DarkTextSecondary
import com.example.ui.theme.MarketPrimary
import com.example.ui.theme.PriceGreen
import com.example.ui.theme.TelegramCyan
import com.example.ui.theme.TonGold
import com.example.ui.theme.WhatsAppGreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListingDetailScreen(
    listing: ListingEntity,
    viewModel: MarketViewModel,
    isAdminUnlocked: Boolean,
    onBack: () -> Unit,
    onOpenDealChat: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val platform = listing.getPlatformEnum()
    val category = listing.getCategoryEnum()
    val privacy = listing.getPrivacyEnum()
    val tonAds = listing.getTonAdsEnum()

    // Helper to start in-app P2P Escrow Chat
    val startInAppDealChat = {
        viewModel.openOrCreateDealChat(listing) { chatId ->
            onOpenDealChat(chatId)
        }
    }

    // Helper to open links
    val openUrl = { url: String ->
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (_: Exception) { }
    }

    // Helper to open Telegram
    val openTelegramChat = {
        val tgUsername = listing.sellerTelegram.replace("@", "").trim()
        val url = "https://t.me/$tgUsername"
        openUrl(url)
    }

    // Helper to open WhatsApp with prefilled message
    val openWhatsAppChat = {
        val rawNumber = listing.sellerWhatsApp.replace("+", "").replace(" ", "").replace("-", "").trim()
        val message = "Hello! I saw your ${platform.displayName} ${category.displayName} '${listing.title}' for ${listing.price.toInt()} ${listing.currency} on ChannelMarket. Is it still available?"
        val encodedMsg = URLEncoder.encode(message, StandardCharsets.UTF_8.toString())
        val url = "https://wa.me/$rawNumber?text=$encodedMsg"
        openUrl(url)
    }

    // Helper to request admin escrow
    val openAdminEscrow = {
        val adminEscrowMsg = "Hello Admin, I want to request ESCROW protection for listing ID #${listing.id} (${listing.title}) on ChannelMarket."
        val encodedMsg = URLEncoder.encode(adminEscrowMsg, StandardCharsets.UTF_8.toString())
        openUrl("https://t.me/ChannelMarketAdminEscrow?start=escrow_${listing.id}")
    }

    val shareListing = {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Check out this ${platform.displayName} ${category.displayName}: '${listing.title}' priced at ${listing.price.toInt()} ${listing.currency} on ChannelMarket! Handle: ${listing.handleOrLink}"
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share Listing")
        context.startActivity(shareIntent)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = DarkBg,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onBack, modifier = Modifier.testTag("detail_back_btn")) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = DarkTextPrimary
                    )
                }

                Text(
                    text = "${platform.displayName} ${category.displayName}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = DarkTextPrimary
                )

                Row {
                    IconButton(onClick = shareListing, modifier = Modifier.testTag("detail_share_btn")) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = DarkTextPrimary
                        )
                    }

                    IconButton(
                        onClick = { viewModel.toggleFavorite(listing.id, listing.isFavorite) },
                        modifier = Modifier.testTag("detail_favorite_btn")
                    ) {
                        Icon(
                            imageVector = if (listing.isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (listing.isFavorite) TonGold else DarkTextPrimary
                        )
                    }
                }
            }
        },
        bottomBar = {
            // Action Buttons Bar with Small Chat Button and Buy Channel Button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkSurface)
                    .border(1.dp, DarkBorder, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Small Size Chat Button (To privately chat and discuss with channel owner first)
                    OutlinedButton(
                        onClick = startInAppDealChat,
                        modifier = Modifier
                            .weight(0.38f)
                            .height(48.dp)
                            .testTag("chat_owner_small_btn"),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = TelegramCyan
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, TelegramCyan),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = "Chat",
                            tint = TelegramCyan,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Chat",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    // Buy Channel Button (To proceed with purchase after discussion)
                    Button(
                        onClick = startInAppDealChat,
                        modifier = Modifier
                            .weight(0.62f)
                            .height(48.dp)
                            .testTag("buy_channel_btn"),
                        colors = ButtonDefaults.buttonColors(containerColor = PriceGreen),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Buy",
                            tint = Color.Black,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Buy ($${listing.price.toInt()})",
                            color = Color.Black,
                            fontWeight = FontWeight.Black,
                            fontSize = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = TonGold,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Chat privately with owner • 100% Escrow Protected",
                        fontSize = 11.sp,
                        color = DarkTextMuted,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Header Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DarkBorder, RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PlatformIconBadge(platform = platform, size = 52.dp)

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = listing.title,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black,
                                color = DarkTextPrimary
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = listing.handleOrLink,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = TelegramCyan
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Badges
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TagChip(
                            text = category.displayName,
                            textColor = DarkTextSecondary,
                            bgColor = DarkSurfaceVariant,
                            borderColor = DarkBorder
                        )

                        PrivacyBadge(privacy = privacy)

                        if (platform == Platform.TELEGRAM && category != ItemCategory.USER_ACCOUNT) {
                            TonAdsBadge(status = tonAds)
                        }

                        if (listing.isVerified) VerifiedBadge()
                        if (listing.isPremium) PremiumBadge()
                        EscrowGuaranteedBadge()
                        if (listing.isSold) SoldBadge()
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Price and Status banner
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DarkSurfaceVariant, RoundedCornerShape(12.dp))
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "OFFERING PRICE",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkTextMuted,
                                letterSpacing = 0.5.sp
                            )
                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(
                                    text = "${listing.price.toInt()} ",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Black,
                                    color = PriceGreen
                                )
                                Text(
                                    text = listing.currency,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TelegramCyan,
                                    modifier = Modifier.padding(bottom = 2.dp)
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .background(
                                    if (listing.isSold) Color(0x33EF4444) else Color(0x2225D366),
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = if (listing.isSold) "SOLD OUT" else "AVAILABLE NOW",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (listing.isSold) DangerRed else WhatsAppGreen
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Key Specifications Grid
            Text(
                text = "SPECIFICATIONS & METRICS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DarkTextMuted,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SpecBox(
                    label = if (category == ItemCategory.CHANNEL) "Subscribers" else "Members",
                    value = formatMembers(listing.membersCount, category),
                    icon = Icons.Default.People,
                    iconTint = TelegramCyan,
                    modifier = Modifier.weight(1f)
                )

                SpecBox(
                    label = "Privacy",
                    value = privacy.displayName,
                    icon = Icons.Default.Lock,
                    iconTint = if (privacy == PrivacyType.PUBLIC) TelegramCyan else Color(0xFFC084FC),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SpecBox(
                    label = "TON Ads Program",
                    value = when (tonAds) {
                        TonAdsStatus.ACTIVE -> "Active (Earning)"
                        TonAdsStatus.NOT_ACTIVE -> "Eligible / Off"
                        TonAdsStatus.NONE -> "Not Applicable"
                    },
                    icon = Icons.Default.AttachMoney,
                    iconTint = if (tonAds == TonAdsStatus.ACTIVE) TonGold else DarkTextMuted,
                    modifier = Modifier.weight(1f)
                )

                SpecBox(
                    label = "Monthly Revenue",
                    value = listing.monthlyIncome,
                    icon = Icons.Default.AttachMoney,
                    iconTint = PriceGreen,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SpecBox(
                    label = "Niche / Category",
                    value = listing.niche,
                    icon = Icons.Default.Category,
                    iconTint = TonGold,
                    modifier = Modifier.weight(1f)
                )

                SpecBox(
                    label = "Verification",
                    value = if (listing.isVerified) "Verified Badge" else "Unverified",
                    icon = Icons.Default.CheckCircle,
                    iconTint = if (listing.isVerified) TelegramCyan else DarkTextMuted,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Description Box
            Text(
                text = "ABOUT THIS CHANNEL / GROUP",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DarkTextMuted,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DarkBorder, RoundedCornerShape(14.dp)),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                shape = RoundedCornerShape(14.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = listing.description,
                        fontSize = 14.sp,
                        color = DarkTextPrimary,
                        lineHeight = 21.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0x192AABEE), RoundedCornerShape(8.dp))
                            .padding(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.Top) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = TelegramCyan,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Transfer terms: Primary ownership will be transferred after buyer funds are secured in escrow. 2-Step Verification (2FA) must be active for 7+ days on buyer account before taking primary channel owner rights.",
                                fontSize = 11.sp,
                                color = DarkTextSecondary,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Seller Contact Information (Escrow Enforced)
            Text(
                text = "SELLER & ESCROW VERIFICATION",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DarkTextMuted,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, if (isAdminUnlocked) DarkBorder else TonGold.copy(alpha = 0.5f), RoundedCornerShape(14.dp)),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                shape = RoundedCornerShape(14.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(TonGold.copy(alpha = 0.15f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Security,
                                contentDescription = null,
                                tint = TonGold,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Admin Escrow Protected Deal",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkTextPrimary
                            )
                            Text(
                                text = "Direct deals outside without Admin are forbidden",
                                fontSize = 11.sp,
                                color = TonGold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    if (isAdminUnlocked) {
                        // Admin can see raw direct contacts
                        Text(
                            text = "Admin View (Raw Contact Credentials):",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkTextSecondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Telegram: ${listing.sellerTelegram.ifBlank { "Not provided" }}",
                            fontSize = 13.sp,
                            color = TelegramCyan
                        )
                        Text(
                            text = "WhatsApp: ${listing.sellerWhatsApp.ifBlank { "Not provided" }}",
                            fontSize = 13.sp,
                            color = WhatsAppGreen
                        )
                    } else {
                        Text(
                            text = "Direct private discussion with channel owner to verify statistics, discuss pricing, and agree on transfer.",
                            fontSize = 12.sp,
                            color = DarkTextSecondary,
                            lineHeight = 17.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = startInAppDealChat,
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = TelegramCyan),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Chat,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Chat with Owner (${listing.sellerTelegram.ifBlank { "Seller" }})",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }

            // ADMIN CONTROL SECTION (Visible ONLY when Admin Passcode has been verified)
            if (isAdminUnlocked) {
                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, TonGold, RoundedCornerShape(14.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1B1B10)),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Security,
                                contentDescription = null,
                                tint = TonGold,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "ADMINISTRATOR CONTROLS",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Black,
                                color = TonGold
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Toggle Sold
                            Button(
                                onClick = { viewModel.adminToggleSold(listing.id, listing.isSold) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (listing.isSold) WhatsAppGreen else DarkSurfaceVariant
                                ),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(if (listing.isSold) "Mark Available" else "Mark Sold")
                            }

                            // Toggle Featured
                            Button(
                                onClick = { viewModel.adminToggleFeatured(listing.id, listing.isFeatured) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (listing.isFeatured) TonGold else DarkSurfaceVariant
                                ),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(if (listing.isFeatured) "Unfeature" else "Feature Listing")
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Delete Listing
                        Button(
                            onClick = {
                                viewModel.adminDeleteListing(listing.id)
                                onBack()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = DangerRed),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Delete Listing from Database")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun SpecBox(
    label: String,
    value: String,
    icon: ImageVector,
    iconTint: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.border(1.dp, DarkBorder, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .background(iconTint.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = label,
                    fontSize = 11.sp,
                    color = DarkTextMuted
                )
                Text(
                    text = value,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkTextPrimary
                )
            }
        }
    }
}
