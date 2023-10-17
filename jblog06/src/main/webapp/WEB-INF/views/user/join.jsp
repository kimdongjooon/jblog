<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script>
function onCheckingId(){
	alert("!!!");
	var paragraph = document.getElementById("blog-id");
	var id = paragraph.innerHTML;
	alert(id);

}
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<form:form 
			modelAttribute="userVo"
			class="join-form" 
			id="join-form" 
			method="post" 
			action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="${userVo.name}">
			<p style= "padding:3px 0 5px 0; text-align: left; color: #f00">
				<spring:hasBindErrors name="userVo">
					<c:if test="${errors.hasFieldErrors('name') }">
						<spring:message code="${errors.getFieldError('name').codes[0] }" />
					</c:if>
				</spring:hasBindErrors>
			</p>
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text" value="${userVo.id}"> 
			<input id="btn-checkemail" onclick="onCheckingId()" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<p style= "padding:3px 0 5px 0; text-align: left; color: #f00">
				<c:if test="${not empty checkId}">
					현재 사용중인 ID입니다.
				</c:if>
				<c:if test="${assets == 1}">
					assets계정은 생성할 수 없습니다. 
				</c:if>
				
				<spring:hasBindErrors name="userVo">
					<c:if test="${errors.hasFieldErrors('id') }">
						<spring:message code="${errors.getFieldError('id').codes[0] }" />
					</c:if>
				</spring:hasBindErrors>
			</p>

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />
			<p style= "padding:3px 0 5px 0; text-align: left; color: #f00">
				<spring:hasBindErrors name="userVo">
					<c:if test="${errors.hasFieldErrors('password') }">
						<spring:message code="${errors.getFieldError('password').codes[0] }" />
					</c:if>
				</spring:hasBindErrors>
			</p>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
