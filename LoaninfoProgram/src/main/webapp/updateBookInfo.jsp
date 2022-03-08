

















<!-- 이 페이지는 필요 없을 듯 -->

<%@page import="egovframework.sample.service.BooksVO"%>
<%@page import="org.springframework.context.support.AbstractApplicationContext" %>
<%@page import="org.springframework.context.support.GenericXmlApplicationContext" %>
<%@page import="egovframework.sample.service.BooksService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>도서 추가</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
<%


//int cnt = booksService.selectBookListTotCnt(vo);
%>

<table class="table table-bordered" border = "0">

	<tr>
		<td width="20%">
			<table class="table table-bordered">
				
				<tr>
					<td>
					<p style="font-weight:bold;">회원 관리</p>
					<ul>
						<li><a href="insertUser.jsp" style="text-decoration-line : none; color:black;">회원 가입</a></li>
						<li><a href="/LoaninfoProgram/selectAllUserList.do" style="text-decoration-line : none; color:black;">회원 조회</a></li>
					</ul>
					</td>
				</tr>
				<tr>
					<td>
						<p style="font-weight:bold;">도서 관리</p>
						<ul>
							<li><a href="insertBook.jsp" style="text-decoration-line : none; color:black;">도서 입력</a></li>
							<li><a href="/LoaninfoProgram/selectBook.do" style="text-decoration-line : none; color:black;">도서 조회</a></li>
						</ul>
					
					</td>
				</tr>
				<tr>
					<td>
						<p style="font-weight:bold;"><a href="/" style="text-decoration-line : none; color:black;">도서 / 대출</a></p>
						
					</td>
				</tr>

			</table>
		</td>
		
		<td>

		<form action="insertBook.do" method="get">
	<table class="table table-bordered" border="0">
		<tr>
			<td colspan="2"> <h4>도서 정보 수정 하기</h4> </td>
		</tr>
		
		<tr>
			<td> 도서명 </td>
			<!-- 이게 원본 <td> <input type="text" name="b_name"/>${b_name} </td>-->
			<td> <input type="text" name="b_name"value="${b_name}"></td>
		</tr>
			
		<tr>
			<td> 저자 </td>
			<td> <input type="text" name="b_author"  value="${b_author}"></td>
		</tr>
		<tr>
			<td> 출판사 </td>
			<td> <input type="text" name="b_publisher" value="${b_publisher}"></td>
		</tr>
		<tr>
			<td> 청구 기호 </td>
			<td> <input type="text" name="b_callNum" disabled="disabled" value="${b_callNum}"></td>
		</tr>
		<!-- <tr>
			<td> 정보 </td>
			<td> <input type="text" name="info" placeholder="500자 이내"/> </td>
		</tr> -->
		
	</table>
	
	<div align="center">
		<input type="submit" value="확인"/>
				<input type="button" onclick="location.href='/'" value="취소"/>
	</div>
</form>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</div>
</body>

</html>


