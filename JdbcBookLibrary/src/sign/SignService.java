package sign;

import java.util.List;

public class SignService {
	private SignDAO dao = new SignDAO();
  public List<SignVO> getSigns() throws Exception {
 	 return dao.getSigns();
  }
  
  public SignVO getSign(String searchId) throws Exception {
 	 return dao.getSign(searchId);
  }
  
  public int insertSign(SignVO vo) throws Exception {
 	 return dao.insertSign(vo);
  }
  
  public int updateSign(SignVO vo) {
 	 return dao.updateSign(vo);
  }
  
  public int deleteSign(String deleteId) {
 	 return dao.deleteSign(deleteId);
  }

	public static SignService getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}
