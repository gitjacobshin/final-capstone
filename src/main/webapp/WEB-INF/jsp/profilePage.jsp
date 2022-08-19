<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="editProfileAction" value="/users/edit"/>
<c:url var="addWorkoutAction" value="/users/workout/newWorkoutForm"/>
<c:url var="uploadImageAction" value="/users/upload"/>

<c:if test="${not empty currentUser}">
    <c:choose>
        <c:when test="${not empty currentUser.name}">
            <c:set var="displayName" value="${currentUser.name}"/>
        </c:when>
        <c:otherwise>
            <c:set var="displayName" value="${currentUser.userName}"/>
        </c:otherwise>
    </c:choose>
</c:if>

<div class="page-background">
    <h1>
        <c:url var="imgSrc" value="/img/fitnesslogo.png" />
        <img src="${imgSrc}" class="logo-big" style="height: 50px;"  alt="Logo"/> ${displayName}'s Page</a>

    </h1>
    <div class="flex-container flex-container-style">

        <div class="flex-column-profile">
            <div class="flex-column flex-column-style">
                <h4 class="section-header">${displayName}'s Info</h4>

                <table>
                    <td>
                        <img src="imgSrc" alt="Profile Pic"/>
                        <form method="GET" action="${uploadImageAction}">
                            <button type="submit" class="btn btn-primary">Edit Image</button>
                        </form>
                    </td>
                </table>

                <form method="GET" action="${editProfileAction}">
                    <button type="submit" class="btn btn-primary">Edit Profile</button>
                </form>


            </div>
            <div class="flex-column flex-column-style">
               <h4 class="section-header">Workout History</h4>
                <form method="GET" action="${addWorkoutAction}">
                    <button type="submit" class="btn btn-primary">Add Workout</button>
                </form>
            </div>
        </div>


        <div class="flex-column flex-column-style">
            <h4 class="section-header">Graph Data</h4>

        </div>
    </div>
</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />
