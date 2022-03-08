<%@page import="egovframework.sample.service.UsersVO"%>
<%@page import="org.springframework.context.support.AbstractApplicationContext" %>
<%@page import="org.springframework.context.support.GenericXmlApplicationContext" %>
<%@page import="egovframework.sample.service.UsersService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보 수정 / 삭제</title>
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
</head>
<body>
<div class="container">
<%
AbstractApplicationContext container = new GenericXmlApplicationContext("egovframework/spring/context-*.xml");
UsersService usersService = (UsersService) container.getBean("usersService");
UsersVO vo = new UsersVO();

int cnt = usersService.selectUserListTotCnt(vo);
%>


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
		
	<form action="updateDeleteUser.do" method="post">
	<table class="table table-bordered" border="0">
		<tr>
			<td colspan="2"> <h4>회원 정보 수정 / 삭제</h4> </td>
		</tr>
		
		<tr>
			<td> 회원번호 </td>
			<td> <input type="text" name="u_id" readonly value="${uvo.id}"/> </td>
		</tr>
		
		<tr>
			<td> 이름 </td>
			<td> <input type="text" name="name" readonly value="${uvo.name}"/> </td>
		</tr>
		
		<tr>
			<td> 연락처 </td>
			<td> <input type="text" name="phoneNum" placeholder="***-****-**** 형태의 번호 입력" value="${uvo.phoneNum}"/> </td>
		</tr>
		<tr>
			<td> 주소 </td>
			<td> <input type="text" name="address" value="${uvo.address}"/> </td>
		</tr>
		<tr>
			<td> 정보 </td>
			<td>
				<textarea rows="10" cols="80" placeholder="500자 이내" style="resize:none;" name="info">${uvo.info}</textarea>
			</td>
		</tr>
		
	</table>
	<div align="center">
		<input type="submit" name="updateUser" value="수정"/>
		<input type="submit" name="deleteUser" value="삭제"/>
		<input type="button" name="cancel" value="취소" onclick="location.href='/selectAllUserList.do'"/>
	</div>
</form>
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

<%
container.close();
%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</body>
</html>