package egovframework.sample.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springmodules.validation.util.condition.string.LengthRangeStringCondition;

import egovframework.sample.service.UsersService;
import egovframework.sample.service.UsersVO;

public class selectAllUserListController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=utf-8");
		System.out.println("회원 조회");
		
		// 1. DB연동 처리
		AbstractApplicationContext container = new GenericXmlApplicationContext(
				"egovframework/spring/context-*.xml"
				);

		// 2. Spring 컨테이너로부터 LoaninfoProgramServiceImpl 객체를 lookup한다.
		UsersService usersService = (UsersService) container.getBean("usersService");
		
		List<UsersVO> ulist = usersService.selectUserList(new UsersVO());

		ModelAndView mav = new ModelAndView();
		mav.addObject("ulist", ulist);
		mav.setViewName("users/selectUser");
		
		return mav;
	}
	
	
}