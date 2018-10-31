package org.d3ifcool.jagosholat.presenters.interfaces;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * 40-mobpro-4002-jago-sholat
 * Copyright (C) 29/10/2018.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public interface InterfaceCatatanFragment {

    void insertDataToDatabase();
    void addData(String mShalat);
    void tampilanButtonSimpan(String mShalat);
    String cekDataSudahAda();
    boolean isEmptyRowTable();

}
