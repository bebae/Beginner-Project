package Sign;

import java.util.List;
import java.util.Scanner;

public class SignView {
	public void printSigns(List<SignVO> list) throws Exception {

		SignDAO signDAO = SignDAO.getInstance();

		Scanner scanner = new Scanner(System.in);

		System.out.println("-----------------로그인-----------------");
		System.out.print("아이디를 입력하세요: ");
		String id = scanner.nextLine();

		System.out.print("비밀번호를 입력하세요: ");
		String password = scanner.nextLine();

		if (signDAO.checkLogin(id, password)) {
			System.out.println("로그인 성공!");
			// 로그인 성공 후에는 사용자가 원하는 작업을 진행하도록 메뉴를 출력하는 등의 코드를 추가할 수 있습니다.
		} 
	}
}
