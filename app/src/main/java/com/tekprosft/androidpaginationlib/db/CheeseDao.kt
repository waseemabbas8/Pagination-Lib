package com.tekprosft.androidpaginationlib.db

import androidx.paging.DataSource
import androidx.room.*
import com.tekprosft.androidpaginationlib.model.Cheese

@Dao
interface CheeseDao {

    @Query("SELECT * FROM Cheese ORDER BY name COLLATE NOCASE ASC")
    fun getAllCheese(): DataSource.Factory<Int,Cheese>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cheese: Cheese)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cheese: List<Cheese>)

    @Delete
    fun delete(cheese: Cheese)
}