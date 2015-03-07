package com.jayseeofficial.agecalculator;

import android.util.Log;

/**
 * Created by jon on 07/03/15.
 */
public class XKCDAgeCalculator {

    /**
     * Calculate the minimum age of a person you can date, as per https://xkcd.com/314/
     *
     * @param age Your age
     * @return THe minimum age you can date
     */
    public static int getMinimumAge(int age) {
        Log.d(XKCDAgeCalculator.class.getSimpleName(), "Calculating minimum age for " + age + ", which is " + ((age / 2) + 7));
        return (age / 2) + 7;
    }

    /**
     * Loop through getting the minimum age of everyone older until we get someone who's minimum age
     * is our's
     *
     * @param age Your age
     * @return The max age you can date
     */
    public static int getMaximumAge(int age) {
        return (age - 7) * 2;
    }

}
