package egovframework.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.sample.service.UsersDAO;
import egovframework.sample.service.UsersService;
import egovframework.sample.service.UsersVO;


@Service("usersService")
public class UsersServiceImpl implements UsersService {

	
	@Resource(name="daoUsers")
	private UsersDAO usersDAO;
	
	@Resource(name="egovIdGnrService_U")
	private EgovIdGnrService egovIdGnrService;
	
	
	@Override
	public void insertUser(UsersVO vo) throws Exception {
		
		// ID Generation Service
		String id = egovIdGnrService.getNextStringId();
		vo.setId(id);
		usersDAO.insertUser(vo);
	}
	

	@Override
	public UsersVO selectUser(UsersVO vo) throws Exception {
		return usersDAO.selectUser(vo);
	}
	
	@Override
	public UsersVO selectUserI(UsersVO vo) throws Exception {
		return usersDAO.selectUserI(vo);

	}
	
	@Override
	public UsersVO selectAllUser(UsersVO vo) throws Exception {
		return usersDAO.selectAllUser(vo);
	}
	
	@Override
	public List<UsersVO> selectAllUserList(UsersVO vo) throws Exception {
		return usersDAO.selectAllUserList(vo);
	}

	@Override
	public List<UsersVO> selectUserList(UsersVO vo) throws Exception {
		return usersDAO.selectUserList(vo);
	}

	@Override
	public int selectUserListTotCnt(UsersVO vo) throws Exception {
		// TODO Auto-generated method stub
		return usersDAO.selectUserListTotCnt(vo);
	}


	@Override
	public void deleteUser(UsersVO vo) throws Exception {
		usersDAO.deleteUser(vo);
		
	}


	@Override
	public void updateUser(UsersVO vo) throws Exception {
		usersDAO.updateUser(vo);
		
	}

	@Override
	public int selectUserMaxId() throws Exception {
		return usersDAO.selectUserMaxId();
	}


	@Override
	public int IDS_CNT() throws Exception {
		// TODO Auto-generated method stub
		return usersDAO.IDS_CNT();
	}



}
