package Model.communicator;

import java.io.*;
import java.util.Properties;

public class ConfigReader {
    private final static String CONFIG_PATH = "resources/config.properties";

    public static int WORKER_NUM;
    public static int WORKER_BATCH_NUM;

    public static String CORPUS_DIR_PATH;
    public static String POSTING_DIR_PATH;

    public static String BATCH_PRE_POSTING_DIR_PATH;
    public static String FINAL_POSTING_FILE_PATH;
    public static String INVERTED_DICTIONARY_FILE_PATH;
    public static String STOP_WORDS_FILE_PATH;
    public static boolean STEMMING = false;

    public static void loadConfiguration(){
        try{
            Properties properties = new Properties();
            FileInputStream inputStream = new FileInputStream(CONFIG_PATH);
            properties.load(inputStream);

            WORKER_NUM = Integer.parseInt(properties.getProperty("WORKER_NUM"));
            WORKER_BATCH_NUM = Integer.parseInt(properties.getProperty("WORKER_BATCH_NUM"));

            // temp directory for batch pre-posting files
            BATCH_PRE_POSTING_DIR_PATH = POSTING_DIR_PATH +"\\temp";

            if (STEMMING){
                // final posting file path
                FINAL_POSTING_FILE_PATH = POSTING_DIR_PATH + "\\stemming_posting.txt";
                // inverted dictionary file path
                INVERTED_DICTIONARY_FILE_PATH = POSTING_DIR_PATH + "\\stemming_dictionary.txt";
            }
            else{
                // final posting file path
                FINAL_POSTING_FILE_PATH = POSTING_DIR_PATH + "\\no_stemming_posting.txt";
                // inverted dictionary file path
                INVERTED_DICTIONARY_FILE_PATH = POSTING_DIR_PATH + "\\no_stemming_dictionary.txt";
            }

            // stop words file path
            STOP_WORDS_FILE_PATH = CORPUS_DIR_PATH + "\\05 stop_words.txt";

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateCorpusPath(String newCorpusPath){
        CORPUS_DIR_PATH = newCorpusPath;
    }
    public static void updatePostingPath(String newPostingPath){
        POSTING_DIR_PATH = newPostingPath;
    }
    public static void setStemming(boolean stemming){
        STEMMING = stemming;
    }
}