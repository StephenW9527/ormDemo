package codegym;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import codegym.Helper;

public class DAO {
	
	public void insertEmp(Employees emp){
		Helper helper = new Helper();
		Connection conn = helper.getConn();
		String sql = "insert into employees(employeeid,lastname, firstname, birthdate) values(17,?,?,?)";
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			stmt.setInt(1,emp.getEmployeeid());
			stmt.setString(1, emp.getLastname());
			stmt.setString(2, emp.getFirstname());
			stmt.setDate(3, emp.getBirthdate());
			stmt.execute();
			conn.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			try{
				conn.rollback();
			}catch(SQLException e1){
				e1.printStackTrace();
			}
		}finally{
			helper.closeConn(conn);
		}
	}
}
