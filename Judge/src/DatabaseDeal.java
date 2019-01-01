import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDeal {
    private static String sql;
    private static PreparedStatement preparedStatement;

    public static void insert(String table,Data data)
    {
        sql=data.insertSql(table);
        try {
            preparedStatement = DatabaseConnect.getStatement(sql);
            data.setInsert(preparedStatement);
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void delete(String table,int id,Data data)
    {
        sql=data.deleteSql(table,id);
        try {
            preparedStatement = DatabaseConnect.getStatement(sql);
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void update(String table,Data data)
    {
        sql=data.updateSql(table);
        try
        {
            preparedStatement=DatabaseConnect.getStatement(sql);
            data.setupdata(preparedStatement);
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /*DataChoiceQuestion data1=new DataChoiceQuestion();
        DatabaseConnect databaseConnect=new DatabaseConnect("test","root","1234");
        databaseConnect.getConnection();
        int id=1;
        String question = "'大煮干丝'是哪个菜系的代表菜之一( )。";
        String choice1 = "A.四川菜系";
        String choice2 = "B.山东菜系";
        String choice3 = "C.广东菜系";
        String choice4 = "D.淮扬菜系";
        String ans ="A";
        for(int i=1;i<100;i++)
        {
            id=i;
            data1.setValue(id,question,choice1,choice2,choice3,choice4,ans);
            DatabaseDeal.insert("question",data1);
        }*/
    }
}
