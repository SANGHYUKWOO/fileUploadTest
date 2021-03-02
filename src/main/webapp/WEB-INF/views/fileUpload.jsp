<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	파일 업로드 테스트  
</h1>
<form method="post" action="upload" enctype="multipart/form-data">
	<input type="file" name="file" id="fileOpenInput">
	<input type="submit">
</form>
</body>
</html>
