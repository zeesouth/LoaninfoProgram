package egovframework.sample.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.sample.service.BooksDAO;
import egovframework.sample.service.BooksVO;
import egovframework.sample.service.LoaninfoVO;
import egovframework.sample.service.UsersVO;

@Repository("daoBooks")
public class BooksDAOIbatis extends EgovAbstractDAO implements BooksDAO {
	
	public BooksDAOIbatis() {
		System.out.println("====> BooksDAOIbatis 객체 생성");
	}

	@Override
	public BooksVO selectBook(BooksVO vo) throws Exception {
		System.out.println("====> iBATIS로 selectBook() 기능 처리");
		return (BooksVO) select("BooksDAO.selectBook", vo);
	}
	
	public List<BooksVO> selectBooksList_LikeName(BooksVO vo) throws Exception {
		System.out.println("====> iBATIS로 selectUserList_LikeName() 기능 처리");
		return (List<BooksVO>) list("BooksDAO.selectBooksList_LikeName", vo);
	}

	@Override
	public void insertBook(BooksVO vo) throws Exception {
		insert("BooksDAO.insertBook", vo);
		
	}
	//여기에 deleteBook이랑 updateBook을 새로 추가해야겠지?
	
	//////////////
	@Override
	public List<BooksVO> selectBooksList(BooksVO vo) throws Exception {
		// TODO Auto-generated method stub
		return (List<BooksVO>) list("BooksDAO.selectBooksList", vo);
	}

	@Override
	public void deleteBook(BooksVO vo) throws Exception {
		delete("BooksDAO.deleteBook", vo);
		
	}

	@Override
	public void updateBook(BooksVO vo) throws Exception {
		update("BooksDAO.updateBook", vo);
		
	}
	
}
