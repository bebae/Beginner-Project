package Book;

import Sign.SignVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDAO {
    private Connection conn;
    private PreparedStatement pstmt;

    public BookDAO() {
    }
    private static BookDAO instance = new BookDAO();
    public static BookDAO getInstance() {return instance;}

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

    // 검색 워드
    public List<BookVO> selectAllPage(int pageNum, int amount) throws Exception {
        ResultSet rs;
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
        sb.setLength(0);
        rs.close();
        close(conn, pstmt);
        return list;
    }
    public List<BookVO> selectAll() throws Exception {
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
        sb.setLength(0);
        rs.close();
        close(conn, pstmt);
        return list;
    }
    public List<BookVO> selectWord(String selectWord, int num) throws Exception {
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

        System.out.println(sb);

        int count = pstmt.executeUpdate();
        close(conn, pstmt);
        return count;
    }

    public int deleteBook(BookVO vo) throws Exception {
        conn = getConnection();

        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM book WHERE b_id = ?");

        pstmt = conn.prepareStatement(sb.toString());
        pstmt.setString(1, vo.getId());

        int count = pstmt.executeUpdate();
        sb.setLength(0);
        close(conn, pstmt);
        return count;
    }

    // 반납 책 리스트
    public List<LoanVO> idSelectReturn(SignVO signVO) throws Exception {

        conn = getConnection();

        List<LoanVO> list = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    l.m_id,");
        sb.append("    l.l_number,");
        sb.append("    b.title,");
        sb.append("    l.ex_return_date ");
        sb.append("FROM");
        sb.append("         loan l");
        sb.append("    JOIN book b ON l.b_id = b.b_id ");
        sb.append("WHERE");
        sb.append("    l.m_id = (");
        sb.append("        SELECT");
        sb.append("            m_id");
        sb.append("        FROM");
        sb.append("            member");
        sb.append("        WHERE");
        sb.append("            rtrim(m_id) = ? ");
        sb.append("          AND real_return_date IS NULL");           // 회원 ID와 같으면서 반납일이 NULL 만 조회
        sb.append("    )");
        pstmt = conn.prepareStatement(String.valueOf(sb));
        pstmt.setString(1, signVO.getId());
        ResultSet rs = pstmt.executeQuery();

        if (rs == null) {
            System.out.println(" 대출 사항이 없습니다.");
        } else {
            while (rs.next()) {
                String id = rs.getString("m_id");
                String loanNumber = rs.getString("l_number");
                String title = rs.getString("title");
                Date exReturnDate = rs.getDate("ex_return_date");
                LoanVO vo = new LoanVO(id, loanNumber, title, exReturnDate);
                list.add(vo);
            }
        }

        sb.setLength(0);
        assert rs != null;
        rs.close();
        close(conn, pstmt);
        return list;
    }

    // 대출 테이블에 데이터 입력
    public int loanBook(BookVO vo, SignVO signVO) throws Exception {
        conn = getConnection();

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT into loan (m_id, b_id) ");
        sb.append("VALUES(?, ?) ");
        pstmt = conn.prepareStatement(String.valueOf(sb));

        pstmt.setString(1, signVO.getId());
        pstmt.setString(2, vo.getId());

        sb.setLength(0);
        sb.append(vo.getId());
        sb.append("님께서 「");
        sb.append(vo.getTitle());
        sb.append("」 책을 대출 하셨습니다.");

        int count = pstmt.executeUpdate();
        close(conn, pstmt);
        return count;
    }

    // 반납 처리 UPDATE
    public int returnBook(LoanVO rBook) throws Exception {
        conn = getConnection();

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE loan SET real_return_date = ? WHERE  rtrim(m_id) = ? AND l_number = ? ");
        pstmt = conn.prepareStatement(String.valueOf(sb));

        pstmt.setDate(1, (java.sql.Date) rBook.getExLonaDate());
        pstmt.setString(2, rBook.getId());
        pstmt.setString(3, rBook.getLongId());


        sb.setLength(0);
        sb.append(rBook.getId());
        sb.append("님께서 「");
        sb.append(rBook.getTitle());
        sb.append("」 책을 반납 하셨습니다.");

        int count = pstmt.executeUpdate();
        close(conn, pstmt);
        return count;
    }
}













