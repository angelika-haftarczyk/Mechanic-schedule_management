<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="headerUser.jsp"/>

<body class="bg-dark">

<div class="container">
    <div class="card card-register mx-auto mt-5" >
        <%--        <div class="card-header">Register an Account</div>--%>
        <div class="card-header card-header-primary">
            <h4 class="card-title">Edycja/usuwanie wizyty</h4>
        </div>
        <div class="card-body">
            <form:form method="post" action="./delete?date=${param.date}">
                <c:if test="${not empty error}"><p>${error}</p></c:if>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="form-group">
                    <div class="form-label-group">
                        <input type="time" id="startTime" name="startTime" class="form-control" value="${startTime}" required="required" disabled>
                        <label for="startTime"></label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="date" id="dateTime" name="dateTime" class="form-control" value="${dateTime}" required="required" disabled>
                        <label for="dateTime"></label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                            <%--                        <input type="text" id="service" name="service" class="form-control" placeholder="Wpisz nazwisko" required="required" autofocus="autofocus">--%>
                        <select name="service" id="service" disabled>
                            <c:forEach var="product" items="${products}">
                                <option value="${product.id}" ${product.id == productId ? 'selected="selected"' : ''}>${product.serviceName}</option>
                            </c:forEach>
                        </select>
                        <label for="service">Usługa</label>
                    </div>
                </div>

                <input type="submit" class="btn btn-danger btn-lg" value="Usuń wizytę"/>
                <a type="button" class="btn btn-primary btn-lg" href="./edit?date=${param.date}">Wróć do edycji</a>
                <div class="col-md-6">
                    <div class="form-label-group">
                        <form:errors path="startTimeWork" />
                        <form:errors path="service" />

                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<jsp:include page="footerUser.jsp"/>