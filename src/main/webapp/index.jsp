<%@ page import="domain.model.Person" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="container">
		<header>
			<h1>Sportzaal Heverlee</h1>
			<jsp:include page="nav.jsp">
				<jsp:param name="page" value="profile"/>
			</jsp:include>
			<h2>Home</h2>
		</header>

		<c:if test="${not empty message}">
			<div class="message">
				<p>${message}</p>
			</div>
		</c:if>

		<c:if test="${not empty errors}">
			<div class="alert-danger">
				<p>${errors}</p>
			</div>
		</c:if>

		<main>
			<div class="index">
				<p>
					Sed ut perspiciatis unde omnis iste natus error sit
					voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
					ipsa quae ab illo inventore veriatis et quasi architecto beatae vitae
					dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
					aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
					qui ratione voluptatem sequi nesciunt.
				</p>
			</div>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
		</main>

	</div>
</body>
</html>