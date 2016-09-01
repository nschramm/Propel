package com.propel.services;

import java.io.File;
import java.util.List;

import java.util.regex.Pattern;

/**
 * Created by U0011960 on 8/24/16.
 */
public interface RecordParsingService<T> {

    List<T> parse(File logfile, Pattern pattern);

}
