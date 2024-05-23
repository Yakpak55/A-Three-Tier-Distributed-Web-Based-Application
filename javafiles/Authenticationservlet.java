//Name:Christopher Deluigi
//Course: CNT 4714 – Spring 2024 – Project Four
//Assignment title: A Three-Tier Distributed Web-Based Application
//Date: April 23, 2024

package project4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet("/authenticate")
public class AuthenticationServlet extends HttpServlet {

    private Connection connection;

    @Override
    public void init() throws ServletException {
        super.init();
        connectToDatabase(); // Establish database connection immediately after servlet initialization
    }

    private void connectToDatabase() {
        try {
            // Load database connection details from properties file
            Properties props = new Properties();
            props.load(new FileInputStream("C:/Program Files/Apache Software Foundation/Tomcat 10.1/webapps/Project-4/WEB-INF/lib/systemapp.properties"));

            // Load JDBC driver
            Class.forName(props.getProperty("MYSQL_DB_DRIVER_CLASS"));

            // Establish connection to the database
            connection = DriverManager.getConnection(
                props.getProperty("MYSQL_DB_URL"),
                props.getProperty("MYSQL_DB_USERNAME"),
                props.getProperty("MYSQL_DB_PASSWORD")
            );
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database connection error: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authenticateUser(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);  // Store user info in session
            redirectUserBasedOnRole(username, response);
        } else {
            response.sendRedirect("error.html"); // Redirect to error page if login fails
        }
    }

    private boolean authenticateUser(String username, String password) throws ServletException {
        try (PreparedStatement statement = connection.prepareStatement(
            "SELECT * FROM usercredentials WHERE login_username = ? AND login_password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();  // Check if credentials match any user entry in the database
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Authentication query failed.", e);
        }
    }

    private void redirectUserBasedOnRole(String username, HttpServletResponse response) throws IOException {
        // Dummy example - adjust logic according to actual roles in your database
        String target = "generalUserHome.jsp";  // default redirection
        if ("admin".equals(username)) {
            target = "adminHome.jsp";
        } else if ("client".equals(username)) {
            target = "clientHome.jsp";
        }
        response.sendRedirect(target); // Redirect based on user role
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            if (connection != null) {
                connection.close();  // Close database connection when servlet is destroyed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
