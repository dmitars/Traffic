package com.company;

import com.ibm.icu.text.Transliterator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CyrillicReplacer {
    public static void replace(String[] args) throws IOException {
        var lines = Files.lines(Path.of(args[1]));

        String collect = deleteCyrillic(lines).collect(Collectors.joining("\n"));

        FileWorker.writeLinesToFile(List.of(collect), Path.of(args[2]));
        System.out.println("Replacing done");
    }

    private static Stream<String> deleteCyrillic(Stream<String> lines) {
        String CYRILLIC_TO_LATIN = "Cyrillic-Latin";
        Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        return lines.map(toLatinTrans::transliterate);
    }

}
