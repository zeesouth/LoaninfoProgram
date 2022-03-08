package egovframework.sample.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.sample.service.LoaninfoDAO;
import egovframework.sample.service.LoaninfoVO;

@Repository("daoLoaninfo")
public class LoaninfoDAOIbatis extends EgovAbstractDAO implements LoaninfoDAO {
	
	public LoaninfoDAOIbatis() {
		System.out.println("====> LoaninfoDAOIbatis 객체 생성");
	}
	
	@Override
	public void insertLoaninfo(LoaninfoVO vo) throws Exception {
		System.out.println("====> iBATIS로 insertLoaninfo() 기능 처리");
		insert("LoaninfoDAO.insertLoaninfo", vo);
		
	}

	@Override
	public void updateLoaninfoIU(LoaninfoVO vo) throws Exception {
		System.out.println("====> iBATIS로 updateLoaninfoIU() 기능 처리");
		update("LoaninfoDAO.updateLoaninfoIU", vo);
		
	}

	@Override
	public void updateLoaninfoIB(LoaninfoVO vo) throws Exception {
		System.out.println("====> iBATIS로 updateLoaninfoIB() 기능 처리");
		update("LoaninfoDAO.updateLoaninfoIB", vo);
		
	}

	@Override
	public LoaninfoVO selectLoaninfoBLR(LoaninfoVO vo) throws Exception {
		System.out.println("====> iBATIS로 selectLoaninfoBLR() 기능 처리");
		return (LoaninfoVO) select("LoaninfoDAO.selectLoaninfoBLR", vo);
	}

	@Override
	public LoaninfoVO selectLoaninfoUBLR(LoaninfoVO vo) throws Exception {
		System.out.println("====> iBATIS로 selectLoaninfoUBLR() 기능 처리");
		return (LoaninfoVO) select("LoaninfoDAO.selectLoaninfoUBLR", vo);
	}

	@Override
	public List<LoaninfoVO> selectLoaninfoList(LoaninfoVO vo) throws Exception {
		System.out.println("====> iBATIS로 selectLoaninfoList() 기능 처리");
		return (List<LoaninfoVO>) list("LoaninfoDAO.selectLoaninfoList", vo);
	}

	@Override
	public int selectLoaninfoListTotCnt(LoaninfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		return (int) select("LoaninfoDAO.selectLoaninfoListTotCnt", vo);
	}

	@Override
	public List<LoaninfoVO> selectLoaninfoList_notme(LoaninfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		return (List<LoaninfoVO>) list("LoaninfoDAO.selectLoaninfoList_notme", vo);
	}

	@Override
	public void updateLoaninfoUB(LoaninfoVO vo) throws Exception {
		System.out.println("====> iBATIS로 updateLoaninfoUB() 기능 처리");
		update("LoaninfoDAO.updateLoaninfoUB", vo);
		
	}

	@Override
	public void deleteLoaninfo(LoaninfoVO vo) throws Exception {
		delete("LoaninfoDAO.deleteLoaninfo", vo);
		
	}
	
	

}
