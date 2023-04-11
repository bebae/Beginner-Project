package join;

import java.util.List;

public class JoinController {
	public void getJoins() throws Exception {
	 	 JoinService service = new JoinService();
	 	 List<CustomerVO> list = service.getJoins();
	 	 JoinView view = new JoinView();
	 	 view.printJoins(list);
	  }
}
