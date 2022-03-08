<%@page import="egovframework.sample.service.impl.BooksDAOIbatis"%>
<%@page import="egovframework.sample.service.BooksVO"%>
<%@page import="org.springframework.context.support.AbstractApplicationContext" %>
<%@page import="org.springframework.context.support.GenericXmlApplicationContext" %>
<%@page import="egovframework.sample.service.BooksService" %>
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
#cont_left {float: left; width: 250px;}
#cont_center {
    overflow: hidden; min-height: 1300px; margin-right: 250px;
    border-right: 1px solid #dbdbdb;
    border-left: 1px solid #dbdbdb;
}
ul{ list-style:none; padding-left:0px;}
/* 메뉴 */
.menu {float: left; width: 160px; }
.menu li {position: relative;}
.menu li a {
text-decoration-line: none;
    font-size: 16px; text-transform: uppercase;
    color: #878787;
    border-bottom: 1px solid #dbdbdb;
    padding: 10px; display: block;
    transition: box-shadow 0.34s ease, background 0.34s ease;
}
.menu li a i {
    position: absolute; right: 10px; top: 15px;
}
.menu li a:hover {
    box-shadow: inset 180px 0 0 0 rgba(35,63,118,0.7);
    color: #fff;
    background: rgba(17,38,79,0.9);
}



/* 푸터 */
#footer {background: #11264f;}
.footer {background: #11264f; text-align: center; padding: 30px 50px; }
.footer a {position: relative; text-decoration-line: none; display: inline; color: #222; padding: 0 7px 0 10px; white-space: nowrap;}
</style>

</head>

<body>


<script type="text/javascript" charset="utf-8">

	var result = ${result};

	if (result != null || result != "null") {
		
		if (result == false || result == "false" || result === false || result === "false") {
			// 실패한 결과일 경우
			location.href = '/selectBook.do';
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
                         <div class="menu">
                             <ul>
                                 <li><a href="insertUser.jsp">회원 가입 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a></li>
                                 <li><a href="/selectAllUserList.do">회원 조회 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a></li>
                                 <li><a href="insertBook.jsp">도서 입력 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a></li>
                                 <li><a href="/selectBook.do">도서 조회 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a></li>
                                 <li><a href="/">도서 / 대출<i class="fa fa-angle-double-right" aria-hidden="true"></i></a></li>
                             </ul>
                         </div>
                         <!-- //메뉴 -->
   </section>
		<section id="cont_center">
		<!-- 이부분에 넣으셔 -->
		
		<table  width="100%">

<c:if test="${not empty bookList}">
	<input type="hidden" name="opt2" value="opt2"/>
	<tr>
	<td colspan="2">
		<center>
			<table class= "table table-striped" width="100%">
				<tr align="center">
					<th width="100">책 코드</th>
					<th width="100">서명</th>
					<th width="60">저자</th>
					<th width="100">출판사</th>
					<th width="100">청구 기호</th>
				</tr>
				
				
				
				<c:forEach var="book" items="${bookList}">
				<tr>

					<td align="center">${book.id} </td>
					<td align="center"> <a href="selectBookInfo.do?b_name=${book.name}&b_author=${book.author}&b_publisher=${book.publisher}&b_callNum=${book.callNum}"> ${book.name} </a></td>
					<td align="center"> ${book.author} </td>
					<td align="center"> ${book.publisher} </td>
					<td align="center"> ${book.callNum} </td>
					
					<!-- <td align="center"> <a href="selectBookInfo.do?b_name=${book.name}"> ${book.name} </a></td>
					<td align="center"> <a href="selectBookInfo.do?b_author=${book.author}">${book.author}</a> </td>
					<td align="center"> <a href="selectBookInfo.do?b_publisher=${book.publisher}">${book.publisher} </td>
					<td align="center"> <a href="selectBookInfo.do?b_callNum=${book.callNum}">${book.callNum}</a> </td>-->
					
					

				</tr>

				</c:forEach>	
			</table>
				
		</center>
	</td>
	</tr>
</c:if>

</table>
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

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</body>
</html>