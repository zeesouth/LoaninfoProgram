package egovframework.sample.service;

import java.util.List;

public interface UsersService {
	
	// 회원가입 할 때 사용
	void insertUser(UsersVO vo) throws Exception;

	void deleteUser(UsersVO vo) throws Exception;

	void updateUser(UsersVO vo) throws Exception;
	
	// where id (유효한 이용자인지 main & 회원가입 중복확인)
	UsersVO selectUser(UsersVO vo) throws Exception;
	
	UsersVO selectUserI(UsersVO vo) throws Exception;
	
	// 전체 목록 조회
	UsersVO selectAllUser(UsersVO vo) throws Exception;
	List<UsersVO> selectAllUserList(UsersVO vo) throws Exception;
	
	// where name in (이름에 * 가 포함된 유저들 출력)
	List<UsersVO> selectUserList(UsersVO vo) throws Exception;
	
	int selectUserListTotCnt(UsersVO vo) throws Exception;
	
	int selectUserMaxId() throws Exception;
	
	int IDS_CNT() throws Exception;

}
