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
        while (true) {
            // View에 선택창 불러오는 부분
            int menu = view.mainMenu(sc);
            switch (menu) {
                // 로그인
                case 1:
//                    BookVo loginMember = view.로그인(scanner);
                    System.out.println("로그인 미구현");
                    break;
                // 회원가입
                case 2:
//                    Book.BookVO joinMember = view.회원가입(scanner)
                    System.out.println("회원가입 미구현");
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
                            if (bookIfSelect == 0) {

                            } else if (bookIfSelect == 1) {
                                System.out.print("제목 입력 : ");
                            } else if (bookIfSelect == 2) {
                                System.out.print("저자 입력 : ");
                            } else if (bookIfSelect == 3) {
                                System.out.print("장르 입력 : ");
                            } else if (bookIfSelect == 4) {
                                System.out.print("출판년도 입력 : ");
                            }
                            List<BookVO> selectTitle = controller.selectWord(sc.nextLine(),bookIfSelect);
                            view.bookIfSelect(selectTitle);

                            continue;
                        case 3 :        // 책 입고, 책 반납
                            System.out.println("책 입고 미구현");
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
