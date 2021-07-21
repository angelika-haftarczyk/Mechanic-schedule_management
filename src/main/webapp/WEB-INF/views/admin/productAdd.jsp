<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<jsp:include page="../headerDashboard.jsp"/>

<body class="bg-dark">

<div class="container">
    <div class="card card-register mx-auto mt-5" >
        <div class="card-header card-header-primary">
            <h4 class="card-title">Dodawanie nowych usług</h4>
        </div>
        <div class="card-body">
            <form:form method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="serviceName" name="serviceName" class="form-control" placeholder="Wpisz nazwę usługi" required="required" autofocus="autofocus">
                        <label for="serviceName">Nazwa usługi</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="description" name="description" class="form-control" placeholder="Wpisz opis usługi" required="required" autofocus="autofocus">
                        <label for="description">Opis usługi</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="price" name="price" class="form-control" placeholder="Wpisz koszt usługi" required="required" autofocus="autofocus">
                        <label for="price">Koszt usługi</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="number" id="durationInMinutes" name="durationInMinutes" class="form-control" placeholder="Wpisz czas trwania" required="required" autofocus="autofocus">
                        <label for="durationInMinutes">Czas trwania</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="checkbox" id="available" name="available" class="form-control" placeholder="Zmień dostępność" required="required">
                        <label for="available">Dostępność</label>
                    </div>
                </div>
                <input type="submit" class="btn btn-primary btn-lg" value="Dodaj usługę"/>
                <div class="col-md-6">
                    <div class="form-label-group">
                        <form:errors path="serviceName" />
                        <form:errors path="description" />
                        <form:errors path="price" />
                        <form:errors path="durationInMinutes" />
                        <form:errors path="available" />
                    </div>
                </div>
            </form:form>


        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>

