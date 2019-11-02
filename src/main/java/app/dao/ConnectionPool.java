package app.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    public static ConnectionPool connectionPool;

    private BlockingQueue<Connection> blockingQueue = new LinkedBlockingQueue<>();

    public static ConnectionPool getConnectionPool(){
        if(connectionPool == null){
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    private ConnectionPool() {
        for (int i = 0; i < 5; i++) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Properties properties = new Properties();
                FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Ares\\IdeaProjects\\dictionary\\src\\main\\resources\\connection.properties");
                properties.load(fileInputStream);
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fantasticfour?allowPublicKeyRetrieval=true&serverTimezone=Europe/Moscow&useSSL=false", properties);
                blockingQueue.put(connection);
            } catch (InterruptedException | ClassNotFoundException | SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() throws InterruptedException {
        return blockingQueue.take();
    }

    void returnConnection(Connection connection) throws InterruptedException {
        blockingQueue.put(connection);
    }
}
