package egovframework.sample.web;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.sample.service.LoaninfoService;
import egovframework.sample.service.LoaninfoVO;
import egovframework.sample.service.impl.LoaninfoDAOIbatis;

import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
public class LoaninfoListController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		res.setContentType("text/html; charset=utf-8");
		System.out.println("Loaninfo 목록 검색 처리");
		
		// 2. DB연동 처리
		
		AbstractApplicationContext container = new GenericXmlApplicationContext(
				"egovframework/spring/context-*.xml"
				);

		// 2. Spring 컨테이너로부터 LoaninfoProgramServiceImpl 객체를 lookup한다.
		LoaninfoService loaninfoService = (LoaninfoService) container.getBean("loaninfoService");
//		LoaninfoDAOIbatis loaninfoService = new LoaninfoDAOIbatis();
		LoaninfoVO vo = new LoaninfoVO();
		
		String message = (String) req.getAttribute("message");
		Boolean result = (Boolean) req.getAttribute("result");
		

		try {
			String uid = req.getAttribute("uid").toString();
			if (uid != null)
					vo.setU_id(uid);
		} catch (Exception e) {}
		
		
		List<LoaninfoVO> loaninfoList = loaninfoService.selectLoaninfoList(vo);
				
		// 3. 검색 결과를 세션에 저장하고 목록 화면으로 이동한다.
		
		
		ModelAndView mav = new ModelAndView();
		
		res.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = res.getWriter();
		if(message != null) {
			if(!message.equals("")) {
				out.print("<script>");
				out.print("alert('"+message+"')");
				out.print("</script>");
				out.flush();
			}
		}
		
		mav.addObject("result", result);
		mav.addObject("loaninfoList", loaninfoList);
		mav.setViewName("loaninfo/selectLoaninfoList");
		container.close();
		
		
		return mav;
	}

}
