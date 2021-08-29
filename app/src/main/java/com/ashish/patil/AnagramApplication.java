package com.ashish.patil;

import android.app.Application;

import com.ashish.patil.di.AppComponents;
import com.ashish.patil.di.DIModule;
import com.ashish.patil.di.DaggerAppComponents;

public class AnagramApplication extends Application {

    private static AppComponents appComponents;
    DIModule diModule;

    @Override
    public void onCreate() {
        super.onCreate();
        diModule  =   new DIModule();

        appComponents = DaggerAppComponents.builder()
                .dIModule(diModule)
                .build();
    }

    public static AppComponents getDiComponents(){
        return appComponents;
    };
}
