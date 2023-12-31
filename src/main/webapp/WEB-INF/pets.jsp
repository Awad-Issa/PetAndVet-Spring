<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login and Registration</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
   
<main class="container">
    <h1>Welcome, <c:out value="${user.userName}"/> !</h1>
    <h4>This is your dashboard.</h4>
    <h4><a href="/logout">logout</a></h4>
    <c:forEach items="${pets}" var="pet">
        <c:out value="${pet.id} ${pet.name} ${pet.age} ${pet.breed.name} ${pet.gender} ${pet.description}"/><br>
    </c:forEach>
</main>
</body>
</html>