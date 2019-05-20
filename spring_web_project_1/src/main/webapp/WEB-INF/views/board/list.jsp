<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../includes/header.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
		
		var result = '<c:out value="${result}"/>';
		
		//등록확인
		checkModal(result);
		
		//뒤로가기 시 history 삭제
		history.replaceState({}, null, null);
		
		$("#regBtn").on("click", function(){
			self.location = "/board/register";
		});
		
		
		function checkModal(result){
			if(result == '' || history.state){
				return;
			}
			if(parseInt(result) > 0){
				$(".modal-body").html("게시글 " + parseInt(result) + "번이 등록되었습니다.");
			}
			$("#myModal").modal("show");
		}
		
		var actionForm = $("#actionForm");
		
		$(".paginate_button a").on("click", function(e){
			e.preventDefault();
			
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		});
		
		$(".move").on("click", function(e){
			e.preventDefault();
			
			actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
			actionForm.attr("action", "/board/get");
			actionForm.submit();
		});
	});
</script>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Tables</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Board List Page
				<button id="regBtn" type="button" class="btn btn-xs pull-right">Register New Board</button>
			</div>
			<div class="panel-body">
			
				<!-- S : table -->
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="board">
							<tr>
								<td><c:out value="${board.bno }"></c:out></td>
								<td><a class="move" href="/board/get?bno=<c:out value="${board.bno }"/>"><c:out value="${board.title }"></c:out></a></td>
								<td><c:out value="${board.writer }"></c:out></td>
								<td><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd"/></td>
								<td><fmt:formatDate value="${board.updatedate }" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- E : table -->
				
				<div class="pull-right">
					<ul class="pagination">
						<c:if test="${pageMaker.prev }">
							<li class="paginate_button previous"><a href="${pageMaker.startPage - 1 }">Previous</a></li>
						</c:if>
						
						<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
							<li class="paginate_button"><a href="${num }">${num }</a></li>
						</c:forEach>
						
						<c:if test="${pageMaker.next }">
							<li class="paginate_button previous"><a href="${pageMaker.endPage + 1 }">next</a></li>
						</c:if>
					</ul>
				</div>
				
				<form id="actionForm" action="/board/list" method="get">
					<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
					<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
				</form>
				
				<!-- S : modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="moModalLabel">알림</h4>
							</div>
							<div class="modal-body">
								처리가 완료되었습니다.
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary">Save changes</button>
							</div>
						</div>
					</div>
				</div>
				<!-- E : modal -->
			</div>
		</div>
	</div>
</div>

<%@ include file="../includes/footer.jsp" %>