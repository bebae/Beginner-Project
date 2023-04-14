package Book;

import java.util.List;
import java.util.Map;

public class BookService {
    private static BookService instance = new BookService();
    public static BookService getInstance() {return instance;}
    private BookService() {}

    private BookDAO dao = BookDAO.getInstance();

    // 전체 검색
    public List<BookVO> selectAllPage(int pageNum, int amount) throws Exception {return dao.selectAllPage(pageNum, amount);}
    public List<BookVO> selectAll() throws Exception {return dao.selectAll();}
    public List<BookVO> selectWord(String title, int num) throws Exception { return dao.selectWord(title, num);}

    public int insertBook(BookVO vo) throws Exception {
        return dao.insertBook(vo);
    }

    public int updateBook(BookVO vo) throws Exception {
        return dao.updateBook(vo);
    }

    public int deleteBook(BookVO vo) throws Exception {
        return dao.deleteBook(vo);
    }

    public Map<String, String> idSelectReturn(String loginId) throws Exception {
        return dao.idSelectReturn(loginId);
    }
}
