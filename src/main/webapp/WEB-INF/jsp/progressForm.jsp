<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="formAction" value="/users/progress" />
<c:url var="cancelAction" value="/users/profile"/>
<c:set var="currentUser" scope="session" value="${currentUser}"/>

<form:form method="POST" action="${formAction}">
    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>

    <div class="row form-container">
        <div class="col-sm-4 form-column">

            <div class="form-group">
                <label for="workoutDate">Date: </label>
                <input type="date" id="workoutDate" name="workoutDate" placeHolder="Date" class="form-control" />
            </div>

            <div class="form-group">
                <label for="workoutType">Workout Type: </label>
                <select name="workoutType" id="workoutType" class="form-control">
                    <option value="Arms">Arms</option>
                    <option value="Legs">Legs</option>
                    <option value="Core">Core</option>
                    <option value="Cardio">Cardio</option>
                    <option value="Rest">Rest</option>
                </select>
            </div>

            <div class="form-group">
                <label for="workoutLength">Workout Length (min): </label>
                <input type="number" id="workoutLength" name="workoutLength" placeHolder="ex. 45"
                       class="form-control" />
            </div>

            <div class="form-group">
                <label for="weightAfter">Weight After Workout (lbs): </label>
                <input type="number" id="weightAfter" name="weightAfter" placeHolder="ex. 200"
                       class="form-control" />
            </div>

            <a href="${cancelAction}" class="btn btn-primary">Cancel</a>
            <button type="submit" class="btn btn-primary">Track Progress</button>

        </div>
    </div>
</form:form>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />