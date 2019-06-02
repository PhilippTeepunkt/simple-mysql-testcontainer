import java.sql.*;

public class JDBC {

    private String url;
    private String user;
    private String pw;
    private Connection connection;

    public JDBC(String url,String user, String password) throws SQLException //throws ClassNotFoundException{
    {
        this.url = url;
        this.user = user;
        this.pw = password;
        //Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(this.url,this.user,this.pw);
    }

    public ResultSet submitQuery(String query)throws SQLException
    {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        return result;
    }

    public void submitUpdate(String query) throws SQLException
    {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        connection.commit();
    }

    public void switchDatabase(String database) throws SQLException
    {
        connection.setCatalog(database);
    }

    public void executeStatement(String query) throws SQLException
    {
        Statement statement = connection.createStatement();
        statement.execute(query);
        connection.commit();
    }

}
