package Sign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SignDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static SignDAO instance = new SignDAO();

	public static SignDAO getInstance() {
		return instance;
	}

	public Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.142.23:1521:XE";
		String user = "project";
		String password = "java";
		return DriverManager.getConnection(url, user, password);
	}

	public List<SignVO> getSigns() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.23:1521:xe", "project", "java");
		Statement statement = connection.createStatement();
		String sql = "select m_id, password from member";
		ResultSet resultSet = statement.executeQuery(sql);
		List<SignVO> list = new ArrayList<>();
		while (resultSet.next()) {
			String id = resultSet.getString("m_id");
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
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.23:1521:xe", "project", "java");
		String sql = "select m_id, password where m_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, serachId);
		ResultSet resultSet = statement.executeQuery();
		SignVO vo = null;

		if (resultSet.next()) {
			resultSet.getString("m_id");
			String id = resultSet.getString("m_id");
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
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.23:1521:xe", "project", "java");
		String sql = "INSERT INTO member (m_id, password) VALUES (sign_seq.nextval, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getId());
		statement.setString(2, vo.getPassword());
		int count = statement.executeUpdate(); // 완료한 로우의 갯수
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

	public Map<String, Object> selectSign(String id, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkLogin(String id, String password) throws Exception {
		String SQL = "SELECT password FROM member WHERE rtrim(m_id) = ? and password = ?";
		conn = getConnection();
		pstmt = conn.prepareStatement(SQL);
		pstmt.setString(1, id.trim());
		pstmt.setString(2, password);
		rs = pstmt.executeQuery();

		if (rs.next()) { // 결과값이 있는 경우
//    	String savedPassword = rs.getString("password");
//    	if (savedPassword.equals(password)) { // 비밀번호도 맞는 경우
//        return true;
//    	} else {
//    		 System.out.println("비밀번호가 틀렸습니다.");
//         return false;
//	      }
			return true;
		} else { // 아이디가 틀린 경우
			System.out.println("로그인 실패! 아이디 또는 비밀번호가 일치하지 않습니다. 다시 입력 해주세요.");
			return false;
		}
	}
}
