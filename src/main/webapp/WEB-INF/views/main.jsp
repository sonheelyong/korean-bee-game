<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
// ���� ���� ��������
$(document).ready(function() {
	$.ajax( {
            url  :  '/getSpelling'  ,     
            method   : "GET",
            dataType:  "json",
            
            success: function(data) {
                let spellingAList = [];
                let spellingBList = [];
                // JSON �迭�� �ݺ��Ͽ� spellingA�� spellingB ����
                $.each(data, function(index, item) {
                    spellingAList.push(item.spellingA);
                    spellingBList.push(item.spellingB);
                });

                // ��ǥ�� ���ļ� ���
                $("#spellingA").text("����: " + spellingAList.join(", "));
                $("#spellingB").text("����: " + spellingBList.join(", "));
            },
            error: function() {
                $("#spellingA").text("�����͸� �ҷ����� �� �����߽��ϴ�.");
                $("#spellingB").text("");
            }                 
        })       
});


// ��������� ȣ��
$(document).ready(function(){
	$("#sendButton").click(function () {
		
		var answer = $("#answer").val();
		
		$.ajax({
			 type: "POST",
             url: "/getAnswer",  // Java �鿣�� URL (����)
             data: { answer: answer },  // ������ ���� ������
             dataType: "json",
                  
             success: function (data) {
            	 
            	 var word = data.word;
            	 var pos = data.pos;
            	 var definition = data.definition;
            	 
            	 if(word === undefined){
            		 console.log("���� �ܾ�");
            		 alert("���� �ܾ��Դϴ�.");
            	 }
            	 else{
           		 
            		 console.log(data);	 
                     console.log("�ִ� �ܾ�");
                	     	
                	 document.getElementById("word").value = word;
                     document.getElementById("pos").value = pos;
                     document.getElementById("definition").value = definition;            		 	
            	 }
                
             },
             error: function (xhr, status, error) {
                 $("#result").html("���� �߻�: " + error);
             }	
		})		
	});	
});

</script>
</head>
<body>
	<div>
		<h2>�ܾ ����� ������.</h2>
			<p id="spellingA">������ �ε� ��...</p>
			<p id="spellingB"></p>
			���� : <input type="text" name="answer" id="answer">
			<button id="sendButton">���� ����</button>
			<div id=result>
				�ܾ� : <input type="text" name="word" id="word">
			    ǰ�� : <input type="text" name="pos" id="pos">
				�� : <input type="text" name="definition" id="definition">
			</div>
	</div>
</body>
</html>