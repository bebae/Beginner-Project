package Sign;

import java.util.List;
import java.util.Scanner;

public class SignView {
    public int printSigns(List<SignVO> list) throws Exception {

        SignDAO signDAO = SignDAO.getInstance();

        Scanner scanner = new Scanner(System.in);

        System.out.println("┌───────────────────────────────────────────────┐");
        System.out.println("├                                      로그인                                           ");
        System.out.println("└───────────────────────────────────────────────┘");
        System.out.print("아이디를 입력하세요: ");
        String id = scanner.nextLine();

        System.out.print("비밀번호를 입력하세요: ");
        String password = scanner.nextLine();

        if (signDAO.checkLogin(id, password)) {
            if (id.equals("admin")) { // 관리자 로그인 체크
                System.out.println("┌───────────────────────────────────────────────┐");
                System.out.println("├                              관리자 로그인 성공!                                       ");
                System.out.println("└───────────────────────────────────────────────┘");
                return 2;
                // admin 정보를 매개변수로 전달하여 getSigns() 메서드 호출
            } else {
                System.out.println("┌───────────────────────────────────────────────┐");
                System.out.println("├                                     로그인 성공!                                       ");
                System.out.println("└───────────────────────────────────────────────┘");
                return 1;
            }
        }
        return 0;
    }
}
