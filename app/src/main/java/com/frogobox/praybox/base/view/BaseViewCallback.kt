package com.frogobox.praybox.base.view

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PregnantFashsion
 * Copyright (C) 02/09/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.praybox.base
 *
 */
interface BaseViewCallback {
    fun onShowProgress()
    fun onHideProgress()
    fun onSuccesInsert()
    fun onSuccesDelete()
    fun onFailed(message: String)
}