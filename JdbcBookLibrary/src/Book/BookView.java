package Book;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookView {
    private static final BookView instance = new BookView();
    public  BookView() {
    }
    public static BookView getInstance() {
        return instance;
    }
    private final BookInsertRandom bookInsertRandom = BookInsertRandom.getInstance();
    public static Map<String, Object> login;


    public int mainMenu(Scanner sc) {
        StringBuilder sb = new StringBuilder();
        sb.append("────────────────────────────────────────────────\n");
        sb.append(" 1. 로그인 | 2. 회원가입 | 3. 비회원 | 0. 종료\n");
        sb.append("────────────────────────────────────────────────\n");
        sb.append("메뉴를 선택 > ");
        System.out.print(sb);
        String input = sc.nextLine();
        while(!input.matches("^[0-3]+$")) {
            System.out.println("잘못된 입력입니다.");
            System.out.print(sb);
            input = sc.nextLine();
        }
        return Integer.parseInt(input);
    }
    // 로그인 후 보일 목록
    // 책 검색 | 책 반납 / 책 검색 | 책 입고
    public int bookUse(Scanner sc){
        StringBuilder sb = new StringBuilder();
        sb.append("─────────────────────────────────────────────────────────────────────────────────\n");
        sb.append(" 1.책 목록 | 2.책 검색 | 3.책 반납 | 4.입고 | 5.수정 6.삭제 | 0. 메인화면으로\n");
        sb.append("─────────────────────────────────────────────────────────────────────────────────\n");
        sb.append("번호 선택 > ");
        System.out.print(sb);
        assert sc != null;          // sc가 null 이라면 예외를 throw 하는 문법
        String input = sc.nextLine();
        while(!input.matches("^[0-5]+$")) {
            System.out.println("잘못된 입력입니다.");
            System.out.print(sb);
            input = sc.nextLine();
        }
        sb.setLength(0);
        return Integer.parseInt(input);
    }

    public int bookIfSelectMenu(Scanner sc){
        StringBuilder sb = new StringBuilder();
        sb.append("───────────────────────────────────────────────────────\n");
        sb.append(" 1. 제목으로 검색\n");
        sb.append(" 2. 작가로 검색\n");
        sb.append(" 3. 장르로 검색\n");
        sb.append(" 4. 출판년도로 검색\n");
        sb.append(" 0. 검색취소 \n");
        sb.append("───────────────────────────────────────────────────────\n");
        sb.append("번호 선택 > ");
        System.out.print(sb);
        String input = sc.nextLine();
        while(!input.matches("^[0-5]+$")) {
            System.out.println("잘못된 입력입니다.");
            System.out.print(sb);
            input = sc.nextLine();
        }
        sb.setLength(0);
        return Integer.parseInt(input);
    }
    public void bookIfSelect(List<BookVO> books){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        System.out.println(" 검색 목록");
        System.out.println("───────────────────────────────────────────────────────────────");
        for (BookVO vo: books) {
            System.out.printf("%2d. %-30s\t%-9s\t%-7s\t%-9s\t%-4s\t%-3s\n", ++i, vo.getTitle(), vo.getAuthor(), vo.getGenre(), vo.getCallSign(), vo.getYear(), vo.getLoanYN());
        }
        System.out.println("───────────────────────────────────────────────────────────────");
    }

    public void selectBook(List<BookVO> books){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        System.out.println(" 목록");
        System.out.println("───────────────────────────────────────────────────────────────────────────────────────");
        for (BookVO vo: books) {
            System.out.printf("%2d. %-30s\t%-9s\t%-7s\t%-9s\t%-4s\t%-3s\n", ++i, vo.getTitle(), vo.getAuthor(), vo.getGenre(), vo.getCallSign(), vo.getYear(), vo.getLoanYN());
        }
        System.out.println("───────────────────────────────────────────────────────────────────────────────────────");
    }

    // 페이징 기법 메뉴창
    public int detailBookMenu(Scanner sc, int pageNum){
        StringBuilder sb = new StringBuilder();
        sb.append("────────────────────────────────────────────────────────────────────\n");
        sb.append("│ 현재 쪽:");
        sb.append(pageNum);
        sb.append(" │ 1.다음 페이지 │ 2.이전 페이지 │ 0. 목록 나가기 │\n");
        sb.append("───────────────────────────────────────────────────────────────────\n");
        sb.append("번호 선택 > ");
        System.out.print(sb);
        String input = sc.nextLine();
        while(!input.matches("^[0-2]+$")) {
            System.out.println("잘못된 입력입니다.");
            System.out.print(sb);
            input = sc.nextLine();
        }
        sb.setLength(0);
        return Integer.parseInt(input);
    }

    public BookVO insertBook(Scanner sc) throws Exception {
        System.out.println("책 입고 정보를 입력합니다.");
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
        sb.append("────────────────────────────────────────────────────────────────────\n");
        sb.append("\t\t 수정할 책 선택 : ");
        System.out.println(sb);
        String num = sc.nextLine();
        while(!num.matches("^[1-9]+$")) {
            System.out.println("잘못된 입력입니다.");
            System.out.print(sb);
            num = sc.nextLine();
        }
        sb.setLength(0);

        StringBuilder sb1 = new StringBuilder();
        sb1.append("┌────────────────────────────────────────────────────────────────────┐\n");
        sb1.append("│ 1.제목 │ 2.저자 │ 3.장르 │ 4.출판사 │ 5.발행연도 │ 6.가격 │ 0.취소 │\n");
        sb1.append("└────────────────────────────────────────────────────────────────────┘\n");
        sb1.append("수정할 항목 입력 :");
        System.out.println(sb1);
        String input = sc.nextLine();
        while(!input.matches("^[0-6]+$")) {
            System.out.println("잘못된 입력입니다. 숫자만 입력해주세요.");
            System.out.print(sb);
            input = sc.nextLine();
        }

        BookVO uBook = new BookVO();
        switch (input) {
            case "1":
                title = BookInsertRandom.inputLimit("제목 : ", 50);
                uBook.setId(title);
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
    public void updateResult(int count){
        if (count > 0) {
            System.out.println("정상적으로 삭제되었습니다.");
        } else {
            System.out.println("정상적으로 삭제되지 않았습니다.");
        }
    }

    public void insertResult(int count) {
        if (count > 0) {
            System.out.println("책이 정상적으로 입고 되었습니다.");
            System.out.println();
        } else {
            System.out.println("책이 정상적으로 입고 되지 않았습니다.");
        }
    }
}
