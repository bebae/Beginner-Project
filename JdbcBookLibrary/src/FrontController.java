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
                    int bookcase = view.bookUse(sc, 0);
                    switch (bookcase) {
                        case 1 :        // 책 목록
                            List<BookVO> selectBook = controller.selectBook();
                            view.selectBook(selectBook);
                            continue;
                        case 2 :        // 책 검색
                            int bookIfSelect = view.bookIfSelectMenu(sc);
                            switch (bookIfSelect) {
                                case 1 :
                                    System.out.print("제목 입력 : ");
                                    List<BookVO> selectTitle = controller.selectWord(sc.nextLine(),1);
                                    view.selectBook(selectTitle);
                                    break;
                                case 2 :
                                    System.out.print("저자 입력 : ");
                                    List<BookVO> selectAuthor = controller.selectWord(sc.nextLine(),2);
                                    view.selectBook(selectAuthor);
                                    break;
                                case 3 :
                                    break;
                                default:
                                    break;
                            }
                            continue;
                        case 3 :
                            System.out.println("미구현");
                            continue;
                        case 0:
                            System.out.println("메인화면으로 돌아갑니다.");
                            break;
                        default: break;
                    }
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default: break;
            }
        }
    }
}
