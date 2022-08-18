<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="createExerciseAction" value="/users/workout/addWorkout"/>
<c:url var="submitWorkoutAction" value="/users/profile"/>

<h1>Add Workout Page</h1>

<div>
    <form method="GET" action="${createExerciseAction}">
        <button type="submit" class="btn btn-primary">Create Exercise</button>
    </form>
</div>

<div>
    <form method="POST" action="${submitWorkoutAction}">
        <button type="submit" class="btn btn-primary">Submit Workout</button>
    </form>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />
