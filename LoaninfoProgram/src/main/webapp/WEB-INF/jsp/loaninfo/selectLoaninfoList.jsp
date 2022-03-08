<%@page import="egovframework.sample.service.impl.LoaninfoDAOIbatis"%>
<%@page import="egovframework.sample.service.LoaninfoVO"%>
<%@page import="org.springframework.context.support.AbstractApplicationContext" %>
<%@page import="org.springframework.context.support.GenericXmlApplicationContext" %>
<%@page import="egovframework.sample.service.LoaninfoService" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>지수도서관 대출반납 시스템</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<style>
#header {background: #11264f;}
.header {height: 150px; }
.header .header_tit {text-align: center; margin-top: 55px; font-family: var(--bs-font-sans-serif); text-decoration-line: none;}
.header .header_tit h1 a{
    text-decoration-line: none;
    font-size: 30px;
    color: #fff;
    background: #11264f;
    display: inline-block;
    padding: 5px 30px 5px 30px;
    letter-spacing: 2px;
    font-weight: 900;
}

#cont_left {float: left; width: 170px;}
#cont_center {
    overflow: hidden; min-height: 1300px; margin-right: 1px;
    border-right: 30px solid #fff;
    border-left: 30px solid #fff;
}

/* 메뉴 */
.menu {float: left; width: 160px;}

.menu li a {
text-decoration-line: none;
    font-size: 16px; text-transform: uppercase;
    color: #878787;
    border-bottom: 1px solid #dbdbdb;
    padding: 10px; display: block;
    transition: box-shadow 0.34s ease, background 0.34s ease;
}
.col_tit{
padding: 20px 0px;

}
col_menu{
marginp-bottom: 20px;

}
.menu .title{
text-decoration-line: none;
    font-size: 16px; text-transform: uppercase;
    color: #fff;
    padding: 10px 10px; display: block;
    border-bottom: 1px solid #fff;
    background: #11264f;
}
.menu li a:hover {
    box-shadow: inset 180px 0 0 0 rgba(35,63,118,0.7);
    color: #fff;
    background: rgba(17,38,79,0.9);
}



/* 푸터 */
#footer {background: #11264f;}
.footer {background: #11264f; text-align: center; padding: 30px 50px; }
.footer a {position: relative; text-decoration-line: none; display: inline; color: #fff; padding: 0 7px 0 10px; white-space: nowrap;}
</style>
<c:if test = "${not empty returnBookCallNum and not empty returnName}">
	<script>
		window.open('showReturnInfo.do?returnName=<%=request.getAttribute("returnName")%>&returnBookCallNum=<%=request.getAttribute("returnBookCallNum")%>', '반납 완료', 
		'width=400, height=300, left = (window.screen.width / 2) - (400 / 2), top = (window.screen.height /2) - (300 / 2), menubar=no, status=no, toolbar=no, resizable=no, scrollbars=yes, location=no');
	</script>
</c:if>
</head>



<body>
<div class="container">
<script>
	var result = ${result};
	if (result != null || result != "null") {
		
		if (result == false || result == "false" || result === false || result === "false") {
			// 실패한 결과일 경우
			location.href = '/';
		} 
	} 
	
</script>

<header id="header">
        <div class="container">

            <div class="row">
                <div class="header">
                    <!-- //header_menu -->
                    <div class="header_tit">
                    
                        <h1><i class="far-solid fa-books"></i><a href="/">지수도서관 대출반납시스템</a></h1>
                    </div>
                    <!-- //header_tit -->
                </div>
            </div>

        </div>
</header>
    <!-- //header -->
    <!-- 메뉴 -->
        <section id="cont_left">
				<h4 class="col_menu"></h4>
                         <div class="menu">
                         	<div class="title">회원 관리</div>
                             <ul>
                                 <li><a href="insertUser.jsp">회원 가입 </a></li>
                                 <li><a href="/selectAllUserList.do">회원 조회 </a></li>
                             </ul>
                             <div class="title">도서 관리</div>
                             <ul>
                                 <li><a href="insertBook.jsp">도서 입력 </a></li>
                                 <li><a href="/selectBook.do">도서 조회 </a></li>
                             </ul>
                             <div class="title">도서 / 대출</div>
                             <ul> 
                                 <li><a href="/">도서 / 대출</a></li>
                             </ul>
                         </div>
                         <!-- //메뉴 -->
		
		
		   </section>
		   	<section id="cont_center">
		
			<form:form commandName="UsersVO" action="selectMain.do" method="post">
				<div>
				<h4 class="col_tit">도서 / 대출</h4> 
				<table class="table table-bordred" >
				
					<tr style="border-left:none; border-right: none; border-top: none; background-color: #11264f; color: #fff;">
						<td width="50%"> <h6>도서 & 회원 검색</h6> </td>
						<td width="50%"> <h6>회원 정보</h6></td>
					</tr>
					<tr>
							<td width="50%">
								
									<table>
										<tr>
											<td>회원코드 | 청구번호 </td>
											<td><input type="text" name="code"/></td>
											<td width="100"align="center" rowspan="3"><input type="submit" value="검색"/></td>
										</tr>
										<tr>
											<td>회원명 </td>
											<td><input type="text" name="name"/></td>
										</tr>
										<tr>
											<td>서명 </td>
											<td><input type="text" name="b_name"/></td>
										</tr>
									</table>
							
							</td>
					
							<td width="50%">
								<table>
									<tr>
										<td>회원코드</td>
										
											<td><input type="text" name="user_id" readonly value="<c:out value = '${UsersVO.id}'/>"/></td>
										
									</tr>
									<tr>
										<td>이름</td>
										<td><input type="text" name="user_name" readonly value="<c:out value = '${UsersVO.name}'/>"/></td>
										
									</tr>
									<tr>
										<td>연락처</td>
										<td><input type="text" name="user_phoneNum" readonly value="<c:out value = '${UsersVO.phoneNum}'/>"/></td>
										
									</tr>
								</table>
							</td>
					</tr>
				</table>
				</div>
			</form:form> 
			
			
			<form:form action="selectCheckBox.do" method="post">
			<table  width="100%">
				<tr>
					
					<c:if test="${not empty loaninfoList and empty bookList}">
						<td><h6>대출/반납 조회</h6></td>
					</c:if>
					<c:if test="${not empty bookList and empty LoaninfoList}">
						<td><h6>서명 조회</h6></td>
					</c:if>
					
					
					<td align="right" style="padding-bottom:15px;">
						<c:if test="${not empty bookList and empty LoaninfoList}">
							<input type="submit" value="대출" name="loan"/>
						</c:if>
						<c:if test="${not empty loaninfoList or not empty bookList}">
							<input type="submit" value="반납" name="return"/>
						</c:if>
					</td>
				</tr>
			<c:if test="${not empty loaninfoList and empty bookList}">
			
				<input type="hidden" name="opt1" value="opt1"/>
				<tr>
				<td colspan="2">
					<center>
						<table class= "table table-bordred" width="100%">
							<tr align="center" style="border-left:none; border-right: none; border-top: none; background-color: #11264f; color: #fff;">
								<th width="100">선택</th>
								<th width="100">대출번호</th>
								<th width="100">책번호</th>
								<th width="100">서명</th>
								<th width="100">저자</th>
								<th width="100">출판사</th>
								<th width="240">청구기호</th>
								<th width="100">대출날짜</th>
								<th width="100">반납날짜</th>
							</tr>
							<c:forEach var="loaninfo" items="${loaninfoList}">
							<tr style="border-left:none; border-right: none;">
								<c:if test="${loaninfo.returnDate eq null}">
									<td align="center">
										<input type="checkbox" name="checkLoaninfo" value="${loaninfo.id}"/> 
										<input type="hidden" name="checkUser" value="${loaninfo.u_id}"/>
									</td>
								</c:if>
								<c:if test="${loaninfo.returnDate ne null}">
									<td align="center"> <input type="checkbox" disabled="disabled"> </td>
								</c:if>
									<td align="center">${loaninfo.id} </td>
									<td align="center">${loaninfo.b_id} </td>
									<td align="center">${loaninfo.b_name} </td>
									<td align="center">${loaninfo.b_author} </td>
									<td align="center">${loaninfo.b_publisher} </td>
									<td align="center">${loaninfo.b_callnum} </td>
									<td align="center">${loaninfo.loanDate}</td>
									<td align="center">${loaninfo.returnDate}</td>
							</tr>
							</c:forEach>
						</table>
						<br/>
					</center>
				</td>
				</tr>
			</c:if>
			<c:if test="${not empty bookList and empty LoaninfoList}">
				<input type="hidden" name="opt2" value="opt2"/>
				<tr>
				<td colspan="2">
					<center>
						<table class= "table table-bordred" width="100%">
							<tr align="center" style="border-left:none; border-right: none; border-top: none; background-color: #11264f; color: #fff;">
								<th width="100">도서 선택</th>
								<th width="100">책 코드</th>
								<th width="100">서명</th>
								<th width="60">저자</th>
								<th width="100">출판사</th>
								<th width="100">청구 기호</th>
								<th width="100">대출 여부</th>
							</tr>
							<c:forEach var="book" items="${bookList}">
							<tr style="border-left:none; border-right: none;">
								
								<c:if test="${map[book.id] eq '대출 불가'}">
									<td align="center"> <input type="checkbox" disabled="disabled"> </td>
									<input type="hidden" name="bookStatus" value="대출 불가"/>
								</c:if>
								
								<c:if test="${map[book.id] ne '대출 불가'}">
									<td align="center">
										<input type="checkbox" name="checkBook" value="${book.id}"/> 
										<input type="hidden" name="checkUser" value="${user_id}"/>
										<c:if test="${empty map[book.id]}">
											<input type="hidden" name="bookStatus" value="대출 가능"/>
										</c:if>
								
										<c:if test="${not empty map[book.id]}">
											<input type="hidden" name="bookStatus" value="${map[book.id]}"/>
										</c:if>
									</td>
									
								</c:if>
			
								<td align="center">${book.id} </td>
								<td align="center">${book.name} </td>
								<td align="center">${book.author} </td>
								<td align="center">${book.publisher} </td>
								<td align="center">${book.callNum} </td>
			
								<c:if test="${empty map[book.id]}">
									<td align="center" style="color: green;">대출 가능</td>
								</c:if>
								
								<c:if test="${not empty map[book.id] and map[book.id] eq '대출 불가'}">
									<td align="center" style="color: red;">${map[book.id]}</td>
								</c:if>
								
								<c:if test="${not empty map[book.id] and map[book.id] eq '반납 가능'}">
									<td align="center" style="color: blue;">${map[book.id]}</td>
								</c:if>
								
							</tr>
							</c:forEach>
						</table>
					</center>
				</td>
				</tr>
			</c:if>
			
			</table>
			</form:form>
		
			</section>

		<footer id="footer">
			        <div class="container">
			            <div class="row">
			                <div class="footer">
			                    <ul>
			                        <tr><a href="#">고객센터</a></tr>
			                    </ul>
			                </div>
			            </div>
			        </div>
		</footer>


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</body>
</html>