package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VerifiedUser
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.ChatMessageEntity
import com.example.data.DealChatEntity
import com.example.ui.MarketViewModel
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
fun P2PChatScreen(
    dealChatId: Long,
    viewModel: MarketViewModel,
    isAdminMode: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dealChat by viewModel.getDealChat(dealChatId).collectAsStateWithLifecycle(initialValue = null)
    val messages by viewModel.getMessagesForDeal(dealChatId).collectAsStateWithLifecycle(initialValue = emptyList())
    var inputText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    // Default sender role: Admin if admin mode, else Buyer
    var currentRole by remember { mutableStateOf(if (isAdminMode) "ADMIN" else "BUYER") }

    LaunchedEffect(dealChatId) {
        if (isAdminMode) {
            viewModel.markNoticeRead(dealChatId)
        }
    }

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = DarkBg,
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkSurface)
                    .border(1.dp, DarkBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = onBack, modifier = Modifier.testTag("p2p_chat_back_btn")) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = dealChat?.listingTitle ?: "P2P Escrow Deal",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(MarketPrimary.copy(alpha = 0.2f))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "${dealChat?.price ?: 0.0} ${dealChat?.currency ?: "USDT"}",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PriceGreen
                                    )
                                }
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Owner: ${dealChat?.sellerName ?: "@seller"}",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TelegramCyan
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "• Private Chat",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = DarkTextMuted
                                )
                            }
                        }
                    }

                    // Role switch indicator
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                when (currentRole) {
                                    "ADMIN" -> TonGold.copy(alpha = 0.2f)
                                    "SELLER" -> TelegramCyan.copy(alpha = 0.2f)
                                    else -> WhatsAppGreen.copy(alpha = 0.2f)
                                }
                            )
                            .clickable {
                                currentRole = when (currentRole) {
                                    "BUYER" -> "SELLER"
                                    "SELLER" -> if (isAdminMode) "ADMIN" else "BUYER"
                                    else -> "BUYER"
                                }
                            }
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Chat as: $currentRole",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = when (currentRole) {
                                "ADMIN" -> TonGold
                                "SELLER" -> TelegramCyan
                                else -> WhatsAppGreen
                            }
                        )
                    }
                }

                // Private Chat Notice Banner
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF161E2E))
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = null,
                            tint = TelegramCyan,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Discuss channel transfer, stats & price directly with the owner before buying.",
                            fontSize = 11.sp,
                            color = Color(0xFFBAE6FD),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkSurface)
                    .padding(8.dp)
            ) {
                // Admin Actions Bar (when Admin is in chat)
                if (isAdminMode || currentRole == "ADMIN") {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF191F2D)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    viewModel.adminApproveDeal(dealChatId)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Approve Deal", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }

                            Button(
                                onClick = {
                                    dealChat?.let {
                                        viewModel.adminCompleteDeal(dealChatId, it.listingId)
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = TonGold),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(Icons.Default.Star, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color.Black)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Release Escrow", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                            }
                        }
                    }
                }

                // Chat Input Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("p2p_chat_input"),
                        placeholder = {
                            Text(
                                text = "Message as $currentRole...",
                                fontSize = 13.sp,
                                color = DarkTextMuted
                            )
                        },
                        singleLine = false,
                        maxLines = 3,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                        keyboardActions = KeyboardActions(onSend = {
                            if (inputText.isNotBlank()) {
                                val senderName = when (currentRole) {
                                    "ADMIN" -> "👑 Marketplace Admin"
                                    "SELLER" -> "Channel Owner"
                                    else -> "Buyer"
                                }
                                viewModel.sendChatMessage(dealChatId, currentRole, senderName, inputText)
                                inputText = ""
                            }
                        }),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = DarkTextPrimary,
                            unfocusedTextColor = DarkTextPrimary,
                            focusedContainerColor = DarkSurfaceVariant,
                            unfocusedContainerColor = DarkSurfaceVariant,
                            focusedBorderColor = MarketPrimary,
                            unfocusedBorderColor = DarkBorder
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = {
                            if (inputText.isNotBlank()) {
                                val senderName = when (currentRole) {
                                    "ADMIN" -> "👑 Marketplace Admin"
                                    "SELLER" -> "Channel Owner"
                                    else -> "Buyer"
                                }
                                viewModel.sendChatMessage(dealChatId, currentRole, senderName, inputText)
                                inputText = ""
                            }
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MarketPrimary)
                            .testTag("p2p_chat_send_btn")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 14.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Deal Info Card at Top of Chat
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "ORDER DETAILS (P2P ESCROW)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                color = TonGold,
                                letterSpacing = 0.5.sp
                            )
                            Text(
                                text = "Order #ORD-${dealChatId}",
                                fontSize = 11.sp,
                                color = DarkTextMuted
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = dealChat?.listingTitle ?: "",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkTextPrimary
                        )

                        Text(
                            text = "Agreed Price: ${dealChat?.price ?: 0.0} ${dealChat?.currency ?: "USDT"}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = PriceGreen
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = if (dealChat?.adminApproved == true) {
                                "✅ Admin has approved this deal. Payment escrow is active."
                            } else {
                                "⏳ Notice sent to Admin Panel. Admin review in progress."
                            },
                            fontSize = 11.sp,
                            color = if (dealChat?.adminApproved == true) PriceGreen else TonGold
                        )
                    }
                }
            }

            // Message Items
            items(messages) { message ->
                ChatMessageItem(message = message)
            }
        }
    }
}

@Composable
fun ChatMessageItem(message: ChatMessageEntity) {
    val isSystem = message.senderRole == "SYSTEM"
    val isAdmin = message.senderRole == "ADMIN"
    val isBuyer = message.senderRole == "BUYER"

    val timeString = remember(message.timestamp) {
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        sdf.format(Date(message.timestamp))
    }

    if (isSystem) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF1D1B13))
                    .border(1.dp, TonGold.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = message.text,
                        fontSize = 11.sp,
                        color = Color(0xFFFDE68A),
                        lineHeight = 16.sp
                    )
                    Text(
                        text = timeString,
                        fontSize = 9.sp,
                        color = DarkTextMuted,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
        }
    } else {
        val alignment = if (isBuyer) Alignment.End else Alignment.Start
        val bubbleColor = when {
            isAdmin -> Color(0xFF281C3D) // Admin Royal Purple
            isBuyer -> Color(0xFF0F3047) // Buyer Telegram Dark Cyan
            else -> Color(0xFF162A21) // Seller Green Tint
        }
        val borderColor = when {
            isAdmin -> TonGold
            isBuyer -> MarketPrimary
            else -> WhatsAppGreen
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = alignment
        ) {
            Box(
                modifier = Modifier
                    .widthIn(max = 300.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(bubbleColor)
                    .border(1.dp, borderColor.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                    .padding(10.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = message.senderName,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = when {
                                isAdmin -> TonGold
                                isBuyer -> TelegramCyan
                                else -> WhatsAppGreen
                            }
                        )
                        Text(
                            text = timeString,
                            fontSize = 9.sp,
                            color = DarkTextMuted
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = message.text,
                        fontSize = 13.sp,
                        color = DarkTextPrimary,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}
