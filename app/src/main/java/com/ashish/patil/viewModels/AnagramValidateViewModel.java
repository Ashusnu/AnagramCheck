package com.ashish.patil.viewModels;


import android.app.Application;

import com.ashish.patil.AnagramApplication;
import com.ashish.patil.model.AnagramValidationResponse;
import com.ashish.patil.repo.iAnagramValidatorRepo;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AnagramValidateViewModel extends AndroidViewModel {

    @Inject
    iAnagramValidatorRepo anagramValidatorRepo;

    public AnagramValidateViewModel(@NonNull Application application) {
        super(application);
        AnagramApplication.getDiComponents().inject(this);
    }


    public void validateIfAnagrams(String firstWord,
                                   String  secondWord) {
        anagramValidatorRepo.validateAnagramWords(firstWord,secondWord);
    }

    public LiveData<AnagramValidationResponse> checkAnagramResponse() {
        return anagramValidatorRepo.checkAnagramResponse();
    }
}
