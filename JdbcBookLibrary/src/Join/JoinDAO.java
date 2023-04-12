package Join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JoinDAO {
	public List<CustomerVO> getJoins() throws Exception {
 	 Class.forName("oracle.jdbc.driver.OracleDriver");
 	 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.23:1521:xe","project","java");
 	 Statement statement = connection.createStatement();
 	 String sql = "select m_id, password, name, birth_Date, phone_num, email, address, loans_num from member";
 	 ResultSet resultSet = statement.executeQuery(sql);
 	 List<CustomerVO> list = new ArrayList<>();
 	 while(resultSet.next()) {
			 String id = resultSet.getString("m_id");
			 String name = resultSet.getString("name");
			 String password = resultSet.getString("password");
			 String birthDate = resultSet.getString("birth_Date");
			 String phonenumber = resultSet.getString("phone_num");
			 String email = resultSet.getString("email");
			 String address = resultSet.getString("address");
			 double loansnumber = resultSet.getDouble("loans_num");
			 list.add(new CustomerVO(id, password, name, birthDate, phonenumber, email, address, loansnumber));
		 }
 	 
 	   resultSet.close();
		 statement.close();
		 connection.close();
		 return list;
  }
  
  public CustomerVO getJoin(String serachId) throws Exception {
 	 Class.forName("oracle.jdbc.driver.OracleDriver");
 	 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.23:1521:xe","project","java");
 	 String sql = "select id, password, name, birthDate, phonenumber, email, address, loansnumber from member where id = ?";
 	 PreparedStatement statement = connection.prepareStatement(sql);
 	 statement.setString(1, serachId);
 	 ResultSet resultSet = statement.executeQuery();
 	 CustomerVO vo = null;
 	 
 	 if (resultSet.next()) {
 		 resultSet.getString("id");
 		 String id = resultSet.getString("m_id");
 		 String password = resultSet.getString("password");
 		 String name = resultSet.getString("name");
		 String birthDate = resultSet.getString("birth_Date");
		 String phonenumber = resultSet.getString("phone_num");
		 String email = resultSet.getString("email");
		 String address = resultSet.getString("address");
		 double loansnumber = resultSet.getDouble("loans_num");
 		 
 		 vo = new CustomerVO(id, password);
 	 }
 	 resultSet.close();
 	 statement.close();
 	 connection.close();
		 return vo;
  }
  
  public int insertJoin(CustomerVO vo) throws Exception {
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.23:1521:xe","project","java");
		 String sql = "INSERT INTO join (id, password, name, birthDate, phonenumber, email, address, loansnumber) VALUES (sign_seq.nextval, ?, ?)";
		 try (PreparedStatement statement = connection.prepareStatement(sql)) {;
		 statement.setString(1, vo.getId());
		 statement.setString(2, vo.getName());
		 statement.setString(3, vo.getPassword());
		 statement.setString(4, vo.getBirthDate());
		 statement.setString(5, vo.getPhonenumber());
		 statement.setString(6, vo.getEmail());
		 statement.setString(7, vo.getAddress());
		 statement.setDouble(8, vo.getLoansnumber());
		 int count = statement.executeUpdate();   //완료한 로우의 갯수
		 statement.close();
		 connection.close();
		 return count;
		 
	 } catch (SQLException e) {
     throw new Exception("User registration failed!");
	   }
  }
  public int updateJoin(CustomerVO vo) {
		 return 0;
	 }
	 
	 // delete from memo where id = ?
	 public int deleteJoin(String deleteId) {
		 return 0;
	 }

	public static JoinDAO getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertJoin() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean insertCustomer() {
		// TODO Auto-generated method stub
		
		return false;
	}
}
