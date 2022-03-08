<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>반납 완료</title>
</head>
<body>

<center>
	<h2><c:out value='${userName}'/>님 !</h2>
	<h2> <c:out value='${bookName}'/> 반납이 완료되었습니다.</h2>
	<input type="submit" onclick="window.close()" value="닫기"/>
</center>

</body>
</html>