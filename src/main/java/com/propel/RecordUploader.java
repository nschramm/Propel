package com.propel;

import com.propel.services.RecordLoaderService;
import com.propel.utils.LogFileUtils;
import org.apache.commons.cli.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.List;

/**
 * Created by U0011960 on 8/22/16.
 */
//@SpringBootApplication
public class RecordUploader implements CommandLineRunner {

    private static final Log logger = LogFactory.getLog(RecordUploader.class);

    @Autowired
    @Qualifier(value = "browserRecordLoader")
    RecordLoaderService browserService;

    @Autowired
    @Qualifier(value = "userActionRecordLoader")
    RecordLoaderService userActionService;

    public RecordUploader() {}

    public void run(String... args) throws Exception {

        Options cliOptions = this.createCommandLineOptions();
        CommandLine commandLineOptions = this.createCommandLineOptions(cliOptions, args);

        boolean isShowHelp = commandLineOptions.hasOption("h");
        if (isShowHelp) {

            showApplicationHelp(cliOptions);
            return;
        }

        logger.info("starting application.....");

        List<File> logfiles = LogFileUtils.get(commandLineOptions.getOptionValue("p"));

        String commandLoggerExecution = commandLineOptions.getOptionValue("l");
        switch (commandLoggerExecution) {

            case "useraction" :
                userActionService.process(commandLineOptions, logfiles);
                break;

            case "browser" :
                browserService.process(commandLineOptions, logfiles);
                break;

            default:
                showApplicationHelp(cliOptions);
        }

        logger.info("end application.....");

    }

    private void showApplicationHelp(Options cliOptions) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "application", cliOptions );
    }

    private Options createCommandLineOptions() {
        Options options = new Options();
        Option help = Option.builder("h")
                .argName("help")
                .desc("show help")
                .build();
        Option pattern = Option.builder("l")
                .argName("browser|useraction|session")
                .required()
                .hasArg()
                .desc("pattern of log file(s) to process")
                .build();
        Option logfile = Option.builder("p")
                .argName("file|dir")
                .required()
                .hasArg()
                .desc("log file(s) path")
                .build();


        options.addOption(help);
        options.addOption(pattern);
        options.addOption(logfile);
        return options;
    }

    private CommandLine createCommandLineOptions(Options cliOptions, String... args) {

        // create the parser
        CommandLineParser parser = new DefaultParser();
        CommandLine line = null;
        try {
            // parse the command line arguments
            line = parser.parse( cliOptions, args );
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
            System.exit(1);
        }
        return line;
    }

/*
    public static void main(String[] args) throws Exception {
        SpringApplication.run(RecordUploader.class, args);
    }
*/


}
