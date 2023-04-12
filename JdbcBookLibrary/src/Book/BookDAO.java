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



    // 검색 워드
    public List<BookVO> selectAll() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //String url = "jdbc:oracle:thin:@localhost:1521:XE";
        //String user = "pc030";
        String url = "jdbc:oracle:thin:@192.168.142.23:1521:XE";
        String user = "project";
        //String url = "jdbc:oracle:thin:@localhost:1521:XE";
        //String user = "pc030";
        String password = "java";
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM book");
        String sql = sb.toString();
        ResultSet rs = statement.executeQuery(sql);
        List<BookVO> list = new ArrayList<>();
        while (rs.next()) {
            String title = rs.getString("title");
            String author = rs.getString("author");
            String genre = rs.getString("genre");
            String callSing = rs.getString("callsign_num");
            String loanYN = rs.getString("loan_YN");
            list.add(new BookVO(title, author, genre, callSing, loanYN));
        }
        this.close();
        return list;
    }
    public List<BookVO> selectWord(String selectWord, int num) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@192.168.142.23:1521:XE";
        String user = "project";
        //String url = "jdbc:oracle:thin:@localhost:1521:XE";
        //String user = "pc030";
        String password = "java";
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();

        PreparedStatement pstmt = null;
        // 검색 목표 1 = 제목 / 2 = 저자 / 3 = 출판사
        if (num == 1){
            pstmt = connection.prepareStatement("SELECT * FROM book where title LIKE '%' || ? || '%'");
        } else if (num == 2) {
            pstmt = connection.prepareStatement("SELECT * FROM book where author LIKE '%' || ? || '%'");
        } else if (num == 3) {
            pstmt = connection.prepareStatement("SELECT * FROM book where genre LIKE '%' || ? || '%'");
        }

        assert pstmt != null;
        pstmt.setString(1, selectWord);
        ResultSet rs = pstmt.executeQuery();

        List<BookVO> list = new ArrayList<>();
        while (rs.next()) {
            String title = rs.getString("title");
            String author = rs.getString("author");
            String genre = rs.getString("genre");
            String callSing = rs.getString("callsign_num");
            String loanYN = rs.getString("loan_YN");
            list.add(new BookVO(title, author, genre, callSing, loanYN));
        }
        this.close();
        return list;
    }

    public void close() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public BookVO insertBook(BookVO vo) throws Exception {
        return null;
    }
}
