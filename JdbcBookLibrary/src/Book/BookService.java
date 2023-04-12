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
    public List<BookVO> selectWord(String title, int num) throws Exception { return dao.selectWord(title, num);}

    public BookVO insertBook(BookVO vo) throws Exception {
        return dao.insertBook(vo);
    }
}
