package com.example.navitateste.util;

import android.os.Build;
import android.os.LocaleList;

import java.util.Locale;

public class LanguageSelector {

    public String LANGUAGE;

    public String getSystemLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LANGUAGE = LocaleList.getDefault().get(0).getLanguage();
            return LANGUAGE;
        } else {
            return LANGUAGE = Locale.getDefault().getLanguage();

        }
    }
}
