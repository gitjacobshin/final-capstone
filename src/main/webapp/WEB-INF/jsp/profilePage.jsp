<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="editProfileAction" value="/users/edit"/>
<c:url var="addWorkoutAction" value="/users/workout/addWorkout"/>

<div class="page-background">
    <h1>
        <c:url var="imgSrc" value="/img/fitnesslogo.png" />
        <c:if test="${not empty currentUser}">
            <c:choose>
                <c:when test="${not empty currentUser.name}">
                    <img src="${imgSrc}" class="logo-big" style="height: 50px;"  alt="Logo"/> ${currentUser.name}'s Page</a>
                </c:when>
                <c:otherwise>
                    <img src="${imgSrc}" class="logo-big" style="height: 50px;"  alt="Logo"/> ${currentUser.userName}'s Page</a>
                </c:otherwise>
            </c:choose>
        </c:if>

    </h1>
    <div class="flex-container flex-container-style">

        <div class="flex-column-profile">
            <div class="flex-column flex-column-style">
                Profile Info
                <form method="GET" action="${editProfileAction}">
                    <button type="submit" class="btn btn-primary">Edit Profile</button>
                </form>
            </div>
            <div class="flex-column flex-column-style">
                Workout History
                <form method="GET" action="${addWorkoutAction}">
                    <button type="submit" class="btn btn-primary">Add Workout</button>
                </form>
            </div>
        </div>


        <div class="flex-column flex-column-style">
            Graph Data

        </div>
    </div>
</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />
