package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrendingUp
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DealChatEntity
import com.example.data.ListingEntity
import com.example.model.Platform
import com.example.ui.MarketUiState
import com.example.ui.MarketViewModel
import com.example.ui.components.PlatformIconBadge
import com.example.ui.components.TagChip
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AdminPanelScreen(
    uiState: MarketUiState,
    viewModel: MarketViewModel,
    onBack: () -> Unit,
    onListingClick: (Long) -> Unit,
    onOpenDealChat: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    // Default to "deals" if there are unread notices or deal chats, otherwise "all"
    var selectedTab by remember {
        mutableStateOf(if (uiState.dealChats.isNotEmpty()) "deals" else "all")
    }

    val displayedListings = when (selectedTab) {
        "pending" -> uiState.allListingsForAdmin.filter { !it.isApproved }
        "sold" -> uiState.allListingsForAdmin.filter { it.isSold }
        "featured" -> uiState.allListingsForAdmin.filter { it.isFeatured }
        else -> uiState.allListingsForAdmin
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = DarkBg,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("admin_back_btn")) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = DarkTextPrimary
                        )
                    }
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Admin Control Hub",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = TonGold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .background(Color(0x28FFB300), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 5.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "ESCROW MODERATOR",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TonGold
                                )
                            }
                        }
                        Text(
                            text = "Supervise in-app deals, notices & listings",
                            fontSize = 11.sp,
                            color = DarkTextMuted
                        )
                    }
                }

                // Lock button
                IconButton(
                    onClick = {
                        viewModel.setAdminUnlocked(false)
                        onBack()
                    },
                    modifier = Modifier.testTag("admin_lock_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Lock Admin",
                        tint = DangerRed
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Highlighted Banner for P2P Escrow Deal Notices
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = if (uiState.unreadNoticesCount > 0) TonGold else DarkBorder,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .clickable { selectedTab = "deals" },
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .background(
                                        if (uiState.unreadNoticesCount > 0) DangerRed.copy(alpha = 0.2f) else MarketPrimary.copy(alpha = 0.2f),
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = if (uiState.unreadNoticesCount > 0) Icons.Default.NotificationsActive else Icons.Default.Chat,
                                    contentDescription = null,
                                    tint = if (uiState.unreadNoticesCount > 0) DangerRed else MarketPrimary,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "In-App P2P Deals & Notices",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DarkTextPrimary
                                    )
                                    if (uiState.unreadNoticesCount > 0) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Box(
                                            modifier = Modifier
                                                .background(DangerRed, CircleShape)
                                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                        ) {
                                            Text(
                                                text = "${uiState.unreadNoticesCount} NEW",
                                                color = Color.White,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Black
                                            )
                                        }
                                    }
                                }
                                Text(
                                    text = "${uiState.dealChats.size} deals under Escrow supervision",
                                    fontSize = 12.sp,
                                    color = DarkTextSecondary
                                )
                            }
                        }

                        Text(
                            text = "View →",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = TonGold
                        )
                    }
                }
            }

            // Stats Grid
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    AdminStatCard(
                        title = "Total Listings",
                        value = "${uiState.allListingsForAdmin.size}",
                        icon = Icons.Default.List,
                        color = TelegramCyan,
                        modifier = Modifier.weight(1f)
                    )

                    AdminStatCard(
                        title = "Market Volume",
                        value = "$${uiState.totalVolumeUsd.toInt()}",
                        icon = Icons.Default.AttachMoney,
                        color = PriceGreen,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    AdminStatCard(
                        title = "Pending Listings",
                        value = "${uiState.pendingCount}",
                        icon = Icons.Default.PendingActions,
                        color = if (uiState.pendingCount > 0) DangerRed else WhatsAppGreen,
                        modifier = Modifier.weight(1f)
                    )

                    AdminStatCard(
                        title = "Active Deal Chats",
                        value = "${uiState.dealChats.size}",
                        icon = Icons.Default.Chat,
                        color = TonGold,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Horizontal Tab bar
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(DarkSurface)
                        .horizontalScroll(rememberScrollState())
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    AdminTabItem(
                        text = "🚨 P2P Deals (${uiState.dealChats.size})",
                        isSelected = selectedTab == "deals",
                        onClick = { selectedTab = "deals" }
                    )
                    AdminTabItem(
                        text = "All Listings (${uiState.allListingsForAdmin.size})",
                        isSelected = selectedTab == "all",
                        onClick = { selectedTab = "all" }
                    )
                    AdminTabItem(
                        text = "Pending (${uiState.pendingCount})",
                        isSelected = selectedTab == "pending",
                        onClick = { selectedTab = "pending" }
                    )
                    AdminTabItem(
                        text = "Featured",
                        isSelected = selectedTab == "featured",
                        onClick = { selectedTab = "featured" }
                    )
                    AdminTabItem(
                        text = "Sold",
                        isSelected = selectedTab == "sold",
                        onClick = { selectedTab = "sold" }
                    )
                }
            }

            // Tab Content
            if (selectedTab == "deals") {
                if (uiState.dealChats.isEmpty()) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = DarkSurface)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Chat,
                                    contentDescription = null,
                                    tint = DarkTextMuted,
                                    modifier = Modifier.size(36.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "No in-app deal chats yet",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DarkTextPrimary
                                )
                                Text(
                                    text = "When a buyer clicks 'Chat & Deal' on any ad, the deal will appear here instantly with an alert notice for admin approval.",
                                    fontSize = 12.sp,
                                    color = DarkTextMuted,
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }
                } else {
                    items(
                        items = uiState.dealChats,
                        key = { it.id }
                    ) { deal ->
                        AdminDealCard(
                            deal = deal,
                            onOpenChat = { onOpenDealChat(deal.id) },
                            onApproveDeal = { viewModel.adminApproveDeal(deal.id) },
                            onCompleteDeal = { viewModel.adminCompleteDeal(deal.id, deal.listingId) }
                        )
                    }
                }
            } else {
                // Listing Moderation Cards
                items(
                    items = displayedListings,
                    key = { it.id }
                ) { item ->
                    AdminListingRow(
                        listing = item,
                        onClick = { onListingClick(item.id) },
                        onToggleFeatured = { viewModel.adminToggleFeatured(item.id, item.isFeatured) },
                        onToggleSold = { viewModel.adminToggleSold(item.id, item.isSold) },
                        onToggleApproval = { viewModel.adminSetApproval(item.id, !item.isApproved) },
                        onDelete = { viewModel.adminDeleteListing(item.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun AdminDealCard(
    deal: DealChatEntity,
    onOpenChat: () -> Unit,
    onApproveDeal: () -> Unit,
    onCompleteDeal: () -> Unit,
    modifier: Modifier = Modifier
) {
    val timeString = remember(deal.lastMessageTime) {
        val sdf = SimpleDateFormat("MMM dd, hh:mm a", Locale.getDefault())
        sdf.format(Date(deal.lastMessageTime))
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = if (deal.hasAdminNotice) 2.dp else 1.dp,
                color = if (deal.hasAdminNotice) DangerRed else DarkBorder,
                shape = RoundedCornerShape(14.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header with Alert badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (deal.hasAdminNotice) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(DangerRed)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "🚨 ACTION REQUIRED",
                                color = Color.White,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    Text(
                        text = "Order #ORD-${deal.id}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TonGold
                    )
                }

                Text(
                    text = timeString,
                    fontSize = 10.sp,
                    color = DarkTextMuted
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Channel Title & Price
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = deal.listingTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = DarkTextPrimary,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${deal.price} ${deal.currency}",
                    fontWeight = FontWeight.Black,
                    fontSize = 15.sp,
                    color = PriceGreen
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Participants
            Text(
                text = "Buyer: ${deal.buyerName}  •  Seller: ${deal.sellerName}",
                fontSize = 11.sp,
                color = DarkTextSecondary
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Last Message Snippet
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(DarkSurfaceVariant)
                    .padding(8.dp)
            ) {
                Text(
                    text = deal.lastMessage,
                    fontSize = 11.sp,
                    color = DarkTextPrimary,
                    maxLines = 2
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Deal Status & Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onOpenChat,
                    colors = ButtonDefaults.buttonColors(containerColor = MarketPrimary),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Chat,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Enter Chat", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }

                if (!deal.adminApproved) {
                    Button(
                        onClick = onApproveDeal,
                        colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Approve Deal", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                } else if (deal.status != "COMPLETED") {
                    Button(
                        onClick = onCompleteDeal,
                        colors = ButtonDefaults.buttonColors(containerColor = TonGold),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Release Escrow", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
fun AdminTabItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) TonGold else Color.Transparent)
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) Color.Black else DarkTextSecondary
        )
    }
}

@Composable
fun AdminStatCard(
    title: String,
    value: String,
    icon: ImageVector,
    color: Color,
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
                    .size(36.dp)
                    .background(color.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 11.sp,
                    color = DarkTextMuted
                )
                Text(
                    text = value,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    color = DarkTextPrimary
                )
            }
        }
    }
}

@Composable
fun AdminListingRow(
    listing: ListingEntity,
    onClick: () -> Unit,
    onToggleFeatured: () -> Unit,
    onToggleSold: () -> Unit,
    onToggleApproval: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, DarkBorder, RoundedCornerShape(14.dp)),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                PlatformIconBadge(platform = listing.getPlatformEnum(), size = 36.dp)

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = listing.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = DarkTextPrimary,
                        maxLines = 1
                    )
                    Text(
                        text = "${listing.handleOrLink} • ${listing.membersCount} members",
                        fontSize = 11.sp,
                        color = DarkTextMuted
                    )
                }

                Text(
                    text = "$${listing.price.toInt()}",
                    fontWeight = FontWeight.Black,
                    fontSize = 15.sp,
                    color = PriceGreen,
                    maxLines = 1,
                    softWrap = false
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Action Pills Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Approval Toggle
                OutlinedButton(
                    onClick = onToggleApproval,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = if (listing.isApproved) WhatsAppGreen else DangerRed
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = if (listing.isApproved) Icons.Default.Check else Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (listing.isApproved) "Live" else "Hidden",
                        fontSize = 11.sp
                    )
                }

                // Featured Toggle
                OutlinedButton(
                    onClick = onToggleFeatured,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = if (listing.isFeatured) TonGold else DarkTextMuted
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (listing.isFeatured) "Featured" else "Standard",
                        fontSize = 11.sp
                    )
                }

                // Delete Button
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = DangerRed.copy(alpha = 0.8f),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
