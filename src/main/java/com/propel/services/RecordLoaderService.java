package com.propel.services;

import org.apache.commons.cli.CommandLine;

import java.io.File;
import java.util.List;

/**
 * Created by U0011960 on 8/24/16.
 */
public interface RecordLoaderService {

    void process(CommandLine commandLineOptions, List<File> logfiles);

}
