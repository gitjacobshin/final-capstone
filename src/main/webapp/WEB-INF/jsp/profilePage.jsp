<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="editProfileAction" value="/users/edit"/>
<c:url var="addWorkoutAction" value="/users/workout/newWorkoutForm"/>
<c:url var="uploadImageAction" value="/users/upload"/>
<c:url var="trackProgressAction" value="/users/progress"/>

<c:set var="workouts" scope="session" value="${workouts}"/>
<c:set var="exercises" scope="session" value="${exercises}"/>

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

                <div class="flex-container-profile">
                    <div class="flex-column-profile">
                        <c:choose>
                            <c:when test="${not empty currentUser.profilePic}">
                                <c:url var="profPic" value="/img/uploads/${currentUser.profilePic}"/>
                                <img class="profile-pic" src="${profPic}" alt="Profile Pic"/>
                            </c:when>
                            <c:otherwise>
                                <c:url var="profPic" value="/img/blank-profile.png"/>
                                <img class="profile-pic" src="${profPic}" alt="Profile Pic"/>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="flex-column-profile">
                        <table class="info-table table-style">
                            <tr>
                                <th>Name:
                                <td>${currentUser.name}</td>
                                </th>
                            </tr>
                            <tr>
                                <th>Birthday:
                                <td>${currentUser.birthdate}</td>
                                </th>
                            </tr>
                            <tr>
                                <th>Height:
                                <td>${currentUser.height} in.</td>
                                </th>
                            </tr>
                            <tr>
                                <th>Starting Weight:
                                <td>${currentUser.currentWeight} lbs.</td>
                                </th>
                            </tr>
                            <tr>
                                <th>Desired Weight:
                                <td>${currentUser.desiredWeight} lbs.</td>
                                </th>
                            </tr>

                        </table>
                    </div>
                </div>

                <div class="flex-column">
                    <table class="table-style">

                        <th>Goal:<td class="goal-table">${currentUser.goal}</td></th>

                    </table>
                </div>

                <div class="profile-section">
                    <form class="flex-column-profile" method="GET" action="${uploadImageAction}">
                        <button type="submit" class="btn btn-primary">Edit Image</button>
                    </form>

                    <form class="flex-column-profile" method="GET" action="${editProfileAction}">
                        <button type="submit" class="btn btn-primary">Edit Profile</button>
                    </form>
                </div>

            </div>
            <div class="flex-column flex-column-style">
               <h4 class="section-header">Workout History</h4>

                <div>
                    <table>
                        <thead>
                        <tr>
                            <th>Workout Name</th>
                            <th>Type</th>
                            <th>Total Calories</th>
                            <th>Date</th>
                        </tr>

                        </thead>
                        <tbody>

                        <c:forEach var="workout" items="${workouts}">
                            <tr>
                                <td>${workout.workoutName}</td>
                                <td>${workout.workoutType}</td>
                                <td>${workout.totalCalories}</td>
                                <td>${workout.date}</td>

                                <c:url var="editWorkout" value="/users/workout/edit/${workout.workoutName}"/>
                                <td><a href="${editWorkout}" class="btn btn-primary">View/Edit</a></td>

                                <c:url var="deleteWorkout"  value="/users/delete-workout/${workout.id}"/>
                                <td><a href="${deleteWorkout}" class="btn btn-primary">Delete</a></td>

                            </tr>

                        </c:forEach>

                        </tbody>
                    </table>
                </div>

                <form method="GET" action="${addWorkoutAction}">
                    <button type="submit" class="btn btn-primary">Add Workout</button>
                </form>
            </div>

            <div class="flex-column flex-column-style">
                <h4 class="section-header">Recommended Exercises</h4>

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

                        <c:forEach var="exercise" items="${exercises}" begin="0" end="4">
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

        </div>


        <div class="flex-column flex-column-style">
            <h4 class="section-header">Graph Data</h4>


            <canvas id="progressChart" width="400px" height="400px"></canvas>
            <script type="module">
                const ctx = 'progressChart'
                const progressChart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                        datasets: [{
                            label: '# of Votes',
                            data: [12, 19, 3, 5, 2, 3],
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)'
                            ],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            </script>

            <form method="GET" action="${trackProgressAction}">
                <button type="submit" class="btn btn-primary">Track Progress</button>
            </form>

        </div>

    </div>

    </div>
</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />
