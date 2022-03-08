//작성자:김나희
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

public class selectBookController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=utf-8");
		// TODO Auto-generated method stub
		System.out.println("도서 조회 처리");

		AbstractApplicationContext container = new GenericXmlApplicationContext(
				"egovframework/spring/context-*.xml"
				);
		BooksService booksService = (BooksService) container.getBean("booksService");
		
		Boolean result = (Boolean) (request.getAttribute("result") == null ? true : request.getAttribute("result"));
		
		
		///////////////////////
		
		BooksVO vo = new BooksVO();
		List<BooksVO> bookList = booksService.selectBooksList(vo);
		

		
		container.close();

		// 3. 화면 네비게이션
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("bookList", bookList); //원래 selectBooksInfo에는 이게 없어서 값을 못 보내준건가???
		mav.addObject("result", result);
		
		//mav.setViewName("forward:WEB-INF/jsp/books/selectBooksList.jsp");
		mav.setViewName("books/selectBooksList");
		return mav;
		
		
	}

}














