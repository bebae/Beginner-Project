package Book;

import java.util.List;
import java.util.Scanner;

public class BookView {
    private static BookView instance = new BookView();
    public  BookView() {
    }
    public static BookView getInstance() {
        return instance;
    }

    public void welcome() {
        System.out.println("나의 메모");
    }

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
    public int bookUse(Scanner sc, int nonMember){
        StringBuilder sb = new StringBuilder();
        sb.append("────────────────────────────────────────────────\n");
        sb.append(" 1. 책 목록 | 2. 책 검색 | ");
        if (0 != nonMember) {
            sb.append(" 3. 책 반납 | ");
        } else {
            sb.append(" 3. 책 입고 | ");
        }
        sb.append("0. 메인화면으로\n");
        sb.append("────────────────────────────────────────────────\n");
        sb.append("번호 선택 > ");
        System.out.print(sb);
        assert sc != null;          // sc가 null 이라면 예외를 throw 하는 문법
        String input = sc.nextLine();
        while(!input.matches("^[0-3]+$")) {
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
        sb.append(" 3. 출판사로 검색\n");
        sb.append(" 4. 검색취소 \n");
        sb.append("───────────────────────────────────────────────────────\n");
        sb.append("번호 선택 > ");
        System.out.print(sb);
        //assert sc != null;          // sc가 null 이라면 예외를 throw 하는 문법
        String input = sc.nextLine();
        while(!input.matches("^[0-4]+$")) {
            System.out.println("잘못된 입력입니다.");
            System.out.print(sb);
            input = sc.nextLine();
        }
        sb.setLength(0);
        return Integer.parseInt(input);
    }
    public void bookIfSelect(List<BookVO> books){
        // 쪽으로 나타내는 전체 리스트 나중에 추가 작업 필요
        System.out.println(" 전체 목록");
        System.out.println("───────────────────────────────────────────────────────────────");
        for (BookVO vo: books) {
            System.out.println(vo);
        }
        System.out.println("───────────────────────────────────────────────────────────────");
    }

    public void selectBook(List<BookVO> books){
        // 쪽으로 나타내는 전체 리스트 나중에 추가 작업 필요
        System.out.println(" 검색 목록");
        System.out.println("───────────────────────────────────────────────────────────────");
        for (BookVO vo: books) {
            System.out.println(vo);
        }
        System.out.println("───────────────────────────────────────────────────────────────");
    }
    public BookVO insertBook(Scanner sc){
        return null;
        //return new Book.BookVO(title, author, publisher, publicationYear, isbn, price, genre, 등록번호, 청구기호);
    }
    public BookVO updateBook(Scanner sc){
        System.out.print("수정할 번호 입력 :");
        int no = Integer.parseInt(sc.nextLine());
        return new BookVO();
    }
    public void updateResult(int count){
        if (count > 0) {
            System.out.println("정상적으로 삭제되었습니다.");
        } else {
            System.out.println("정상적으로 삭제되지 않았습니다.");
        }
    }
}
