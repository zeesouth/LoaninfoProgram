//작성자:김나희
package egovframework.sample.web;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import egovframework.sample.service.BooksService;
import egovframework.sample.service.BooksVO;
import egovframework.sample.service.LoaninfoService;
import egovframework.sample.service.LoaninfoVO;

public class UpdateDeleteBookController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("도서 정보 수정/삭제 하기");

		AbstractApplicationContext container = new GenericXmlApplicationContext(
				"egovframework/spring/context-*.xml"
				);
		BooksService booksService = (BooksService) container.getBean("booksService");
		LoaninfoService loaninfoService = (LoaninfoService) container.getBean("loaninfoService");
		
		
		boolean result = false;
		String message="";

		
		BooksVO vo = new BooksVO();
		List<BooksVO> bookList = booksService.selectBooksList(vo);
		
		String updateBook=request.getParameter("updateBook");
		String deleteBook=request.getParameter("deleteBook");
		

		int b_id = Integer.parseInt(request.getParameter("b_id"));
	
		System.out.println(b_id);
		String b_name = request.getParameter("b_name");
		String b_author = request.getParameter("b_author");
		String b_publisher = request.getParameter("b_publisher");


		
		//수정 기능
		if (updateBook != null && updateBook.equals("수정") && deleteBook == null) {
			System.out.println("수정 기능 수행");
			
			// 들어온 어느 값 하나라도 공란일 경우
			if(b_name.equals("") || b_author.equals("") || b_publisher.equals("")) {
				message = "모든 정보를 입력해주세요! ";
				System.out.println("들어온 어느 값 하나가 공란임");
				
				result = false;
			}else {
				
				vo.setId(b_id);
				vo.setName(b_name);
				vo.setAuthor(b_author);
				vo.setPublisher(b_publisher);
				booksService.updateBook(vo);
				message = "도서 정보 수정이 완료되었습니다.";
				System.out.println("수정 완료");

				result = true;
			}
			
		}
		//삭제 기능
		else if (deleteBook != null && deleteBook.equals("삭제") && updateBook == null) {
			
			System.out.println("삭제 기능 수행");
			try {
				
				// 도서정보 삭제
				LoaninfoVO lvo = new LoaninfoVO();
				lvo.setB_id(b_id);
				loaninfoService.deleteLoaninfo(lvo);
				
				// 책 정보 삭제
				vo.setId(b_id);
				booksService.deleteBook(vo);
				
				message ="도서 정보가 삭제되었습니다 ";
				result = true;
				
			} catch (Exception e) {
				message="도서 삭제 실패. ";
				result = false;
				e.printStackTrace();
			}
			
		}

		
		container.close();
		
		PrintWriter out = response.getWriter();
		if(message != null) {
			if(!message.equals("")) {
				out.print("<script charset=CP949>");
				out.print("alert('"+message+"')");
				out.print("</script>");
				out.flush();
			}
		}

		
		// 3. 화면 네비게이션
		ModelAndView mav = new ModelAndView();
		
		
		String viewName = (result == true) ? "forward:/selectBook.do" : 
			("forward:selectBookInfo.do?b_id="+b_id); // 성공했을때 페이지 : 실패했을때 페이지
		
		mav.addObject("bookList", bookList);
		mav.addObject("result", result);

		mav.setViewName(viewName);//true일 때 selectBooksList로 가게 하

		return mav;
		
		
	}

}


















