package com.ashish.patil.repo;

import com.ashish.patil.enums.INPUT_TYPE;
import com.ashish.patil.model.AnagramValidationResponse;
import com.ashish.patil.model.Error;
import com.ashish.patil.source.DataSource;

import androidx.lifecycle.MutableLiveData;

public class AnagramValidatorRepoImpl implements iAnagramValidatorRepo {

    private final MutableLiveData<AnagramValidationResponse> anagramValidatorObservable = new MutableLiveData<>();
    AnagramValidationResponse anagramValidationResponse = new AnagramValidationResponse();
    @Override
    public void validateAnagramWords(String firstWord,
                                     String secondWord) {

        anagramValidationResponse.clear();

        if (!DataSource.isValidInput(firstWord)) {
            anagramValidationResponse.setError(new Error(INPUT_TYPE.FIRST));
            anagramValidatorObservable.postValue(anagramValidationResponse);
            return;
        }

        if (!DataSource.isValidInput(secondWord)) {
            anagramValidationResponse.setError(new Error(INPUT_TYPE.SECOND));
            anagramValidatorObservable.postValue(anagramValidationResponse);
            return;
        }
        anagramValidationResponse.setAnagram(DataSource.isAnagram(firstWord,secondWord));
        anagramValidatorObservable.postValue(anagramValidationResponse);
    }

    @Override
    public MutableLiveData<AnagramValidationResponse> checkAnagramResponse() {
        return anagramValidatorObservable;
    }
}
