package egovframework.sample.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import egovframework.sample.service.UsersService;
import egovframework.sample.service.UsersVO;

public class InsertUserController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("도서관 회원 등록 처리");
		
		AbstractApplicationContext container = new GenericXmlApplicationContext(
				"egovframework/spring/context-*.xml"
				);
		UsersService usersService = (UsersService) container.getBean("usersService");
		
		//1. 사용자 입력 정보 추출
		String name = request.getParameter("name");
		String phoneNum = request.getParameter("phoneNum");
		String address = request.getParameter("address");
		String info = request.getParameter("info");
		
		UsersVO vo = new UsersVO();
		
		boolean result = false;
		String message = "";
		
		// 들어온 어느 값 하나라도 공란일 경우
		if(name.equals("") || phoneNum.equals("") || address.equals("") || info.equals("")) {
			message = "모든 정보를 입력해주세요! ";
			result = false;
		} 
		// 모두 입력됐을 경우
		else {
			
			String correctNumber = "\\d{3}-\\d{4}-\\d{4}";

			if (phoneNum.matches(correctNumber)) {
				
				vo.setName(name);
				vo.setPhoneNum(phoneNum);
				vo.setAddress(address);
				vo.setInfo(info);

				UsersVO u = usersService.selectUser(vo);
				
				try {
					if (name.equals(u.getName()) && phoneNum.equals(u.getPhoneNum())) {
						// 같은 이름 + 같은 연락처로 회원가입을 시도하는 경우
						message = "이미 동륵된 회원입니다.";
						result = false;
					}
				} catch (Exception e) {
					message = "회원가입이 완료되었습니다.";
					usersService.insertUser(vo);
					result = true;
				}
			} else {
				message ="연락처 양식을 확인해주세요.";
			}
			
		}
		
		PrintWriter out = response.getWriter();
		if(message != null) {
			if(!message.equals("")) {
				out.print("<script>");
				out.print("alert('"+message+"')");
				out.print("</script>");
				out.flush();
			}
		}
		
		container.close();

		// 3. 화면 네비게이션
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		
		String setView = (result == true) ? "forward:/selectAllUserList.do" : "forward:/insertUser.jsp";
		
		mav.setViewName(setView);
		return mav;
		
	}

}
