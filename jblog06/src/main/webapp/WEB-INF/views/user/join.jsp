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
	var id = document.getElementById("blog-id").value;
	/* alert(id+' 문자 붙이기.'); */
	
	const Http = new XMLHttpRequest();
	const url = 'join/'+id;
	/* alert(url); */
	
	Http.open('GET',url);
	Http.send();
	
	Http.onreadystatechange = function () {
		/* alert('요청 받기시도...'); */
	    if (this.readyState == 4 && this.status == 200) {
	    	/* alert('요청 성공.'); */
	    	
	    	var result = Http.responseText;
	    	if("사용가능한 ID입니다." == result){
	    		/* alert(Http.responseText+"1"); */
	    		var e= document.getElementById("img-checkemail")
	    		e.style.display="";
	    		console.log(e);
	    		
	    	}else if("현재 사용중인 ID입니다." == result){
	    		alert(Http.responseText+"2");
	    		var e= document.getElementById("img-checkemail")
	    		e.style.display="none";
	    		console.log(e);
	    	}else{
	    		alert(Http.responseText+"3");
	    	}
	    /*     const response = JSON.parse(Http.responseText);
	        alert(response.message); */
	    } else {
	    	/* alert('요청 실패.'); */
	    }
	}
	/* Http.onreadystatechange = function () {
		if(this.readyState == 4 && this.status == 200){
			console.log(Http.responseText);
		}
		else{
			console.log('요청 실패.');
		}
	}
	 */
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
			<p id="p-checkemail" style= "padding:3px 0 5px 0; text-align: left; color: #f00">
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

			<input type="submit" value="가입하기" onclick="onCheckingId()">

		</form:form>
	</div>
</body>
</html>
