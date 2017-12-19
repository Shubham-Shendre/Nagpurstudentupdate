package com.nagstud.adnan.mobileapp_1;
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */
public class Faculty {

    String id,name,code,trade;

    public Faculty(String id, String name, String code, String trade)
    {
        this.id = id;
        this.name = name;
        this.code =code;
        this.trade = trade;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getTrade() {
        return trade;
    }
}