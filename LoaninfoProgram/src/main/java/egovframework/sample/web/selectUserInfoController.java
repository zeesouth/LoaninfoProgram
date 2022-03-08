package egovframework.sample.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import egovframework.sample.service.UsersService;
import egovframework.sample.service.UsersVO;

public class selectUserInfoController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("검색된 회원 창에서 회원 선택");
		
		// 1. DB연동 처리
		AbstractApplicationContext container = new GenericXmlApplicationContext(
				"egovframework/spring/context-*.xml"
				);
		
		
		// 2. Spring 컨테이너로부터 LoaninfoProgramServiceImpl 객체를 lookup한다.
		UsersService usersService = (UsersService) container.getBean("usersService");
		
		String u_id = (String) request.getParameter("u_id");
		
		UsersVO uvo = new UsersVO();
		uvo.setId(u_id);
		uvo = usersService.selectUserI(uvo);
		

		ModelAndView mav = new ModelAndView();
		mav.addObject("uvo", uvo);
		mav.setViewName("users/userInfo");
		
		
		return mav;
	}

}