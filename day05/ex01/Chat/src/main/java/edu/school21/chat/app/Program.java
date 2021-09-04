package edu.school21.chat.app;

import edu.school21.chat.models.*;
import edu.school21.chat.repositories.DataSource;
import edu.school21.chat.repositories.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Scanner;
public class Program {

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        try {
            DataSource dataSource = new DataSource();
            MessagesRepository mess = new MessagesRepositoryJdbcImpl(dataSource);
            String createSql = null;

            FileInputStream fileInput = new FileInputStream("./src/main/resources/schema.sql");
            FileInputStream fileInputData = new FileInputStream("./src/main/resources/data.sql");
            Scanner sc = new Scanner(fileInput).useDelimiter(";");
            Scanner scData = new Scanner(fileInputData).useDelimiter(";");

            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();

            while (sc.hasNext()) {
                createSql = sc.next().trim();
                createSql += ";";
                stmt.executeUpdate(createSql);
            }

            while (scData.hasNext()) {
                createSql = scData.next().trim();
                createSql += ";";
                stmt.executeUpdate(createSql);
            }
            stmt.close();
            connection.close();

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter message id\n-> ");
            Long id = scanner.nextLong();

            Optional<Message> message = mess.findById(id);
            try {
                System.out.println(message.get().toString());
            } catch (Exception e) {
                System.err.println("null optional returned");
            }
        }
        catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(-1);
        }
    }
}
