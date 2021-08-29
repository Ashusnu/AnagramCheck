package com.ashish.patil.di;

import com.ashish.patil.viewModels.AnagramValidateViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = DIModule.class)
public interface AppComponents {
    void inject(AnagramValidateViewModel anagramValidateViewModel);
}
