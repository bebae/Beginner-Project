package Book;

import java.util.*;


public class BookInsertRandom {
    private static BookInsertRandom instance = new BookInsertRandom();
    public static BookInsertRandom getInstance() {
        return instance;
    }
    private BookController controller = BookController.getInstance();
    // SQL에서 해당 키값이 존재하는지 검사하기 위한 Set


    private static final Random RANDOM = new Random();

    public static String inputLimit(String message, int limit) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do {
            System.out.print(message);
            input = scanner.nextLine();
            if (input.length() > limit) {
                System.out.println("입력값이 잘못되었습니다. 다시 입력해주세요.");
            }
        } while (input.length() > limit);
        return input;
    }
    public static String inputLimit(String message, int limit, boolean yearYN) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String input = "";
        do {
            System.out.print(message);
            input = scanner.nextLine();
            sb.setLength(0); // 이전 메시지 초기화
            if (input.length() > limit) {
                sb.append("입력값이 잘못되었습니다.");
            } else if (yearYN && !input.matches("[0-9]{4}")) { // 0~9의 4자리 숫자만 허용
                sb.append("숫자 4자리만 입력 가능합니다.");
            } else if (yearYN && Integer.parseInt(input) >= 2024) {   // 2024년 이전에 출판된 책만 가능하게
                sb.append("2024년 이전 출판된 책만 입력 가능합니다.");
            } else if (!yearYN && !input.matches("[0-9]+")) { // 숫자만 허용
                sb.append("숫자만 입력 가능합니다.");
            }
            if (sb.length() > 0) {
                sb.append(" 다시 입력해주세요.\n");
                System.out.print(sb);
            }
        } while (input.length() > limit || (yearYN && !input.matches("[0-9]{4}") || (yearYN && Integer.parseInt(input) >= 2024)));
        return input;
    }

    // 랜덤한 번호 생성 메소드
    public String generateKey() throws Exception {
        List<BookVO> selectBook = controller.selectBook();
        Set<String> KEY_SET = new HashSet<>();

        for (BookVO vo: selectBook) {
            KEY_SET.add(vo.getId());
        }
        String key = generateRandomKey();
        // 중복 체크를 위해 Set에 있는지 검사
        while (KEY_SET.contains(key)) {
            key = generateRandomKey();
        }
        // Set에 추가
        KEY_SET.add(key);
        return key;
    }

    // 랜덤한 번호 생성을 수행하는 내부 메소드
    public String generateRandomKey() {
        StringBuilder sb = new StringBuilder();

        // 'SE' 혹은 'SJ' 추가
        sb.append(RANDOM.nextBoolean() ? "SE" : "SJ");
        // '0000' 추가
        sb.append("0000");
        // 6자리 랜덤 숫자 추가
        for (int i = 0; i < 5; i++) {
            if (i == 0) sb.append((char) (RANDOM.nextInt(9)+1 + '0'));
            sb.append((char) (RANDOM.nextInt(10) + '0')); // 0~9 중 랜덤한 숫자 문자를 추가
        }
        return sb.toString();
    }
    public String generateRandomCallString(int year) {
        // 청구 기호 만드는 메소드 예) 813.62-발행연도-등록순 번호
        StringBuilder sb = new StringBuilder();
        // 첫번째 구역(정수 부분) 000 ~ 999
        sb.append(String.format("%03d", RANDOM.nextInt(1000)));
        sb.append(".");
        // 두번째 구역(소수 부분) 0~99
        sb.append(Math.abs(RANDOM.nextInt(109)-9));
        sb.append("-");
        // 세번째 구역(년도부분)
        sb.append(String.valueOf(year).substring(2));
        sb.append("-");
        // 4번째 구역 (순서)
        sb.append(Math.abs(RANDOM.nextInt(89)-9));
        return sb.toString();
    }
    public String generateISBN() {
        StringBuilder sb = new StringBuilder();
        sb.append(RANDOM.nextBoolean() ? 978 : 979);
        sb.append(RANDOM.nextBoolean() ? 89 : 11);
        sb.append(RANDOM.nextInt(9)+1);
        sb.append(String.format("%07d", RANDOM.nextInt(1000000)));
        return sb.toString();
    }

//    public static void main(String[] args) {
//        BookInsertRandom random = new BookInsertRandom();
//        inputLimit("발행연도 : ", 4, true);
//        System.out.println(random.generateRandomKey());
//        System.out.println(random.generateRandomCallString(2023));
//    }
}
