import java.sql.*;

public class DatabaseConnect {
    private static String url;//数据库服务地址
    private static String driver = "com.mysql.cj.jdbc.Driver";//驱动路径
    private static String username;
    private static String passworld;
    static Connection connection=null;
    public void getConnection()
    {
        try{
            Class.forName(driver).newInstance();
            connection= DriverManager.getConnection(url,username,passworld);
            System.out.println("数据库连接成功");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    DatabaseConnect(String database,String username,String passworld)
    {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("jdbc:mysql://localhost:3306/");
        stringBuilder.append(database);
        stringBuilder.append("?useSSL=false&serverTimezone=UTC");
        this.url=stringBuilder.toString();
        this.username=username;
        this.passworld=passworld;
    }
    public static PreparedStatement getStatement(String sql)throws SQLException
    {
        return connection.prepareStatement(sql);
    }

    public static void main(String[] args) {
        DatabaseConnect databaseConnect=new DatabaseConnect("test","root","1234");
        databaseConnect.getConnection();

    }
}
