package com.parkgo.parkgorithm.background.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lony on 7/28/2016.
 */
public class RegexName implements Regex {
    private static final Pattern NAME_VALIDATION = Pattern.compile("^[\\p{L} .'-]+$");
    /**
     * Determines if string passed matches specifications
     * provided by regex stored
     * @param _String [in] string to compare
     * @return true iff matches else false.
     */
    @Override
    public boolean matches(String _String){
        Matcher matcher = NAME_VALIDATION.matcher(_String);
        return (matcher.matches());
    }
}
