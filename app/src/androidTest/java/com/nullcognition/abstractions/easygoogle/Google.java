package com.nullcognition.abstractions.easygoogle;

import android.app.Activity;
import android.content.Context;

/**
 * Created by mms on 5/31/16.
 */

public class Google {

    // holds the gacFragment
    Frag fragment;

    // uses a builder
    public static class Builder{

        private Activity activity;

        private GOperation.Listener gOperationListener;
        private String infoToBePassed;

        // required as the main operational unit
        public Builder(Activity activity){
            this.activity = activity;
        }

        public Builder enableGOperation(GOperation.Listener gOperationListener){
            this.gOperationListener = gOperationListener;
            return this;
        }

        public Builder enableGOperation(GOperation.Listener gOperationListener, String infoToBePassed){
            this.gOperationListener = gOperationListener;
            this.infoToBePassed = infoToBePassed;
            return this;
        }

        public Google build() {
            Google google = new Google(activity);
            if (gOperationListener != null) {
                if(infoToBePassed != null) {
                    google.fragment.setInfoToBePassed(infoToBePassed);
                }
                google.fragment.enableModule(GOperation.class, gOperationListener);
            }
            return google;
        }
    }
    private Google(Activity activity) {
        fragment = (Frag)activity.getFragmentManager().getFragment(null, "TheFragment");
    }

    public Context getSomeServiceFromFragment(){
        return fragment.getContext();
    }

    public GOperation getSpecificModuleGOperationModule(){
        return fragment.getModule(GOperation.class);
    }

//    public GSomethingElse getSpecificModuleSomeOtherModule(){
//        return fragment.getModule(SomeOther.class);
//    }
}


















































