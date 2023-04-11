package Book;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    private static BookService instance = new BookService();
    public static BookService getInstance() {return instance;}
    private BookService() {}

    private BookDAO dao = BookDAO.getInstance();

    // 전체 검색
    public List<BookVO> selectAll() throws Exception {return dao.selectAll();}
    public BookVO selectByBookId(int bookId) { return dao.findByBookId(bookId);}

    public BookVO insertBook(BookVO vo) throws Exception {
        return dao.insertBook(vo);
    }
}
