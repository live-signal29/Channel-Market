package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ListingDao {
    @Query("SELECT * FROM listings ORDER BY isFeatured DESC, createdAt DESC")
    fun getAllListings(): Flow<List<ListingEntity>>

    @Query("SELECT * FROM listings WHERE isApproved = 1 ORDER BY isFeatured DESC, createdAt DESC")
    fun getApprovedListings(): Flow<List<ListingEntity>>

    @Query("SELECT * FROM listings WHERE id = :id LIMIT 1")
    fun getListingById(id: Long): Flow<ListingEntity?>

    @Query("SELECT * FROM listings WHERE isFavorite = 1 ORDER BY createdAt DESC")
    fun getFavoriteListings(): Flow<List<ListingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListing(listing: ListingEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listings: List<ListingEntity>)

    @Update
    suspend fun updateListing(listing: ListingEntity)

    @Query("DELETE FROM listings WHERE id = :id")
    suspend fun deleteListingById(id: Long)

    @Query("UPDATE listings SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun toggleFavorite(id: Long, isFavorite: Boolean)

    @Query("UPDATE listings SET isSold = :isSold WHERE id = :id")
    suspend fun setSoldStatus(id: Long, isSold: Boolean)

    @Query("UPDATE listings SET isApproved = :isApproved WHERE id = :id")
    suspend fun setApprovalStatus(id: Long, isApproved: Boolean)

    @Query("UPDATE listings SET isFeatured = :isFeatured WHERE id = :id")
    suspend fun setFeaturedStatus(id: Long, isFeatured: Boolean)

    @Query("SELECT COUNT(*) FROM listings")
    suspend fun getCount(): Int
}
