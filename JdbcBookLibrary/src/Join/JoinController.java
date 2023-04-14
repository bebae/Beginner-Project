package Join;

import java.util.List;

public class JoinController {
	JoinService service = new JoinService();
	JoinView view = new JoinView();
	public void getJoins() throws Exception {
		List<JoinVO> list = service.getJoins();
		view.printJoins1(list);
	}
}
