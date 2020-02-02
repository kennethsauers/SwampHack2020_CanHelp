package com.hackathon.blighteye;

import android.app.Application;

import java.io.Serializable;

public class SomeData extends Application implements Serializable {
    private int chance;
    private String conditionName;
    private String link;

    public int getChance() {
        return chance;
    }

    public String getConditionName() {
        return conditionName;
    }

    public String getLink() {
        return link;
    }

    SomeData(int c, String con, String l) {
        chance = c; conditionName = con; link = l;
    }
}
