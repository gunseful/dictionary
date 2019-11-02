package app.dao;

import entity.Word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordsDao {

    private ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    public List<Word> getAllWords() throws InterruptedException {
        List<Word> list = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM dictionary.words");
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                list.add(new Word(
                        resultSet.getInt("id"),
                        resultSet.getString("russian"),
                        resultSet.getString("spanish"),
                        resultSet.getString("english"),
                        resultSet.getString("french")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionPool.returnConnection(connection);
        }
        return list;
    }

    public void addNewWord(Word word) throws InterruptedException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO dictionary.words (russian, english, spanish, french) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, word.getRussian());
        preparedStatement.setString(2, word.getEnglish());
        preparedStatement.setString(3, word.getSpanish());
        preparedStatement.setString(4, word.getFrench());
        preparedStatement.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }finally {
            connectionPool.returnConnection(connection);
        }

    }

    public void delete(int id) throws InterruptedException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("Delete from dictionary.words where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    public Word getWord(String word, String sql) throws InterruptedException {
        Connection connection = null;
        Word wordFromDb = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, word);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                wordFromDb = new Word(
                        resultSet.getInt("id"),
                        resultSet.getString("russian"),
                        resultSet.getString("spanish"),
                        resultSet.getString("english"),
                        resultSet.getString("french"));
            }
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }finally {
            connectionPool.returnConnection(connection);
        }
        return wordFromDb;
    }

    public Word getRussian(String word) throws InterruptedException {
        return getWord(word, "SELECT * FROM dictionary.words where russian=?");
    }

    public Word getEnglish(String word) throws InterruptedException {
        return getWord(word, "SELECT * FROM dictionary.words where english=?");
    }

    public Word getSpanish(String word) throws InterruptedException {
        return getWord(word, "SELECT * FROM dictionary.words where spanish=?");
    }

    public Word getFrench(String word) throws InterruptedException {
        return getWord(word, "SELECT * FROM dictionary.words where french=?");
    }

    public Word getWordByLanguage(String word, String language) throws InterruptedException {
        switch (language){
            case "russian" : return getRussian(word);
            case "english" : return getEnglish(word);
            case "spanish" : return getSpanish(word);
            case "french" : return getFrench(word);
            default: return null;
        }
    }
}
