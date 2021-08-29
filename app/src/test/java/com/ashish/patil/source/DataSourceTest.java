package com.ashish.patil.source;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DataSourceTest {

    @Test
    public void blankInputTestValidation() {
        boolean output  =   DataSource.isValidInput("");
        Assert.assertFalse(output);
    }

    @Test
    public void validInputTestValidation() {
        boolean output  =   DataSource.isValidInput("Android");
        Assert.assertTrue(output);
    }

    @Test
    public void testTrueAnagram() {
        boolean isAnagram   =   DataSource.isAnagram("Rat", "tar");
        Assert.assertTrue(isAnagram);
    }

    @Test
    public void testFalseAnagram() {
        boolean isAnagram   =   DataSource.isAnagram("Rat", "Tom");
        Assert.assertFalse(isAnagram);
    }

    @Test
    public void testDifferentLengthStringInputs() {
        boolean isAnagram   =   DataSource.isAnagram("Android","IOS");
        Assert.assertFalse(isAnagram);
    }
}