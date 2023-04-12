package Join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class JoinDAO {
	public List<CustomerVO> getJoins() throws Exception {
 	 Class.forName("oracle.jdbc.driver.OracleDriver");
 	 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","library","java");
 	 Statement statement = connection.createStatement();
 	 String sql = "select m_id, name, birth_Date, phone_num, email, address, loans_num from member";
 	 ResultSet resultSet = statement.executeQuery(sql);
 	 List<CustomerVO> list = new ArrayList<>();
 	 while(resultSet.next()) {
			 String id = resultSet.getString("m_id");
			 String name = resultSet.getString("name");
			 Date birthDate = resultSet.getDate("birth_Date");
			 String phonenumber = resultSet.getString("phone_num");
			 String email = resultSet.getString("email");
			 String address = resultSet.getString("address");
			 double loansnumber = resultSet.getDouble("loans_num");
			 list.add(new CustomerVO(id, name, birthDate, phonenumber, email, address, loansnumber));
		 }
 	 
 	   resultSet.close();
		 statement.close();
		 connection.close();
		 return list;
  }
  
  public CustomerVO getJoin(String serachId) throws Exception {
 	 Class.forName("oracle.jdbc.driver.OracleDriver");
 	 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","library","java");
 	 String sql = "select id, password, name, birthDate, phonenumber, email, address, loansnumber from join where id = ?";
 	 PreparedStatement statement = connection.prepareStatement(sql);
 	 statement.setString(1, serachId);
 	 ResultSet resultSet = statement.executeQuery();
 	 CustomerVO vo = null;
 	 
 	 if (resultSet.next()) {
 		 resultSet.getString("id");
 		 String id = resultSet.getString("m_id");
 		 String name = resultSet.getString("name");
		 Date birthDate = resultSet.getDate("birth_Date");
		 String phonenumber = resultSet.getString("phone_num");
		 String email = resultSet.getString("email");
		 String address = resultSet.getString("address");
		 double loansnumber = resultSet.getDouble("loans_num");
 		 
 		 vo = new CustomerVO(id, address);
 	 }
 	 resultSet.close();
 	 statement.close();
 	 connection.close();
		 return vo;
  }
  
  public int insertJoin(CustomerVO vo) throws Exception {
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","library","java");
		 String sql = "INSERT INTO join (id, password, name, birthDate, phonenumber, email, address, loansnumber) VALUES (sign_seq.nextval, ?, ?)";
		 PreparedStatement statement = connection.prepareStatement(sql);
		 statement.setString(1, vo.getId());
		 statement.setString(2, vo.getName());
		 statement.setDate(3, vo.getBirthDate());
		 statement.setString(4, vo.getPhonenumber());
		 statement.setString(5, vo.getEmail());
		 statement.setString(6, vo.getAddress());
		 statement.setDouble(7, vo.getLoansnumber());
		 int count = statement.executeUpdate();   //완료한 로우의 갯수
		 statement.close();
		 connection.close();
		 return 0;
	 }
  
  public int updateJoin(CustomerVO vo) {
		 return 0;
	 }
	 
	 // delete from memo where id = ?
	 public int deleteJoin(String deleteId) {
		 return 0;
	 }
}
