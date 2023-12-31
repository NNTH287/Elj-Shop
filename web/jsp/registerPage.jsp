<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <title>Elj Shop - Online Art Supplies Shop</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!<!-- Favicon -->
        <link rel="icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/icon type">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap"
              rel="stylesheet">

        <!-- Font Awesome -->
        <link href="${pageContext.request.contextPath}/css/font-awesome_5.10.0_css_all.min.css?version=1"
              rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="${pageContext.request.contextPath}/lib/animate/animate.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css"
              rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/loginStyle.css?version=1" rel="stylesheet">
    </head>
    <div class="wrapper">
        <form action="register" class="register" method="POST">
            <p class="title">Register</p>

            <c:if test="${notification != null}">
                <div class="alert <c:choose><c:when test="${notiType == 'RED'}">alert-danger</c:when><c:otherwise>alert-success</c:otherwise></c:choose>">
                    <strong>${notification}</strong>
                    <%session.removeAttribute("notification");%>
                    <%session.removeAttribute("notiType");%>
                </div>
            </c:if>

            <div class="row">
                <div class="col-md-6 form-group">
                    <label>First Name</label>
                    <input name="firstName" class="form-control" type="text" value="${firstName}" required>
                </div>
                <div class="col-md-6 form-group">
                    <label>Last Name</label>
                    <input name="lastName" class="form-control" type="text" value="${lastName}" required>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 form-group">
                    <label>E-mail</label>
                    <input name="email" class="form-control" type="email" value="${email}" required>
                </div>
                <div class="col-md-6 form-group">
                    <label>Password</label>
                    <input name="password" class="form-control" type="password" value="${password}" minlength="8" required>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 form-group">
                    <label>Confirm your password</label>
                    <input name="confirmedPassword" class="form-control" type="password" value="${confirmedPassword}" minlength="8" required>
                </div>
            </div>

            <a href="login">Login</a>
            <div class="row text-center">
                <input name="registerSubmit" class="registerSubmit" type="submit" value="Register">
            </div>
        </form>
    </p>
</div>

</html>