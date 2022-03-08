package egovframework.sample.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import egovframework.sample.service.UsersService;

public class selectAllUserController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("검색된 회원 창에서 회원 선택");
		
		response.setContentType("text/html; charset=utf-8");
		// 1. DB연동 처리
		AbstractApplicationContext container = new GenericXmlApplicationContext(
				"egovframework/spring/context-*.xml"
				);
		
		
		// 2. Spring 컨테이너로부터 LoaninfoProgramServiceImpl 객체를 lookup한다.
		UsersService usersService = (UsersService) container.getBean("usersService");
		
		String code = request.getParameter("checkuser");
		String id = "";
		String name = "";
		String phoneNum = "";
		
		container.close();
		System.out.println(code);
		
		
		ModelAndView mav = new ModelAndView();
		
		if (code == null || code.equals("null")) {
			mav.addObject("result", false);
			mav.setViewName("forward:/");
		} else {
			mav.addObject("code", code);
			mav.addObject("id", id);
			mav.addObject("name", name);
			mav.addObject("phoneNum", phoneNum);
			mav.setViewName("forward:/selectAllUser.do");
		}
		
		return mav;
	}

}
