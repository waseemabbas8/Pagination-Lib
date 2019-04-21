package com.tekprosft.androidpaginationlib.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.tekprosft.androidpaginationlib.db.AppDatabase
import com.tekprosft.androidpaginationlib.model.Cheese
import java.util.concurrent.Executors

class AppRepository {

    private val mDb : AppDatabase
    private val executor  = Executors.newSingleThreadExecutor()

    private constructor(context : Context){
        //mNotes = SampleData().getNotes()
        mDb = AppDatabase.getDatabase(context)
        //mNotes = getAllNotes()
    }

    fun getAllCheese() : DataSource.Factory<Int, Cheese> {
        return mDb.cheeseDao().getAllCheese()
    }

    fun addSampleData(cheeseList: List<Cheese>) {
        executor.execute {
            mDb.cheeseDao().insertAll(cheeseList)
        }
    }

    companion object {
        private lateinit var sInstance : AppRepository

        @JvmStatic
        fun getInstance(context: Context) : AppRepository{
            if (!::sInstance.isInitialized){
                sInstance = AppRepository(context)
            }
            return sInstance
        }

    }

}