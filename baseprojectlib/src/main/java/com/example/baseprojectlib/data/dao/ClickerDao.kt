package com.example.baseprojectlib.data.dao

import androidx.room.*
import com.example.baseprojectlib.data.entity.ClickerEntity

@Dao
abstract class ClickerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg clickerEntity: ClickerEntity)

    @Delete
    abstract fun delete(vararg clickerEntity: ClickerEntity)

    @Delete
    abstract fun delete(models: List<ClickerEntity>)

    @Query("DELETE FROM clicker_entity WHERE idScript = :idScript")
    abstract fun deleteByScriptId(idScript : Int)

}