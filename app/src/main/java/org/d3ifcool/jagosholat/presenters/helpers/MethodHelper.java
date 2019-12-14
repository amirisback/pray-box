package org.d3ifcool.jagosholat.presenters.helpers;

import android.widget.TextView;

import org.d3ifcool.jagosholat.models.constants.VarConstants.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Bryan Rafsanzani on 06/09/2018.
 */

public class MethodHelper {

    // Requirement Random Karakter -----------------------------------------------------------------
    private char[] chars = "1234567890".toCharArray();
    private StringBuilder stringBuilder = new StringBuilder();
    private Random random = new Random();
    private String randomChar;
    // ---------------------------------------------------------------------------------------------

    // Requirement Tanggal dan Waktu ---------------------------------------------------------------
    private Calendar currentTime;
    private String dateToday, nilai_jam, nilai_menit, nilai_detik,
            nol_jam = "", nol_menit = "",nol_detik = "";
    private int systemJam, systemMenit, systemDetik, sumWaktuDetik, systemYear;
    // ---------------------------------------------------------------------------------------------

    public MethodHelper() {
        this.currentTime = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT_DATE);
        this.dateToday = simpleDateFormat.format(currentTime.getTime());
        getSystemRealTime();
        getSumRealTime();
        getSumWaktuDetik();



    }

    // Method Random Character ---------------------------------------------------------------------
    public String getRandomChar() {
        for (int lenght = 0; lenght < 5; lenght++) {
            Character character = chars[random.nextInt(chars.length)];
            stringBuilder.append(character);
        }
        randomChar = stringBuilder.toString();
        stringBuilder.delete(0, 5);
        return randomChar;
    }
    // ---------------------------------------------------------------------------------------------

    public String getDateToday() {
        return dateToday;
    }

    // ---------------------------------------------------------------------------------------------
    private String getOutputStringTime() {
        return nilai_jam + ":" + nilai_menit;
    }
    // ---------------------------------------------------------------------------------------------

    private void getPureSystemTime() {
        // Skala Waktu 24 Jam ----------------------------------------------------------------------
        Calendar cal = Calendar.getInstance();
        systemYear = cal.get(Calendar.YEAR);
        systemJam = cal.get(Calendar.HOUR_OF_DAY);
        systemMenit = cal.get(Calendar.MINUTE);
        systemDetik = cal.get(Calendar.SECOND);
        // -----------------------------------------------------------------------------------------
    }

    private void getCekFormat(){
        getPureSystemTime(); // skala waktu 24 Jam
        // -----------------------------------------------------------------------------------------
        // Jika waktu itu kurang dari 9 maka akan di tambahkan 0 didepan angkanya
        if(systemJam <= 9) {
            nol_jam = "0";
        }

        if(systemMenit <= 9) {
            nol_menit = "0";
        }

        if(systemDetik <= 9) {
            nol_detik = "0";
        }
        // -----------------------------------------------------------------------------------------
    }

    public void getSystemTime() {
        getCekFormat();
        // -----------------------------------------------------------------------------------------
        // Tampilan String dari systemJam, systemMenit, systemDetik Ex : 02 , 05 , 15
        nilai_jam = nol_jam + Integer.toString(systemJam);
        nilai_menit = nol_menit + Integer.toString(systemMenit);
        nilai_detik = nol_detik + Integer.toString(systemDetik);
        // -----------------------------------------------------------------------------------------
    }

    public void getFormatTimePicker(TextView txt_text, int hourInput, int menitInput){
        // -----------------------------------------------------------------------------------------
        String nolMenits = "";
        String nolJams = "";
        if (hourInput <= 9){
            nolJams = "0";
        }

        if (menitInput <= 9){
            nolMenits = "0";
        }
        // -----------------------------------------------------------------------------------------

        // Tampilan String dari systemJam, systemMenit, systemDetik Ex : 02 , 05 , 15
        String NewJam = nolJams + Integer.toString(hourInput);
        String NewMenit = nolMenits + Integer.toString(menitInput);
        // -----------------------------------------------------------------------------------------
        String Waktu = NewJam + " : " + NewMenit;
        txt_text.setText(Waktu);
    }

    // ---------------------------------------------------------------------------------------------
    // Mendapatkan Waktu system yang bergerak setiap detik
    public void getSystemRealTime(){
        Thread p = new Thread(){
            public void run(){
                for(;;){
                    getSystemTime(); // Panggil waktu dari System
                    getOutputStringTime(); // Panggil String waktu;
                    try {
                        sleep(1000);
                    }catch (InterruptedException ex){
//                        Logger.getLogger(CatatanFragment.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        p.start();
    }
    // ---------------------------------------------------------------------------------------------


    // ---------------------------------------------------------------------------------------------
    // Mendapatkan Jumlah detik waktu sekarang
    public void getSumRealTime() {
        Thread p = new Thread(){
            public void run(){
                for(;;){
                    getPureSystemTime();
                    try {
                        sleep(1000);
                    }catch (InterruptedException ex){
//                        Logger.getLogger(CatatanFragment.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        p.start();
    }
    // ---------------------------------------------------------------------------------------------

    public int getSumWaktuDetik() {
        getSumRealTime();
        sumWaktuDetik = (systemJam * Constants.JAM_KE_DETIK) + (systemMenit * Constants.MENIT_KE_DETIK) + systemDetik;
        return sumWaktuDetik;
    }

    public int getSystemJam() {
        return systemJam;
    }

    public int getSystemMenit() {
        return systemMenit;
    }

    public int getSystemYear(){
        return systemYear;
    }

}