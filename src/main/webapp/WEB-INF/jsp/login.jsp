<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<c:url var="cancelAction" value="/" />
<c:url var="registerRedirect" value="/users/new"/>

<script type="text/javascript">
	$(document).ready(function () {
	
		$("form").validate({
			
			rules : {
				userName : {
					required : true
				},
				password : {
					required : true
				}
			},
			messages : {			
				confirmPassword : {
					equalTo : "Passwords do not match"
				}
			},
			errorClass : "error"
		});
	});
</script>

<div class="row form-container">
	<div class="col-sm-4 form-column">
		<c:url var="formAction" value="/login" />
		<form method="POST" action="${formAction}">
		<input type="hidden" name="destination" value="${param.destination}"/>
		<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
			<div class="form-group">
				<label for="userName">User Name: </label>
				<input type="text" id="userName" name="userName" placeHolder="User Name" class="form-control" />
			</div>
			<div class="form-group">
				<label for="password">Password: </label>
				<input type="password" id="password" name="password" placeHolder="Password" class="form-control" />
			</div>
			<a href="${registerRedirect}">No account? Click here to create!</a>
			<div>
				<a href="${cancelAction}" class="btn btn-primary">Cancel</a>
				<button type="submit" class="btn btn-primary">Login</button>
			</div>
		</form>
	</div>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />