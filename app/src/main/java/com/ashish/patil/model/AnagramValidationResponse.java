package com.ashish.patil.model;

public class AnagramValidationResponse {
    Error error = null;
    boolean isAnagram = true;


    public AnagramValidationResponse() {

    }

    public Error getError() {
        return error;
    }

    public boolean isAnagram() {
        return isAnagram;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public void setAnagram(boolean anagram) {
        isAnagram = anagram;
    }

    public void clear() {
        error = null;
    }
}
