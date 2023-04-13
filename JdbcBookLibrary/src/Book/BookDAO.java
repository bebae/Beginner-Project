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

    public Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "pc030";
        //String url = "jdbc:oracle:thin:@192.168.142.23:1521:1521:XE";
//        String user = "project";
        return DriverManager.getConnection(url, user, "java");
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
            String year = Integer.toString(rs.getInt("PUBLICATION_YEAR"));
            String loanYN = rs.getString("loan_YN");
            list.add(new BookVO(title, author, genre, callSing, year, loanYN));
        }
        rs.close();
        close(conn, stmt);
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
        } else if (num == 4) {
            pstmt = conn.prepareStatement("SELECT * FROM book where publication_year LIKE '%' || ? ");
            pstmt.setInt(1, Integer.parseInt(selectWord));
        }

        assert pstmt != null;
        pstmt.setString(1, selectWord);
        // executeQuery 는 Select 용
        rs = pstmt.executeQuery();

        List<BookVO> list = new ArrayList<>();
        while (rs.next()) {
            String title = rs.getString("title");
            String author = rs.getString("author");
            String genre = rs.getString("genre");
            String callSing = rs.getString("callsign_num");
            String year = Integer.toString(rs.getInt("PUBLICATION_YEAR"));
            String loanYN = rs.getString("loan_YN");
            list.add(new BookVO(title, author, genre, callSing, year, loanYN));
        }
        rs.close();
        close(conn, pstmt);
        return list;
    }


    public int insertBook(BookVO vo) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = getConnection();

        pstmt = conn.prepareStatement(
                "INSERT into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)" +
                " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        );
        pstmt.setString(1, vo.getTitle());
        pstmt.setString(2, vo.getAuthor());
        pstmt.setString(3, vo.getPublisher());
        pstmt.setString(4, vo.getYear());
        pstmt.setString(5, String.valueOf(vo.getIsbn()));
        pstmt.setString(6, String.valueOf(vo.getPrice()));
        pstmt.setString(7, vo.getGenre());
        pstmt.setString(8, vo.getId());
        pstmt.setString(9, vo.getCallSign());
        int count = pstmt.executeUpdate();

        close(conn, pstmt);
        return count;
    }
    public void close(Connection conn, Statement pstmt) throws SQLException {
        if (pstmt != null) {
            pstmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
