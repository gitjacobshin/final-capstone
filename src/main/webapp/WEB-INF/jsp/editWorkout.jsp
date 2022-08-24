<%@ page import="com.techelevator.model.dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>


<c:url var="toExercises" value="/users/workout/previousWorkoutForm"/>
<c:url var="addWorkoutAction" value="/users/workout/newWorkoutForm"/>
<c:url var="customExercise" value="/users/custom-exercise" />
<c:url var="cancelAction" value="/users/profile" />

<c:set var="workout" scope="session" value="${workout}"/>


<form method="POST" action="${toExercises}">
  <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>

  <div class="row form-container">

    <div class="col-sm-4 form-column">

      <div class="form-group">
        <label for="workoutType">Type: </label>
        <select name="workoutType" id="workoutType" value="${workout.workoutType}" class="form-control">
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
        <input type="date" id="date" value="${workout.date}" name="date" placeHolder="Date" class="form-control" />
      </div>

      <button type="submit" class="btn btn-primary">Edit Workout</button>

      <a href="${cancelAction}" class="btn btn-primary">Cancel</a>

    </div>

  </div>
</form>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />