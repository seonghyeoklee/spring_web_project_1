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
			
			var regex = new RegExp("(.*?)\.(zip|exe|sh|alz)$");
			var maxSize = 5242880;
			
			function checkExtension(fileName, fileSize){
				
				if(fileSize >= maxSize){
					alert("파일 사이즈 초과");
					return false;
				}
				
				if(regex.test(fileName)){
					alert("업로드 불가");
					return false;
				}
				
				return true;
			}
			
			$("#uploadBtn").on("click", function(){
				
				var formData = new FormData();
				var inputFile = $("input[name='uploadFile']");
				var files = inputFile[0].files;
				
				console.log(files);
				
				for(var i=0; i<files.length; i++){
					if(!checkExtension(files[i].name, files[i].size)){
						return false;
					}
					
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
						console.log(result);
					}
				});
			});
			
		});
	</script>
</body>
</html>