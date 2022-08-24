<%@ page import="com.techelevator.model.dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="finalizeWorkout" value="/users/exerciseForm"/>
<c:url var="customExercise" value="/users/custom-exercise" />
<%--<c:url var="obtainRecentExercises" value="/users/recentExercises"/>--%>
<c:url var="cancelAction" value="/users/workout/newWorkoutForm" />

<c:set var="workout" scope="session" value="${workout}"/>
<c:set var="exercises" scope="session" value="${exercises}"/>
<c:set var="recentExercises" scope="session" value="${recentExercises}"/>


<form method="POST" action="${finalizeWorkout}">
  <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>

  <div class="row form-container-exercise">

    <div class="form-column">
      <h4>Selected Exercises</h4>
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

              <c:url var="deleteExercise"  value="/users/delete-exercise/${exercise.id}"/>
              <td><a href="${deleteExercise}" class="btn btn-primary">Delete</a></td>

            </tr>

          </c:forEach>

          </tbody>
        </table>

      <div class="flex-column">
        <h4>Recent Exercises</h4>

        <div>
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

            <c:forEach var="recentExercises" items="${recentExercises}" begin="0" end="4">
              <tr>
                <td>${recentExercises.exerciseName}</td>
                <td>${recentExercises.reps}</td>
                <td>${recentExercises.sets}</td>
                <td>${recentExercises.calories}</td>

                <c:url var="addExercise"  value="/users/add-exercise/${exercise.id}"/>
                <td><a href="${addExercise}" class="btn btn-primary">Add</a></td>

              </tr>

            </c:forEach>

            </tbody>
          </table>
        </div>
      </div>

      <a href="${cancelAction}" class="btn btn-primary">Cancel</a>

      <a href="${customExercise}" class="btn btn-primary">Add Custom Exercise</a>

      <button type="submit" class="btn btn-primary">Finalize Workout</button>


    </div>
<%--    <div class="col-sm-4 form-column">--%>
<%--      <div class="flex-column flex-column-style">--%>
<%--        <h4 class="section-header">Recommended Exercises</h4>--%>

<%--        <div>--%>
<%--          <table>--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--              <th>Exercise Name</th>--%>
<%--              <th>Reps</th>--%>
<%--              <th>Sets</th>--%>
<%--              <th>Calories</th>--%>
<%--            </tr>--%>

<%--            </thead>--%>
<%--            <tbody>--%>

<%--              <c:forEach var="exercise" items="${exercises}" begin="0" end="4">--%>
<%--                <tr>--%>
<%--                  <td>${exercise.exerciseName}</td>--%>
<%--                  <td>${exercise.reps}</td>--%>
<%--                  <td>${exercise.sets}</td>--%>
<%--                  <td>${exercise.calories}</td>--%>
<%--                </tr>--%>

<%--              </c:forEach>--%>

<%--            </tbody>--%>
<%--          </table>--%>
<%--        </div>--%>
<%--      </div>--%>
<%--    </div>--%>
  </div>
</form>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />

