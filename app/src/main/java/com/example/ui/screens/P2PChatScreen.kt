package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.ChatMessageEntity
import com.example.data.DealChatEntity
import com.example.ui.MarketViewModel
import com.example.ui.theme.DangerRed
import com.example.ui.theme.LightBg
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

    BackHandler(onBack = onBack)

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
        containerColor = LightBg,
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LightSurface)
                    .statusBarsPadding()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .size(40.dp)
                            .testTag("p2p_chat_back_btn")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = LightTextPrimary
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = dealChat?.listingTitle ?: "Chat",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = LightTextPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(1.dp))
                        Text(
                            text = "Owner: ${dealChat?.sellerName ?: "@seller"}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = TelegramCyan,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Role switch indicator
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                when (currentRole) {
                                    "ADMIN" -> TonGold.copy(alpha = 0.15f)
                                    "SELLER" -> TelegramCyan.copy(alpha = 0.15f)
                                    else -> WhatsAppGreen.copy(alpha = 0.15f)
                                }
                            )
                            .border(
                                1.dp,
                                when (currentRole) {
                                    "ADMIN" -> TonGold.copy(alpha = 0.4f)
                                    "SELLER" -> TelegramCyan.copy(alpha = 0.4f)
                                    else -> WhatsAppGreen.copy(alpha = 0.4f)
                                },
                                RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                currentRole = when (currentRole) {
                                    "BUYER" -> "SELLER"
                                    "SELLER" -> if (isAdminMode) "ADMIN" else "BUYER"
                                    else -> "BUYER"
                                }
                            }
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = currentRole,
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

                HorizontalDivider(thickness = 0.8.dp, color = LightBorder)
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LightSurface)
                    .navigationBarsPadding()
                    .padding(8.dp)
            ) {
                // Admin Actions Bar (when Admin is in chat)
                if (isAdminMode || currentRole == "ADMIN") {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = LightSurfaceVariant),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, LightBorder)
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
                                Text("Approve Deal", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
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
                                Icon(Icons.Default.Star, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color.White)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Release Escrow", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
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
                                color = LightTextMuted
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
                            focusedTextColor = LightTextPrimary,
                            unfocusedTextColor = LightTextPrimary,
                            focusedContainerColor = LightSurfaceVariant,
                            unfocusedContainerColor = LightSurfaceVariant,
                            focusedBorderColor = MarketPrimary,
                            unfocusedBorderColor = LightBorder
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
                    colors = CardDefaults.cardColors(containerColor = LightSurface),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, LightBorder)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
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
                                color = LightTextMuted
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = dealChat?.listingTitle ?: "",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = LightTextPrimary
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
                    .background(Color(0xFFFEF9C3))
                    .border(1.dp, Color(0xFFFDE047), RoundedCornerShape(10.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = message.text,
                        fontSize = 11.sp,
                        color = Color(0xFF854D0E),
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = timeString,
                        fontSize = 9.sp,
                        color = Color(0xFFA16207),
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
        }
    } else {
        val alignment = if (isBuyer) Alignment.End else Alignment.Start
        val bubbleColor = when {
            isAdmin -> Color(0xFFF5F3FF) // Soft Lavender Purple
            isBuyer -> Color(0xFFE0F2FE) // Telegram Sky Blue Tint
            else -> Color(0xFFFFFFFF)    // Crisp White Seller Bubble
        }
        val borderColor = when {
            isAdmin -> Color(0xFFDDD6FE)
            isBuyer -> Color(0xFFBAE6FD)
            else -> LightBorder
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
                    .border(1.dp, borderColor, RoundedCornerShape(12.dp))
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
                                isAdmin -> Color(0xFF6D28D9)
                                isBuyer -> Color(0xFF0369A1)
                                else -> WhatsAppGreen
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = timeString,
                            fontSize = 9.sp,
                            color = LightTextMuted
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = message.text,
                        fontSize = 13.sp,
                        color = LightTextPrimary,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}
