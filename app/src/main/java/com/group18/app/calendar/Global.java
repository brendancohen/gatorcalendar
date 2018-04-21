package com.group18.app.calendar;

import android.content.Context;

public class Global {
    private static Global instance;
    private Context mContext;

    //restrict this class from being instantiated unless we allow it (Singleton Class)
    private Global(){}

    public static synchronized Global getInstance(){
        if(instance == null)
            instance = new Global();
        return instance;
    }


    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }
}
