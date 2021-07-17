<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<jsp:include page="headerDashboard.jsp"/>

<%--<head>--%>

<%--    <meta charset="utf-8">--%>
<%--    <meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
<%--    <meta name="description" content="">--%>
<%--    <meta name="author" content="">--%>

<%--    <title>SB Admin - Register</title>--%>

<%--    <!-- Bootstrap core CSS-->--%>
<%--    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>

<%--    <!-- Custom fonts for this template-->--%>
<%--    <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">--%>

<%--    <!-- Custom styles for this template-->--%>
<%--    <link href="/resources/css/sb-admin.css" rel="stylesheet">--%>

<%--</head>--%>

<body class="bg-dark">

<div class="container">
    <div class="card card-register mx-auto mt-5" >
        <%--        <div class="card-header">Register an Account</div>--%>
        <div class="card-body">
            <form:form method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="username" name="username" class="form-control" placeholder="Podaj login" required="required" autofocus="autofocus">
                        <label for="username">Login</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Podaj hasło" required="required">
                        <label for="inputPassword">Hasło</label>
                    </div>
                </div>
                <input type="submit" class="btn btn-primary btn-block" value="Zaloguj się"/>
                <div class="col-md-6">
                    <div class="form-label-group">
                        <form:errors path="login" />
                        <form:errors path="password" />
                    </div>
                </div>
            </form:form>

            <div class="text-center">
                <a class="d-block small mt-3" href="/register">Zarejestruj się</a>
                <a class="d-block small" onclick="alert('Nie dzisiaj :)')">Zapomniałeś hasła?</a>
                <%--                <a class="d-block small" href="/forgot-password">Zapomniałeś hasła?</a>--%>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>