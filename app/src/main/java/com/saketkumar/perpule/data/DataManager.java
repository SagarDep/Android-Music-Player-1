package com.saketkumar.perpule.data;

import com.google.gson.annotations.SerializedName;
import com.saketkumar.perpule.data.model.App;

import java.util.List;

public class DataManager {

    @SerializedName("data")
    private List<App> dataList;

    public List<App> getDataList() {
        return dataList;
    }

    public void setDataList(List<App> list) {
        dataList = list;
    }

}