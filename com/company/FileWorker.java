package com.company;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileWorker {
    public static Optional<File> choiceFile(String title){
        JFileChooser chooser = new JFileChooser();
        int ret = chooser.showDialog(null, title);
        if (ret == JFileChooser.APPROVE_OPTION)
             return Optional.of(chooser.getSelectedFile());
        return Optional.empty();
    }

    public static List<String> readFile(Path path) throws IOException {
        return Files.lines(path).collect(Collectors.toList());
    }

    public static void writeLinesToFile(List<String>edges, Path path) throws IOException {
        path.toFile().createNewFile();
        String collect = String.join("\n", edges);
        Files.write(path,collect.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }

}
