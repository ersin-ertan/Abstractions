package com.mmsofts.apachecommons.cmdChain;

import org.apache.commons.chain.impl.ContextBase;

import java.util.Locale;

class MailReader extends ContextBase {
    public static String LOCALE_KEY = "locale";
    private Locale locale;
    public Locale getLocale() {
        return locale;
    }
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}

//  if your application can use a Map-style context, see the leaner version of a MailReader context

/*
public class MailReader extends Hashmap implements Context {
    public static String LOCALE_KEY = "locale";
}
*/