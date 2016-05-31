package com.mmsofts.apachecommons.cmdChain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * Created by mms on 5/31/16.
 */

public class ProfileCheck implements Command {

    public Profile newProfile(Context context) { return new Profile(); }

    public boolean execute(Context context) throws Exception {
        Object profile = context.get(Profile.PROFILE_KEY);
        if (null == profile) {
            profile = newProfile(context);
            context.put(Profile.PROFILE_KEY, profile);
        }
        return false;
    }
}

class Profile {
    public static String PROFILE_KEY = "profile";
}