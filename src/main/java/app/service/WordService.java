package app.service;

import app.dao.WordsDao;
import entity.Word;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WordService {

    private final WordsDao wordsDao;

    private static Logger logger = LogManager.getLogger(WordsDao.class);

    public WordService(ApplicationContext applicationContext) {
        this.wordsDao = applicationContext.getBean(WordsDao.class);
    }

    public void delete(int id){
        try {
            wordsDao.delete(id);
        } catch (InterruptedException e) {
            logger.error("can't delete word", e);
        }
    }


    public List<Word> getAllWords(){
        try {
            return wordsDao.getAllWords();
        } catch (InterruptedException e) {
            logger.error("can't get all words", e);
            return Collections.emptyList();
        }
    }

    public void addNewWord(Word word){
        try {
            wordsDao.addNewWord(word);
        } catch (InterruptedException e) {
            logger.error("can't add new word", e);
        }
    }

    public String translateWord(String firstLanguage, String secondLanguage, String word) {
        Word wordFromDb = null;
        try {
            wordFromDb = wordsDao.getWordByLanguage(word, firstLanguage);
        } catch (InterruptedException e) {
            logger.error("can't get word by lang", e);
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
