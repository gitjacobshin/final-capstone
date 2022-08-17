<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="validationJs" value="/js/user-validation.js" />
<script src="${validationJs}"></script>

<h1>Tech Fitness Pal</h1>

<div class="flex-container">
    <div class="flex-column">
        <img class="img-tile" src="../../img/exercise_image.png" alt="Exercise Pic">
        <div class="text-tile">
            <h3>Track Workouts</h3>
            <p>Words</p>
        </div>
    </div>
    <div class="flex-column">
        <img class="img-tile" src=../../img/line-graph-example.png alt="Graph Pic">
        <div class="text-tile">
            <h3>See Results</h3>
            <p>More Words</p>
        </div>
    </div>
    <div class="flex-column">
        <img class="img-tile" src="../../img/weights.jpg" alt="Weights Pic">
        <div class="text-tile">
            <h3>Custom Workouts</h3>
            <p>Even More Words</p>
        </div>
    </div>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />
