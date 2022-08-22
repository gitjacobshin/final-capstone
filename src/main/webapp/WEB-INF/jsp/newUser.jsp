<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="formAction" value="/users/new" />
<c:url var="cancelAction" value="/" />
<form:form modelAttribute="user" method="POST" action="${formAction}">
<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	<div class="row form-container">
		<div class="col-sm-4 form-column">
			<div class="form-group">
				<form:label path="userName">User Name: </form:label>
				<form:input path="userName" cssClass="form-control" />
				<form:errors path="userName" cssClass="error" />
			</div>
			<div class="form-group">
				<label for="password">Password: </label>
				<input type="password" id="password" name="password" placeHolder="Password" class="form-control" />
			</div>
			<div class="form-group">
				<label for="confirmPassword">Confirm Password: </label>
				<input type="password" id="confirmPassword" name="confirmPassword" placeHolder="Re-Type Password" class="form-control" />	
			</div>

			<a href="${cancelAction}" class="btn btn-primary">Cancel</a>
			<button type="submit" class="btn btn-primary">Create User</button>
		</div>
	</div>
</form:form>
		
<c:import url="/WEB-INF/jsp/common/footer.jsp" />