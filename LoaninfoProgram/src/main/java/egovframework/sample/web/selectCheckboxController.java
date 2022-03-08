package egovframework.sample.web;

import java.io.PrintWriter;
import java.net.URLEncoder;

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
import egovframework.sample.service.UsersService;
import egovframework.sample.service.UsersVO;

public class selectCheckboxController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("checked List 대출 & 반납 처리");
		
		boolean result = false;
		String message = "";
		
		// 1. DB연동 처리
		AbstractApplicationContext container = new GenericXmlApplicationContext(
				"egovframework/spring/context-*.xml"
				);

		// 2. Spring 컨테이너로부터 LoaninfoProgramServiceImpl 객체를 lookup한다.
		LoaninfoService loaninfoService = (LoaninfoService) container.getBean("loaninfoService");
		UsersService usersService = (UsersService) container.getBean("usersService");
		BooksService booksService = (BooksService) container.getBean("booksService");

		ModelAndView mav = new ModelAndView();
		
		if (request.getParameter("opt1") != null && request.getParameter("opt2") == null) {
			
			System.out.println("도서 대출 정보에서 넘어온 경우");
			
			try {
				String checkedList[] = request.getParameterValues("checkLoaninfo"); // checkbox 배열 형태 저장
				String user = request.getParameterValues("checkUser")[0]; //
				
				for (String checked : checkedList) {
					System.out.println(checked + "/" + user);
					
					LoaninfoVO lvo = new LoaninfoVO();
					lvo.setId(checked);
					lvo.setU_id(user); 
					loaninfoService.updateLoaninfoIU(lvo);
					
				}
				
				UsersVO uvo = new UsersVO();
				uvo.setId(user);
				uvo = usersService.selectUserI(uvo);
				
				mav.addObject("UsersVO", uvo);
				mav.addObject("uid", uvo.getId());
				
				result = true;
				message = "반납이 완료되었습니다.";
				
			} catch(NullPointerException ne){
				
				message = "잘못된 접근입니다.";
				
			}
		}
		else if (request.getParameter("opt1") == null && request.getParameter("opt2") != null) {
			
			System.out.println("책 정보에서 넘어온 경우");
			
			try {
				
				String checkedList[] = request.getParameterValues("checkBook"); // checkbox 배열 형태 저장
				String user = request.getParameterValues("checkUser")[0]; //
				String loanBtn = request.getParameter("loan");
				String returnBtn = request.getParameter("return");
				String bookStatusList[] = request.getParameterValues("bookStatus"); // 책의 대출 & 반납 가능 상태
				
				// 대출 가능, 반납 가능한 도서가 동시에 넘어왔는지 & 대출인데 반납 & 반납인데 대출 
				// 넘어왔다면, 오류메시지 출력해주기.
				String checkedBtn = (loanBtn == null) ? returnBtn : loanBtn;
				
				// 체크된 버튼은..?!
				System.out.println("체크된 버튼 : " + checkedBtn);
				System.out.println("checkedSize : "+checkedBtn.length());
				System.out.println("bookStatusListSize : "+bookStatusList.length);
				
				
				for (String checked : checkedList) {
					
					// 3, 1
					// bookStatusList.length() - (checked)
					// 3 - (3) -> 0
					// 3 - (1) -> 2
					
					System.out.println(checked);
					System.out.println(bookStatusList.length-Integer.parseInt(checked));
					String pos = bookStatusList[bookStatusList.length-Integer.parseInt(checked)]; // 체크된 책의 대출 상태
					
					if (!pos.contains(checkedBtn))
						throw new Exception();
					
				}
				
				LoaninfoVO lvo = new LoaninfoVO();
				
				// 반납
				if (checkedBtn.equals("반납")) {
					
					System.out.println("반납 기능 처리");
					
					for(String checked : checkedList) {
						
						System.out.println(checked+", "+user);
						
						lvo.setU_id(user);
						lvo.setB_id(Integer.parseInt(checked));
						loaninfoService.updateLoaninfoUB(lvo);
					}
					
					message = "반납이 완료되었습니다.";
					
				}
				
				// 대출
				else if (checkedBtn.equals("대출")) {
					
					System.out.println("대출 기능 처리");
					for(String checked : checkedList) {
						
						System.out.println(checked+", "+user);
						
						BooksVO bvo = new BooksVO();
						bvo.setId(Integer.parseInt(checked));
						BooksVO rbvo = booksService.selectBook(bvo);
						
						lvo.setU_id(user);
						lvo.setB_id(rbvo.getId());
						lvo.setB_name(rbvo.getName());
						lvo.setB_author(rbvo.getAuthor());
						lvo.setB_publisher(rbvo.getPublisher());
						lvo.setB_callnum(rbvo.getCallNum());

						loaninfoService.insertLoaninfo(lvo);
					}
					
					message = "대출이 완료되었습니다.";
				}
				
				
				result = true;
				
				
			} catch (Exception e) {
				// 처리 실패
				result = false;
				message = "잘못된 접근입니다.";
				e.printStackTrace();
			}
				
		}
		
		PrintWriter out = response.getWriter();
		if(message != null) {
			if(!message.equals("")) {
				out.print("<script charset=utf-8>");
				out.print("alert('"+message+"')");
				out.print("</script>");
				out.flush();
			}
		}

		container.close();
		mav.addObject("result", result);
		mav.setViewName("forward:/selectLoaninfoList.do");
		return mav;
	}

}
