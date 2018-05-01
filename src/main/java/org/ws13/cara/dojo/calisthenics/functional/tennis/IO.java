package org.ws13.cara.dojo.calisthenics.functional.tennis;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author ctranxuan
 */
public class IO {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static final Consumer<String> PRINT = System.out::println;

    public static final Supplier<String> READ = SCANNER::nextLine;

    public String print(String aStatement) {
        System.out.println(aStatement);
        return aStatement;
    }

    public String read() {
        return SCANNER.nextLine();
    }
}
