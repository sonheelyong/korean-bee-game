<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ������</title>
</head>

<body>
	
    <form action = "/register" method="post">
    
	    <p>ȸ������</p>
	    
		<div>
			<input type="text" id="userId" name="userId" placeholder="���̵�" maxlength="20"><br>
		</div>
		
		<div>
			<input type="passWord" id="passWord" name="passWord" placeholder="��й�ȣ" maxlength="20"><br>
		</div>
		
		<div>
			<input type="text" id="name" name="name" placeholder="�̸�" maxlength="20"><br>
		</div>
		
		<div>
			<input type="number" id="age" name="age" placeholder="����" maxlength="20"><br>
		</div>
		
		<button type = "submit"> ȸ������ </button>
	</form>
</body>	
</html>