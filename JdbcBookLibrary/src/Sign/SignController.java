package Sign;

import java.util.List;

public class SignController {

    public SignVO getSigns() throws Exception {
        SignService service = new SignService();
        List<SignVO> list = service.getSigns();
        SignView view = new SignView();
        String loginId = view.printSigns(list);

        if (loginId == null) {
            return null;
        }

        return service.getSign(loginId);
    }
}
