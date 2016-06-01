package com.nullcognition.abstractions.easygoogle;


import android.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mms on 6/1/16.
 */

public class Frag extends Fragment {

    private Map<Class<? extends GModule>, GModule> mModules = new HashMap<>();
    String string;

    public void setInfoToBePassed(String string){
        this.string = string;
    }

    public <M extends GModule<L>, L> void enableModule(Class<M> clazz, L listener) {
    }

    public <T> T getModule(Class<T> clazz) {
        return (T) mModules.get(clazz);
    }
}
