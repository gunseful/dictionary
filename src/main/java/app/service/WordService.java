package app.service;

import app.dao.WordsDao;
import entity.Word;

public class WordService {
    public String translateWord(String firstLanguage, String secondLanguage, String word) {
        WordsDao wordsDao = new WordsDao();
        Word wordFromDb = null;
        try {
            wordFromDb = wordsDao.getWordByLanguage(word, firstLanguage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(wordFromDb==null){
            return null;
        }else {
            switch (secondLanguage) {
                case "russian":
                    return wordFromDb.getRussian();
                case "english":
                    return wordFromDb.getEnglish();
                case "spanish":
                    return wordFromDb.getSpanish();
                case "french":
                    return wordFromDb.getFrench();
                default:return null;
            }
        }
    }
}
