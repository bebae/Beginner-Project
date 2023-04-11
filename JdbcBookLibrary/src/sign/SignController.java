package sign;

import java.util.List;

public class SignController {
	public void getSigns() throws Exception {
	 	 SignService service = new SignService();
	 	 List<SignVO> list = service.getSigns();
	 	 SignView view = new SignView();
	 	 view.printSigns(list);
	  }
}
