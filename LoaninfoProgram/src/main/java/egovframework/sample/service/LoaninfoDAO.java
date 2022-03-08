package egovframework.sample.service;

import java.util.List;

public interface LoaninfoDAO {

	void insertLoaninfo(LoaninfoVO vo) throws Exception;
	
	// where id, u_id
	void updateLoaninfoIU(LoaninfoVO vo) throws Exception;
		
	// where id, b_id
	void updateLoaninfoIB(LoaninfoVO vo) throws Exception;
	
	// where u_id, b_id
	void updateLoaninfoUB(LoaninfoVO vo) throws Exception;
	
	void deleteLoaninfo(LoaninfoVO vo) throws Exception;

	// where b_id, loandate, returndate
	LoaninfoVO selectLoaninfoBLR(LoaninfoVO vo) throws Exception;
		
	// where u_id, b_id, loandate, returndate
	LoaninfoVO selectLoaninfoUBLR(LoaninfoVO vo) throws Exception;
	
	// where u_id
	List<LoaninfoVO> selectLoaninfoList(LoaninfoVO vo) throws Exception;
	
	List<LoaninfoVO> selectLoaninfoList_notme(LoaninfoVO vo) throws Exception;
	
	int selectLoaninfoListTotCnt(LoaninfoVO vo) throws Exception;
	
	
}