<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<jsp:include page="../headerDashboard.jsp"/>

<body class="bg-dark">

<div class="container">
    <div class="card card-register mx-auto mt-5" >
        <div class="card-header card-header-primary">
            <h4 class="card-title">Edycja/usuwanie wizyty</h4>
        </div>
        <div class="card-body">
            <form:form method="post">
                <c:if test="${not empty error}"><p>${error}</p></c:if>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>


                <c:if test="${not empty isAdmin and isAdmin}">
                    <div class="form-group">
                        <div class="form-label-group">
                            <input type="text" id="user" name="user" class="form-control" value="${user.firstName} ${user.lastName}" required="required" disabled>
                            <label for="user"></label>
                        </div>
                    </div>
                </c:if>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="checkbox" id="accepted" name="accepted" class="form-control" <c:if test="${accepted}">checked='checked'</c:if> <c:if test="${empty isAdmin or not isAdmin}">value="on" disabled</c:if>>
                        <label for="accepted">Zaakceptowany</label>
                    </div>
                </div>
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
                        <select name="service" id="service" disabled>
                            <c:forEach var="product" items="${products}">
                                <option value="${product.id}" ${product.id == productId ? 'selected="selected"' : ''}>${product.serviceName}</option>
                            </c:forEach>
                        </select>
                        <label for="service">Usługa</label>
                    </div>
                </div>
                <div>
                    <c:forEach var="note" items="${notes}">
                        <p style="color:   ${fn:contains(note.user.roles.stream().map(role -> role.name).toList(), 'ROLE_ADMIN') ? 'RED' : 'GREEN'}">${note.user.firstName} - ${note.note}</p>
                    </c:forEach>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="note" name="note" class="form-control">
                        <label for="note">Wpisz markę,model i rocznik samochodu oraz uwagi</label>
                    </div>
                </div>
                <input type="submit" class="btn btn-primary btn-lg" value="<c:if test="${not empty isAdmin and isAdmin}">Zapisz / </c:if>Dodaj notatkę"/>
                <a type="button" class="btn btn-danger btn-lg" href="./confirm?date=${param.date}">Usuń wizytę</a>
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

<jsp:include page="../footer.jsp"/>