
package com.guidovezzoni.mvplibrary.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class List_ {

    public Integer dt;
    public Main main;
    public List<Weather> weather = null;
    public Clouds clouds;
    public Wind wind;
    public Snow snow;
    public Sys_ sys;

    @SerializedName("dt_txt")
    public Date dtTxt;

}
