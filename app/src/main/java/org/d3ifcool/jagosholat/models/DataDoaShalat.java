package org.d3ifcool.jagosholat.models;

/**
 * Created by Bryan on 3/12/2018.
 */

public class DataDoaShalat {
    private String arabDoa, latinDoa, terjemahDoa;

    public DataDoaShalat(String arabDoa, String latinDoa, String terjemahDoa) {
        this.arabDoa = arabDoa;
        this.latinDoa = latinDoa;
        this.terjemahDoa = terjemahDoa;
    }

    public String getArabDoa() {
        return arabDoa;
    }

    public void setArabDoa(String arabDoa) {
        this.arabDoa = arabDoa;
    }

    public String getLatinDoa() {
        return latinDoa;
    }

    public void setLatinDoa(String latinDoa) {
        this.latinDoa = latinDoa;
    }

    public String getTerjemahDoa() {
        return terjemahDoa;
    }

    public void setTerjemahDoa(String terjemahDoa) {
        this.terjemahDoa = terjemahDoa;
    }
}
