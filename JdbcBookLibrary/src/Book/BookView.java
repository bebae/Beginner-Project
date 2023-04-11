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
        sb.append("──────────────────────────────────────────────\n");
        sb.append(" 1. 로그인 | 2. 회원가입 | 3. 비회원 | 0. 종료\n");
        sb.append("──────────────────────────────────────────────\n");
        sb.append("메뉴를 선택 > ");
        System.out.print(sb);
        sb.setLength(0);
        String input = sc.nextLine();
        while(!input.matches("^[0-3]+$")) {
            System.out.println("잘못된 입력입니다.");
            System.out.print(sb);
            input = sc.nextLine();
        }
        return Integer.parseInt(input);
    }
    // 로그인 후 보일 목록

    public void selectBook(List<BookVO> books){
        // 쪽으로 나타내는 전체 리스트 나중에 추가 작업 필요
        System.out.println("전체 목록");
        for (BookVO vo: books) {
            System.out.println(vo);
        }
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
