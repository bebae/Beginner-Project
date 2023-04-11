import Book.BookController;
import Book.BookVO;
import Book.BookView;

import java.util.List;
import java.util.Scanner;

public class FrontController {
    private static FrontController instance = new FrontController();
    private FrontController() {
    }
    public static FrontController getInstance() {
        return instance;
    }
    private BookController controller = BookController.getInstance();
    private BookView view = BookView.getInstance();
    private Scanner sc = new Scanner(System.in);
    public void process() throws Exception {
        view.welcome();
        while (true) {
            // View에 선택창 불러오는 부분
            int menu = view.mainMenu(sc);
            switch (menu) {
                // 로그인
                case 1:
//                    BookVo loginMember = view.로그인(scanner);
                    break;
                // 회원가입
                case 2:
//                    Book.BookVO joinMember = view.회원가입(scanner)
                    break;
                // 비회원
                case 3:
//                    BookVo loginMember = view.로그인(권한 없는 사용자);
                    List<BookVO> selectBook = controller.selectBook();

                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default: break;
            }
        }
    }
}
