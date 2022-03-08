package egovframework.sample.service;

import java.util.List;

public interface BooksDAO {

	void insertBook(BooksVO vo) throws Exception;

	void deleteBook(BooksVO vo) throws Exception;

	void updateBook(BooksVO vo) throws Exception;
	
	
	// where id
	BooksVO selectBook(BooksVO vo) throws Exception;
	
	// like name
	List<BooksVO> selectBooksList_LikeName(BooksVO vo) throws Exception;
	
	// 전체 리스트 조회
	List<BooksVO> selectBooksList(BooksVO vo) throws Exception;


}
