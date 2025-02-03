<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {
	$.ajax( {
            url  :  '/getSpelling'  ,     
            method   : "GET",
            dataType:  "json",
            
            success: function(data) {
                let spellingAList = [];
                let spellingBList = [];

                // JSON 배열을 반복하여 spellingA와 spellingB 추출
                $.each(data, function(index, item) {
                    spellingAList.push(item.spellingA);
                    spellingBList.push(item.spellingB);
                });

                // 쉼표로 합쳐서 출력
                $("#spellingA").text("자음: " + spellingAList.join(", "));
                $("#spellingB").text("모음: " + spellingBList.join(", "));
            },
            error: function() {
                $("#spellingA").text("데이터를 불러오는 데 실패했습니다.");
                $("#spellingB").text("");
            }
                          
        } )
            
});
</script>
</head>
<body>
	<div>
		<h2>단어를 만들어 보세요.</h2>
			<p id="spellingA">데이터 로딩 중...</p>
			<p id="spellingB"></p>
			정답 : <input type="text" name="answer">
			<input type="button" value="제출" onclick="" />
	</div>
</body>
</html>