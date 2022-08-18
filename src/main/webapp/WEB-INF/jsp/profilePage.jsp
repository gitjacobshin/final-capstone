<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="editProfileAction" value="/users/edit"/>
<c:url var="addWorkoutAction" value="/users/workout/addWorkout"/>

<h1>Profile page</h1>

<div class="flex-container">


    <div class="flex-column">
        <div>
            Profile Info
            <form method="GET" action="${editProfileAction}">
                <button type="submit" class="btn btn-primary">Edit Profile</button>
            </form>
        </div>

        <div>
            Workout History
            <form method="GET" action="${addWorkoutAction}">
                <button type="submit" class="btn btn-primary">Add Workout</button>
            </form>
        </div>
    </div>



    <div class="flex-column">
        Graph Data

    </div>
</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />
