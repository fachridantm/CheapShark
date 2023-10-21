package com.technical.test.fachridan.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.technical.test.fachridan.core.data.source.local.entity.DealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DealDao {
    @Query("SELECT * FROM Deals where isFavorite = 1")
    fun getFavoriteGames(): Flow<List<DealEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGame(deal: DealEntity)

    @Delete
    suspend fun deleteGame(deal: DealEntity)

    @Update
    suspend fun updateGame(deal: DealEntity)
}