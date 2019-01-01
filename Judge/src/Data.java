import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class Data {
    abstract String insertSql(String table);
    abstract String deleteSql(String table,int id);
    abstract String updateSql(String table);
    abstract String inquirySql(String table);
    abstract void setInsert(PreparedStatement preparedStatement) throws SQLException;
    abstract void setupdata(PreparedStatement preparedStatement)throws SQLException;
    abstract void getValue(ResultSet rs) throws SQLException;
    abstract Data Clone() throws CloneNotSupportedException;
}
