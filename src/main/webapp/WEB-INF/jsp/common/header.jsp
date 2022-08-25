<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Tech-Fitness Pal</title>
<c:url var="bootstrapCss" value="/css/bootstrap.min.css" />
<c:url var="siteCss" value="/css/site.css" />

<c:url var="jQueryJs" value="/js/jquery.min.js" />
<c:url var="jqValidateJs" value="/js/jquery.validate.min.js" />
<c:url var="jqvAddMethJs" value="/js/additional-methods.min.js" />
<c:url var="jqTimeagoJs" value="/js/jquery.timeago.js" />
<c:url var="popperJs" value="/js/popper.min.js" />
<c:url var="bootstrapJs" value="/js/bootstrap.min.js" />
<c:url var="chartJs" value="/js/Chart.js_3.9.1_chart.js"/>

<link rel="stylesheet" type="text/css" href="${bootstrapCss}">
<link rel="stylesheet" type="text/css" href="${siteCss}">

<script src="${jQueryJs}"></script>
<script src="${jqValidateJs}"></script>
<script src="${jqvAddMethJs}"></script>
<script src="${jqTimeagoJs}"></script>
<script src="${popperJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="${chartJs}"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("time.timeago").timeago();

		$("#logoutLink").click(function(event) {
			$("#logoutForm").submit();
		});

		var pathname = window.location.pathname;
		$("nav a[href='" + pathname + "']").parent().addClass("active");

	});
</script>

</head>
<body>
	<div class="entire-header">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#"> 
			<c:url var="homePageHref" value="/" />
			<c:url var="imgSrc" value="/img/fitnesslogo.png" />
			<a href="${homePageHref}"><img src="${imgSrc}" class="img-fluid" style="height: 50px;" /></a>
		</a>
<%--		<button class="navbar-toggler ml-auto" type="button" data-toggle="collapse"--%>
<%--			data-target="#navbarSupportedContent"--%>
<%--			aria-controls="navbarSupportedContent" aria-expanded="false"--%>
<%--			aria-label="Toggle navigation">--%>
<%--			<span class="navbar-toggler-icon"></span>--%>
<%--		</button>--%>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<table>
				<tr>
					<div class="navbar-nav mr-auto">
						<c:if test="${not empty currentUser}">
							<c:url var="homePageHref" value="/users/profile" />

							<c:choose>
								<c:when test="${not empty currentUser.name}">
									<c:set var="displayName" value="${currentUser.name}"/>
								</c:when>
								<c:otherwise>
									<c:set var="displayName" value="${currentUser.userName}"/>
								</c:otherwise>
							</c:choose>
							<td class="nav-item"><a class="nav-link" href="${homePageHref}">
									${displayName}'s Home Page</a></td>
<%--							<c:url var="dashboardHref" value="/users/${currentUser}" />--%>
<%--							<td class="nav-item"><a class="nav-link" href="${dashboardHref}">Private Messages</a></td>--%>
<%--							<c:url var="newMessageHref"--%>
<%--								value="/users/${currentUser}/messages/new" />--%>
<%--							<td class="nav-item"><a class="nav-link" href="${newMessageHref}">New Message</a></td>--%>
<%--							<c:url var="sentMessagesHref"--%>
<%--								value="/users/${currentUser}/messages" />--%>
<%--							<td class="nav-item"><a class="nav-link" href="${sentMessagesHref}">Sent Messages</a></td>--%>
<%--							<c:url var="changePasswordHref"--%>
<%--								value="/users/${currentUser}/changePassword" />--%>
<%--							<td class="nav-item"><a class="nav-link" href="${changePasswordHref}">Change Password</a></td>--%>
						</c:if>
					</div>
					<div class="navbar-nav ml-auto">
						<c:choose>
							<c:when test="${empty currentUser}">
								<c:url var="homePageHref" value="/" />
								<td class="nav-item"><a class="nav-link" href="${homePageHref}">Home</a></td>
								<c:url var="newUserHref" value="/users/new" />
								<td class="nav-item"><a class="nav-link" href="${newUserHref}">Sign Up</a></td>
								<c:url var="loginHref" value="/login" />
								<td class="nav-item"><a class="nav-link" href="${loginHref}">Log In</a></td>
							</c:when>
							<c:otherwise>
								<c:url var="logoutAction" value="/logout" />
								<form id="logoutForm" action="${logoutAction}" method="POST">
									<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
								</form>
								<td class="nav-item"><a id="logoutLink" href="#">Log Out</a></td>
							</c:otherwise>
						</c:choose>
					</div>
				</tr>
			</table>
		</div>
	</nav>
	</div>

	<p id="currentUser" style="font-weight:bold">Current User: ${displayName}</p>
	<div class="container">