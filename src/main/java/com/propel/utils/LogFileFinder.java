package com.propel.utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Created by U0011960 on 8/22/16.
 */
public class LogFileFinder extends SimpleFileVisitor<Path> {

    protected final PathMatcher matcher;
    private List<Path> matchedPaths = new ArrayList<>();

    public LogFileFinder(String pattern) {
        matcher = FileSystems.getDefault()
                .getPathMatcher("glob:" + pattern);
    }

    // Compares the glob pattern against
    // the file or directory name.
    private void match(Path file) {
        if (file != null && matcher.matches(file)) {
            matchedPaths.add(file);
        }
    }

    // Invoke the pattern matching
    // method on each file.
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        match(file);
        return CONTINUE;
    }

    // Invoke the pattern matching
    // method on each directory.
    @Override
    public FileVisitResult preVisitDirectory(Path dir,
                                             BasicFileAttributes attrs) {
        match(dir);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return CONTINUE;
    }

    public Collection<Path> getMatchedPaths() {
        return matchedPaths;
    }
}
