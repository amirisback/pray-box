/**
 * Created by faisalamir on 19/09/21
 * FrogoRecyclerView
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.
 * All rights reserved
 *
 */

object ProjectVariant {

    val listPlayStore = listOf(
        Frogobox.playConsoleVariant
    )

    object Frogobox {

        // Play Console / Godev Name
        const val PLAY_STORE_NAME = "frogobox"
        val APP_ID = "${ProjectSetting.APP_DOMAIN}.$PLAY_STORE_NAME.${ProjectSetting.APP_NAME}"
        val DB = "db_${PLAY_STORE_NAME}_${ProjectSetting.NAME_SETTING_LOWERCASE}.db"
        val PREF_NAME = "PREF_GAME_${PLAY_STORE_NAME.uppercase()}_${ProjectSetting.NAME_SETTING_UPERCASE}"

        // -----------------------------------------------------------------------------------------

        // Server
        val URL_ADMOB_SERVER = ""

        // -----------------------------------------------------------------------------------------

        // Key Store
        const val PLAYSTORE_STORE_FILE = "keystore/frogoboxmedia.jks"
        const val PLAYSTORE_STORE_PASSWORD = "amirisback"
        const val PLAYSTORE_KEY_ALIAS = "frogoisback"
        const val PLAYSTORE_KEY_PASSWORD = "amirisback"

        val playConsoleVariant = PlayConsoleVariant(
            PLAY_STORE_NAME,
            APP_ID,
            DB,
            PREF_NAME,
            URL_ADMOB_SERVER,
            PLAYSTORE_STORE_FILE,
            PLAYSTORE_STORE_PASSWORD,
            PLAYSTORE_KEY_ALIAS,
            PLAYSTORE_KEY_PASSWORD
        )

    }

}

data class PlayConsoleVariant(
    val name: String,
    val appId: String,
    val dbName: String,
    val prefName: String,
    val urlAdmobServer: String,
    val playStoreStoreFile: String,
    val playStoreStorePassword: String,
    val playStoreKeyAlias: String,
    val playStoreKeyPassword: String,
)
