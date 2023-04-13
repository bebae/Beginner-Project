package Join;

import java.util.List;

public class JoinService {
	private JoinDAO dao = new JoinDAO();
  public List<JoinVO> getJoins() throws Exception {
 	 return dao.getJoins();
  }
  
  public JoinVO getJoin(String searchId) throws Exception {
 	 return dao.getJoin(searchId);
  }
  
  public int insertJoin(JoinVO vo) throws Exception {
 	 return dao.insertJoin(vo);
  }
  
  public int updateJoin(JoinVO vo) throws Exception {
 	 return dao.updateJoin(vo);
  }
  
  public int deleteJoin(String deleteId) throws Exception {
 	 return dao.deleteJoin(deleteId);
  }

	public static JoinService getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}
