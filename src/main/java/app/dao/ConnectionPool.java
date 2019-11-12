package app.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class ConnectionPool {

    private static Logger logger = LogManager.getLogger(ConnectionPool.class);

    private static final String DB_URL = "jdbc:mysql://localhost:3306/fantasticfour?allowPublicKeyRetrieval=true&serverTimezone=Europe/Moscow&useSSL=false";
    private static final String DB_LOGIN = "root";
    private static final String DB_PASSWORD = "ce97a50f";

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
                Connection connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
                blockingQueue.put(connection);
                logger.info("Start new connection");
                logger.info("Count of free connections = {}", blockingQueue.size());

            } catch (InterruptedException | ClassNotFoundException | SQLException e) {
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
