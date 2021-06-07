package com.target.targetcasestudy.data.db

import androidx.room.*
import com.target.targetcasestudy.data.models.DealItem
import io.reactivex.Observable

@Dao
interface DealsDao {
    @Query("SELECT * FROM deals")
    fun getAllDeals() : Observable<List<DealItem>>

    @Query("SELECT * FROM deals WHERE id = :id")
     fun getDealItem(id: Int): Observable<DealItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(deals: List<DealItem>)

    @Query("DELETE FROM deals")
     fun deleteAll()
}