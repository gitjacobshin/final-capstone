<%@ page import="com.techelevator.model.dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="formAction" value="/users/edit" />
<c:set var="currentUser" scope="session" value="${currentUser}"/>
<form:form modelAttribute="user" method="POST" action="${formAction}">
<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>

    <div class="row form-container">
        <div class="col-sm-4 form-column">

            <div class="form-group">
                <label for="name">Name: </label>
                <input type="text" id="name" value="${currentUser.name}" name="name" placeHolder="Name" class="form-control" />
            </div>

            <div class="form-group">
                <label for="height">Height: </label>
                <input type="text" id="height" value="${currentUser.height}" name="height" placeHolder="Height" class="form-control" />
            </div>

            <div class="form-group">
                <label for="currentWeight">Current Weight: </label>
                <input type="text" id="currentWeight" value="${currentUser.currentWeight}" name="currentWeight" placeHolder="Current Weight" class="form-control" />
            </div>

            <div class="form-group">
                <label for="desiredWeight">Desired Weight: </label>
                <input type="text" id="desiredWeight" value="${currentUser.desiredWeight}" name="desiredWeight" placeHolder="Desired Weight" class="form-control" />
            </div>

            <div class="form-group">
                <label for="goal">Goal: </label>
                <input type="text" id="goal" name="goal" value="${currentUser.goal}" placeHolder="Goal" class="form-control" />
            </div>

            <div class="form-group">
                <label for="birthdate">Birthday (yyyy-MM-dd): </label>
                <input type="date" id="birthdate" name="birthdate" value="${currentUser.birthdate}" placeHolder="Birthdate" class="form-control" />
            </div>

            <div class="form-group">
                <label for="file">Insert Profile Picture </label>
                <input type="file" name="file" id="file"/>
            </div>

            <button type="submit" class="btn btn-primary">Update User</button>

        </div>
    </div>
</form:form>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />