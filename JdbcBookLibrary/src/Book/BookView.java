package Book;

import Sign.SignVO;

import java.sql.Date;
import java.time.LocalDate;

import java.util.List;
import java.util.Scanner;

public class BookView {
    private static final BookView instance = new BookView();
    private final String LINE = "───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────\n";
    public  BookView() {
    }
    public static BookView getInstance() {
        return instance;
    }
    private final BookInsertRandom bookInsertRandom = BookInsertRandom.getInstance();

    public int mainMenu(Scanner sc) {
        StringBuilder sb = new StringBuilder();
        sb.append("┌───────────────────────────────────────────────┐\n");
        sb.append("│ 1. 로그인 │ 2. 회원가입 │ 3. 비회원 │ 0. 종료 │\n");
        sb.append("└───────────────────────────────────────────────┘\n");
        sb.append(" 메뉴를 선택 > ");
        System.out.print(sb);
        String input = sc.nextLine();
        while(!input.matches("^[0-3]+$")) {
            System.out.println(" 잘못된 입력입니다.");
            System.out.print(sb);
            input = sc.nextLine();
        }
        return Integer.parseInt(input);
    }
    // 로그인 후 보일 목록
    // 책 검색 | 책 반납 / 책 검색 | 책 입고
    public int bookUse(Scanner sc, SignVO signVO){
        String loginId = signVO.getId();
        StringBuilder sb = new StringBuilder();
        sb.append("┌──────────────────────────────────");
        if (loginId.equals("")) {                  // 비회원
            sb.append( "──────");
        } else if ("admin".equals(loginId)) {   // 관리자
            sb.append("──────────────────────────────");
        } else {                                // 회원
            sb.append("──────────────────────────────");
        }
        sb.append("┐\n");
        sb.append("│ 1.책 목록 │");
        sb.append( " 2.책 검색 │");
        if (!(loginId.equals("")) && !("admin".equals(loginId) )) {           // 회원
            sb.append(" 3.책 대출 │");
            sb.append(" 4.책 반납 │");
        } else if(signVO.getId().equals("admin")) {   // 관린자
            sb.append( " 3.책 입고 │");
            sb.append( " 4.책 수정 │");
//            sb.append( " 5.책 삭제 │");
        }
        sb.append( " 0.메인화면으로 │\n");
        sb.append("└──────────────────────────────────");
        if (loginId.equals("")) {                  // 비회원
            sb.append( "──────");
        } else if ("admin".equals(loginId)) {   // 관리자
            sb.append("──────────────────────────────");
        } else {                                // 회원
            sb.append("──────────────────────────────");
        }
        sb.append("┘\n");
        sb.append(" 번호 선택 > ");
        System.out.print(sb);

        boolean userInsertCheck;
        String input;

        do {
            input = sc.nextLine();
            if (loginId.equals("")) {                  // 비회원
                userInsertCheck = !input.matches("^[0-2]+$");
            } else if ("admin".equals(loginId)) {   // 관리자
                userInsertCheck = !input.matches("^[0-4]+$");
            } else {                                // 회원입력값 체크");
                userInsertCheck = !input.matches("^[0-4]+$");
            }

            if (userInsertCheck) {
                System.out.println(" 잘못된 입력입니다.");
                System.out.print(sb);
            }
        } while (userInsertCheck);

        sb.setLength(0);
        return Integer.parseInt(input);
    }

    public int bookIfSelectMenu(Scanner sc){
        StringBuilder sb = new StringBuilder();
        sb.append("┌────────────────────┐\n");
        sb.append("│ 1. 제목으로 검색   │\n");
        sb.append("│ 2. 작가로 검색     │\n");
        sb.append("│ 3. 장르로 검색     │\n");
        sb.append("│ 4. 출판년도로 검색 │\n");
        sb.append("│ 0. 검색취소        │\n");
        sb.append("└────────────────────┘\n");
        sb.append(" 번호 선택 > ");
        System.out.print(sb);
        String input = sc.nextLine();
        while(!input.matches("^[0-5]+$")) {
            System.out.println(" 잘못된 입력입니다.");
            System.out.print(sb);
            input = sc.nextLine();
        }
        sb.setLength(0);
        int bookIfSelect = Integer.parseInt(input);
        if (bookIfSelect == 0) {
            return 0;
        } else if (bookIfSelect == 1) {
            System.out.print(" 제목 입력 : ");
        } else if (bookIfSelect == 2) {
            System.out.print(" 저자 입력 : ");
        } else if (bookIfSelect == 3) {
            System.out.print(" 장르 입력 : ");
        } else if (bookIfSelect == 4) {
            System.out.print(" 출판년도 입력 : ");
        }
        return bookIfSelect;
    }
    public void bookIfSelect(List<BookVO> books){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        sb.append(" 검색 목록\n");
        sb.append(LINE);
        for (BookVO vo: books) {
            sb.append(String.format(" %2d. %-40s\t%-5s\t%-10s\t%-9s\n", ++i, vo.getTitle(), vo.getAuthor(), vo.getGenre(), vo.getCallSign()));
        }
        sb.append(LINE);
        System.out.println(sb);
    }

    public void selectBook(List<BookVO> books){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        sb.append(" 목록\n");
        sb.append(LINE);
        for (BookVO vo: books) {
            sb.append(String.format(" %2d. %-40s\t%-5s\t%-10s\t%-9s\n", ++i, vo.getTitle(), vo.getAuthor(), vo.getGenre(), vo.getCallSign()));
        }
        sb.append(LINE);
        System.out.println(sb);
    }

    // 페이징 기법 메뉴창
    public int detailBookMenu(Scanner sc, int pageNum){
        StringBuilder sb = new StringBuilder();
        sb.append("┌────────────────────────────────────────────────────────────┐\n");
        sb.append("│ 현재 쪽:");
        sb.append(pageNum);
        sb.append(" │ 1.다음 페이지 │ 2.이전 페이지 │ 0. 목록 나가기 │\n");
        sb.append("└────────────────────────────────────────────────────────────┘\n");
        sb.append(" 번호 선택 > ");
        System.out.print(sb);
        String input = sc.nextLine();
        while(!input.matches("^[0-2]+$")) {
            System.out.println(" 잘못된 입력입니다.");
            System.out.print(sb);
            input = sc.nextLine();
        }
        sb.setLength(0);
        return Integer.parseInt(input);
    }

    public BookVO insertBook() throws Exception {
        System.out.println(" 책 입고 정보를 입력합니다.");
        String title = BookInsertRandom.inputLimit("제목 : ", 50);
        String author = BookInsertRandom.inputLimit("저자 : ", 15);
        String genre = BookInsertRandom.inputLimit("장르 : ", 15);
        String publisher = BookInsertRandom.inputLimit("출판사 : ", 10);
        String year = BookInsertRandom.inputLimit("발행연도 : ",4,true);
        String price = BookInsertRandom.inputLimit("가격(원) : ",10,false);
        String key = bookInsertRandom.generateKey();
        String call = bookInsertRandom.generateRandomCallString(Integer.parseInt(year));
        String isbn_num = bookInsertRandom.generateISBN();
        return new BookVO(title, author, genre, publisher, year, price, key, call, isbn_num);
    }

    public BookVO updateBook(Scanner sc, List<BookVO> books){
        String title = null;
        String author = null;
        String genre = null;
        String publisher = null;
        String year = null;
        int price;

        StringBuilder sb = new StringBuilder();
        sb.append(LINE);
        sb.append("\t\t 수정할 책 선택【0.취소】: ");
        System.out.print(sb);
        String num = sc.nextLine();
        while(!num.matches("^[0-9]+$")) {
            System.out.println(" 잘못된 입력입니다.");
            System.out.print(sb);
            num = sc.nextLine();
        }
        int size = books.size();
        if (Integer.parseInt(num) > size) {
            System.out.println(" 목록에 수정할 책이 없습니다.");
            return null;
        } else if (Integer.parseInt(num) <= 0){
            return null;
        }
        sb.setLength(0);

        sb.append("┌────────────────────────────────────────────────────────────────────┐\n");
        sb.append("│ 1.제목 │ 2.저자 │ 3.장르 │ 4.출판사 │ 5.발행연도 │ 6.가격 │ 0.취소 │\n");
        sb.append("└────────────────────────────────────────────────────────────────────┘\n");
        sb.append(" 수정할 항목 입력 :");
        System.out.print(sb);
        String input = sc.nextLine();
        while(!input.matches("^[0-6]+$")) {
            System.out.println(" 잘못된 입력입니다. 0 ~ 6 숫자만 입력해주세요.");
            System.out.print(sb);
            input = sc.nextLine();
        }
        if ("0".equals(input)){
            return null;
        }

        int index = Integer.parseInt(num) - 1; // 리스트 인덱스는 0부터 시작하므로 선택한 숫자에서 1을 빼줌

        BookVO uBook = books.get(index);
        uBook.setId(books.get(index).getId());
        System.out.println("「"+uBook.getTitle() + "」 책을 수정합니다.");

        switch (input) {
            case "1":
                title = BookInsertRandom.inputLimit("제목 : ", 50);
                uBook.setTitle(title);
                break;
            case "2":
                author = BookInsertRandom.inputLimit("저자 : ", 15);
                uBook.setAuthor(author);
                break;
            case "3":
                genre = BookInsertRandom.inputLimit("장르 : ", 15);
                uBook.setGenre(genre);
                break;
            case "4":
                publisher = BookInsertRandom.inputLimit("출판사 : ", 10);
                uBook.setPublisher(publisher);
                break;
            case "5":
                year = BookInsertRandom.inputLimit("발행연도 : ", 4, true);
                uBook.setYear(year);
                break;
            case "6":
                price = Integer.parseInt(BookInsertRandom.inputLimit("가격(원) : ", 10, false));
                uBook.setPrice(price);
                break;
            default:
                return null;
        }
        return uBook;
    }

    public BookVO deleteBook(Scanner sc, List<BookVO> books){
        StringBuilder sb = new StringBuilder();
        sb.append(LINE);
        sb.append("\t\t 삭제할 책 선택【0.취소】 : ");
        System.out.print(sb);
        String num = sc.nextLine();
        while(!num.matches("^[0-9]+$")) {
            System.out.println(" 잘못된 입력입니다. 숫자만 입력해주세요");
            System.out.print(sb);
            num = sc.nextLine();
        }
        if ("0".equals(num)){
            return null;
        }
        int index = Integer.parseInt(num) - 1; // 리스트 인덱스는 0부터 시작하므로 선택한 숫자에서 1을 빼줌

        BookVO uBook = books.get(index);
        uBook.setId(books.get(index).getId());
        System.out.println(" "+uBook.getTitle() + "을(를) 삭제합니다.");


        return uBook;
    }


    public void insertResult(int count) {
        if (count > 0) {
            System.out.println(" 책이 정상적으로 입고 되었습니다.");
            System.out.println();
        } else {
            System.out.println(" 책이 정상적으로 입고 되지 않았습니다.");
        }
    }
    public void updateResult(int count){
        if (count > 0) {
            System.out.println(" 정상적으로 업데이트되었습니다.");
        } else {
            System.out.println(" 정상적으로 업데이트되지 않았습니다.");
        }
    }
    public void deleteResult(int count){
        if (count > 0) {
            System.out.println(" 정상적으로 삭제되었습니다.");
        } else {
            System.out.println(" 정상적으로 삭제되지 않았습니다.");
        }
    }

    public LoanVO returnBook(Scanner sc, SignVO signVO,  List<LoanVO> loans) {
        int i=0;
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t").append(signVO.getId()).append("님의 대출 현황 입니다.\n");
        sb.append("──────────────────────────────────────────────────────────────\n");
        if (loans != null) {
            for (LoanVO vo: loans) {
                sb.append(String.format(" %2d. %-30s\t%-10s\n", ++i, vo.getTitle(), vo.getExLonaDate()));
            }
        } else {
            sb.append("  반납할 책이 없습니다.");
        }
        sb.append("──────────────────────────────────────────────────────────────\n");
        sb.append(" 반납할 책 선택 【0.취소】 : ");
        System.out.print(sb);
        String num = sc.nextLine();
        if (loans != null) {
            while(!num.matches("^[0-9]+$")) {
                System.out.println(" 잘못된 입력입니다. 숫자만 입력해주세요.");
                System.out.print(sb);
                num = sc.nextLine();
            }
        } else {
            System.out.println("  반납할 책이 없습니다.");
            return null;
        }
        if ("0".equals(num)){
            return null;
        }
        int index = Integer.parseInt(num) - 1; // 리스트 인덱스는 0부터 시작하므로 선택한 숫자에서 1을 빼줌
        LoanVO vo = loans.get(index);

        // ID
        loans.get(index).setId(signVO.getId());
        // L_ID 대출 번호
        loans.get(index).setLongId(vo.getLongId());
        // Title
        loans.get(index).setTitle(vo.getTitle());
        // 실제 반납일 시간값
        LocalDate currentDate = LocalDate.now();                        // 현재 시간 받아오기
        Date currentDateAsDate = java.sql.Date.valueOf(currentDate);    // sql Date 타입으로 변환
        loans.get(index).setExLonaDate(currentDateAsDate);

        System.out.println("「"+loans.get(index).getTitle() + "」 책을 반납합니다.");

        return vo;
    }

    public BookVO loanBook(Scanner sc, List<BookVO> selectBook) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(" 대출할 책 선택【0.취소】: ");
        System.out.print(sb);
        String num = sc.nextLine();
        while(!num.matches("^[0-9]+$")) {
            System.out.println(" 잘못된 입력입니다. 숫자만 입력해주세요.");
            System.out.print(sb);
            num = sc.nextLine();
        }
        if ("0".equals(num)){
            return null;
        }
        BookVO lBook = selectBook.get(Integer.parseInt(num)-1);

        sb.setLength(0);
        return lBook;
    }

    public void loanResult(int loanBook) {
        if (loanBook > 0) {
            System.out.println(" 정상적으로 대출되었습니다.");
        } else {
            System.out.println(" 정상적으로 대출되지 않았습니다.");
        }
    }

    public void returnResult(int returnBook) {
        if (returnBook > 0) {
            System.out.println(" 정상적으로 반납되었습니다.");
        } else {
            System.out.println(" 정상적으로 반납되지 않았습니다.");
        }
    }
}














