package com.example.vova.budjet.classes;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InfoLab {

    public List<Info> mInfos;

    private static InfoLab sInfoLab;

    private InfoLab(Context context) {

        mInfos = new ArrayList<Info>();
        for (int i = 0; i < 5; i++) {
            Info info = new Info();
            info.setChoise("Costs");
            info.setShortDescribe("№ " + i);
            info.setMoneyChange(""+i);
            if (i%3==0){
                info.setDescribe("Info number: "+i);
            }
            mInfos.add(info);
        }

    }

    public List<Info> getInfos() {
        return mInfos;
    }

    public static InfoLab get(Context context) {
        if (sInfoLab != null) {
            return sInfoLab;
        } else sInfoLab = new InfoLab(context);
        return sInfoLab;
    }

    public Info getInfo(UUID id) {
        for (Info info : mInfos) {
            if (info.getId().equals(id)) return info;
        }
        return null;
    }

}
