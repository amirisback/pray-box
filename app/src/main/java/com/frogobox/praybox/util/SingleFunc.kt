package com.frogobox.praybox.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.CountDownTimer
import android.os.Environment
import android.widget.TextView
import com.frogobox.praybox.BuildConfig
import com.frogobox.praybox.R
import com.frogobox.praybox.util.SingleConstant.Dir.DIR_NAME
import com.frogobox.praybox.util.SingleConstant.Dir.VIDEO_FILE_NAME
import com.frogobox.praybox.util.SingleConstant.Pref.PREF_NAME
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PublicSpeakingBooster
 * Copyright (C) 16/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.publicspeakingbooster.helper
 *
 */
object SingleFunc {

    object Func {

        fun createFolderPictureVideo() {
            val videoFolder = Environment.getExternalStoragePublicDirectory(DIR_NAME)
            if (!videoFolder.exists()) {
                videoFolder.mkdirs()
            }
        }

        fun getVideoFilePath(): String {
            val dir = Environment.getExternalStoragePublicDirectory(DIR_NAME)
            return if (dir == null) {
                VIDEO_FILE_NAME
            } else {
                "${dir.absoluteFile}/$VIDEO_FILE_NAME"
            }
        }

        fun createDialogDefault(
            context: Context,
            title: String,
            message: String,
            listenerYes: () -> Unit,
            listenerNo: () -> Unit
        ) {
            val dialogBuilder = AlertDialog.Builder(context)
            // set message of alert dialog
            dialogBuilder.setMessage(message)
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton(
                    context.getText(R.string.dialog_button_yes),
                    DialogInterface.OnClickListener { dialog, id ->
                        listenerYes()
                    })
                // negative button text and action
                .setNegativeButton(
                    context.getText(R.string.dialog_button_no),
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                        listenerNo()
                    })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle(title)
            // show alert dialog
            alert.show()
        }

        fun noAction(): Boolean {
            return true
        }

        fun isNetworkAvailable(context: Context): Boolean? {
            var isConnected: Boolean? = false // Initial Value
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected) isConnected = true
            return isConnected
        }

        fun showVersion(): String {
            return "Version " + BuildConfig.VERSION_NAME
        }

    }

    object Preference {

        fun getSp(context: Context): SharedPreferences {
            return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        }

        object Save {
            fun savePrefFloat(
                sharedPreferences: SharedPreferences,
                constPref: String,
                data: Float
            ) {
                sharedPreferences.edit().putFloat(constPref, data).apply()
            }

            fun savePrefInt(sharedPreferences: SharedPreferences, constPref: String, data: Int) {
                sharedPreferences.edit().putInt(constPref, data).apply()
            }

            fun savePrefString(
                sharedPreferences: SharedPreferences,
                constPref: String,
                data: String
            ) {
                sharedPreferences.edit().putString(constPref, data).apply()
            }

            fun savePrefBoolean(
                sharedPreferences: SharedPreferences,
                constPref: String,
                data: Boolean
            ) {
                sharedPreferences.edit().putBoolean(constPref, data).apply()
            }

            fun savePrefLong(sharedPreferences: SharedPreferences, constPref: String, data: Long) {
                sharedPreferences.edit().putLong(constPref, data).apply()
            }

        }

        object Delete {

            fun deletePref(sharedPreferences: SharedPreferences, constPref: String) {
                sharedPreferences.edit().remove(constPref).apply()
            }

        }

        object Load {

            fun loadPrefFloat(sharedPreferences: SharedPreferences, constPref: String): Float {
                return sharedPreferences.getFloat(constPref, 0f)
            }

            fun loadPrefString(sharedPreferences: SharedPreferences, constPref: String): String {
                return sharedPreferences.getString(constPref, "")!!
            }

            fun loadPrefInt(sharedPreferences: SharedPreferences, constPref: String): Int {
                return sharedPreferences.getInt(constPref, 0)
            }

            fun loadPrefLong(sharedPreferences: SharedPreferences, constPref: String): Long {
                return sharedPreferences.getLong(constPref, 0)
            }

            fun loadPrefBoolean(sharedPreferences: SharedPreferences, constPref: String): Boolean {
                return sharedPreferences.getBoolean(constPref, false)
            }

        }

    }

    object WaktuShalat {

        // Deklarasi Method helper ---------------------------------------------------------------------
        private val prayers = PrayerTime()
        private val now = Date()
        private val cal = Calendar.getInstance()

        // ---------------------------------------------------------------------------------------------
        // Deklarasi Requirement Kebutuhan -------------------------------------------------------------
        private val waktuSaatIni: Int

        // ---------------------------------------------------------------------------------------------
        var jmlWaktuShubuh = 0
            private set
        var jmlWaktuTerbit = 0
            private set
        var jmlWaktuTerbenam = 0
            private set
        var jmlWaktuDzuhur = 0
            private set
        var jmlWaktuAshar = 0
            private set
        var jmlWaktuMaghrib = 0
            private set
        var jmlWaktuIsya = 0
            private set
        val jmlBeMidnight: Int
        val jmlAftMidnight: Int
        var jamWaktuShubuh: String? = null
            private set
        var jamWaktuDzuhur: String? = null
            private set
        var jamWaktuAshar: String? = null
            private set
        var jamWaktuMaghrib: String? = null
            private set
        var jamWaktuIsya: String? = null
            private set

        // ---------------------------------------------------------------------------------------------
        // ---------------------------------------------------------------------------------------------
        private val timezone =
            (Calendar.getInstance().timeZone.getOffset(Calendar.getInstance().timeInMillis) / (1000 * 60 * 60)).toDouble()

        // ---------------------------------------------------------------------------------------------
        // ---------------------------------------------------------------------------------------------
        private val offsets =
            intArrayOf(0, 0, 0, 0, 0, 0, 0) // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha}
        private var prayerTimes: ArrayList<*>? = null
        private var prayerNames: ArrayList<*>? = null

        // ---------------------------------------------------------------------------------------------
        fun saatWaktunyaShubuh(): Boolean {
            return waktuSaatIni == jmlWaktuShubuh || waktuSaatIni < jmlWaktuTerbit
        }

        fun saatWaktunyaDzuhur(): Boolean {
            return waktuSaatIni == jmlWaktuDzuhur || waktuSaatIni < jmlWaktuAshar
        }

        fun saatWaktunyaAshar(): Boolean {
            return waktuSaatIni == jmlWaktuAshar || waktuSaatIni < jmlWaktuMaghrib
        }

        fun saatWaktunyaMaghrib(): Boolean {
            return waktuSaatIni == jmlWaktuMaghrib || waktuSaatIni < jmlWaktuIsya
        }

        fun saatWaktunyaIsyaPagi(): Boolean {
            return waktuSaatIni == jmlAftMidnight || waktuSaatIni < jmlWaktuShubuh
        }

        fun saatWaktunyaIsyaMalam(): Boolean {
            return waktuSaatIni == jmlWaktuIsya || waktuSaatIni <= jmlBeMidnight
        }

        fun saatWaktunyaBukan(): Boolean {
            return waktuSaatIni == jmlWaktuTerbit || waktuSaatIni < jmlWaktuDzuhur
        }

        fun setJadwalShalat(txt: TextView) {
            if (saatWaktunyaIsyaPagi()) {
                txt.text = SingleConstant.Const.ISYA // isya
            } else if (saatWaktunyaShubuh()) {
                txt.text = SingleConstant.Const.SHUBUH // shubuh
            } else if (saatWaktunyaBukan()) {
                txt.text = SingleConstant.Const.BUKAN_WAKTU_SHOLAT // bukan waktu shalat
                txt.textSize = 20f
            } else if (saatWaktunyaDzuhur()) {
                txt.text = SingleConstant.Const.DZUHUR // dzuhur
            } else if (saatWaktunyaAshar()) {
                txt.text = SingleConstant.Const.ASHAR // ashar
            } else if (saatWaktunyaMaghrib()) {
                txt.text = SingleConstant.Const.MAGHRIB // maghrib
            } else if (saatWaktunyaIsyaMalam()) {
                txt.text = SingleConstant.Const.ISYA // isya
            } else {
                txt.text = SingleConstant.Const.BUKAN_WAKTU_SHOLAT
                txt.textSize = 20f
            }
        }// bukan waktu shalat// isya// maghrib// ashar// dzuhur// bukan waktu shalat// shubuh

        // isya
        val jadwalShalat: String
            get() = if (saatWaktunyaIsyaPagi()) {
                SingleConstant.Const.ISYA // isya
            } else if (saatWaktunyaShubuh()) {
                SingleConstant.Const.SHUBUH // shubuh
            } else if (saatWaktunyaBukan()) {
                SingleConstant.Const.BUKAN_WAKTU_SHOLAT // bukan waktu shalat
            } else if (saatWaktunyaDzuhur()) {
                SingleConstant.Const.DZUHUR // dzuhur
            } else if (saatWaktunyaAshar()) {
                SingleConstant.Const.ASHAR // ashar
            } else if (saatWaktunyaMaghrib()) {
                SingleConstant.Const.MAGHRIB // maghrib
            } else if (saatWaktunyaIsyaMalam()) {
                SingleConstant.Const.ISYA // isya
            } else {
                SingleConstant.Const.BUKAN_WAKTU_SHOLAT // bukan waktu shalat
            }

        // ---------------------------------------------------------------------------------------------
        fun CoundownTime(waktu: Int, mTextView: TextView) {
            object : CountDownTimer(waktu.toLong(), 1000) {
                // adjust the milli seconds here
                override fun onTick(millisUntilFinished: Long) {
                    mTextView.text = "- " + String.format(
                        SingleConstant.Const.FORMAT_COUNTDOWN,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
                        ),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                        )
                    )
                }

                override fun onFinish() {
                    mTextView.setText(R.string.jadwal_helper_saatnya_shalat)
                }
            }.start()
        }

        // ---------------------------------------------------------------------------------------------
        private fun MethodWaktuShalatHelper() {
            val latitude = -6.1744
            val longitude = 106.8294
            // -----------------------------------------------------------------------------------------
            prayers.timeFormat = prayers.Time24
            prayers.calcMethod = prayers.MWL
            prayers.asrJuristic = prayers.Shafii
            prayers.adjustHighLats = prayers.MidNight
            prayers.tune(offsets)
            // -----------------------------------------------------------------------------------------
            cal.time = now
            prayerTimes = prayers.getPrayerTimes(cal, latitude, longitude, timezone)
            prayerNames = prayers.timeNames
            // -----------------------------------------------------------------------------------------
        }

        private fun setJmlWaktu() {
            // -----------------------------------------------------------------------------------------
            MethodWaktuShalatHelper()
            // -----------------------------------------------------------------------------------------
            for (i in prayerTimes!!.indices) {
                // -------------------------------------------------------------------------------------
                val tempWaktu = prayerTimes!![i].toString()
                val hours = tempWaktu.substring(0, 2)
                val minutes = tempWaktu.substring(5, tempWaktu.length)
                // -------------------------------------------------------------------------------------
                val jam = hours.toInt()
                val menit = minutes.toInt()
                // -------------------------------------------------------------------------------------
                val total =
                    jam * SingleConstant.Const.JAM_KE_DETIK + menit * SingleConstant.Const.MENIT_KE_DETIK
                // -------------------------------------------------------------------------------------
                if (prayerNames!![i] == SingleConstant.Const.SHUBUH.substring(7)) {
                    jmlWaktuShubuh = total
                } else if (prayerNames!![i] == SingleConstant.Const.DZUHUR.substring(7)) {
                    jmlWaktuDzuhur = total
                } else if (prayerNames!![i] == SingleConstant.Const.ASHAR.substring(7)) {
                    jmlWaktuAshar = total
                } else if (prayerNames!![i] == SingleConstant.Const.MAGHRIB.substring(7)) {
                    jmlWaktuMaghrib = total
                } else if (prayerNames!![i] == SingleConstant.Const.ISYA.substring(7)) {
                    jmlWaktuIsya = total
                } else if (prayerNames!![i] == SingleConstant.Const.MATAHARI_TERBIT) {
                    jmlWaktuTerbit = total
                } else if (prayerNames!![i] == SingleConstant.Const.MATAHARI_TERBENAM) {
                    jmlWaktuTerbenam = total
                }
                // -------------------------------------------------------------------------------------
            }
        }

        fun setTimeOnline(
            mTextViewWaktuShubuh: TextView, mTextViewWaktuDzuhur: TextView,
            mTextViewWaktuAshar: TextView, mTextViewWaktuMaghrib: TextView,
            mTextViewWaktuIsya: TextView
        ) {
            // -----------------------------------------------------------------------------------------
            MethodWaktuShalatHelper()
            // -----------------------------------------------------------------------------------------
            // Menset Waktu Sholat
            for (i in prayerTimes!!.indices) {
                if (prayerNames!![i] == SingleConstant.Const.SHUBUH.substring(7)) {
                    mTextViewWaktuShubuh.text = prayerTimes!![i].toString()
                    jamWaktuShubuh = prayerTimes!![i].toString()
                } else if (prayerNames!![i] == SingleConstant.Const.DZUHUR.substring(7)) {
                    mTextViewWaktuDzuhur.text = prayerTimes!![i].toString()
                    jamWaktuDzuhur = prayerTimes!![i].toString()
                } else if (prayerNames!![i] == SingleConstant.Const.ASHAR.substring(7)) {
                    mTextViewWaktuAshar.text = prayerTimes!![i].toString()
                    jamWaktuAshar = prayerTimes!![i].toString()
                } else if (prayerNames!![i] == SingleConstant.Const.MAGHRIB.substring(7)) {
                    mTextViewWaktuMaghrib.text = prayerTimes!![i].toString()
                    jamWaktuMaghrib = prayerTimes!![i].toString()
                } else if (prayerNames!![i] == SingleConstant.Const.ISYA.substring(7)) {
                    mTextViewWaktuIsya.text = prayerTimes!![i].toString()
                    jamWaktuIsya = prayerTimes!![i].toString()
                }
            }
            // -----------------------------------------------------------------------------------------
        }

        // ---------------------------------------------------------------------------------------------
        init {
            val methodHelper = Controller
            jmlBeMidnight =
                23 * SingleConstant.Const.JAM_KE_DETIK + 59 * SingleConstant.Const.MENIT_KE_DETIK // 86.340
            jmlAftMidnight = 0 // 0
            setJmlWaktu()
            Controller.systemRealTime
            waktuSaatIni = Controller.sumWaktuDetik
        }

    }

    object Controller {
        // Requirement Random Karakter -----------------------------------------------------------------
        private val chars = "1234567890".toCharArray()
        private val stringBuilder = StringBuilder()
        private val random = Random()

        // Method Random Character ---------------------------------------------------------------------
        var randomChar: String? = null
            get() {
                for (lenght in 0..4) {
                    val character = chars[random.nextInt(chars.size)]
                    stringBuilder.append(character)
                }
                field = stringBuilder.toString()
                stringBuilder.delete(0, 5)
                return field
            }
            private set

        // ---------------------------------------------------------------------------------------------
        // Requirement Tanggal dan Waktu ---------------------------------------------------------------
        private val currentTime: Calendar

        // ---------------------------------------------------------------------------------------------
        val dateToday: String
        private var nilai_jam: String? = null
        private var nilai_menit: String? = null
        private var nilai_detik: String? = null
        private var nol_jam = ""
        private var nol_menit = ""
        private var nol_detik = ""
        var systemJam = 0
            private set
        var systemMenit = 0
            private set
        private var systemDetik = 0

        // ---------------------------------------------------------------------------------------------
        var sumWaktuDetik = 0
            get() {
                sumRealTime
                field =
                    systemJam * SingleConstant.Const.JAM_KE_DETIK + systemMenit * SingleConstant.Const.MENIT_KE_DETIK + systemDetik
                return field
            }
            private set
        var systemYear = 0
            private set

        // ---------------------------------------------------------------------------------------------
        private val outputStringTime: String
            private get() = "$nilai_jam:$nilai_menit"// Skala Waktu 24 Jam ----------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // ---------------------------------------------------------------------------------------------
        private val pureSystemTime: Unit
            private get() {
                // Skala Waktu 24 Jam ----------------------------------------------------------------------
                val cal = Calendar.getInstance()
                systemYear = cal[Calendar.YEAR]
                systemJam = cal[Calendar.HOUR_OF_DAY]
                systemMenit = cal[Calendar.MINUTE]
                systemDetik = cal[Calendar.SECOND]
                // -----------------------------------------------------------------------------------------
            }

        // skala waktu 24 Jam
        // -----------------------------------------------------------------------------------------
        // Jika waktu itu kurang dari 9 maka akan di tambahkan 0 didepan angkanya
        // -----------------------------------------------------------------------------------------
        private val cekFormat: Unit
            private get() {
                pureSystemTime // skala waktu 24 Jam
                // -----------------------------------------------------------------------------------------
                // Jika waktu itu kurang dari 9 maka akan di tambahkan 0 didepan angkanya
                if (systemJam <= 9) {
                    nol_jam = "0"
                }
                if (systemMenit <= 9) {
                    nol_menit = "0"
                }
                if (systemDetik <= 9) {
                    nol_detik = "0"
                }
                // -----------------------------------------------------------------------------------------
            }

        // -----------------------------------------------------------------------------------------
        // Tampilan String dari systemJam, systemMenit, systemDetik Ex : 02 , 05 , 15
        // -----------------------------------------------------------------------------------------
        val systemTime: Unit
            get() {
                cekFormat
                // -----------------------------------------------------------------------------------------
                // Tampilan String dari systemJam, systemMenit, systemDetik Ex : 02 , 05 , 15
                nilai_jam = nol_jam + Integer.toString(systemJam)
                nilai_menit = nol_menit + Integer.toString(systemMenit)
                nilai_detik = nol_detik + Integer.toString(systemDetik)
                // -----------------------------------------------------------------------------------------
            }

        fun getFormatTimePicker(txt_text: TextView, hourInput: Int, menitInput: Int) {
            // -----------------------------------------------------------------------------------------
            var nolMenits = ""
            var nolJams = ""
            if (hourInput <= 9) {
                nolJams = "0"
            }
            if (menitInput <= 9) {
                nolMenits = "0"
            }
            // -----------------------------------------------------------------------------------------

            // Tampilan String dari systemJam, systemMenit, systemDetik Ex : 02 , 05 , 15
            val NewJam = nolJams + Integer.toString(hourInput)
            val NewMenit = nolMenits + Integer.toString(menitInput)
            // -----------------------------------------------------------------------------------------
            val Waktu = "$NewJam : $NewMenit"
            txt_text.text = Waktu
        }//                        Logger.getLogger(CatatanFragment.class.getName()).log(Level.SEVERE, null, ex);// Panggil waktu dari System

        // Panggil String waktu;
        // ---------------------------------------------------------------------------------------------
        // Mendapatkan Waktu system yang bergerak setiap detik
        val systemRealTime: Unit
            get() {
                val p: Thread = object : Thread() {
                    override fun run() {
                        while (true) {
                            systemTime // Panggil waktu dari System
                            outputStringTime // Panggil String waktu;
                            try {
                                sleep(1000)
                            } catch (ex: InterruptedException) {
//                        Logger.getLogger(CatatanFragment.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                p.start()
            }//                        Logger.getLogger(CatatanFragment.class.getName()).log(Level.SEVERE, null, ex);

        // ---------------------------------------------------------------------------------------------
        // ---------------------------------------------------------------------------------------------
        // Mendapatkan Jumlah detik waktu sekarang
        val sumRealTime: Unit
            get() {
                val p: Thread = object : Thread() {
                    override fun run() {
                        while (true) {
                            pureSystemTime
                            try {
                                sleep(1000)
                            } catch (ex: InterruptedException) {
//                        Logger.getLogger(CatatanFragment.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                p.start()
            }

        // ---------------------------------------------------------------------------------------------
        init {
            currentTime = Calendar.getInstance()
            val simpleDateFormat = SimpleDateFormat(SingleConstant.Const.FORMAT_DATE)
            dateToday = simpleDateFormat.format(currentTime.time)
            systemRealTime
            sumRealTime
            sumWaktuDetik
        }
    }

}