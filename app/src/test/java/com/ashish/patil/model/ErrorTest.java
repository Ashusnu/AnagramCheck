package com.ashish.patil.model;

import com.ashish.patil.enums.INPUT_TYPE;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ErrorTest {

    @Test
    public void testErrorWithFirstInputType() {
        Error error = new Error(INPUT_TYPE.FIRST);
        Assert.assertEquals(INPUT_TYPE.FIRST, error.inputType);
    }

    @Test
    public void testErrorWithSecondInputType() {
        Error error = new Error(INPUT_TYPE.SECOND);
        Assert.assertEquals(INPUT_TYPE.SECOND, error.inputType);
    }

    @Test
    public void testErrorEquality() {
        Error error1 = new Error(INPUT_TYPE.FIRST);
        Error error2 = new Error(INPUT_TYPE.FIRST);
        Assert.assertEquals(error1, error2);
    }

    @Test
    public void testErrorInequality() {
        Error error1 = new Error(INPUT_TYPE.FIRST);
        Error error2 = new Error(INPUT_TYPE.SECOND);
        Assert.assertNotEquals(error1, error2);
    }
}