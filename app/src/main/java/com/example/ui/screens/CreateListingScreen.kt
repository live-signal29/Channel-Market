package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ItemCategory
import com.example.model.Platform
import com.example.model.PrivacyType
import com.example.model.TonAdsStatus
import com.example.ui.MarketViewModel
import com.example.ui.components.PlatformIconBadge
import com.example.ui.theme.DarkBg
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.DarkTextMuted
import com.example.ui.theme.DarkTextPrimary
import com.example.ui.theme.DarkTextSecondary
import com.example.ui.theme.TelegramCyan
import com.example.ui.theme.TonGold
import com.example.ui.theme.WhatsAppGreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateListingScreen(
    viewModel: MarketViewModel,
    onBack: () -> Unit,
    onSuccess: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    // Form fields
    var selectedPlatform by remember { mutableStateOf(Platform.TELEGRAM) }
    var selectedCategory by remember { mutableStateOf(ItemCategory.CHANNEL) }
    var selectedPrivacy by remember { mutableStateOf(PrivacyType.PUBLIC) }
    var selectedTonAds by remember { mutableStateOf(TonAdsStatus.ACTIVE) }
    var isVerified by remember { mutableStateOf(false) }
    var isPremium by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf("") }
    var handleOrLink by remember { mutableStateOf("") }
    var membersCountText by remember { mutableStateOf("") }
    var priceText by remember { mutableStateOf("") }
    var currency by remember { mutableStateOf("USDT") }
    var monthlyIncome by remember { mutableStateOf("") }
    var niche by remember { mutableStateOf("Crypto & Trading") }
    var description by remember { mutableStateOf("") }
    var sellerTelegram by remember { mutableStateOf("") }
    var sellerWhatsApp by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf<String?>(null) }

    val handleFormSubmit = {
        val members = membersCountText.toIntOrNull() ?: 0
        val price = priceText.toDoubleOrNull() ?: 0.0

        if (title.isBlank()) {
            errorMessage = "Please enter a listing title"
        } else if (handleOrLink.isBlank()) {
            errorMessage = "Please enter @handle or invite link"
        } else if (price <= 0) {
            errorMessage = "Please enter a valid price greater than $0"
        } else if (sellerTelegram.isBlank() && sellerWhatsApp.isBlank()) {
            errorMessage = "Please enter either your Telegram or WhatsApp contact"
        } else {
            errorMessage = null
            viewModel.createListing(
                title = title,
                platform = selectedPlatform,
                category = selectedCategory,
                privacy = selectedPrivacy,
                tonAdsStatus = if (selectedPlatform == Platform.TELEGRAM && selectedCategory != ItemCategory.USER_ACCOUNT) selectedTonAds else TonAdsStatus.NONE,
                isVerified = isVerified,
                isPremium = isPremium,
                handleOrLink = handleOrLink,
                membersCount = members,
                price = price,
                currency = currency,
                description = description.ifBlank { "Asset available for verified ownership acquisition via official Escrow. Genuine audience and clean history." },
                niche = niche,
                monthlyIncome = monthlyIncome.ifBlank { "$0" },
                sellerTelegram = sellerTelegram.ifBlank { "@Seller" },
                sellerWhatsApp = sellerWhatsApp.ifBlank { "+1000000000" },
                onComplete = onSuccess
            )
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = DarkBg,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack, modifier = Modifier.testTag("create_back_btn")) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = DarkTextPrimary
                    )
                }
                Text(
                    text = "Post New Channel / Group Ad",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkTextPrimary
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // STEP 1: SELECT PLATFORM
            Text(
                text = "1. SELECT PLATFORM",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DarkTextMuted,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Platform.entries.forEach { p ->
                    val isSelected = selectedPlatform == p
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isSelected) DarkSurfaceVariant else DarkSurface)
                            .border(
                                1.dp,
                                if (isSelected) TelegramCyan else DarkBorder,
                                RoundedCornerShape(10.dp)
                            )
                            .clickable { selectedPlatform = p }
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PlatformIconBadge(platform = p, size = 24.dp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = p.displayName,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) TelegramCyan else DarkTextPrimary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // STEP 2: CATEGORY & TYPES (Channel, Group, Bot, User Account)
            Text(
                text = "2. ASSET TYPE",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DarkTextMuted,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                val availableCategories = when (selectedPlatform) {
                    Platform.TELEGRAM -> listOf(ItemCategory.CHANNEL, ItemCategory.GROUP, ItemCategory.BOT, ItemCategory.USER_ACCOUNT)
                    Platform.WHATSAPP -> listOf(ItemCategory.CHANNEL, ItemCategory.GROUP, ItemCategory.USER_ACCOUNT)
                    Platform.TIKTOK -> listOf(ItemCategory.USER_ACCOUNT)
                    Platform.INSTAGRAM -> listOf(ItemCategory.USER_ACCOUNT, ItemCategory.PAGE)
                    Platform.FACEBOOK -> listOf(ItemCategory.PAGE, ItemCategory.GROUP)
                    Platform.YOUTUBE -> listOf(ItemCategory.CHANNEL)
                    Platform.X_TWITTER -> listOf(ItemCategory.USER_ACCOUNT)
                    Platform.DISCORD -> listOf(ItemCategory.SERVER, ItemCategory.BOT)
                    Platform.OTHER -> listOf(ItemCategory.CHANNEL, ItemCategory.GROUP, ItemCategory.USER_ACCOUNT, ItemCategory.PAGE, ItemCategory.SERVER, ItemCategory.BOT)
                }

                availableCategories.forEach { cat ->
                    val isSelected = selectedCategory == cat
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSelected) TelegramCyan else DarkSurface)
                            .border(
                                1.dp,
                                if (isSelected) TelegramCyan else DarkBorder,
                                RoundedCornerShape(8.dp)
                            )
                            .clickable { selectedCategory = cat }
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = cat.displayName,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.Black else DarkTextSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // STEP 3: PLATFORM SPECIFIC PROPERTIES (Private/Public, TON Ads Active/Not Active, Verified/Premium)
            Text(
                text = "3. SPECIFICATIONS & FEATURES",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DarkTextMuted,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DarkBorder, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    // If Telegram/WhatsApp and NOT user account -> Privacy & TON Ads
                    if (selectedCategory != ItemCategory.USER_ACCOUNT) {
                        // Privacy (Public or Private)
                        Text(
                            text = "Channel / Group Privacy:",
                            fontSize = 12.sp,
                            color = DarkTextMuted
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            PrivacyType.entries.forEach { pt ->
                                val isSelected = selectedPrivacy == pt
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (isSelected) TelegramCyan.copy(alpha = 0.2f) else DarkSurfaceVariant)
                                        .border(
                                            1.dp,
                                            if (isSelected) TelegramCyan else DarkBorder,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .clickable { selectedPrivacy = pt }
                                        .padding(vertical = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = pt.displayName,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) TelegramCyan else DarkTextSecondary,
                                        fontSize = 13.sp
                                    )
                                }
                            }
                        }

                        // Telegram TON Ads (Active or Not Active)
                        if (selectedPlatform == Platform.TELEGRAM) {
                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = "Telegram TON Ads Monetization:",
                                fontSize = 12.sp,
                                color = DarkTextMuted
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                val tonOptions = listOf(TonAdsStatus.ACTIVE, TonAdsStatus.NOT_ACTIVE)
                                tonOptions.forEach { st ->
                                    val isSelected = selectedTonAds == st
                                    val color = if (st == TonAdsStatus.ACTIVE) TonGold else DarkTextSecondary
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(if (isSelected) color.copy(alpha = 0.2f) else DarkSurfaceVariant)
                                            .border(
                                                1.dp,
                                                if (isSelected) color else DarkBorder,
                                                RoundedCornerShape(8.dp)
                                            )
                                            .clickable { selectedTonAds = st }
                                            .padding(vertical = 10.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = st.displayName,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            color = if (isSelected) color else DarkTextSecondary,
                                            fontSize = 13.sp
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // If User Account or extra toggles -> Verified & Premium
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Account Verification & Status:",
                        fontSize = 12.sp,
                        color = DarkTextMuted
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        // Verified toggle
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isVerified) TelegramCyan.copy(alpha = 0.2f) else DarkSurfaceVariant)
                                .border(
                                    1.dp,
                                    if (isVerified) TelegramCyan else DarkBorder,
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable { isVerified = !isVerified }
                                .padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (isVerified) "✓ Verified Badge" else "+ Non-Verified",
                                fontWeight = if (isVerified) FontWeight.Bold else FontWeight.Normal,
                                color = if (isVerified) TelegramCyan else DarkTextSecondary,
                                fontSize = 13.sp
                            )
                        }

                        // Premium toggle
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isPremium) TonGold.copy(alpha = 0.2f) else DarkSurfaceVariant)
                                .border(
                                    1.dp,
                                    if (isPremium) TonGold else DarkBorder,
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable { isPremium = !isPremium }
                                .padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (isPremium) "★ Premium Active" else "+ Non-Premium",
                                fontWeight = if (isPremium) FontWeight.Bold else FontWeight.Normal,
                                color = if (isPremium) TonGold else DarkTextSecondary,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // STEP 4: TITLE, HANDLE, MEMBERS, PRICE
            Text(
                text = "4. LISTING DETAILS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DarkTextMuted,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Channel / Group Title") },
                placeholder = { Text("e.g. Crypto Signals VIP & News") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("create_title_input"),
                colors = defaultTextFieldColors(),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Username Handle / Link
            OutlinedTextField(
                value = handleOrLink,
                onValueChange = { handleOrLink = it },
                label = { Text("Username Handle or Invite Link") },
                placeholder = { Text("e.g. @channel_handle or t.me/...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("create_handle_input"),
                colors = defaultTextFieldColors(),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Members and Price Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = membersCountText,
                    onValueChange = { membersCountText = it },
                    label = { Text("Members / Subs") },
                    placeholder = { Text("e.g. 45000") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("create_members_input"),
                    colors = defaultTextFieldColors(),
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    value = priceText,
                    onValueChange = { priceText = it },
                    label = { Text("Price ($currency)") },
                    placeholder = { Text("e.g. 150") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("create_price_input"),
                    colors = defaultTextFieldColors(),
                    shape = RoundedCornerShape(10.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Currency & Monthly Income
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Currency Selector
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(DarkSurface)
                        .border(1.dp, DarkBorder, RoundedCornerShape(10.dp))
                        .padding(6.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    listOf("USDT", "USD", "PKR").forEach { cur ->
                        val isCur = currency == cur
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isCur) TelegramCyan else Color.Transparent)
                                .clickable { currency = cur }
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = cur,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = if (isCur) Color.Black else DarkTextSecondary
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = monthlyIncome,
                    onValueChange = { monthlyIncome = it },
                    label = { Text("Monthly Earnings") },
                    placeholder = { Text("e.g. $150/mo") },
                    modifier = Modifier.weight(1f),
                    colors = defaultTextFieldColors(),
                    shape = RoundedCornerShape(10.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Niche
            OutlinedTextField(
                value = niche,
                onValueChange = { niche = it },
                label = { Text("Niche / Category") },
                placeholder = { Text("e.g. Crypto, Tech, Gaming, Memes") },
                modifier = Modifier.fillMaxWidth(),
                colors = defaultTextFieldColors(),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Description
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description & Transfer Terms") },
                placeholder = { Text("Describe engagement rate, history, audience country, transfer details...") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                colors = defaultTextFieldColors(),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // STEP 5: CONTACT INFORMATION
            Text(
                text = "5. YOUR CONTACT DETAILS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DarkTextMuted,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = sellerTelegram,
                onValueChange = { sellerTelegram = it },
                label = { Text("Your Telegram Username") },
                placeholder = { Text("e.g. @your_username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("create_tg_contact_input"),
                colors = defaultTextFieldColors(),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = sellerWhatsApp,
                onValueChange = { sellerWhatsApp = it },
                label = { Text("Your WhatsApp Number (with country code)") },
                placeholder = { Text("e.g. +923001234567 or +1...") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("create_wa_contact_input"),
                colors = defaultTextFieldColors(),
                shape = RoundedCornerShape(10.dp)
            )

            if (errorMessage != null) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = errorMessage!!,
                    color = Color(0xFFEF4444),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Escrow info box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0x1A25D366), RoundedCornerShape(10.dp))
                    .border(1.dp, Color(0x3325D366), RoundedCornerShape(10.dp))
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = WhatsAppGreen,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "All deals are protected via ChannelMarket Admin Escrow. Buyers will connect with you safely.",
                        fontSize = 12.sp,
                        color = DarkTextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Submit button
            Button(
                onClick = handleFormSubmit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("submit_ad_btn"),
                colors = ButtonDefaults.buttonColors(containerColor = TelegramCyan),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Publish Listing",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun defaultTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = DarkTextPrimary,
    unfocusedTextColor = DarkTextPrimary,
    focusedContainerColor = DarkSurface,
    unfocusedContainerColor = DarkSurface,
    focusedBorderColor = TelegramCyan,
    unfocusedBorderColor = DarkBorder
)
