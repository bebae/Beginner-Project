package Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;
    private Statement statement;

    public BookDAO() {
    }
    private static BookDAO instance = new BookDAO();
    public static BookDAO getInstance() {return instance;}

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //String url = "jdbc:oracle:thin:@localhost:1521:XE";
        //String user = "pc030";
        String url = "jdbc:oracle:thin:@192.168.142.23:1521:XE";
        String user = "project";
        String password = "java";
        return DriverManager.getConnection(url, user, password);
    }

    // 검색 워드
    public List<BookVO> selectAll() throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        conn = getConnection();
        stmt = conn.createStatement();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM book");
        String sql = sb.toString();
        rs = stmt.executeQuery(sql);
        List<BookVO> list = new ArrayList<>();
        while (rs.next()) {
            String title = rs.getString("title");
            String author = rs.getString("author");
            String genre = rs.getString("genre");
            String callSing = rs.getString("callsign_num");
            String loanYN = rs.getString("loan_YN");
            list.add(new BookVO(title, author, genre, callSing, loanYN));
        }
        close(conn, stmt, rs);
        return list;
    }
    public List<BookVO> selectWord(String selectWord, int num) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        conn = getConnection();
        // 검색 목표 1 = 제목 / 2 = 저자 / 3 = 출판사
        if (num == 1){
            pstmt = conn.prepareStatement("SELECT * FROM book where title LIKE '%' || ? || '%'");
        } else if (num == 2) {
            pstmt = conn.prepareStatement("SELECT * FROM book where author LIKE '%' || ? || '%'");
        } else if (num == 3) {
            pstmt = conn.prepareStatement("SELECT * FROM book where genre LIKE '%' || ? || '%'");
        }

        assert pstmt != null;
        pstmt.setString(1, selectWord);
        rs = pstmt.executeQuery();

        List<BookVO> list = new ArrayList<>();
        while (rs.next()) {
            String title = rs.getString("title");
            String author = rs.getString("author");
            String genre = rs.getString("genre");
            String callSing = rs.getString("callsign_num");
            String loanYN = rs.getString("loan_YN");
            list.add(new BookVO(title, author, genre, callSing, loanYN));
        }
        close(conn, pstmt, rs);
        return list;
    }

    public void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public BookVO insertBook(BookVO vo) throws Exception {
        return null;
    }
}
