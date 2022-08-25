<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<c:url var="editProfileAction" value="/users/edit"/>
<c:url var="addWorkoutAction" value="/users/workout/newWorkoutForm"/>
<c:url var="uploadImageAction" value="/users/upload"/>
<c:url var="trackProgressAction" value="/users/progress"/>
<c:url var="obtainRecentExercises" value="/users/recentExercises"/>

<c:set var="workouts" scope="session" value="${workouts}"/>
<c:set var="exercises" scope="session" value="${exercises}"/>
<c:set var="recentExercises" scope="session" value="${recentExercises}"/>

<c:set var="progressDateList" scope="session" value="${progressDates}"/>
<c:set var="progressWeightsList" scope="session" value="${progressWeights}"/>
<c:set var="desiredWeightsList" scope="session" value="${desiredWeights}"/>
<c:set var="progressTimesList" scope="session" value="${progressTimes}"/>
<c:set var="workoutTypesList" scope="session" value="${workoutTypes}"/>

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

                                <c:url var="editWorkout" value="/users/workout/view/${workout.workoutName}"/>
                                <td><a href="${editWorkout}" class="btn btn-primary">View</a></td>

                                <c:url var="deleteWorkout"  value="/users/delete-workout/${workout.id}"/>
                                <td><a href="${deleteWorkout}" class="btn btn-primary">Delete</a></td>

                            </tr>

                        </c:forEach>

                        </tbody>
                    </table>
                </div>
                <div class="profile-section">
                    <form class="flex-column-profile" method="GET" action="${addWorkoutAction}">
                        <button type="submit" class="btn btn-primary">Add Workout</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="flex-column flex-column-style">
            <h4 class="section-header">Data From Last 7 Workouts</h4>

<%--            Weights Line Graph--%>
            <div class="graph">
                <canvas id="progressWeightChart"></canvas>
                <script type="module">
                    <c:set var="graphType" value="line"/>
                    <c:set var='chartName' value='Recorded vs. Desired Weight per Workout'/>
                    <c:set var="data" value="${progressWeightsList}"/>
                    <c:set var="data2" value="${desiredWeightsList}"/>
                    <c:set var="label" value="${progressDateList}"/>

                    var dates = [];
                    <c:forEach var="date" items="${progressDateList}">
                        dates.push('${date}')
                    </c:forEach>
                    const ctx = 'progressWeightChart'
                    const progressWeightChart = new Chart(ctx, {
                       type: '${graphType}',
                       data: {
                           labels: dates,
                           datasets: [{
                               label: 'Recorded Weight',
                               data: ${data},
                               fill: true,
                               borderColor: 'rgb(255, 159, 64)',
                               tension: 0.1,
                               backgroundColor: 'rgb(255, 159, 64, 0.25)'
                           }
                           , {
                               label: 'Desired Weight',
                               data: ${data2},
                               fill: true,
                               borderColor: 'rgb(54, 162, 235)',
                               tension: 0.1,
                               backgroundColor: 'rgb(54, 162, 235, 0.25)'
                               }]
                       },
                       options: {
                           scales: {
                               x: {
                                   display: true,
                                   title: {
                                       display: true,
                                       text: 'Workout Date'
                                   },
                                   ticks: {
                                       maxRotation: 45,
                                       minRotation: 45
                                   }
                               },
                               y: {
                                   beginAtZero: true,
                                   display: true,
                                   title: {
                                       display: true,
                                       text: 'Weight'
                                   }
                               }
                           },
                           plugins: {
                               title: {
                                   display: true,
                                   text: '${chartName}',
                                   color: 'rgb(0,0,0)'
                               }
                           }
                       }
                    });
                </script>
            </div>

<%--            Times Line Graph--%>
            <div class="graph">
                <canvas id="progressTimesChart"></canvas>
                <script type="module">
                    <c:set var="graphType" value="line"/>
                    <c:set var='chartName' value='Time per Workout'/>
                    <c:set var="data" value="${progressTimesList}"/>
                    <c:set var="label" value="${progressDateList}"/>

                    var dates = [];
                    <c:forEach var="date" items="${progressDateList}">
                    dates.push('${date}')
                    </c:forEach>
                    const ctx_2 = 'progressTimesChart'
                    const progressTimesChart = new Chart(ctx_2, {
                        type: '${graphType}',
                        data: {
                            labels: dates,
                            datasets: [{
                                label: 'Time',
                                data: ${data},
                                fill: true,
                                borderColor: 'rgb(255, 159, 64)',
                                tension: 0.1,
                                backgroundColor: 'rgb(255, 159, 64, 0.25)'
                            }]
                        },
                        options: {
                            scales: {
                                x: {
                                    display: true,
                                    title: {
                                        display: true,
                                        text: 'Workout Date'
                                    },
                                    ticks: {
                                        maxRotation: 45,
                                        minRotation: 45
                                    }
                                },
                                y: {
                                    beginAtZero: true,
                                    display: true,
                                    title: {
                                        display: true,
                                        text: 'Time'
                                    }
                                }
                            },
                            plugins: {
                                title: {
                                    display: true,
                                    text: '${chartName}',
                                    color: 'rgb(0,0,0)'
                                }
                            }
                        }
                    });
                </script>
            </div>

<%--        Types Bar Chart    --%>
            <h4 class="section-header">Data From Last 30 Workouts</h4>
            <div class="graph">
                <canvas id="workoutTypeChart"></canvas>
                <script type="module">
                    <c:set var="graphType" value="bar"/>
                    <c:set var='chartName' value='Workout Type Count'/>

                    var types = [];
                    <c:forEach var="type" items="${workoutTypesList}">
                    types.push('${type}')
                    </c:forEach>

                    const ctx = 'workoutTypeChart'
                    const workoutTypeChart = new Chart(ctx, {
                        type: '${graphType}',
                        data: {
                            labels: ['Arms', 'Legs', 'Core', 'Cardio', 'Rest'],
                            datasets: [{
                                label: 'Workout Type Count',
                                data: [types.filter((v) => (v === 'Arms')).length,
                                    types.filter((v) => (v === 'Legs')).length,
                                    types.filter((v) => (v === 'Core')).length,
                                    types.filter((v) => (v === 'Cardio')).length,
                                    types.filter((v) => (v === 'Rest')).length,
                                ],
                                backgroundColor: [
                                    'rgba(255, 159, 64, 0.25)',
                                    'rgba(255, 159, 64, 0.25)',
                                    'rgba(255, 159, 64, 0.25)',
                                    'rgba(255, 159, 64, 0.25)',
                                    'rgba(255, 159, 64, 0.25)',
                                    ],
                                borderColor: [
                                    'rgba(255, 159, 64)',
                                    'rgba(255, 159, 64)',
                                    'rgba(255, 159, 64)',
                                    'rgba(255, 159, 64)',
                                    'rgba(255, 159, 64)',
                                ],
                                borderWidth: 1
                            }]
                        },
                        options: {
                            plugins: {
                                title: {
                                    display: true,
                                    text: '${chartName}',
                                    color: 'rgb(0,0,0)'
                                }
                            }
                        }
                    });
                </script>
            </div>
            <div class="profile-section">
                <form class="flex-column-profile" method="GET" action="${trackProgressAction}">
                    <button type="submit" class="btn btn-primary">Track Progress</button>
                </form>
            </div>

        </div>

    </div>

    </div>
</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />
