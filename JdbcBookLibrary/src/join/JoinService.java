package join;

import java.util.List;

public class JoinService {
	private JoinDAO dao = new JoinDAO();
  public List<CustomerVO> getJoins() throws Exception {
 	 return dao.getJoins();
  }
  
  public CustomerVO getJoin(String searchId) throws Exception {
 	 return dao.getJoin(searchId);
  }
  
  public int insertJoin(CustomerVO vo) throws Exception {
 	 return dao.insertJoin(vo);
  }
  
  public int updateJoin(CustomerVO vo) {
 	 return dao.updateJoin(vo);
  }
  
  public int deleteJoin(String deleteId) {
 	 return dao.deleteJoin(deleteId);
  }

	public static JoinService getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}
