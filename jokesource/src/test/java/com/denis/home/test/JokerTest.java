package com.denis.home.test;

import com.denis.home.JokeSource;

import org.junit.Test;

/**
 * Created by Denis on 30.01.2016.
 */
public class JokerTest {
    @Test
    public void testGetJoke() throws Exception {
        JokeSource joker = new JokeSource();
        assert joker.getJoke().length() != 0;
    }
}