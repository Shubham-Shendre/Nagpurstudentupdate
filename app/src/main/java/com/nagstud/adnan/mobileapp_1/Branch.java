package com.nagstud.adnan.mobileapp_1;
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */
public class Branch {

    String id,name,code,wrtf,yors,ans;

    public Branch(String id, String name, String code, String wrtf, String yors, String ans)
    {
        this.id = id;
        this.name = name;
        this.code =code;
        this.wrtf =wrtf;
        this.yors =yors;
        this.ans= ans;
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

    public String getYors() {
        return yors;
    }

    public String getAns() {
        return ans;
    }
}