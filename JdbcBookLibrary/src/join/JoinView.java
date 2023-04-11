package join;

import java.util.List;

public class JoinView {
	public void printJoins(List<CustomerVO> list) {
	 	 for (CustomerVO vo : list) {
	 		 System.out.println(vo);
	 	 }
	}
}
