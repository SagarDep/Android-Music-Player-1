package com.saketkumar.perpule.network;

import com.google.gson.annotations.SerializedName;
import com.saketkumar.perpule.model.Data;

import java.util.List;

public class ResponseData {

    @SerializedName("data")
    private List<Data> dataList;

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> list) {
        dataList = list;
    }

}