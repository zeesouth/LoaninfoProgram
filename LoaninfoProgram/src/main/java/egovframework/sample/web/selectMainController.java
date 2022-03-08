package egovframework.sample.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.sample.service.BooksService;
import egovframework.sample.service.BooksVO;
import egovframework.sample.service.LoaninfoService;
import egovframework.sample.service.LoaninfoVO;
import egovframework.sample.service.UsersService;
import egovframework.sample.service.UsersVO;
import egovframework.sample.service.impl.LoaninfoDAOIbatis;

import org.springframework.web.bind.annotation.RequestMethod;

import java.io.PrintWriter;
import java.sql.Date;

//@Controller
public class selectMainController implements Controller {
	

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("책 & 회원 코드, 회원 검색 처리");
		
		// 1. DB연동 처리
		AbstractApplicationContext container = new GenericXmlApplicationContext(
				"egovframework/spring/context-*.xml"
				);

		// 2. Spring 컨테이너로부터 LoaninfoProgramServiceImpl 객체를 lookup한다.
		LoaninfoService loaninfoService = (LoaninfoService) container.getBean("loaninfoService");
		BooksService booksService = (BooksService) container.getBean("booksService");
		UsersService usersService = (UsersService) container.getBean("usersService");

		// 3. 받아온 데이터를 추출한다.
		String code = request.getParameter("code"); // 책 혹은 사용자 코드 입력
		String name = request.getParameter("name"); // 검색할 회원 정보 입력
		String b_name = request.getParameter("b_name"); // 검색할 책 정보 입력
		
		// 회원명으로 검색하는 경우
		if (code == null && name == null && b_name == null) {
			code = (String) request.getAttribute("code");
			name = (String) request.getAttribute("name");
			b_name = (String) request.getAttribute("b_name");
		}
		
		// 4. 사용자가 지정되었는지 확인
		String user_id = request.getParameter("user_id");
		String user_name = request.getParameter("user_name");
		String user_phoneNum = request.getParameter("user_phoneNum");
		
		// 사용자 정보가 존재하면 true, 그렇지 않으면 null
		boolean isUser = !(user_id == null || user_name == null || user_phoneNum == null
				|| user_id.equals("") || user_name.equals("") || user_phoneNum.equals("")) ;
		boolean result = false;
		String message = "";
		
		ModelAndView mav = new ModelAndView();
		
		// case 1: 회원정보가 입력된 경우
		if (isUser) {
			
			UsersVO uvo = new UsersVO();
			uvo.setId(user_id);
			uvo.setName(user_name);
			uvo.setPhoneNum(user_phoneNum);
			
			mav.addObject("UsersVO", uvo);
			mav.addObject("uid", uvo.getId());

			// 코드만 입력된 경우
			if(!code.equals("") && name.equals("") && b_name.equals("")) {
				
				//String regExp1 = "B+[0-9]+$"; // B문자를 포함하며 숫자로 이루어졌는지
				String regExp2 = "^[0-9]+$"; //문자로만 구성되어 있는지
				
				// 책 코드를 입력하는 경우
				// 1. 회원이 대출중인 책 코드 -> 반납
				// 2. 아무도 대출중이지 않고, 존재하는 책 코드 -> 대출
				// 3. 누군가 대출중인 책 코드, 존재하지 않는 책 코드 -> 현재 대출 불가능한 도서입니다.
				if (!code.matches(regExp2)) {
					
					
					LoaninfoVO lvo = new LoaninfoVO();
					LoaninfoVO rlvo;
					try {
						
						lvo.setU_id(user_id);
						lvo.setB_callnum(code);
						rlvo = loaninfoService.selectLoaninfoUBLR(lvo); //★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
						
						// 1
						if (code.equals(rlvo.getB_callnum())) {
							System.out.println("1");
							lvo.setId(rlvo.getId());
							loaninfoService.updateLoaninfoIU(lvo);
							System.out.println("반납 완료");
							
							
							mav.addObject("UsersVO", uvo);
							mav.addObject("uid", uvo.getId());
							message = "반납이 완료되었습니다.";
							result = true;
						}
					} catch (Exception e) {
						// 2

						lvo = new LoaninfoVO();
						lvo.setB_callnum(code);
						rlvo = loaninfoService.selectLoaninfoBLR(lvo); //★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
						
						try {
							if(!(rlvo.getU_id().equals(user_id))) {
								message = "대출 불가한 도서입니다.";
								result = false;
							}
						} catch (Exception e2) {
							
							try {
								BooksVO bvo = new BooksVO();
								bvo.setCallNum(code);
								BooksVO rbvo = booksService.selectBook(bvo); //★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
								System.out.println(bvo);
								System.out.println(rbvo);
								System.out.println(code+ ", "+ rbvo.getCallNum());
								
								if (code.equals(rbvo.getCallNum())) {
									System.out.println("2");
									lvo = new LoaninfoVO();
									lvo.setU_id(user_id);
									lvo.setB_id(rbvo.getId());
									lvo.setB_name(rbvo.getName());
									lvo.setB_author(rbvo.getAuthor());
									lvo.setB_publisher(rbvo.getPublisher());
									lvo.setB_callnum(code);
									
									loaninfoService.insertLoaninfo(lvo);
									message = "대출이 완료되었습니다.";
									result = true;
									
								}
							} catch (Exception e3) {
								System.out.println("3"); 
								message = "등록되지 않은 도서입니다.";
								e3.printStackTrace();
								result = false;
							}
						}

					}
	
				} else if (code.matches(regExp2)) {
					// 회원 정보가 존재할 경우
					// 입력된 코드의 회원으로 지정해주기
					
					uvo = new UsersVO();
					uvo.setId(code);
					UsersVO ruvo = usersService.selectUserI(uvo);
					// 회원 정보가 존재할 경우
					// 입력된 코드의 회원으로 지정해주기
					try {
						if (ruvo.getId().equals(code)) {
							System.out.println("4");
							
							result = true;
							mav.addObject("UsersVO", ruvo);
							mav.addObject("uid", ruvo.getId());
							
						}
					// 회원 정보가 존재하지 않을 경우
					} catch (Exception e) {
						System.out.println("5");
						message = "존재하지 않는 회원입니다.";
						result = false;
					}
					
				} else {
					System.out.println("6");
					message = "잘못된 접근입니다.";
					result = false;
				}
			
			}
			// 이름만 입력된 경우
			else if (code.equals("") && !name.equals("") && b_name.equals("")){
				System.out.println("회원 선택 창 열기 7");
				
				container.close();
				mav.addObject("name", name);
				mav.setViewName("forward:/selectUserList.do");
				return mav;
				
			}
			// 책 이름만 입력된 경우 ************ ******************************** ***********************
			else if (code.equals("") && name.equals("") && !b_name.equals("")) {
				BooksVO bvo = new BooksVO();
				bvo.setName(b_name);
				
				try {
					List<BooksVO> booksList = booksService.selectBooksList_LikeName(bvo);
					
					
					mav.addObject("bookList", booksList);
					result = true;
				
					
					LoaninfoVO blvo = new LoaninfoVO();
					blvo.setU_id(user_id);
					blvo.setB_name(b_name);
					
					List<LoaninfoVO> meList = loaninfoService.selectLoaninfoList(blvo);
					List<LoaninfoVO> notMeList = loaninfoService.selectLoaninfoList_notme(blvo);
					
					HashMap<Integer, String> map = new HashMap<>();

					for (LoaninfoVO me : meList) {
						map.put(me.getB_id(), "반납 가능");
						System.out.println(me.getB_id());
					}
					
					for (LoaninfoVO notMe : notMeList) {
						map.put(notMe.getB_id(), "대출 불가");
						System.out.println(notMe.getB_id());
					}
					
					mav.addObject("user_id", user_id);
					mav.addObject("map", map);
					
					
					// 검색어가 포함된 책 이름이 없는 경우
					if (booksList.size() == 0)
						throw new Exception();
					

					
				}  catch (Exception e) {
					message = "등록되지 않은 도서입니다.";
					result = false;
				}
				
				
				
			}
			
			// 코드와 이름이 입력된 경우 & 아무것도 입력되지 않은 경우
			else {
				System.out.println("8");
				message = "잘못된 접근입니다.";
				result = false;
			}
			
			
		}
		
		// case 2: 회원정보가 입력되지 않은 경우
		else {
			// 코드만 입력된 경우
			if(!code.equals("") && name.equals("")) {
				
				//String regExp1 = "B+[0-9]+$";
				String regExp2 = "^[0-9]+$"; // 숫자로만 이루어진 경우
				// 책 코드를 입력하거나 존재하지 않는 회원 번호를 입력한경우
				// 잘못된 접근입니다 메시지 띄우기
				
				if (!(code.matches(regExp2))) {
					
					LoaninfoVO lvo = new LoaninfoVO();
					lvo.setB_callnum(code);
					LoaninfoVO rlvo = loaninfoService.selectLoaninfoBLR(lvo);
					
					try {
						if (code.equals(rlvo.getB_callnum())) {
							System.out.println("9");
							lvo = new LoaninfoVO();
							lvo.setId(rlvo.getId());
							lvo.setB_callnum(code);
							loaninfoService.updateLoaninfoIB(lvo); 
							mav.addObject("returnBookCallNum", code);
							
							UsersVO uvo = new UsersVO();
							uvo.setId(rlvo.getU_id());
							UsersVO ruvo = usersService.selectUserI(uvo);
							mav.addObject("returnName", ruvo.getName());
							
							result = true;
						}
					// 회원 정보가 존재하지 않을 경우
					} catch (Exception e) {
						System.out.println("10");
						message = "유효하지 않은 도서 청구번호 입니다.";
						result = false;
					}

				} else if (code.matches(regExp2)){
					UsersVO uvo = new UsersVO();
					uvo.setId(code);
					UsersVO ruvo = usersService.selectUserI(uvo);
					// 회원 정보가 존재할 경우
					// 입력된 코드의 회원으로 지정해주기
					try {
						if (ruvo.getId().equals(code)) {
							System.out.println("11");
							
							mav.addObject("UsersVO", ruvo);
							mav.addObject("uid", ruvo.getId());
							
							result = true;

						}
					// 회원 정보가 존재하지 않을 경우
					} catch (Exception e) {
						System.out.println("12");
						message = "존재하지 않는 회원입니다.";
						result = false;
					}
				} else {
					message = "잘못된 접근입니다.";
					result = false;
				}

			}
			// 이름만 입력된 경우
			else if (code.equals("") && !name.equals("")){
				// 회원 선택 창 열기
				
				System.out.println("회원 선택 창 열기 13");
				
				container.close();
				mav.addObject("name", name);
				mav.setViewName("forward:/selectUserList.do");
				return mav;
				
			}
			// 코드와 이름이 입력된 경우 & 아무것도 입력되지 않은 경우
			else {
				// 잘못된 접근입니다. 메시지 띄우기
				System.out.println("14");
				message = "잘못된 접근입니다.";
				result = false;
			}
		}

		container.close();
		
		mav.addObject("result", result);
		mav.addObject("message", message);
		
		
		mav.setViewName("forward:/selectLoaninfoList.do");
		
		return mav;
	}

}
