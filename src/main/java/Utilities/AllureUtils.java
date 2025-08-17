package Utilities;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class AllureUtils {

    private final static String ALLURE_HISTORY_FILE = "test-outputs/target/allure-results";

    public static void clearHistory() {

        FileUtils.deleteQuietly(new File(ALLURE_HISTORY_FILE));
    }

}




