<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
// 랜덤 글자 가져오기
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
        })       
});


// 정답제출시 호출
$(document).ready(function(){
	$("#sendButton").click(function () {
		
		var answer = $("#answer").val();
		
		$.ajax({
			 type: "POST",
             url: "/getAnswer",  // Java 백엔드 URL (서블릿)
             data: { answer: answer },  // 서버로 보낼 데이터
             dataType: "json",
                  
             success: function (data) {
            	 
            	 var word = data.word;
            	 var pos = data.pos;
            	 var definition = data.definition;
            	 
            	 if(word === undefined){
            		 console.log("없는 단어");
            		 alert("없는 단어입니다.");
            	 }
            	 else{
           		 
            		 console.log(data);	 
                     console.log("있는 단어");
                	     	
                	 document.getElementById("word").value = word;
                     document.getElementById("pos").value = pos;
                     document.getElementById("definition").value = definition;            		 	
            	 }
                
             },
             error: function (xhr, status, error) {
                 $("#result").html("에러 발생: " + error);
             }	
		})		
	});	
});

</script>
</head>
<body>
	<div>
		<h2>단어를 만들어 보세요.</h2>
			<p id="spellingA">데이터 로딩 중...</p>
			<p id="spellingB"></p>
			정답 : <input type="text" name="answer" id="answer">
			<button id="sendButton">정답 제출</button>
			<div id=result>
				단어 : <input type="text" name="word" id="word">
			    품사 : <input type="text" name="pos" id="pos">
				뜻 : <input type="text" name="definition" id="definition">
			</div>
	</div>
</body>
</html>