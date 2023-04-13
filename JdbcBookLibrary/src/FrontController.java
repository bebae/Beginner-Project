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
            int pageSelect = 1;         // 페이지 기본값
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
                    int bookcase = view.bookUse(sc);
                    switch (bookcase) {
                        case 1:        // 책 목록 페이징
                            while (true) {
                                List<BookVO> selectBook = controller.selectAllPage(pageSelect);
                                view.selectBook(selectBook);
                                int pageNum = view.detailBookMenu(sc, pageSelect);
                                if (pageNum == 1) {
                                    pageSelect++;
                                } else if (pageNum == 2) {
                                    if (--pageSelect <= 0)
                                        pageSelect = 1;
                                } else {
                                    break;
                                }
                            }
                            break;
                        case 2:        // 책 검색
                            int bookIfSelect = view.bookIfSelectMenu(sc);
                            if (bookIfSelect == 0) {
                                break;
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
                        case 3:        // 책 반납

                            System.out.println("책 반납 미구현");
                            continue;
                        case 4:         // 책 입고
                            BookVO iBook = view.insertBook(sc);
                            System.out.println(iBook);
                            if (iBook != null) {
                                int insertBook = controller.insertBook(iBook);
                                view.insertResult(insertBook);
                            }
                            continue;
                        case 5:        // 책 수정
                            List<BookVO> selectBook = controller.selectBook();
                            view.selectBook(selectBook);

                            BookVO uBook = view.updateBook(sc,selectBook);                     // 수정할 항목
                            int updateBook = controller.updateBook(uBook);

                            continue;
                        case 6:        // 책 삭제
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
