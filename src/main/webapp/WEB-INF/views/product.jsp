<%--
  Created by IntelliJ IDEA.
  User: angel
  Date: 2021-07-15
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="headerDashboard.jsp"/>


<div id="content-wrapper">
</div>
<div class="content">
    <div class="container-fluid">

        <div class="row">
            <div class="col-lg-12 col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title">Moje usługi</h4>
                        <p class="card-category">*ceny usług nie zawierają opłaty za części</p>
                    </div>
                    <div class="card-body table-responsive">
                        <table class="table table-hover">
                            <thead class="text-warning">
                            <th>Nr usługi</th>
                            <th>Nazwa usługi</th>
                            <th>Opis usługi</th>
                            <th>Koszt usługi*</th>
                            <th>Czas trwania</th>
                            <c:if test="${not empty isAdmin and isAdmin}">
                                <th>Zmień dostępność</th>
                            </c:if>
                            </thead>
                            <tbody>
                            <c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.id}</td>
                                <td>${product.serviceName}</td>
                                <td>${product.description}</td>
                                <td><fmt:formatNumber type="CURRENCY">${product.price}</fmt:formatNumber></td>
                                <td>${product.durationInMinutes}</td>
                                <c:if test="${not empty isAdmin and isAdmin}">
                                    <th>
                                    <form:form method="post" action="product/toggle" >
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <input type="hidden" name="id" value="${product.id}"/>
                                        <input type="checkbox" name="available" class="form-control"  <c:if test="${product.available}">checked='checked'</c:if>  onclick="this.parentNode.submit()"/> <!-- this - input, parent form, robimy na formie submit -->
                                  </form:form>
                                    </th>
                                </c:if>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <c:if test="${not empty isAdmin and isAdmin}">
                            <a type="button" class="btn btn-primary btn-lg" href="product/add">Dodaj</a>
                        </c:if>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
