<%@ page import="com.techelevator.model.dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="addingWorkout" value="/users/workout/addWorkout" />
<c:url var="customExercise" value="/users/custom-exercise" />

<c:set var="workout" scope="session" value="${workout}"/>

    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>

    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">

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
                <label for="workoutReps">Reps: </label>
                <input type="text" id="workoutReps" value="${workout.workoutReps}" name="workoutReps" placeHolder="Workout Reps" class="form-control" />
            </div>

            <div class="form-group">
                <label for="workoutSets">sets: </label>
                <input type="text" id="workoutSets" value="${workout.workoutSets}" name="workoutSets" placeHolder="Workout Sets" class="form-control" />
            </div>

            <div class="form-group">
                <label for="workoutLength">Length: </label>
                <input type="text" id="workoutLength" value="${workout.workoutLength}" name="workoutLength" placeHolder="Workout Length" class="form-control" />
            </div>


            <div class="form-group">
                <label for="date">Date: </label>
                <input type="date" id="date" name="date" placeHolder="Date" class="form-control" />
            </div>

            <form method="GET" action="${customExercise}">
            <button type="submit" class="btn btn-primary">Add Custom Exercise</button>
            </form>

            <form method="POST" action="${addingWorkout}">
            <button type="submit" class="btn btn-primary">Create Workout</button>
            </form>

        </div>
        <div class="col-sm-4"></div>
    </div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />