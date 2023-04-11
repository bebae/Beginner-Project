package sign;

import java.util.List;

public class SignView {
	public void printSigns(List<SignVO> list) {
 	 for (SignVO vo : list) {
 		 System.out.println(vo);
 	 }
  }
}
