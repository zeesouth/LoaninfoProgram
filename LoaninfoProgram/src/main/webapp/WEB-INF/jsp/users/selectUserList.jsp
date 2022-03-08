<%@page import="egovframework.sample.service.impl.LoaninfoDAOIbatis"%>
<%@page import="egovframework.sample.service.LoaninfoVO"%>
<%@page import="org.springframework.context.support.AbstractApplicationContext" %>
<%@page import="org.springframework.context.support.GenericXmlApplicationContext" %>
<%@page import="egovframework.sample.service.LoaninfoService" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    overflow: hidden; min-height: 700px; margin-right: 1px;
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

<script charset='쇼'>

	let result = ${result};
	
	if (result != null) {
		if (result == false) {
			alert('등록된 회원 정보가 없습니다. ');
			location.href = '/';
		} 
	}
</script>

</head>


<body>
<div class="container">
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
		
		<td>
		   </section>
		   		<section id="cont_center">
		<td>
		 <!-- 이부분에 넣으면 됨 -->
		 
		 <br/>
		 <h4>회원 선택</h4>
		 <br/>
		 <form:form action="selectUser.do" method="get">
			<table class= "table table-bordred" width="100%">
				<tr align="center" style="border-left:none; border-right: none; border-top: none; background-color: #11264f; color: #fff;">
					<th width="100">회원 선택</th>
					<th width="100">회원 번호</th>
					<th width="100">회원 이름</th>
					<th width="60">전화 번호</th>
				</tr>
				<c:forEach var="ulist" items="${ulist}">
					<tr>
						<td align="center"> <input name="checkuser" type="radio" value="${ulist.id}"> </td>
						<td align="center">${ulist.id} </td>
						<td align="center">${ulist.name} </td>
						<td align="center">${ulist.phoneNum} </td>
					</tr>
				</c:forEach>
				
			</table>
			<div align="center">
				<input type="submit" value="선택"/>
				<input type="button" value="취소" onclick="location.href='/'"/>
			</div>
		</form:form>
		 		
		</td>
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
     <!-- //footer -->	
</div>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</body>
</html>