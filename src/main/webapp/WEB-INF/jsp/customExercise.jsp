<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="submitCustomExercise" value="/users/workout"/>

<h1>Custom Exercise Page</h1>

<div>
    <form method="POST" action="${submitCustomExercise}">
        <button type="submit" class="btn btn-primary">Submit Exercise</button>
    </form>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />
