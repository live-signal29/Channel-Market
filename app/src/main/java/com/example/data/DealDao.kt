package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DealDao {

    @Query("SELECT * FROM deal_chats ORDER BY lastMessageTime DESC")
    fun getAllDealChats(): Flow<List<DealChatEntity>>

    @Query("SELECT * FROM deal_chats WHERE listingId = :listingId LIMIT 1")
    fun getDealChatByListingId(listingId: Long): Flow<DealChatEntity?>

    @Query("SELECT * FROM deal_chats WHERE id = :id LIMIT 1")
    fun getDealChatById(id: Long): Flow<DealChatEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDealChat(chat: DealChatEntity): Long

    @Update
    suspend fun updateDealChat(chat: DealChatEntity)

    @Query("UPDATE deal_chats SET hasAdminNotice = 0 WHERE id = :id")
    suspend fun markNoticeAsRead(id: Long)

    @Query("UPDATE deal_chats SET adminApproved = :approved, status = :status WHERE id = :id")
    suspend fun setDealApproval(id: Long, approved: Boolean, status: String)

    @Query("SELECT COUNT(*) FROM deal_chats WHERE hasAdminNotice = 1")
    fun getUnreadAdminNoticesCount(): Flow<Int>

    // Messages
    @Query("SELECT * FROM chat_messages WHERE dealChatId = :dealChatId ORDER BY timestamp ASC")
    fun getMessagesForDeal(dealChatId: Long): Flow<List<ChatMessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatMessageEntity): Long

    @Query("UPDATE deal_chats SET lastMessage = :message, lastMessageTime = :time, hasAdminNotice = :hasNotice WHERE id = :id")
    suspend fun updateLastMessage(id: Long, message: String, time: Long, hasNotice: Boolean)
}
