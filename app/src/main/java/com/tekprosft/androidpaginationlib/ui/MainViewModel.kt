package com.tekprosft.androidpaginationlib.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.tekprosft.androidpaginationlib.data.AppRepository
import com.tekprosft.androidpaginationlib.model.Cheese

class MainViewModel(app: Application) : AndroidViewModel(app) {

    val mCheeseList : LiveData<PagedList<Cheese>>
    private val repository : AppRepository

    init {
        repository = AppRepository.getInstance(app.applicationContext)
        val config = Config(20,50,true,maxSize = 300)
        mCheeseList = repository.getAllCheese().toLiveData(config)
    }

    fun addSampleData(cheeseList: List<Cheese>) {
        repository.addSampleData(cheeseList)
    }

}