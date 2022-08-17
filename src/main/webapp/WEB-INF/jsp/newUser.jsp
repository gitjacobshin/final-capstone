<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="formAction" value="/users/new" />
<form method="POST" action="${formAction}">
<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<div class="form-group">
				<label for="userName">User Name: </label>
				<input type="text" id="userName" name="userName" placeHolder="User Name" class="form-control" />
			</div>
			<div class="form-group">
				<label for="password">Password: </label>
				<input type="password" id="password" name="password" placeHolder="Password" class="form-control" />
			</div>
			<div class="form-group">
				<label for="confirmPassword">Confirm Password: </label>
				<input type="password" id="confirmPassword" name="confirmPassword" placeHolder="Re-Type Password" class="form-control" />	
			</div>

			<div class="form-group">
				<label for="name">Name: </label>
				<input type="text" id="name" name="name" placeHolder="Name" class="form-control" />
			</div>

			<div class="form-group">
				<label for="height">Height: </label>
				<input type="text" id="height" name="height" placeHolder="Height" class="form-control" />
			</div>

			<div class="form-group">

				<form:label path="currentWeight" for="currentWeight" cssClass="detail-header" >Current weight: </form:label>
				<input type="text" id="currentWeight" name="currentWeight" placeHolder="Current Weight" class="form-control" />
				<div class="detail-value">
					<form:errors path="currentWeight" cssClass="error" />
					<form:input path="currentWeight" cssClass="form-control" />
				</div>
			</div>

			<div class="form-group">
				<label for="desiredWeight">Desired Weight: </label>
				<input type="text" id="desiredWeight" name="desiredWeight" placeHolder="Desired Weight" class="form-control" />
			</div>

			<div class="form-group">
				<label for="goal">Goal: </label>
				<input type="text" id="goal" name="goal" placeHolder="Goal" class="form-control" />
			</div>

			<div class="form-group">
				<label for="birthday">Birthday: </label>
				<input type="date" id="birthday" name="birthday" placeHolder="Birthday" class="form-control" />
			</div>

<%--			<div class="form-group">--%>
<%--				<label for="file">Insert Profile Picture </label>--%>
<%--				<input type="file" name="file" id="file"/>--%>
<%--			</div>--%>

			<button type="submit" class="btn btn-primary">Create User</button>
		</div>
		<div class="col-sm-4"></div>
	</div>
</form>
		
<c:import url="/WEB-INF/jsp/common/footer.jsp" />