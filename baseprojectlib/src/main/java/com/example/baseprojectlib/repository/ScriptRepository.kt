package com.example.baseprojectlib.repository

import com.example.baseprojectlib.data.dao.ClickerDao
import com.example.baseprojectlib.data.entity.ClickerEntity
import com.example.baseprojectlib.utils.Logger
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class ScriptRepository @Inject constructor(
    private val clickerDao: ClickerDao
) {
    fun insertNewScript(listClick: List<ClickerEntity>) {
        for (item in listClick) {
            clickerDao.insert(item)
        }
    }

    companion object {
        const val TAG = "ScriptRepository"
    }
}