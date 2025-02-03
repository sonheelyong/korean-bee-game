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
                          
        } )
            
});
</script>
</head>
<body>
	<div>
		<h2>�ܾ ����� ������.</h2>
			<p id="spellingA">������ �ε� ��...</p>
			<p id="spellingB"></p>
			���� : <input type="text" name="answer">
			<input type="button" value="����" onclick="" />
	</div>
</body>
</html>