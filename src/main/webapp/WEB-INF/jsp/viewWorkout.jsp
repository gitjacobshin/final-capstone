<%@ page import="com.techelevator.model.dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:set var="workout" scope="session" value="${workout}"/>
<c:set var="exercises" scope="session" value="${exercises}"/>

<c:url var="back" value="/users/workout/view/${workout.workoutName}" />


<form method="GET" action="${back}">
  <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>

  <div class="row form-container">

    <div class="col form-column">

      <table>
        <thead>
        <tr>
          <th>Workout Name</th>
          <th>Type</th>
          <th>Total Calories</th>
          <th>Date</th>
        </tr>
        </thead>
          <td>${workout.workoutName}</td>
          <td>${workout.workoutType}</td>
          <td>${workout.totalCalories}</td>
          <td>${workout.date}</td>

      </table>

    </div>

    <div class="col form-column">
      <table>
        <thead>
        <tr>
          <th>Exercise Name</th>
          <th>Reps</th>
          <th>Sets</th>
          <th>Calories</th>
        </tr>
        </thead>
        <tbody>
          <c:forEach var="exercise" items="${exercises}">
            <tr>
              <td>${exercise.exerciseName}</td>
              <td>${exercise.reps}</td>
              <td>${exercise.sets}</td>
              <td>${exercise.calories}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

  </div>

  <c:url var="back"  value="/users/profile"/>
  <td><a href="${back}" class="btn btn-primary">Back</a></td>

</form>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />