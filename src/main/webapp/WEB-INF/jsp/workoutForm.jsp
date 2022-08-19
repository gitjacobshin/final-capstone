<%@ page import="com.techelevator.model.dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="addWorkoutAction" value="/users/workout/newWorkoutForm"/>
<c:url var="customExercise" value="/users/custom-exercise" />

<c:set var="workout" scope="session" value="${workout}"/>


<form method="POST" action="${addWorkoutAction}">
    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>

    <div class="row form-container">

        <div class="col-sm-4 form-column">

            <div class="form-group">
                <label for="workoutType">Type: </label>
                <select name="workoutType" id="workoutType" class="form-control">
                    <option value="Arms">Arms</option>
                    <option value="Legs">Legs</option>
                    <option value="Core">Core</option>
                    <option value="Cardio">Cardio</option>
                    <option value="Rest">Rest</option>
                </select>

            </div>

            <div class="form-group">
                <label for="workoutName">Workout Name: </label>
                <input type="text" id="workoutName" value="${workout.workoutName}" name="workoutName" placeHolder="Workout Name" class="form-control" />
            </div>

            <div class="form-group">
                <label for="date">Date (yyyy-MM-dd): </label>
                <input type="date" id="date" name="date" placeHolder="Date" class="form-control" />
            </div>

            <a href="${customExercise}" class="btn btn-primary">Add Custom Exercise</a>

            <button type="submit" class="btn btn-primary">Create Workout</button>


        </div>

    </div>
</form>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />