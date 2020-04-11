import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class Main {

//    public static void main(String[] args) {
//        try{
//            String url = "jdbc:postgresql://localhost/store";
//            String username = "admin";
//            String password = "admin";
//            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
//            try (Connection conn = DriverManager.getConnection(url, username, password)){
//
//                System.out.println("Connection to Store DB successful!");
//            }
//        }
//        catch(Exception ex){
//            System.out.println("Connection failed...");
//
//            System.out.println(ex);
//        }
//    }

    public static void main(String[] args) {
//        try {
//            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
//            try (Connection conn = getConnection()) {
//
//                String sqlCommand = "CREATE TABLE items (id SERIAL PRIMARY KEY , name VARCHAR(20), volume INT, price INT)";
//
//
//                Statement statement = conn.createStatement();
//
//                statement.executeUpdate(sqlCommand);
////                int rows = statement.executeUpdate("INSERT INTO items" +
////                        "(name, volume, price) VALUES ('Whiskey Hooiskey', 700, 700)," +
////                        "('Vodka Hooyodka', 500, 100), ('Moloko Huyoloko', 1000, 30)");
////                System.out.printf("Added %d rows", rows);
////                int rows = statement.executeUpdate("UPDATE items SET price = price * 1.25");
////                System.out.printf("Updated %d rows", rows);
////                System.out.println("Table has been created!");
////                int rows = statement.executeUpdate("DELETE FROM items WHERE id = 3");
////                System.out.printf("%d row(s) deleted", rows);
////                ResultSet resultSet = statement.executeQuery("SELECT * FROM items");
////                while(resultSet.next()){
////
////                    int id = resultSet.getInt(1);
////                    String name = resultSet.getString(2);
////                    int volume = resultSet.getInt(3);
////                    int price = resultSet.getInt(4);
////                    System.out.printf("%d. %s - %d ml, %d UAH\n", id, name, volume, price);
////                }
////                while(resultSet.next()){
////
////                    int id = resultSet.getInt("id");
////                    String name = resultSet.getString("name");
////                    int volume = resultSet.getInt("volume");
////                    int price = resultSet.getInt("price");
////
////                    System.out.printf("%d. %s - %d ml, %d UAH\n", id, name, volume, price);
////                }
////                String sql = "INSERT INTO items (name, volume, price) VALUES (?, ?, ?)";
////
////                PreparedStatement preparedStatement = conn.prepareStatement(sql);
////                preparedStatement.setString(1, "Pivo Hooivo");
////                preparedStatement.setInt(2, 500);
////                preparedStatement.setInt(3, 50);
////
////                int rows = preparedStatement.executeUpdate();
////
////                System.out.printf("%d rows added", rows);
//
//            }
//            System.out.println("\r\n Store DB operation successful!");
//
//        } catch (Exception ex) {
//
//            System.out.println("\r\n Store Db operation failed ...");
//
//            System.out.println(ex);
//        }

//        Connection conn = null;
//        try {
//            conn = getConnection();
//            conn.setAutoCommit(false);
//            String sql = "INSERT INTO items (name, volume, price) VALUES (?, ?, ?)";
//
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setString(1, "Vine Hooyaine");
//            preparedStatement.setInt(2, 750);
//            preparedStatement.setInt(3, 150);
//
//            int rows = preparedStatement.executeUpdate();
//            Statement statement = conn.createStatement();
//
//            System.out.printf("%d rows added", rows);
//            statement.executeUpdate("INSERT INTO items (id, name, volume, price) VALUES (1,  'Pivo Hooivo', 500, 50)");
//            conn.commit();
//        } catch (SQLException e) {
//            if (conn != null) {
//                try {
//                    System.out.println("Transaction is being rolled back");
//                    conn.rollback();
//                } catch(SQLException excep) {
//                    System.out.println("Transaction is not being rolled back");
//                }
//            }
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try (Connection conn = getConnection()){

            conn.setAutoCommit(false);

            try (PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO items (name, volume, price) VALUES (?, ?, ?)");) {

                // Insert 1st record
                insertStmt.setString(1, "Zhivchik Hooivchick");
                insertStmt.setInt(2, 2000);
                insertStmt.setInt(3, 20);

                insertStmt.executeUpdate();

                // Insert 2st record
                insertStmt.setString(1, "Cola Hooyola");
                insertStmt.setInt(2, 1500);
                insertStmt.setInt(3, 30);

                insertStmt.executeUpdate();

                // Insert 3st record
                insertStmt.setString(1, "Juice Hooyuce");
                insertStmt.setInt(2, 1000);
                insertStmt.setInt(3, 35);

                insertStmt.executeUpdate();

                // Create Savepoint
                Savepoint savepoint = conn.setSavepoint();

                // Insert 4st record
                insertStmt.setString(1, "Gin Hooine");
                insertStmt.setInt(2, 1000);
                insertStmt.setInt(3, 300);

                insertStmt.executeUpdate();

                // Insert 5st record
                insertStmt.setString(1, "Tequila Hooila");
                insertStmt.setInt(2, 500);
                insertStmt.setInt(3, 300);

                insertStmt.executeUpdate();

                // Rollback to savepoint
                conn.rollback(savepoint);

                // Commit statement
                conn.commit();

                System.out.println("Transaction is commited successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                if (conn != null) {
                    try {
                        // Roll back transaction
                        System.out.println("Transaction is being rolled back.");
                        conn.rollback();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException, IOException {

        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("jdbc-app/src/database.properties"))){
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }

}
