package com.ashish.patil.model;

import com.ashish.patil.enums.INPUT_TYPE;

public class Error {
    INPUT_TYPE input_type;

    public Error(INPUT_TYPE input_type) {
        this.input_type = input_type;
    }
    public INPUT_TYPE getInput_type() {
        return input_type;
    }

}
