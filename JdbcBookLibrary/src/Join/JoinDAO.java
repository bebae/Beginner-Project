package Join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JoinDAO {
    private Connection conn;
    private PreparedStatement pstmt;

    public Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //String url = "jdbc:oracle:thin:@localhost:1521:XE";
        //String user = "pc030";
        String url = "jdbc:oracle:thin:@192.168.142.23:1521:XE";
        String user = "project";
        return DriverManager.getConnection(url, user, "java");
    }

    public void close(Connection conn, Statement pstmt) throws SQLException {
        if (pstmt != null) {
            pstmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<JoinVO> getJoins() throws Exception {
        conn = getConnection();
        Statement statement = conn.createStatement();
        String sql = "select m_id, name, password, birth_Date, phone_num, email, address, loans_num from member";
        ResultSet resultSet = statement.executeQuery(sql);
        List<JoinVO> list = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString("m_id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String birthDate = resultSet.getString("birth_Date");
            String phonenumber = resultSet.getString("phone_num");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            int loansnumber = resultSet.getInt("loans_num");
            list.add(new JoinVO(id, password, name, birthDate, phonenumber, email, address, loansnumber));
        }

        resultSet.close();
        statement.close();
        conn.close();
        return list;
    }

    public JoinVO getJoin(String serachId) throws Exception {
        conn = getConnection();
        Statement statement = conn.createStatement();
        String sql = "select m_id, name, password, birth_Date, phone_num, email, address, loans_num from member where m_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, serachId);
        ResultSet resultSet = pstmt.executeQuery();
        JoinVO vo = null;

        if (resultSet.next()) {
            resultSet.getString("id");
            String id = resultSet.getString("m_id");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");
            String birthDate = resultSet.getString("birth_Date");
            String phonenumber = resultSet.getString("phone_num");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            int loansnumber = resultSet.getInt("loans_num");

            vo = new JoinVO(id, password, name, birthDate, phonenumber, email, address, loansnumber);
        }
        resultSet.close();
        statement.close();
        conn.close();
        return vo;
    }


    public static int insertJoin(JoinVO vo) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.23:1521:xe","project","java");
        String sql = "INSERT "
                + "    into member(m_id, name, password, birth_Date, phone_num, email, address) "
                + "    values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, vo.getId());
        statement.setString(2, vo.getName());
        statement.setString(3, vo.getPassword());
        statement.setString(4, vo.getBirthDate());
        statement.setString(5, vo.getPhonenumber());
        statement.setString(6, vo.getEmail());
        statement.setString(7, vo.getAddress());
        int count = statement.executeUpdate();   //완료한 로우의 갯수
        statement.close();
        connection.close();
        return count;
    }

    public static boolean isMemberExist(String id) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.23:1521:xe", "project", "java");
        String sql = "SELECT * FROM member WHERE rtrim(m_id) = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();
        boolean isExist = rs.next();
        rs.close();
        statement.close();
        connection.close();
        return !isExist;
    }


    public static int updateJoin(JoinVO vo) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.23:1521:xe", "project", "java");
        String sql = "UPDATE"
                + "   member SET name=?, password=?, birth_Date=?, phone_num=?, email=?, address=?"
                + "   WHERE rtrim(m_id)=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, vo.getName());
        statement.setString(2, vo.getPassword());
        statement.setString(3, vo.getBirthDate());
        statement.setString(4, vo.getPhonenumber());
        statement.setString(5, vo.getEmail());
        statement.setString(6, vo.getAddress());
        statement.setString(7, vo.getId());
        int count = statement.executeUpdate();
        statement.close();
        connection.close();
        return count;
    }

    // delete from memo where id = ?
    public static int deleteJoin(String deleteId) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.142.23:1521:xe", "project", "java");
        String sql = "DELETE FROM member WHERE rtrim(m_id) = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, deleteId);
        int count = statement.executeUpdate();
        statement.close();
        connection.close();
        return count;
    }

    public void getInstance() throws Exception {
        final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        final String DB_URL = "jdbc:oracle:thin:@192.168.142.23:1521:xe";
        final String USER = "project";
        final String PASS = "java";

        JoinDAO joinDAO = new JoinDAO(); // JoinDAO 객체 생성
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        Class.forName(JDBC_DRIVER);

        // 데이터베이스 연결
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // SQL 쿼리 실행
        stmt = conn.createStatement();
        String sql = "SELECT * FROM member";
        rs = stmt.executeQuery(sql);

        // 결과 출력
        while (rs.next()) {
            String memberId = rs.getString("m_id");
            String memberName = rs.getString("name");
            String memberEmail = rs.getString("email");
            // 출력 또는 처리 로직 구현
            System.out.println("m_iD: " + memberId);
            System.out.println("name: " + memberName);
            System.out.println("email: " + memberEmail);
            System.out.println("===============================");
        }

    }

    public static JoinDAO getInstance1() {
        return new JoinDAO();
    }


    public List<JoinVO> selectAllJoin() {
        final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        final String DB_URL = "jdbc:oracle:thin:@192.168.142.23:1521:xe";
        final String USER = "project";
        final String PASS = "java";

        List<JoinVO> members = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM member")) {

            while (rs.next()) {
                String id = rs.getString("m_id");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String birthDate = rs.getString("birth_Date");
                String phonenumber = rs.getString("phone_num");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int loansnumber = rs.getInt("loans_num");


                members.add(new JoinVO(id, password, name, birthDate, phonenumber, email, address, loansnumber));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return members;
    }

}




