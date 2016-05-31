package com.mmsofts.apachecommons.cmdChain;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Locale;

/**
 * Created by mms on 5/31/16.
 */
public class LocaleValueTest extends TestCase {

    MailReader context;

    public void setUp() {
        context = new MailReader();
    }

    public void testLocaleSetPropertyGetMap() {
        Locale expected = Locale.CANADA_FRENCH;
        context.setLocale(expected);
        Locale locale = (Locale) context.get(MailReader.LOCALE_KEY);
        Assert.assertNotNull(locale);
        Assert.assertEquals(expected, locale);
    }

    public void testLocalePutMapGetProperty() {
        Locale expected = Locale.ITALIAN;
        context.put(MailReader.LOCALE_KEY, expected);
        Locale locale = context.getLocale();
        Assert.assertNotNull(locale);
        Assert.assertEquals(expected, locale);
    }

    public void testLocaleSetTypedWithStringException() {
        String localeString = Locale.US.toString();
        try {
            context.put(MailReader.LOCALE_KEY, localeString);
            fail("Expected 'argument type mismatch' error");
        } catch (UnsupportedOperationException expected) {

        }
    }
}
