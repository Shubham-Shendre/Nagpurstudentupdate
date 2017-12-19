package com.nagstud.adnan.mobileapp_1;
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */
public class Years {

    String id,name,code,wrtf,wrtb,wrtsory;

    public Years(String id, String name, String code, String wrtf, String yors, String ans)
    {
        this.id = id;
        this.name = name;
        this.code =code;
        this.wrtf =wrtf;
        this.wrtb =wrtb;
        this.wrtsory= wrtsory;
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

    public String getWrtf() {
        return wrtf;
    }

    public String getWrtb() {
        return wrtb;
    }

    public String getWrtsory() {
        return wrtsory;
    }
}