package com.mmsofts.apachecommons.cmdChain;

import junit.framework.TestCase;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;

/**
 * Created by mms on 5/31/16.
 */

public class CmdTest extends TestCase {

    public void testProfileCheckNeed(){

        Context context = new ContextBase();
        Command command = new ProfileCheck();
        try{
            command.execute(context);
        }catch (Exception e){ fail(e.getMessage());}

        Profile profile = (Profile) context.get(Profile.PROFILE_KEY);
        assertNotNull("Missing Profile", profile);
    }
}
