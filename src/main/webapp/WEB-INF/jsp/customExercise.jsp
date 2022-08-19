<%@ page import="com.techelevator.model.dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="formAction" value="/users/custom-exercise" />

<c:set var="exercise" scope="session" value="${exercise}"/>

<form method="POST" action="${formAction}">
    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>

    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">

            <div class="form-group">
                <label for="category">WE ARE NOW IN THE CUSTOM EXERCISE PAGE: </label>
                <select name="category" id="category" class="form-control">
                    <option value="Arms">Arms</option>
                    <option value="Legs">Legs</option>
                    <option value="Core">Core</option>
                    <option value="Cardio">Cardio</option>
                    <option value="Rest">Rest</option>
                </select>

            </div>

            <div class="form-group">
                <label for="reps">Reps: </label>
                <input type="text" id="reps" value="${workout.reps}" name="reps" placeHolder="Reps" class="form-control" />
            </div>

            <div class="form-group">
                <label for="sets">sets: </label>
                <input type="text" id="sets" value="${workout.sets}" name="sets" placeHolder="Sets" class="form-control" />
            </div>

            <div class="form-group">
                <label for="workoutName">Name: </label>
                <input type="text" id="workoutName" value="${workout.workoutName}" name="workoutName" placeHolder="Workout Name" class="form-control" />
            </div>

            <button type="submit" class="btn btn-primary">Create Exercise</button>

        </div>
        <div class="col-sm-4"></div>
    </div>


</form>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />