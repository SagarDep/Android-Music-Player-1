package com.saketkumar.perpule.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class App implements Serializable {

    @SerializedName("itemId")
    @Expose
    private String itemId;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("audio")
    @Expose
    private String audio;

    public App(String itemId, String desc, String audio) {
        this.itemId = itemId;
        this.desc = desc;
        this.audio = audio;

    }


    public String getId() {
        return itemId;
    }

    public String getDesc() {
        return desc;
    }

    public String getAudio() {
        return audio;
    }
}
