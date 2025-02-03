<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
</head>

<body>
	
    <form action = "/register" method="post">
    
	    <p>회원가입</p>
	    
		<div>
			<input type="text" id="userId" name="userId" placeholder="아이디" maxlength="20"><br>
		</div>
		
		<div>
			<input type="passWord" id="passWord" name="passWord" placeholder="비밀번호" maxlength="20"><br>
		</div>
		
		<div>
			<input type="text" id="name" name="name" placeholder="이름" maxlength="20"><br>
		</div>
		
		<div>
			<input type="number" id="age" name="age" placeholder="나이" maxlength="20"><br>
		</div>
		
		<button type = "submit"> 회원가입 </button>
	</form>
</body>	
</html>