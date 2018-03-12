package com.android.blantik.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public class DataHewanArray implements Serializable {

    @SerializedName("bagian")
    @Expose
    private String bagian;
    @SerializedName("value")
    @Expose
    private Integer value;

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
