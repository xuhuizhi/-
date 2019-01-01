import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseInquiry {
    private static String sql;
    private static PreparedStatement preparedStatement;
    private static ResultSet rs;
    public static ArrayList<Data> Inquiry(String table,Data data)
    {
        ArrayList<Data> result = new ArrayList<Data>();
        sql=data.inquirySql(table);
        try {
            preparedStatement = DatabaseConnect.getStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                Data data1=data.Clone();
                data1.getValue(rs);
                result.add(data1);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        /*DataChoiceQuestion data1=new DataChoiceQuestion();
        DatabaseConnect databaseConnect=new DatabaseConnect("test","root","1234");
        databaseConnect.getConnection();
        ArrayList<Data> res= DatabaseInquiry.Inquiry("question",data1);
        for(int i=0;i<res.size();i++)
        {
            data1=(DataChoiceQuestion) res.get(i);
            System.out.println("id:"+data1.id+"  i:"+i);
            System.out.println(data1.question);
            System.out.println(data1.choice1+" "+data1.choice2+" "+data1.choice3+" "+data1.choice4);
            System.out.println("ans:"+data1.ans);
        }*/
    }
}
