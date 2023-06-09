package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static QueryRunner queryRunner = new QueryRunner();

    private SQLHelper(){
    }

//    private static String url = "jdbc:mysql://localhost:3306/app";
//    private static String url = "jdbc:postgresql://localhost:5432/app";

    @SneakyThrows
    private static Connection getConn(){
        var url = System.getProperty("url");
        return DriverManager.getConnection(url, "app", "pass");
    }

    @SneakyThrows
    public static String getPaymentCardStatus() {
        var paymentCardStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var status = queryRunner.query(conn, paymentCardStatus, new ScalarHandler<String>());
        return status;
    }

    @SneakyThrows
    public static String getCreditCardStatus() {
        var creditCardStatus = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var status = queryRunner.query(conn, creditCardStatus, new ScalarHandler<String>());
        return status;
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var conn = getConn();
        queryRunner.execute(conn, "DELETE FROM payment_entity");
        queryRunner.execute(conn, "DELETE FROM order_entity");
        queryRunner.execute(conn, "DELETE FROM credit_request_entity");
    }


}
