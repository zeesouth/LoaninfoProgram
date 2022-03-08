package egovframework.sample.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.sample.service.LoaninfoDAO;
import egovframework.sample.service.LoaninfoService;
import egovframework.sample.service.LoaninfoVO;


@Service("loaninfoService")
public class LoaninfoServiceImpl extends EgovAbstractServiceImpl implements LoaninfoService {
	

	@Resource(name="daoLoaninfo")
	private LoaninfoDAO loaninfoDAO;

	@Resource(name="egovIdGnrService_L")
	private EgovIdGnrService egovIdGnrService;
	
	public void insertLoaninfo(LoaninfoVO vo) throws Exception {
		
		/** ID Generation Service*/
		String id = egovIdGnrService.getNextStringId();
		vo.setId(id);
		loaninfoDAO.insertLoaninfo(vo);
	}

	@Override
	public void updateLoaninfoIU(LoaninfoVO vo) throws Exception {
		loaninfoDAO.updateLoaninfoIU(vo);
		
	}

	@Override
	public void updateLoaninfoIB(LoaninfoVO vo) throws Exception {
		loaninfoDAO.updateLoaninfoIB(vo);
		
	}
	
	@Override
	public void updateLoaninfoUB(LoaninfoVO vo) throws Exception {
		loaninfoDAO.updateLoaninfoUB(vo);
		
	}

	@Override
	public LoaninfoVO selectLoaninfoBLR(LoaninfoVO vo) throws Exception {
		return loaninfoDAO.selectLoaninfoBLR(vo);
	}

	@Override
	public LoaninfoVO selectLoaninfoUBLR(LoaninfoVO vo) throws Exception {
		return loaninfoDAO.selectLoaninfoUBLR(vo);
	}

	@Override
	public List<LoaninfoVO> selectLoaninfoList(LoaninfoVO vo) throws Exception {
		return loaninfoDAO.selectLoaninfoList(vo);
	}
	
	@Override
	public int selectLoaninfoListTotCnt(LoaninfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		return loaninfoDAO.selectLoaninfoListTotCnt(vo);
	}

	@Override
	public List<LoaninfoVO> selectLoaninfoList_notme(LoaninfoVO vo) throws Exception {
		// TODO Auto-generated method stub
		return loaninfoDAO.selectLoaninfoList_notme(vo);
	}

	@Override
	public void deleteLoaninfo(LoaninfoVO vo) throws Exception {
		loaninfoDAO.deleteLoaninfo(vo);
		
	}

}
