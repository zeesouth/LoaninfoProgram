//작성자:남지수
package egovframework.sample.web;

import java.beans.Encoder;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import egovframework.sample.service.LoaninfoService;
import egovframework.sample.service.LoaninfoVO;
import egovframework.sample.service.UsersService;
import egovframework.sample.service.UsersVO;


public class UpdateDeleteUserController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		// TODO Auto-generated method stub
		System.out.println("수정/삭제 기능");

		AbstractApplicationContext container = new GenericXmlApplicationContext(
				"egovframework/spring/context-*.xml"
				);
		UsersService usersService = (UsersService) container.getBean("usersService");
		LoaninfoService loaninfoService = (LoaninfoService) container.getBean("loaninfoService");
		
		
		String updateUser = request.getParameter("updateUser"); // 수정을 클릭했을 경우
		String deleteUser = request.getParameter("deleteUser"); // 삭제를 클릭했을 경우
		
		System.out.println(updateUser + ", " +deleteUser);
		
		String id = request.getParameter("u_id");
		String name = request.getParameter("name");
		String phoneNum = request.getParameter("phoneNum");
		String address = request.getParameter("address");
		String info = request.getParameter("info");
		
		System.out.println(id + ", " + name);

		UsersVO vo = new UsersVO();
		
		boolean result = false;
		String message = "";

		if (updateUser != null && updateUser.equals("수정") && deleteUser == null) {
			
			System.out.println("수정 기능 수행");
			
			// 들어온 어느 값 하나라도 공란일 경우
			if(phoneNum.equals("") || address.equals("") || info.equals("")) {
				message = "모든 정보를 입력해주세요! ";
				result = false;
			} // 모두 입력됐을 경우
			else {
				String correctNumber = "\\d{3}-\\d{4}-\\d{4}";	// 전화번호 양식
	
				if (phoneNum.matches(correctNumber)) {
					
					vo.setId(id);
					// vo.setName(name);
					vo.setPhoneNum(phoneNum);
					vo.setAddress(address);
					vo.setInfo(info);
	
					UsersVO u = usersService.selectUserI(vo);
					
					

						System.out.println(u);
						if (name.equals(u.getName()) && phoneNum.equals(u.getPhoneNum())) {
							// 같은 이름 + 같은 연락처로 회원가입을 시도하는 경우
							message = "기존과 동일한 연락처이거나 이미 등록된 회원입니다. ";
							result = false;
						} else {
							usersService.updateUser(vo);
							message = "회원 수정이 완료되었습니다. ";
							result = true;
						}
				} else {
					message ="연락처 양식을 확인해주세요.";
				}
				
			}
		} else if (deleteUser != null && deleteUser.equals("삭제") && updateUser == null) {
			
			System.out.println("삭제 기능 수행");
			try {
				
				LoaninfoVO lvo = new LoaninfoVO();
				lvo.setU_id(id);
				loaninfoService.deleteLoaninfo(lvo);
				
				vo = new UsersVO();
				vo.setId(id);
				usersService.deleteUser(vo);
				message ="회원 정보가 삭제되었습니다 ";
				result = true;
				
			} catch (Exception e) {
				message="회원 삭제 실패. ";
				e.printStackTrace();
			}
			
		}
		

		container.close();
		
		PrintWriter out = response.getWriter();
		
		if(message != null) {
			if(!message.equals("")) {
				out.print("");
				out.print("<script>");
				out.print("alert('"+message+"')");
				out.print("</script>");
				out.flush();
			}
		}

		String viewName = (result == true) ? "forward:/selectAllUserList.do" : ("forward:selectUserInfo.do?u_id="+id); // 성공했을때 페이지 : 실패했을때 페이지
		
		// 3. 화면 네비게이션
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		return mav;

		
		
	}

}
