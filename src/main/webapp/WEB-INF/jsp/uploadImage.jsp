<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="formAction" value="/users/edit" />
<c:set var="currentUser" scope="session" value="${currentUser}"/>
<form:form modelAttribute="user" method="POST" action="${formAction}">
    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
    <div class="form-container">
        <div class="form-column">
            <div class="form-group">
                <label for="file">Insert Profile Picture </label>
                <input type="file" name="file" id="file"/>
            </div>

            <button type="submit" class="btn btn-primary">Update User</button>
        </div>
    </div>
</form:form>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />