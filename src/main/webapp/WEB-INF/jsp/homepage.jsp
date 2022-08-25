<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>
<div class="page-background">
    <h1>
        <c:url var="imgSrc" value="/img/fitnesslogo.png" />
        <img src="${imgSrc}" class="logo-big" style="height: 50px;"  alt="Logo"/> Tech Fitness Pal</a>
    </h1>

    <div class="flex-container flex-container-style">
        <div class="flex-column flex-column-style">
            <img class="img-tile" src="../../img/exercise_image.png" alt="Exercise Pic">
            <div class="text-tile">
                <h3>Track Workouts</h3>
                <p>Log your workouts with the Tech Fitness Pal!  The track workout feature holds all exercises
                    performed at a specific day. If you enjoyed the workout, we implemented a feature to allow
                    you to do the same workout. Let's get this grind started!</p>
            </div>
        </div>
        <div class="flex-column flex-column-style">
            <img class="img-tile" src=../../img/line-graph-example.png alt="Graph Pic">
            <div class="text-tile">
                <h3>See Results</h3>
                <p>Keep track of your exercise routines and see your daily progress with the help of our custom graphs.
                    Are you meeting your goals often? Easily adjust your goals to keep challenging yourself to
                    achieve a healthier lifestyle.</p>
            </div>
        </div>
        <div class="flex-column flex-column-style">
            <img class="img-tile" src="../../img/weights.jpg" alt="Weights Pic">
            <div class="text-tile">
                <h3>Custom Workouts</h3>
                <p>With the Tech Fitness Pal, a user is able to track his/her/their workouts on an individual scale.
                Progress tuned to the specific user's choices so that people can pace themselves to however fast or slow
                they would like to make their fitness journey. Tech Fitness Pal allows the user to build
                self agency, motivation, and discipline in order to have a personalized experience.
                Your journey is our journey.</p>
            </div>
        </div>
    </div>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />
