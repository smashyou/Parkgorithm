package com.parkgo.parkgorithm.background.regex;

/**
 * Created by Lony on 7/28/2016.
 */
public interface Regex {

    /**
     * Determines if string passed matches specifications
     * provided by regex stored
     * @param _String [in] string to compare
     * @return true iff matches else false.
     */
    boolean matches(String _String);
}
