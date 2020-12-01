<%@ page import="domain.model.Person" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% Person person = (Person) session.getAttribute("person"); %>
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
			<h1>
				<span>XXX</span>
			</h1>
			<jsp:include page="nav.jsp">
				<jsp:param name="page" value="profile"/>
			</jsp:include>
			<h2>Home</h2>

		</header>
		<main>
			<p>
				Sed ut perspiciatis unde omnis iste natus error sit
				voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
				ipsa quae ab illo inventore veriatis et quasi architecto beatae vitae
				dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
				aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
				qui ratione voluptatem sequi nesciunt.
			</p>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
		</main>

	</div>
</body>
</html>