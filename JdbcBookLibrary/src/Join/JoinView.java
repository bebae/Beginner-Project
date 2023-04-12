package Join;

import java.util.List;
import java.util.Scanner;

public class JoinView {
	public void printJoins(List<CustomerVO> list) {
	 	 
	 	 JoinDAO joinDAO = JoinDAO.getInstance();
	 	 
	 	 Scanner scanner = new Scanner(System.in);
     
	 	 System.out.println("-----------------회원가입-----------------");
	 	 System.out.print("생성할 아이디를 입력하세요: ");
	 	 String id = scanner.nextLine();
	 	 
	 	 System.out.print("비밀번호를 입력하세요: ");
	 	 String password = scanner.nextLine(); 
	 	 
	 	 System.out.print("생일을 입력하세요[ex)2023-01-01]: ");
	 	 String birthDate = scanner.nextLine();
	 	 
	 	 System.out.print("휴대전화 번호를 입력하세요[ex)010-1234-1234]: ");
	 	 String phonenumber = scanner.nextLine();
	 	 
	 	 System.out.print("이메일을 입력하세요: ");
	 	 String email = scanner.nextLine();
	 	 
	 	 System.out.print("주소를 입력하세요: ");
	 	 String address = scanner.nextLine();
	 	 
	 	 System.out.print("대출번호를 입력하세요: ");
	 	 double loansnumber = scanner.nextDouble();
	 	 
	 }
}
