<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<jsp:include page="headerDashboard.jsp"/>


<body class="bg-dark">

<div class="container">
    <div class="card card-register mx-auto mt-5" >
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
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>