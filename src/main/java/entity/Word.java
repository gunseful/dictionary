package entity;

import java.util.Objects;

public class Word {
    private int id;
    private String russian;
    private String spanish;
    private String english;
    private String french;

    public Word(int id, String russian, String spanish, String english, String french) {
        this.id = id;
        this.russian = russian;
        this.spanish = spanish;
        this.english = english;
        this.french = french;
    }

    public Word(String russian, String spanish, String english, String french) {
        this.russian = russian;
        this.spanish = spanish;
        this.english = english;
        this.french = french;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public String getSpanish() {
        return spanish;
    }

    public void setSpanish(String spanish) {
        this.spanish = spanish;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getFrench() {
        return french;
    }

    public void setFrench(String french) {
        this.french = french;
    }

    @Override
    public String toString() {
        return id+" "+russian+" - "+spanish+" - "+english+" - "+french;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return id == word.id &&
                Objects.equals(russian, word.russian) &&
                Objects.equals(spanish, word.spanish) &&
                Objects.equals(english, word.english) &&
                Objects.equals(french, word.french);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, russian, spanish, english, french);
    }
}
