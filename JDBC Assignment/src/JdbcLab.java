import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class JdbcLab {
    public static void main(String[] args) throws Exception {
        if (args.length == 5 && args[4].equals("query1")) {
            query1(args[0], args[1], args[2], args[3]);
        } else if (args.length == 6 && args[4].equals("query2")) {
            query2(args[0], args[1], args[2], args[3], Integer.parseInt(args[5]));
        } else if (args.length == 9 && args[4].equals("dml1")) {
            dml1(args[0], args[1], args[2], args[3]);
        } else {
            System.out.println("Invalid or insufficient arguments passed!");
        }
    }

    public static void query1(String url, String user, String pswd, String driver) {
        ResultSet rs = null;
        Statement statement = null;
        Connection connection = null;

        String sql = "SELECT EMPNO, ENAME, DNAME FROM DEPT d, EMP e WHERE d.DEPTNO = e.DEPTNO";

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pswd);
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

            System.out.printf("%-5s | %-10s | %-10s\n", "EMPNO",  "ENAME", "DNAME");
            while(rs.next()) {
                System.out.printf("%-5d | %-10s | %-10s\n", rs.getInt("EMPNO"),  rs.getString("ENAME"), rs.getString("DNAME"));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found.\n" + e);
        } catch (SQLException e) {
            System.out.println("Error making connection, SQL statement, or executing SQL query.\n" + e);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                System.err.println("Error closing resources.");
            }
        }
    }

    public static void query2(String url, String user, String pswd, String driver, int deptNo) {

    }

    public static void dml1(String url, String user, String pswd, String driver) {

    }
}
