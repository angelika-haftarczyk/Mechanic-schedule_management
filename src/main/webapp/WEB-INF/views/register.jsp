<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="headerDashboard.jsp"/>


<body class="bg-dark">

<div class="container">
    <div class="card card-register mx-auto mt-5" >

        <div class="card-header card-header-primary">
            <h4 class="card-title">Zarejestruj się</h4>
        </div>
        <div class="card-body">
            <form:form method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="firstName" name="firstName" class="form-control" placeholder="Wpisz imię" required="required" autofocus="autofocus">
                        <label for="firstName">Imię</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Wpisz nazwisko" required="required" autofocus="autofocus">
                        <label for="lastName">Nazwisko</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="login" name="login" class="form-control" placeholder="Wpisz login" required="required" autofocus="autofocus">
                        <label for="login">Login</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="tel" id="phoneNumber" name="numberPhone" class="form-control" placeholder="Wpisz numer telefonu" required="required" autofocus="autofocus">
                        <label for="phoneNumber">Numer telefonu</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Wpisz e-mail" required="required">
                        <label for="inputEmail">E-mail</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Wpisz hasło" required="required">
                        <label for="inputPassword">Hasło</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="Wpisz hasło" required="required">
                        <label for="confirmPassword">Powtórz hasło</label>
                    </div>
                </div>
                <input type="submit" class="btn btn-primary btn-lg" value="Zarejestruj się"/>
                <div class="col-md-6">
                    <div class="form-label-group">
                        <form:errors path="firstName" />
                        <form:errors path="lastName" />
                        <form:errors path="login" />
                        <form:errors path="numberPhone" />
                        <form:errors path="email" />
                        <form:errors path="password" />
                        <form:errors path="confirmPassword" />
                    </div>
                </div>
            </form:form>

            <div class="text-center">
                <a class="d-block small mt-3" href="/login">Zaloguj się</a>
                <a class="d-block small" onclick="alert('Nie dzisiaj :)')">Zapomniałeś hasła?</a>

            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>

