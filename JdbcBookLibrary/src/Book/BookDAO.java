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
    public void close(Connection conn, Statement pstmt) throws SQLException {
        if (pstmt != null) {
            pstmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    // 검색 워드
    public List<BookVO> selectAllPage(int pageNum, int amount) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = getConnection();

        // 조회할 페이지 번호와 한 페이지당 데이터 수를 기반으로 시작 번호와 끝 번호를 계산합니다.
        int startRow = (pageNum - 1) * amount + 1;
        int endRow = startRow + amount - 1;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append("(SELECT ROWNUM AS rnum, book.* FROM ");
        sb.append("(SELECT * FROM book ORDER BY callsign_num ASC) book ");
        sb.append("WHERE ROWNUM <= ?) ");
        sb.append("WHERE rnum >= ?");
        String sql = sb.toString();
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, endRow);
        pstmt.setInt(2, startRow);
        rs = pstmt.executeQuery();


        List<BookVO> list = new ArrayList<>();
        while (rs.next()) {
            String id = rs.getString("b_id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String genre = rs.getString("genre");
            String callSing = rs.getString("callsign_num");
            String year = Integer.toString(rs.getInt("PUBLICATION_YEAR"));
            String loanYN = rs.getString("loan_YN");
            list.add(new BookVO(id, title, author, genre, callSing, year, loanYN));
        }
        rs.close();
        close(conn, pstmt);
        return list;
    }
    public List<BookVO> selectAll() throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = getConnection();

        // 조회할 페이지 번호와 한 페이지당 데이터 수를 기반으로 시작 번호와 끝 번호를 계산합니다.

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM book");
        String sql = sb.toString();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();


        List<BookVO> list = new ArrayList<>();
        while (rs.next()) {
            String id = rs.getString("b_id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String genre = rs.getString("genre");
            String callSing = rs.getString("callsign_num");
            String year = Integer.toString(rs.getInt("PUBLICATION_YEAR"));
            String loanYN = rs.getString("loan_YN");
            list.add(new BookVO(id, title, author, genre, callSing, year, loanYN));
        }
        rs.close();
        close(conn, pstmt);
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
            String id = rs.getString("b_id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String genre = rs.getString("genre");
            String callSing = rs.getString("callsign_num");
            String year = Integer.toString(rs.getInt("PUBLICATION_YEAR"));
            String loanYN = rs.getString("loan_YN");
            list.add(new BookVO(id, title, author, genre, callSing, year, loanYN));
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


    public int updateBook(BookVO vo) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = getConnection();

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE book SET ");
        boolean isFirst = true;
        if (vo.getTitle() != null) {
            sb.append("title=?");
            isFirst = false;
        }
        if (vo.getAuthor() != null) {
            if (!isFirst) {
                sb.append(",");
            }
            sb.append("author=?");
            isFirst = false;
        }
        if (vo.getPublisher() != null) {
            if (!isFirst) {
                sb.append(",");
            }
            sb.append("p_name=?");
            isFirst = false;
        }
        if (vo.getGenre() != null) {
            if (!isFirst) {
                sb.append(",");
            }
            sb.append("genre=?");
            isFirst = false;
        }
        if (vo.getYear() != null) {
            if (!isFirst) {
                sb.append(",");
            }
            sb.append("publication_year=?");
            isFirst = false;
        }
        if (vo.getPrice() != 0) {
            if (!isFirst) {
                sb.append(",");
            }
            sb.append("price=? ");
        }
        sb.append(" WHERE b_id=?");
        pstmt = conn.prepareStatement(sb.toString());
        sb.setLength(0);

        int index = 1;
        if (vo.getTitle() != null) {
            pstmt.setString(index, vo.getTitle());
            index++;
        }
        if (vo.getAuthor() != null) {
            pstmt.setString(index, vo.getAuthor());
            index++;
        }
        if (vo.getPublisher() != null) {
            pstmt.setString(index, vo.getPublisher());
            index++;
        }
        if (vo.getGenre() != null) {
            pstmt.setString(index, vo.getGenre());
            index++;
        }
        if (vo.getYear() != null) {
            pstmt.setString(index, vo.getYear());
            index++;
        }
        if (vo.getPrice() != 0) {
            pstmt.setString(index, String.valueOf(vo.getPrice()));
            index++;
        }
        pstmt.setString(index, vo.getId());


        sb.append("수정부분입니다.\n");
        System.out.println(sb);

        int count = pstmt.executeUpdate();
        close(conn, pstmt);
        return count;
    }

    public int deleteBook(BookVO vo) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = getConnection();

        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM book WHERE b_id = ?");

        pstmt = conn.prepareStatement(sb.toString());
        pstmt.setString(1, vo.getId());

        int count = pstmt.executeUpdate();
        close(conn, pstmt);
        return count;
    }
}
