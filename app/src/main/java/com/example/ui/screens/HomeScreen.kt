package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.ItemCategory
import com.example.model.Platform
import com.example.model.PrivacyType
import com.example.model.SortOption
import com.example.model.TonAdsStatus
import com.example.ui.MarketUiState
import com.example.ui.MarketViewModel
import com.example.ui.components.CategoryIconItem
import com.example.ui.components.ListingCard
import com.example.ui.theme.FeaturedPink
import com.example.ui.theme.LightBg
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightSurface
import com.example.ui.theme.LightSurfaceVariant
import com.example.ui.theme.LightTextMuted
import com.example.ui.theme.LightTextPrimary
import com.example.ui.theme.LightTextSecondary
import com.example.ui.theme.MarketBuySellPink
import com.example.ui.theme.MarketPrimary
import com.example.ui.theme.PriceGreen
import com.example.ui.theme.DangerRed
import com.example.ui.theme.TelegramCyan
import com.example.ui.theme.TonGold
import com.example.ui.theme.WhatsAppGreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    uiState: MarketUiState,
    viewModel: MarketViewModel,
    onListingClick: (Long) -> Unit,
    onCreateClick: () -> Unit,
    onOpenAdminSecret: () -> Unit,
    onOpenEscrowGuide: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val filter = uiState.currentFilter
    var sortMenuExpanded by remember { mutableStateOf(false) }
    var logoTapCount by remember { mutableStateOf(0) }
    var lastLogoTapTime by remember { mutableStateOf(0L) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = LightBg,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreateClick,
                containerColor = MarketPrimary,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(16.dp))
                    .testTag("post_ad_fab")
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Sell",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "+ Sell",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 120.dp)
        ) {
            // =========================================================================
            // 1. TOP APP BAR (Logo, Channel Market, "Buy Sell" Pill, Escrow & Admin)
            // =========================================================================
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LightSurface)
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.img_tg_buysell_icon_1789609172981),
                            contentDescription = "Marketplace Logo",
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .border(1.dp, LightBorder, RoundedCornerShape(10.dp))
                                .clickable {
                                    val now = System.currentTimeMillis()
                                    if (now - lastLogoTapTime > 4000L) {
                                        logoTapCount = 1
                                    } else {
                                        logoTapCount += 1
                                    }
                                    lastLogoTapTime = now
                                    if (logoTapCount >= 10) {
                                        logoTapCount = 0
                                        onOpenAdminSecret()
                                    }
                                },
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Channel Market",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Black,
                                    color = LightTextPrimary
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Buy",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Black,
                                        color = PriceGreen
                                    )
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text(
                                        text = "Sell",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Black,
                                        color = DangerRed
                                    )
                                }
                            }
                            Text(
                                text = "Channels • Groups • Bots • Accounts",
                                fontSize = 11.sp,
                                color = LightTextMuted,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    // Escrow Guide & Prominent Admin Panel Access
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        IconButton(
                            onClick = onOpenEscrowGuide,
                            modifier = Modifier.testTag("escrow_guide_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Security,
                                contentDescription = "Escrow Guide",
                                tint = WhatsAppGreen,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        // Secret Admin Panel Button (ONLY shown to owner when already authenticated)
                        if (uiState.isAdminUnlocked) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(TonGold.copy(alpha = 0.2f))
                                    .border(
                                        width = 1.dp,
                                        color = TonGold,
                                        shape = RoundedCornerShape(20.dp)
                                    )
                                    .clickable { onOpenAdminSecret() }
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                                    .testTag("admin_panel_top_btn"),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = "Admin Panel",
                                        tint = TonGold,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "Admin",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = TonGold
                                    )
                                    if (uiState.unreadNoticesCount > 0) {
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Box(
                                            modifier = Modifier
                                                .size(18.dp)
                                                .background(DangerRed, CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "${uiState.unreadNoticesCount}",
                                                color = Color.White,
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Black
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // =========================================================================
            // 2. SEARCH BAR (Compact and fast)
            // =========================================================================
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LightSurface)
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    OutlinedTextField(
                        value = filter.searchQuery,
                        onValueChange = { viewModel.setSearchQuery(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("search_bar"),
                        placeholder = {
                            Text(
                                text = "Search channels, groups, bots, @handle...",
                                fontSize = 13.sp,
                                color = LightTextMuted
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = MarketPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        trailingIcon = {
                            if (filter.searchQuery.isNotEmpty()) {
                                IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Clear",
                                        tint = LightTextMuted
                                    )
                                }
                            }
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = LightTextPrimary,
                            unfocusedTextColor = LightTextPrimary,
                            focusedContainerColor = LightSurfaceVariant,
                            unfocusedContainerColor = LightSurfaceVariant,
                            focusedBorderColor = MarketPrimary,
                            unfocusedBorderColor = LightBorder
                        )
                    )
                }
            }

            // =========================================================================
            // 3. TOP PLATFORMS (Telegram, WhatsApp have highest visual priority)
            // =========================================================================
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, bottom = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "TOP PLATFORMS",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black,
                            color = LightTextPrimary,
                            letterSpacing = 0.5.sp
                        )
                        if (filter.platform != null) {
                            Text(
                                text = "View All",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MarketPrimary,
                                modifier = Modifier.clickable { viewModel.selectPlatform(null) }
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Platform.entries.forEach { platform ->
                            CategoryIconItem(
                                platform = platform,
                                isSelected = filter.platform == platform,
                                onClick = {
                                    if (filter.platform == platform) {
                                        viewModel.selectPlatform(null)
                                    } else {
                                        viewModel.selectPlatform(platform)
                                    }
                                }
                            )
                        }
                    }
                }
            }

            // =========================================================================
            // 6. SUBCATEGORY & FILTER TABS (Channel, Group, Bot, User Account, TON Ads)
            // =========================================================================
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    // Category Chips (All Types, Channel, Group, Bot, Account)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        FilterTagChipLight(
                            text = "All Types",
                            isSelected = filter.category == null,
                            onClick = { viewModel.selectCategory(null) }
                        )

                        ItemCategory.entries.forEach { cat ->
                            FilterTagChipLight(
                                text = cat.displayName,
                                isSelected = filter.category == cat,
                                onClick = {
                                    viewModel.selectCategory(if (filter.category == cat) null else cat)
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Specific filters (TON Ads, Privacy, Verified, Premium)
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // TON Ads filters (especially prominent for Telegram)
                        if (filter.platform == null || filter.platform == Platform.TELEGRAM) {
                            FilterToggleChipLight(
                                text = "TON Ads Active",
                                isSelected = filter.tonAdsStatus == TonAdsStatus.ACTIVE,
                                activeColor = TonGold,
                                onClick = {
                                    viewModel.selectTonAds(
                                        if (filter.tonAdsStatus == TonAdsStatus.ACTIVE) null else TonAdsStatus.ACTIVE
                                    )
                                }
                            )
                            FilterToggleChipLight(
                                text = "TON Ads Off",
                                isSelected = filter.tonAdsStatus == TonAdsStatus.NOT_ACTIVE,
                                activeColor = LightTextMuted,
                                onClick = {
                                    viewModel.selectTonAds(
                                        if (filter.tonAdsStatus == TonAdsStatus.NOT_ACTIVE) null else TonAdsStatus.NOT_ACTIVE
                                    )
                                }
                            )
                        }

                        // Privacy filter
                        FilterToggleChipLight(
                            text = "Public",
                            isSelected = filter.privacy == PrivacyType.PUBLIC,
                            activeColor = TelegramCyan,
                            onClick = {
                                viewModel.selectPrivacy(
                                    if (filter.privacy == PrivacyType.PUBLIC) null else PrivacyType.PUBLIC
                                )
                            }
                        )
                        FilterToggleChipLight(
                            text = "Private",
                            isSelected = filter.privacy == PrivacyType.PRIVATE,
                            activeColor = Color(0xFFA855F7),
                            onClick = {
                                viewModel.selectPrivacy(
                                    if (filter.privacy == PrivacyType.PRIVATE) null else PrivacyType.PRIVATE
                                )
                            }
                        )

                        // Verified & Premium
                        FilterToggleChipLight(
                            text = "Verified",
                            isSelected = filter.verifiedOnly,
                            activeColor = MarketPrimary,
                            onClick = { viewModel.toggleVerifiedOnly() }
                        )

                        FilterToggleChipLight(
                            text = "Premium",
                            isSelected = filter.premiumOnly,
                            activeColor = Color(0xFFA855F7),
                            onClick = { viewModel.togglePremiumOnly() }
                        )

                        // Reset button if any filter is active
                        val isAnyFilterActive = filter.platform != null ||
                                filter.category != null ||
                                filter.privacy != null ||
                                filter.tonAdsStatus != null ||
                                filter.verifiedOnly ||
                                filter.premiumOnly ||
                                filter.searchQuery.isNotBlank()

                        if (isAnyFilterActive) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFFEE2E2))
                                    .clickable { viewModel.resetFilters() }
                                    .padding(horizontal = 8.dp, vertical = 5.dp)
                            ) {
                                Text(
                                    text = "Reset Filters ✕",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFEF4444)
                                )
                            }
                        }
                    }
                }
            }

            // =========================================================================
            // 7. RESULTS HEADER & SORT
            // =========================================================================
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Available Listings",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = LightTextPrimary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .background(LightSurfaceVariant, RoundedCornerShape(10.dp))
                                .padding(horizontal = 7.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "${uiState.listings.size}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MarketPrimary
                            )
                        }
                    }

                    Box {
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(LightSurface)
                                .border(1.dp, LightBorder, RoundedCornerShape(8.dp))
                                .clickable { sortMenuExpanded = true }
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.FilterList,
                                contentDescription = "Sort",
                                tint = MarketPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = filter.sortBy.displayName,
                                fontSize = 12.sp,
                                color = LightTextPrimary,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        DropdownMenu(
                            expanded = sortMenuExpanded,
                            onDismissRequest = { sortMenuExpanded = false },
                            modifier = Modifier.background(LightSurface)
                        ) {
                            SortOption.entries.forEach { option ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = option.displayName,
                                            color = if (filter.sortBy == option) MarketPrimary else LightTextPrimary,
                                            fontSize = 13.sp,
                                            fontWeight = if (filter.sortBy == option) FontWeight.Bold else FontWeight.Normal
                                        )
                                    },
                                    onClick = {
                                        viewModel.setSortBy(option)
                                        sortMenuExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // =========================================================================
            // 8. LOADING & EMPTY STATES
            // =========================================================================
            if (uiState.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MarketPrimary)
                    }
                }
            }

            if (!uiState.isLoading && uiState.listings.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp, vertical = 48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .background(LightSurfaceVariant, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = LightTextMuted,
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "No Listings Found",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = LightTextPrimary
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Try adjusting your filters or post the first listing in this category!",
                            fontSize = 13.sp,
                            color = LightTextMuted,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { viewModel.resetFilters() },
                            colors = ButtonDefaults.buttonColors(containerColor = MarketPrimary),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = "Clear All Filters",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }

            // =========================================================================
            // 9. LISTINGS FEED
            // =========================================================================
            items(
                items = uiState.listings,
                key = { it.id }
            ) { listing ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                    ListingCard(
                        listing = listing,
                        onClick = { onListingClick(listing.id) },
                        onToggleFavorite = { viewModel.toggleFavorite(listing.id, listing.isFavorite) }
                    )
                }
            }
        }
    }
}

@Composable
fun TrustBadgeItem(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(LightSurface, RoundedCornerShape(20.dp))
            .border(1.dp, LightBorder, RoundedCornerShape(20.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MarketPrimary,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = LightTextPrimary
        )
    }
}

@Composable
fun FilterTagChipLight(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) MarketPrimary else LightSurface)
            .border(
                width = if (isSelected) 1.5.dp else 1.dp,
                color = if (isSelected) MarketPrimary else LightBorder,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) Color.White else LightTextPrimary
        )
    }
}

@Composable
fun FilterToggleChipLight(
    text: String,
    isSelected: Boolean,
    activeColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) activeColor.copy(alpha = 0.14f) else LightSurface)
            .border(
                width = if (isSelected) 1.5.dp else 1.dp,
                color = if (isSelected) activeColor else LightBorder,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = activeColor,
                modifier = Modifier.size(13.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.SemiBold,
            color = if (isSelected) activeColor else LightTextSecondary
        )
    }
}
