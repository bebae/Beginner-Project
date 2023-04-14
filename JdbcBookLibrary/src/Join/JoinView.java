package Join;

import java.util.List;
import java.util.Scanner;

public class JoinView {

    public void printJoins1(List<JoinVO> list) throws Exception {

        JoinDAO joinDAO = JoinDAO.getInstance1();

        Scanner scanner = new Scanner(System.in);

        boolean run = true;
        while (run) {
            StringBuilder sb = new StringBuilder();
            sb.append("┌───────────────────────────────────────────────┐\n");
            sb.append("├                     회원가입                     ┤\n"); 
            sb.append("└───────────────────────────────────────────────┘\n");
            sb.append("┌───────────────────────────────────────────────┐\n");
            sb.append("├ 1.입력 2.수정 3.탈퇴 4.회원전체출력 5.메인메뉴   ┤\n");
            sb.append("└───────────────────────────────────────────────┘\n");
            sb.append("메뉴를 선택하세요: ");
            System.out.print(sb);
            sb.setLength(0);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // 입력
                    System.out.print("생성할 아이디를 입력하세요: ");
                    String id = scanner.nextLine();
                    System.out.print("생성할 비밀번호를 입력하세요: ");
                    String password = scanner.nextLine(); // 비밀번호가 맞는지 확인하는 코드 추가하기
                    System.out.print("이름을 입력하세요: ");
                    String name = scanner.nextLine();

                    System.out.print("생년월일 6자리를 입력하세요: ");
                    String birthDate = scanner.nextLine();
                    while (birthDate.length() != 6) {
                        System.out.print("잘못된 입력입니다. 생년월일 6자리를 입력하세요: ");
                        birthDate = scanner.nextLine();
                        if (birthDate.length() != 6) {
                            System.out.println("생년월일은 6자리로 입력해주세요.");
                        } else if (!birthDate.matches("^(0[0-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$")) {
                            System.out.println("생년월일 형식이 잘못되었습니다.");
                            birthDate = "";
                        }
                    }

                    System.out.print("휴대전화 번호를 입력하세요: ");
                    String phonenumber = scanner.nextLine();
                    System.out.print("이메일을 입력하세요: ");
                    String email = scanner.nextLine();
                    System.out.print("주소를 입력하세요: ");
                    String address = scanner.nextLine();

                    JoinVO joinVO = new JoinVO();

                    joinVO.setId(id);
                    joinVO.setPassword(password);
                    joinVO.setName(name);
                    joinVO.setBirthDate(birthDate);
                    joinVO.setPhonenumber(phonenumber);
                    joinVO.setEmail(email);
                    joinVO.setAddress(address);

                    int result = JoinDAO.insertJoin(joinVO);

                    if (result != 0) {
                        System.out.println("┌───────────────────────────────────────────────┐");
                        System.out.println("├  ****성공적으로 가입이 되었습니다!****  ┤");
                        System.out.println("└───────────────────────────────────────────────┘");
                        System.out.println("-----------[회원가입 확인]-----------");
                        System.out.println(joinVO.toString());
                    } else {
                    	System.out.println("┌───────────────────────────────────────────────┐");
                        System.out.println("├         회원가입에 실패했습니다         ┤");
                        System.out.println("└───────────────────────────────────────────────┘");
                    }
                    break;

                case 2: // 수정
                    System.out.print("아이디를 입력하세요: ");
                    String updateid = scanner.nextLine();

                    if (JoinDAO.isMemberExist(updateid)) {
                        System.out.println("존재하지 않는 아이디입니다.");
                        break;
                    }

                    System.out.print("수정할 비밀번호를 입력하세요: ");
                    String updatepassword = scanner.nextLine();
                    System.out.print("수정할 이름을 입력하세요: ");
                    String updatename = scanner.nextLine();
                    System.out.print("수정할 생년월일 6자리를 입력하세요: ");
                    String updatebirthDate = scanner.nextLine();
                    while (updatebirthDate.length() != 6) {
                        System.out.print("잘못된 입력입니다. 수정할 생년월일 6자리를 입력하세요: ");
                        updatebirthDate = scanner.nextLine();
                        if (updatebirthDate.length() != 6) {
                            System.out.println("생년월일은 6자리로 입력해주세요.");
                        } else if (!updatebirthDate.matches("^(0[0-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$")) {
                            System.out.println("생년월일 형식이 잘못되었습니다.");
                            updatebirthDate = "";
                        }
                    }
                    System.out.print("수정할 휴대전화 번호를 입력하세요: ");
                    String updatephonenumber = scanner.nextLine();
                    System.out.print("수정할 이메일을 입력하세요: ");
                    String updateemail = scanner.nextLine();
                    System.out.print("수정할 주소를 입력하세요: ");
                    String updateaddress = scanner.nextLine();

                    JoinVO updatedJoinVO = new JoinVO();

                    updatedJoinVO.setId(updateid);
                    updatedJoinVO.setPassword(updatepassword);
                    updatedJoinVO.setName(updatename);
                    updatedJoinVO.setBirthDate(updatebirthDate);
                    updatedJoinVO.setPhonenumber(updatephonenumber);
                    updatedJoinVO.setEmail(updateemail);
                    updatedJoinVO.setAddress(updateaddress);

                    int result1 = JoinDAO.updateJoin(updatedJoinVO);

                    if (result1 != 0) {
                    	System.out.println("┌───────────────────────────────────────────────┐");
                        System.out.println("├       회원정보가 수정되었습니다!      ┤");
                        System.out.println("└───────────────────────────────────────────────┘");
                    } else {
                    	System.out.println("┌───────────────────────────────────────────────┐");
                        System.out.println("├      회원정보수정에 실패했습니다      ┤");
                        System.out.println("└───────────────────────────────────────────────┘");
                    }
                    break;

                case 3: // 탈퇴
                    System.out.print("탈퇴할 아이디를 입력하세요: ");
                    String deleteid = scanner.nextLine();

                    if (JoinDAO.isMemberExist(deleteid)) {
                        System.out.println("존재하지 않는 아이디입니다.");
                        break;
                    }

                    System.out.print("비밀번호를 입력하세요: ");
                    String deletepassword = scanner.nextLine();

                    int result2 = deleteJoin(deleteid);

                    if (result2 != 0) {
                    	System.out.println("┌───────────────────────────────────────────────┐");
                        System.out.println("├       성공적으로 탈퇴하였습니다.      ┤");
                        System.out.println("└───────────────────────────────────────────────┘");

                    } else {
                    	System.out.println("┌───────────────────────────────────────────────┐");
                        System.out.println("├      회원정보수정에 실패했습니다      ┤");
                        System.out.println("└───────────────────────────────────────────────┘");
                    }
                    break;
                case 4: // 회원전체출력
                    List<JoinVO> joinList = joinDAO.selectAllJoin();
                    if (joinList != null) { // joinList가 null인지 체크
                        if (joinList.size() > 0) {
                        	System.out.println("┌───────────────────────────────────────────────┐");
                            System.out.println("├ -----------[전체 회원 목록]----------- ┤");
                            System.out.println("└───────────────────────────────────────────────┘");

                            for (JoinVO joinVO1 : joinList) {
                                System.out.println(joinVO1.toString());
                            }
                        } else {
                            System.out.println("등록된 회원이 없습니다.");
                        }
                    } else {
                        System.out.println("회원 목록을 가져오는 데 실패했습니다."); // joinList가 null인 경우 처리
                    }
                    break;
                default: //종료
                    System.out.println("메인메뉴로 돌아갑니다.");
                    run = false;
                    break;
            }
        }
    }

    private int deleteJoin(String id) throws Exception {
        return JoinDAO.deleteJoin(id);
    }


}


 
