package app.dao;

import entity.Word;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WordsDaoTest {

    List<Word> db = new ArrayList<>();

    Word word = new Word(1, "слово", "la palabra", "word", "le mot");
    WordsDao wordsDao = new WordsDao();


    @Test
    public void getAllWords() throws InterruptedException {
        db.add(new Word(1, "слово", "la palabra", "word", "le mot"));
        db.add(new Word(7, "&#1087;&#1086;&#1073;&#1077;&#1076;&#1072;", "victoria", "victory", "la victoire"));


        List<Word> list = wordsDao.getAllWords();

        Assert.assertEquals(list, db);
    }

    @Test
    public void getWord() throws InterruptedException {

        Word wordFromDb = wordsDao.getAllWords()
                .stream()
                .filter(w -> w.getEnglish().equals("word")).findFirst().orElseThrow();

        Assert.assertEquals(wordFromDb, word);

    }
}
