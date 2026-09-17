package com.example.ui

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.SecretAdminDialog
import com.example.ui.screens.AdminPanelScreen
import com.example.ui.screens.ChatListScreen
import com.example.ui.screens.CreateListingScreen
import com.example.ui.screens.EscrowGuideScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ListingDetailScreen
import com.example.ui.screens.P2PChatScreen
import com.example.ui.screens.SavedScreen
import com.example.ui.theme.DangerRed
import com.example.ui.theme.DarkBg
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightSurface
import com.example.ui.theme.LightTextMuted
import com.example.ui.theme.LightTextPrimary
import com.example.ui.theme.MarketPrimary
import com.example.ui.theme.TonGold

sealed class Screen {
    data object Home : Screen()
    data class Detail(val listingId: Long) : Screen()
    data object ChatList : Screen()
    data class P2PChat(val dealChatId: Long) : Screen()
    data object Create : Screen()
    data object Saved : Screen()
    data object Escrow : Screen()
    data object Admin : Screen()
}

@Composable
fun MainScreen(
    viewModel: MarketViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    var showAdminDialog by remember { mutableStateOf(false) }

    if (showAdminDialog) {
        SecretAdminDialog(
            onDismiss = { showAdminDialog = false },
            onSuccess = {
                viewModel.setAdminUnlocked(true)
                currentScreen = Screen.Admin
            }
        )
    }

    val showBottomBar = when (currentScreen) {
        is Screen.Home, is Screen.ChatList, is Screen.Saved, is Screen.Escrow, is Screen.Admin -> true
        else -> false
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = DarkBg,
        bottomBar = {
            if (showBottomBar) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LightSurface)
                        .navigationBarsPadding()
                ) {
                    // Alert banner ONLY shown to authenticated owner
                    if (uiState.isAdminUnlocked && uiState.unreadNoticesCount > 0) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF261D07))
                                .clickable {
                                    currentScreen = Screen.Admin
                                }
                                .padding(horizontal = 16.dp, vertical = 6.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .background(DangerRed, CircleShape)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "🚨 ${uiState.unreadNoticesCount} New Channel Inquiries (Owner Oversight)",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = TonGold
                                    )
                                }

                                Text(
                                    text = "Open Admin →",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black,
                                    color = TonGold
                                )
                            }
                        }
                    }

                    NavigationBar(
                        containerColor = LightSurface,
                        contentColor = LightTextPrimary,
                        tonalElevation = 3.dp,
                        modifier = Modifier
                            .height(64.dp)
                            .border(1.dp, LightBorder)
                    ) {
                        // 1. Market Tab
                        NavigationBarItem(
                            selected = currentScreen is Screen.Home,
                            onClick = { currentScreen = Screen.Home },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Storefront,
                                    contentDescription = "Market"
                                )
                            },
                            label = { Text("Market", fontSize = 10.sp, fontWeight = FontWeight.SemiBold) },
                            colors = navigationBarColors(),
                            modifier = Modifier.testTag("nav_market")
                        )

                        // 2. Chats Tab (Direct messages with channel owners)
                        NavigationBarItem(
                            selected = currentScreen is Screen.ChatList || currentScreen is Screen.P2PChat,
                            onClick = { currentScreen = Screen.ChatList },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (uiState.dealChats.isNotEmpty()) {
                                            Badge(
                                                containerColor = MarketPrimary
                                            ) {
                                                Text(
                                                    text = "${uiState.dealChats.size}",
                                                    color = Color.White,
                                                    fontSize = 9.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Chat,
                                        contentDescription = "Chats"
                                    )
                                }
                            },
                            label = { Text("Chats", fontSize = 10.sp, fontWeight = FontWeight.SemiBold) },
                            colors = navigationBarColors(),
                            modifier = Modifier.testTag("nav_chats")
                        )

                        // 3. Post Ad Tab
                        NavigationBarItem(
                            selected = currentScreen is Screen.Create,
                            onClick = { currentScreen = Screen.Create },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.AddCircle,
                                    contentDescription = "Post Ad",
                                    tint = MarketPrimary
                                )
                            },
                            label = { Text("Post Ad", fontSize = 10.sp, fontWeight = FontWeight.SemiBold) },
                            colors = navigationBarColors(),
                            modifier = Modifier.testTag("nav_create")
                        )

                        // 4. Saved Tab
                        NavigationBarItem(
                            selected = currentScreen is Screen.Saved,
                            onClick = { currentScreen = Screen.Saved },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (uiState.favoriteListings.isNotEmpty()) {
                                            Badge(containerColor = MarketPrimary) {
                                                Text(
                                                    "${uiState.favoriteListings.size}",
                                                    color = Color.White,
                                                    fontSize = 9.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = "Saved"
                                    )
                                }
                            },
                            label = { Text("Saved", fontSize = 10.sp, fontWeight = FontWeight.SemiBold) },
                            colors = navigationBarColors(),
                            modifier = Modifier.testTag("nav_saved")
                        )

                        // 5. Admin Tab (ONLY visible if owner is already unlocked)
                        if (uiState.isAdminUnlocked) {
                            NavigationBarItem(
                                selected = currentScreen is Screen.Admin,
                                onClick = { currentScreen = Screen.Admin },
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if (uiState.unreadNoticesCount > 0) {
                                                Badge(containerColor = DangerRed) {
                                                    Text(
                                                        "${uiState.unreadNoticesCount}",
                                                        color = Color.White,
                                                        fontSize = 9.sp,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Lock,
                                            contentDescription = "Admin",
                                            tint = TonGold
                                        )
                                    }
                                },
                                label = { Text("Admin", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = TonGold) },
                                colors = navigationBarColors(),
                                modifier = Modifier.testTag("nav_admin")
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val screen = currentScreen) {
                is Screen.Home -> {
                    HomeScreen(
                        uiState = uiState,
                        viewModel = viewModel,
                        onListingClick = { id -> currentScreen = Screen.Detail(id) },
                        onCreateClick = { currentScreen = Screen.Create },
                        onOpenAdminSecret = {
                            if (uiState.isAdminUnlocked) {
                                currentScreen = Screen.Admin
                            } else {
                                showAdminDialog = true
                            }
                        },
                        onOpenEscrowGuide = { currentScreen = Screen.Escrow }
                    )
                }

                is Screen.Detail -> {
                    val listing = uiState.allListingsForAdmin.firstOrNull { it.id == screen.listingId }
                        ?: uiState.listings.firstOrNull { it.id == screen.listingId }

                    if (listing != null) {
                        ListingDetailScreen(
                            listing = listing,
                            viewModel = viewModel,
                            isAdminUnlocked = uiState.isAdminUnlocked,
                            onBack = { currentScreen = Screen.Home },
                            onOpenDealChat = { chatId -> currentScreen = Screen.P2PChat(chatId) }
                        )
                    } else {
                        currentScreen = Screen.Home
                    }
                }

                is Screen.ChatList -> {
                    ChatListScreen(
                        uiState = uiState,
                        onOpenChat = { chatId -> currentScreen = Screen.P2PChat(chatId) },
                        onBrowseMarket = { currentScreen = Screen.Home }
                    )
                }

                is Screen.P2PChat -> {
                    P2PChatScreen(
                        dealChatId = screen.dealChatId,
                        viewModel = viewModel,
                        isAdminMode = uiState.isAdminUnlocked,
                        onBack = {
                            currentScreen = Screen.ChatList
                        }
                    )
                }

                is Screen.Create -> {
                    CreateListingScreen(
                        viewModel = viewModel,
                        onBack = { currentScreen = Screen.Home },
                        onSuccess = { newId ->
                            currentScreen = Screen.Detail(newId)
                        }
                    )
                }

                is Screen.Saved -> {
                    SavedScreen(
                        favoriteListings = uiState.favoriteListings,
                        viewModel = viewModel,
                        onListingClick = { id -> currentScreen = Screen.Detail(id) }
                    )
                }

                is Screen.Escrow -> {
                    EscrowGuideScreen(
                        onBack = { currentScreen = Screen.Home },
                        onAdminSecretTrigger = { showAdminDialog = true }
                    )
                }

                is Screen.Admin -> {
                    AdminPanelScreen(
                        uiState = uiState,
                        viewModel = viewModel,
                        onBack = { currentScreen = Screen.Home },
                        onListingClick = { id -> currentScreen = Screen.Detail(id) },
                        onOpenDealChat = { chatId -> currentScreen = Screen.P2PChat(chatId) }
                    )
                }
            }
        }
    }
}

@Composable
fun navigationBarColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = MarketPrimary,
    selectedTextColor = MarketPrimary,
    unselectedIconColor = LightTextMuted,
    unselectedTextColor = LightTextMuted,
    indicatorColor = MarketPrimary.copy(alpha = 0.12f)
)
