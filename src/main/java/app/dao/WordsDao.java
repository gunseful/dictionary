package app.dao;

import entity.Word;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WordsDao {

    private static Logger logger = LogManager.getLogger(WordsDao.class);

    private ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    public List<Word> getAllWords() throws InterruptedException {
        List<Word> list = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM dictionary.words");
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                list.add(parseWord(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Server can't get all words from DB", e);
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
                wordFromDb = parseWord(resultSet);
            }
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }finally {
            connectionPool.returnConnection(connection);
        }
        return wordFromDb;
    }

    public Word getWordByLanguage(String word, String language) throws InterruptedException {
        switch (language){
            case "russian" : return getWord(word, "SELECT * FROM dictionary.words where russian=?");
            case "english" : return getWord(word, "SELECT * FROM dictionary.words where english=?");
            case "spanish" : return getWord(word, "SELECT * FROM dictionary.words where spanish=?");
            case "french" : return getWord(word, "SELECT * FROM dictionary.words where french=?");
            default: return null;
        }
    }

    private Word parseWord(ResultSet resultSet) throws SQLException {
        return new Word(
                resultSet.getInt("id"),
                resultSet.getString("russian"),
                resultSet.getString("spanish"),
                resultSet.getString("english"),
                resultSet.getString("french"));
    }
}
