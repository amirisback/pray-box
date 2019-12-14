package com.frogobox.praybox.source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.frogobox.praybox.model.Fashion
import com.frogobox.praybox.util.helper.ConstHelper.RoomDatabase.TABLE_NAME_DATA
import io.reactivex.Single
/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PregnantFashsion
 * Copyright (C) 18/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.praybox.source.dao
 *
 */
@Dao
interface FashionDao {

    @Query("SELECT * FROM $TABLE_NAME_DATA")
    fun getAllData(): Single<List<Fashion>>

    @Insert
    fun insertData(fashion: Fashion)

    @Query("UPDATE $TABLE_NAME_DATA SET favorite = :favorite WHERE table_id = :tableid")
    fun updateFavorite(tableid: Int, favorite: Boolean)

    @Query("DELETE FROM $TABLE_NAME_DATA WHERE table_id = :tableid")
    fun deleteData(tableid: Int)

    @Query("DELETE FROM $TABLE_NAME_DATA")
    fun nukeData()

}