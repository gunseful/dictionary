package app;

import app.dao.WordsDao;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WordsDao wordsDao =  new WordsDao();
        wordsDao.getAllWords().forEach(System.out::println);

    }

}
