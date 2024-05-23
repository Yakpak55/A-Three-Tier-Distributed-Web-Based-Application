//Name:Christopher Deluigi
//Course: CNT 4714 – Spring 2024 – Project Four
//Assignment title: A Three-Tier Distributed Web-Based Application
//Date: April 23, 2024

package project4;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/root")
public class Rootservlet extends HttpServlet {

    private Connection connection;
    private Statement statement;

    @Override
    public void init() throws ServletException {
        super.init();
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("C:/Program Files/Apache Software Foundation/Tomcat 10.1/webapps/Project-4/WEB-INF/lib/client.properties"));

            Class.forName(props.getProperty("MYSQL_DB_DRIVER_CLASS"));
            String dbUrl = props.getProperty("MYSQL_DB_URL");
            String username = props.getProperty("MYSQL_DB_USERNAME");
            String password = props.getProperty("MYSQL_DB_PASSWORD");

            connection = DriverManager.getConnection(dbUrl, username, password);
            statement = connection.createStatement();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (connection == null) {
            connectToDatabase(); // Reconnect if not connected
        }

        String sqlStatement = request.getParameter("sqlStatement");
        String message = "";

        try {
            if (sqlStatement != null) {
                if (sqlStatement.toLowerCase().startsWith("select")) {
                    message = doSelect(sqlStatement);
                } else {
                    message = doUpdate(sqlStatement);
                }
            } else {
                message = "<tr bgcolor=#ff0000><td style=\"text-align:center\"><font color=#ffffff><b>Error: SQL statement is null</b></font></td></tr>";
            }
        } catch (SQLException e) {
            message = "<tr bgcolor=#ff0000><td style=\"text-align:center\"><font color=#ffffff><b>Error executing the SQL statement:</b><br>"
                    + e.getMessage() + "</font></td></tr>";
        }

        request.setAttribute("message", message);
        request.setAttribute("sqlStatement", sqlStatement);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/clientHome.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String doSelect(String sqlStatement) throws SQLException {
        int columnCount = 0;
        StringBuilder result = new StringBuilder("<div><div><div><table><thead><tr>");
        String closeTable = "</table></div></div></div>";

        ResultSet resultSet = statement.executeQuery(sqlStatement);
        ResultSetMetaData metaData = resultSet.getMetaData();
        columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            result.append("<th scope='col'>").append(metaData.getColumnName(i)).append("</th>");
        }
        result.append("</tr></thead><tbody>");

        while (resultSet.next()) {
            result.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                if (i != 1) {
                    result.append("<td>").append(resultSet.getString(i)).append("</th>");
                } else {
                    result.append("<td scope='row'>").append(resultSet.getString(i)).append("</th>");
                }
            }
            result.append("</tr>");
        }
        result.append("</tbody>").append(closeTable);

        return result.toString();
    }

    private String doUpdate(String sqlStatement) throws SQLException {
        String result = "<div> The statement executed successfully.</div><div>";
        int rowUpdates = 0;

        rowUpdates = statement.executeUpdate(sqlStatement);
        result += rowUpdates + " row(s) affected</div><div>";

        return result;
    }
}
