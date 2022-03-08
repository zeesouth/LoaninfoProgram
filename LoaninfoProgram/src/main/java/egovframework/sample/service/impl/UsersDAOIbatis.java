package egovframework.sample.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.sample.service.LoaninfoVO;
import egovframework.sample.service.UsersDAO;
import egovframework.sample.service.UsersVO;

@Repository("daoUsers")
public class UsersDAOIbatis extends EgovAbstractDAO implements UsersDAO {

	public UsersDAOIbatis() {
		System.out.println("====> UsersDAOIbatis 객체 생성");
	}
	
	
	@Override
	public void insertUser(UsersVO vo) throws Exception {
		System.out.println("====> iBATIS로 insertUser() 기능 처리");
		insert("UsersDAO.insertUser", vo);
		
	}
	

	@Override
	public UsersVO selectUser(UsersVO vo) throws Exception {
		System.out.println("====> iBATIS로 selectUser() 기능 처리");
		return (UsersVO) select("UsersDAO.selectUser", vo);
	}
	
	public UsersVO selectUserI(UsersVO vo) throws Exception {
		System.out.println("====> iBatis로 selectUserI() 기능 처리");
		return (UsersVO) select("UsersDAO.selectUserI", vo);

	}
	
	@Override
	public UsersVO selectAllUser(UsersVO vo) throws Exception {
		System.out.println("====> iBATIS로 selectAllUser() 기능 처리");
		return (UsersVO) select("UsersDAO.selectAllUser", vo);
	}
	
	@Override
	public List<UsersVO> selectAllUserList(UsersVO vo) throws Exception {
		System.out.println("====> iBATIS로 selectAllUserList() 기능 처리");
		return (List<UsersVO>) list("UsersDAO.selectAllUserList", vo);
	}
	
	@Override
	public List<UsersVO> selectUserList(UsersVO vo) throws Exception {
		System.out.println("====> iBATIS로 selectUserList() 기능 처리");
		return (List<UsersVO>) list("UsersDAO.selectUserList", vo);
	}
	
	public int selectUserListTotCnt(UsersVO vo) throws Exception {
		return (int) select("UsersDAO.selectUserListTotCnt", vo);
	}


	@Override
	public void deleteUser(UsersVO vo) throws Exception {
		delete("UsersDAO.deleteUser", vo);
		
	}


	@Override
	public void updateUser(UsersVO vo) throws Exception {
		delete("UsersDAO.updateUser", vo);
		
	}


	@Override
	public int selectUserMaxId() throws Exception {
		return (int) select("UsersDAO.selectUserMaxId");
	}


	@Override
	public int IDS_CNT() throws Exception {
		// TODO Auto-generated method stub
		return (int) select("IDS_CNT");
	}

}
