package com.frogobox.praybox.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.frogobox.praybox.util.SingleConstant.RoomDatabase.TABLE_NAME_DATA

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
 * com.frogobox.praybox.model
 *
 */
@Entity(tableName = TABLE_NAME_DATA)
data class Fashion(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "table_id")
    var table_id: Int = 0,

    @ColumnInfo(name = "type")
    var type: String? = "",

    @ColumnInfo(name = "linkImage")
    var linkImage: String? = "",

    @ColumnInfo(name = "favorite")
    var favorite: Boolean? = false

)