package com.ashish.patil.repo;

import com.ashish.patil.model.AnagramValidationResponse;

import androidx.lifecycle.MutableLiveData;

public interface iAnagramValidatorRepo {
   void validateAnagramWords(String firstWord,
                             String secondWord);

   MutableLiveData<AnagramValidationResponse> checkAnagramResponse();
}
