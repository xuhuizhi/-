import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataChoiceQuestion extends Data implements Cloneable {
    //题号，问题，选项1~4，答案
    int id;
    String question;
    String choice1,choice2,choice3,choice4;
    String ans;
    public String insertSql(String table)
    {
        String sql="insert into "+table+" values(?,?,?,?,?,?,?)";
        //System.out.println(sql);
        return sql;
    }
    public String deleteSql(String table,int id)
    {
        String sql="delete from "+table+" where id ="+id;
        //System.out.println(sql);
        return sql;
    }
    public String updateSql(String table)
    {
        String sql="update "+table+" set question= ? ,choice1 = ? ,choice2 = ? ,choice3 = ? ,choice4 = ? ,ans = ? where id= ?";
        //System.out.println(sql);
        return sql;
    }
    public String inquirySql(String table)
    {
        String sql="select * from "+table;
        return sql;
    }
    public void setInsert(PreparedStatement preparedStatement)throws SQLException
    {
        preparedStatement.setInt(1,this.id);
        preparedStatement.setString(2,this.question);
        preparedStatement.setString(3,this.choice1);
        preparedStatement.setString(4,this.choice2);
        preparedStatement.setString(5,this.choice3);
        preparedStatement.setString(6,this.choice4);
        preparedStatement.setString(7,this.ans);
    }

    public void setupdata(PreparedStatement preparedStatement)throws SQLException
    {
        preparedStatement.setString(1,this.question);
        preparedStatement.setString(2,this.choice1);
        preparedStatement.setString(3,this.choice2);
        preparedStatement.setString(4,this.choice3);
        preparedStatement.setString(5,this.choice4);
        preparedStatement.setString(6,this.ans);
        preparedStatement.setInt(7,this.id);
    }
    public void getValue(ResultSet rs) throws SQLException
    {
        this.id=rs.getInt("id");
        this.question=rs.getString("question");
        this.choice1=rs.getString("choice1");
        this.choice2=rs.getString("choice2");
        this.choice3=rs.getString("choice3");
        this.choice4=rs.getString("choice4");
        this.ans=rs.getString("ans");
    }
    public void setValue(int id,String question,String choice1,String choice2,String choice3,String choice4,String ans)
    {
        this.id=id;
        this.question=question;
        this.choice1=choice1;
        this.choice2=choice2;
        this.choice3=choice3;
        this.choice4=choice4;
        this.ans=ans;
    }
    public Data Clone() throws CloneNotSupportedException
    {
        return (Data) super.clone();
    }
}
