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

public class selectBooksInfoController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=utf-8");
		// TODO Auto-generated method stub
		System.out.println("도서 정보 상세 보기");

		AbstractApplicationContext container = new GenericXmlApplicationContext(
				"egovframework/spring/context-*.xml"
				);
		BooksService booksService = (BooksService) container.getBean("booksService");
		
		Boolean result = (Boolean) (request.getAttribute("result") == null ? true : request.getAttribute("result"));
		
		
		///////////////////////
		//System.out.println("This is 1Controller!!!");
		
		
		BooksVO vo = new BooksVO();
		List<BooksVO> bookList = booksService.selectBooksList(vo);
		
		String updateUser=request.getParameter("updateUser");   //수정을 클릭했을 경우
		String deleteUser=request.getParameter("deleteteUser"); //삭제를 클릭했을 경우
		
		int b_id = Integer.parseInt(request.getParameter("b_id")); //도서 정보 상세 보기 화면으로 안 넘어갔던 이유는 id가 안 왔는데 받으려고 해서가 아닐까
//		String b_name = request.getParameter("b_name");
//		String b_author = request.getParameter("b_author");
//		String b_publisher = request.getParameter("b_publisher");
//		String b_callNum = request.getParameter("b_callNum");
		
		
		//System.out.println("This is 2Controller!!!");
		vo.setId(b_id);
		
		BooksVO bvo = booksService.selectBook(vo);


		
		container.close();

		// 3. 화면 네비게이션
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("bookList", bookList);
		//mav.addObject("result", result);
		mav.addObject("b_id", b_id);
		mav.addObject("b_name", bvo.getName());
		mav.addObject("b_author", bvo.getAuthor());
		mav.addObject("b_publisher", bvo.getPublisher());
		mav.addObject("b_callNum", bvo.getCallNum());
		
		
		mav.setViewName("books/selectBooksInfo");
		return mav;
		
		
	}

}














