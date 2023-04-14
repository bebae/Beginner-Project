package Sign;

import java.util.List;

public class SignController {
    public String getSigns() throws Exception {
        SignService service = new SignService();
        List<SignVO> list = service.getSigns();
        SignView view = new SignView();
        return view.printSigns(list);
    }
}
