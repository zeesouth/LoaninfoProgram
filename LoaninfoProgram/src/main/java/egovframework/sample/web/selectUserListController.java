package egovframework.sample.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;import org.springmodules.validation.util.condition.string.LengthRangeStringCondition;

import egovframework.sample.service.BooksService;
import egovframework.sample.service.LoaninfoService;
import egovframework.sample.service.UsersService;
import egovframework.sample.service.UsersVO;

public class selectUserListController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("회원 검색 -> 회원 선택 창");
		
		// 1. DB연동 처리
		AbstractApplicationContext container = new GenericXmlApplicationContext(
				"egovframework/spring/context-*.xml"
				);

		// 2. Spring 컨테이너로부터 LoaninfoProgramServiceImpl 객체를 lookup한다.
		UsersService usersService = (UsersService) container.getBean("usersService");

		// 3. 받아온 데이터를 추출한다.
		String name = request.getParameter("name");
		boolean result = false;
		
		//System.out.println(name);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", name);
		
		UsersVO vo = new UsersVO();
		vo.setName(name);
		try {
			List<UsersVO> ulist = usersService.selectUserList(vo);
			mav.addObject("ulist", ulist);
			System.out.println(ulist.size());
			if (ulist.size() == 0) {
				throw new Exception();
			}
			result = true;
			
		} catch (Exception e) {
			result = false;
		}
		
		
		
		mav.addObject("result", result);
		mav.setViewName("/users/selectUserList");
		
		return mav;
	}
	
	
}