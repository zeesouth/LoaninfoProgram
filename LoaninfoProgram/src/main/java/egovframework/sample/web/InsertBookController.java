//새로 만든 파일, 작성자:김나희
package egovframework.sample.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import egovframework.sample.service.BooksService;
import egovframework.sample.service.BooksVO;

public class InsertBookController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		System.out.println("도서 등록 처리");
		
		boolean result = false;
		String message = "";

		
		AbstractApplicationContext container = new GenericXmlApplicationContext(
				"egovframework/spring/context-*.xml"
				);
		BooksService booksService = (BooksService) container.getBean("booksService");
		
		
		
		//String b_id = request.getParameter("b_id");
		String b_name = request.getParameter("b_name");
		String b_author = request.getParameter("b_author");
		String b_publisher = request.getParameter("b_publisher");
		String b_callNum = request.getParameter("b_callNum");
		
		
		if (b_name.equals("") || b_author.equals("") || b_publisher.equals("") || b_callNum.equals("")) {
			message="모든 정보를 기입해주세요.";
			result = false;
		} else {

			BooksVO vo = new BooksVO();
			vo.setCallNum(b_callNum);
			
			try {
				
				BooksVO rvo = booksService.selectBook(vo);
				if (b_callNum.equals(rvo.getCallNum())) {
					message="이미 등록된 청구번호 입니다.";
					result = false;
				} 
				
			} catch (Exception e) {
				vo.setName(b_name);
				vo.setAuthor(b_author);
				vo.setPublisher(b_publisher);
				booksService.insertBook(vo);
				result = true;
				message="도서 등록이 완료되었습니다.";
			}
		}
		
		
		container.close();
		
		PrintWriter out = response.getWriter();
		if(message != null) {
			if(!message.equals("")) {
				out.print("<script>");
				out.print("alert('"+message+"')");
				out.print("</script>");
				out.flush();
			}
		}

		// 3. 화면 네비게이션
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		System.out.println(message);
		
		String view = ((result == true) ? "forward:/selectBook.do" : "forward:insertBook.jsp" );
		
		mav.setViewName(view);
		return mav;
		
		
	}

}
