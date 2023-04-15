import Book.BookController;
import Book.BookVO;
import Book.BookView;
import Book.LoanVO;
import Join.JoinController;
import Sign.SignController;
import Sign.SignDAO;
import Sign.SignVO;

import java.util.List;
import java.util.Map;
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
            // View 선택창 불러오는 부분
            int menu = view.mainMenu(sc);
            int pageSelect = 1;         // 페이지 기본값
            switch (menu) {
                // 로그인
                case 1:
                    SignController signController = new SignController();
                    SignVO signVO = signController.getSigns();
                    if (signVO == null) {
                        continue;
                    }
                    innerMenu(pageSelect, signVO);
                    break;
                // 회원가입
                case 2:
                    JoinController joinController = new JoinController();
                    joinController.getJoins();
//                    Book.BookVO joinMember = view.회원가입(scanner)
                    continue;
                    // 비회원
                case 3:
                    innerMenu(pageSelect, new SignVO("",""));
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    public void innerMenu(int pageSelect, SignVO signVO) throws Exception {
        boolean run = true;
        while (run) {
            int bookcase = view.bookUse(sc, signVO);
            List<BookVO> selectBook;
            switch (bookcase) {
                case 1:        // 책 목록 페이징
                    while (true) {
                        selectBook = controller.selectAllPage(pageSelect);
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
                    if (bookIfSelect != 0) {
                        List<BookVO> selectTitle = controller.selectWord(sc.nextLine(), bookIfSelect);
                        view.bookIfSelect(selectTitle);
                    } else {
                        System.out.println(" 검색을 취소합니다.");
                    }
                    continue;
                case 3: case 4: case 5: case 6:
                if (!(signVO.getId().equals("")) && !(signVO.getId().equals("admin"))) {

                    if (bookcase == 3) {        // 책 대출
                        selectBook = controller.selectBook();
                        view.selectBook(selectBook);

                        BookVO lBook = view.loanBook(sc, selectBook);                      // 대출 뷰
                        if (lBook != null) {
                            int loanBook = controller.loanBook(lBook, signVO);                  // 대출 확인 여부 DAO까지
                            view.loanResult(loanBook);
                        } else {
                            System.out.println(" 대출을 취소합니다.");
                        }
                        continue;
                    }
                    if (bookcase == 4) {       // 책 반납
                        List<LoanVO> loanVOList = controller.idSelectReturn(signVO);   // 반납 목록      // 대출테이블 참고해서 id에 맞는 책 제목과 반납 예정일 리턴하는 List DAO
                        LoanVO rBook = view.returnBook(sc, signVO, loanVOList);      // 반납 뷰
                        if (rBook != null) {
                            int returnBook = controller.returnBook(rBook);            // 책 반납 처리 DAO (delete 가 아니고 update)
                            view.returnResult(returnBook);
                        } else {
                            System.out.println(" 반납을 취소합니다.");
                        }
                        continue;
                    }
                } else {

                    if (bookcase == 3) {         // 책 입고
                        BookVO iBook = view.insertBook();
                        System.out.println(iBook);
                        if (iBook != null) {
                            int insertBook = controller.insertBook(iBook);
                            view.insertResult(insertBook);
                        }
                        continue;
                    }
                    if (bookcase == 4) {         // 책 수정
                        selectBook = controller.selectBook();
                        view.selectBook(selectBook);

                        BookVO uBook = view.updateBook(sc, selectBook);                      // 수정할 항목
                        if (uBook != null) {
                            int updateBook = controller.updateBook(uBook);                      // 수정 확인 여부
                            view.updateResult(updateBook);
                        } else {
                            System.out.println(" 수정을 취소합니다.");
                        }

                        continue;
                    }
                    if (bookcase == 5) {         // 책 삭제
                        selectBook = controller.selectBook();
                        view.selectBook(selectBook);

                        BookVO dBook = view.deleteBook(sc, selectBook);
                        if (dBook != null) {
                            int deleteBook = controller.deleteBook(dBook);
                            view.deleteResult(deleteBook);
                        } else {
                            System.out.println(" 삭제를 취소합니다.");
                        }
                        continue;
                    }
                }
                default:
                    System.out.println(" 메인화면으로 돌아갑니다.");
                    run = false;
                    break;
            }   // run while
        }
    }
}
