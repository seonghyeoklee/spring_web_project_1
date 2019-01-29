<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple="multiple">
	</div>
	
	<button id="uploadBtn">Upload</button>
	
	<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>

	<script type="text/javascript">
		$(document).ready(function(){
			$("#uploadBtn").on("click", function(){
				
				var formData = new FormData();
				var inputFile = $("input[name='uploadFile']");
				var files = inputFile[0].files;
				
				console.log(files);
				
				for(var i=0; i<files.length; i++){
					formData.append("uploadFile", files[i]);
				}
				
				$.ajax({
					url : "/upload/uploadAjaxAction",
					processData : false,
					contentType : false,
					data : formData,
					type : "post",
					success : function(result){
						console.log("success");
					}
				});
			});
		});
	</script>
</body>
</html>