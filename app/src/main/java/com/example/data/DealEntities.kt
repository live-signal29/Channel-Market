package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deal_chats")
data class DealChatEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val listingId: Long,
    val listingTitle: String,
    val platform: String,
    val price: Double,
    val currency: String = "USDT",
    val buyerName: String = "Buyer #1024",
    val sellerName: String = "Channel Owner",
    val sellerTelegram: String = "",
    val sellerWhatsApp: String = "",
    val status: String = "PENDING_APPROVAL", // PENDING_APPROVAL, ADMIN_APPROVED, PAYMENT_SENT, COMPLETED, CANCELLED
    val adminApproved: Boolean = false,
    val lastMessage: String = "New deal initiated. Awaiting Admin review.",
    val lastMessageTime: Long = System.currentTimeMillis(),
    val hasAdminNotice: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "chat_messages")
data class ChatMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dealChatId: Long,
    val senderRole: String, // "BUYER", "SELLER", "ADMIN", "SYSTEM"
    val senderName: String,
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)
