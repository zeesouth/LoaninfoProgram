package egovframework.sample.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import egovframework.sample.service.BooksService;
import egovframework.sample.service.BooksVO;
import egovframework.sample.service.UsersService;

public class showReturnInfoController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("회원 검색 -> 회원 선택 창");
		
		// 1. DB연동 처리
		AbstractApplicationContext container = new GenericXmlApplicationContext(
				"egovframework/spring/context-*.xml"
				);
		
		
		BooksService booksService = (BooksService) container.getBean("booksService");
		boolean result = false;
		
		ModelAndView mav = new ModelAndView();
		try {
			// 3. 받아온 데이터를 추출한다.
			String returnBookCallNum = request.getParameter("returnBookCallNum");
			String returnName = request.getParameter("returnName");
			
			System.out.println(returnBookCallNum + "    " + returnName);
			
			BooksVO bvo = new BooksVO();
			bvo.setCallNum(returnBookCallNum);
			BooksVO bList = booksService.selectBook(bvo);
			
			mav.addObject("bookName", bList.getName());
			mav.addObject("userName", returnName);
			
			result = true;
		} catch (Exception e) {
			
		}

		mav.addObject("result", result);
		mav.setViewName("/loaninfo/showReturnInfo");
		
		return mav;
	}

}
