package com.parkgo.parkgorithm.background.factory;

import com.parkgo.parkgorithm.background.regex.Regex;

/**
 * Created by Lony on 7/28/2016.
 */
public interface RegexFactory {

    /**
     * Create new Instance of
     * @return
     */
    Regex newInstance();
}
