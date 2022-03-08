package egovframework.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.sample.service.BooksDAO;
import egovframework.sample.service.BooksService;
import egovframework.sample.service.BooksVO;

@Service("booksService")
public class BooksServiceImpl extends EgovAbstractServiceImpl implements BooksService {
   
   
	@Resource(name="daoBooks")
	   private BooksDAO booksDAO;
	   
	@Resource(name="egovIdGnrService_B")
	   private EgovIdGnrService egovIdGnrService;
	
	
	public void insertBook(BooksVO vo) throws Exception {
		booksDAO.insertBook(vo);
		
	}
	
	
	public BooksVO selectBook(BooksVO vo) throws Exception {
		return booksDAO.selectBook(vo);
	}

	
	public List<BooksVO> selectBooksList_LikeName(BooksVO vo) throws Exception {
		return booksDAO.selectBooksList_LikeName(vo);
	}

	public List<BooksVO> selectBooksList(BooksVO vo) throws Exception {
		return booksDAO.selectBooksList(vo);
	}

////////여기에 update랑 delete를 추가했음	

	public void updateBook(BooksVO vo) throws Exception {
		booksDAO.updateBook(vo);
		
	}

	public void deleteBook(BooksVO vo) throws Exception {
		booksDAO.deleteBook(vo);
	}

////////
   

}