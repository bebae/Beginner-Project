package sign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SignDAO {
	public List<SignVO> getSigns() throws Exception {
 	 Class.forName("oracle.jdbc.driver.OracleDriver");
 	 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","pc04","java");
 	 Statement statement = connection.createStatement();
 	 String sql = "select id, password";
 	 ResultSet resultSet = statement.executeQuery(sql);
 	 List<SignVO> list = new ArrayList<>();
 	 while(resultSet.next()) {
			 String id = resultSet.getString("id");
			 String password = resultSet.getString("password");
			 list.add(new SignVO(id, password));
		 }
 	 
 	   resultSet.close();
		 statement.close();
		 connection.close();
		 return list;
  }
  
  public SignVO getSign(String serachId) throws Exception {
 	 Class.forName("oracle.jdbc.driver.OracleDriver");
 	 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","pc04","java");
 	 String sql = "select id, password where id = ?";
 	 PreparedStatement statement = connection.prepareStatement(sql);
 	 statement.setString(1, serachId);
 	 ResultSet resultSet = statement.executeQuery();
 	 SignVO vo = null;
 	 
 	 if (resultSet.next()) {
 		 resultSet.getString("id");
 		 String id = resultSet.getString("id");
 		 String password = resultSet.getString("password");
 		 vo = new SignVO(id, password);
 	 }
 	 resultSet.close();
 	 statement.close();
 	 connection.close();
		 return vo;
  }
  
  public int insertSign(SignVO vo) throws Exception {
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","pc04","java");
		 String sql = "INSERT INTO memo (id, password) VALUES (sign_seq.nextval, ?, ?)";
		 PreparedStatement statement = connection.prepareStatement(sql);
		 statement.setString(1, vo.getId());
		 statement.setString(2, vo.getPassword());
		 int count = statement.executeUpdate();   //완료한 로우의 갯수
		 statement.close();
		 connection.close();
		 return 0;
	 }
  
  public int updateSign(SignVO vo) {
		 return 0;
	 }
	 
	 // delete from memo where id = ?
	 public int deleteSign(String deleteId) {
		 return 0;
	 }
}
