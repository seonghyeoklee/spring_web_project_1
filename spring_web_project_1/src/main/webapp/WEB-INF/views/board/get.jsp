<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../includes/header.jsp" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Register</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel panel-heading">
				Board Read Page
			</div>
			<div class="panel panel-body">
			
				<div class="form-group">
					<label>Bno</label>
					<input class="form-control" name="bno" value='<c:out value="${board.boardIdx }"/>' readonly="readonly">
				</div>
				<div class="form-group">
					<label>Title</label>
					<input class="form-control" name="title" value='<c:out value="${board.title }"/>' readonly="readonly">
				</div>
				<div class="form-group">
					<label>Content</label>
					<textarea class="form-control" rows="3" name="content" readonly="readonly"><c:out value="${board.content }"/></textarea>
				</div>
				<div class="form-group">
					<label>Writer</label>
					<input class="form-control" name="writer" value='<c:out value="${board.writer }"/>' readonly="readonly">
				</div>
				
				<button data-oper="modify" class="btn btn-default"><a href="/board/modify?boardIdx=<c:out value="${board.boardIdx }"/>">Modify</a></button>
				<button data-oper="list" class="btn btn-info"><a href="/board/list">List</a></button>
			</div>
		</div>
	</div>
</div>

<%@ include file="../includes/footer.jsp" %>