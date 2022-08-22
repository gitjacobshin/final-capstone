<%@ page import="com.techelevator.model.dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="finalizeWorkout" value="/users/workout/newWorkoutForm"/>
<c:url var="customExercise" value="/users/custom-exercise" />

<c:set var="workout" scope="session" value="${workout}"/>
<c:set var="exercises" scope="session" value="${exercises}"/>


<form method="POST" action="${finalizeWorkout}">
  <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>

  <div class="row form-container">

    <div class="col-sm-4 form-column">

        <table>
          <thead>
          <tr>
            <th>Exercise Name</th>
            <th>Reps</th>
            <th>Sets</th>
            <th>Calories </th>
          </tr>

          </thead>
          <tbody>

          <c:forEach var="exercise" items="${exercises}">
            <tr>
              <td>${exercise.exerciseName}</td>
              <td>${exercise.reps}</td>
              <td>${exercise.sets}</td>
              <td>${exercise.calories}</td>
              <c:url var="editExercise" value="/users/custom-exercise/edit/${exercise.exerciseName}"/>
              <td><a href="${editExercise}" class="btn btn-primary">Edit</a></td>
              <c:url var="deleteExercise"  value="/users/custom-exercise"/>
              <td><a href="${deleteExercise}" class="btn btn-primary">Delete</a></td>
            </tr>

          </c:forEach>

          </tbody>
        </table>


      <a href="${customExercise}" class="btn btn-primary">Add Custom Exercise</a>

      <button type="submit" class="btn btn-primary">Finalize Workout</button>


    </div>

  </div>
</form>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />

