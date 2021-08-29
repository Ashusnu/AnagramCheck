package com.ashish.patil.di;

import com.ashish.patil.repo.AnagramValidatorRepoImpl;
import com.ashish.patil.repo.iAnagramValidatorRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DIModule {

    @Singleton
    @Provides
    public iAnagramValidatorRepo provideAnagramValidatorRepoImpl() {
        return new AnagramValidatorRepoImpl();
    }
}
