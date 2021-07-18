<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="headerDashboard.jsp"/>


<div id="content-wrapper">
</div>
<div class="content">
    <div class="container-fluid">
        <c:if test="${not empty error}"><p>${error}</p></c:if>
        <div id='calendar'></div>
<%--        <div class="row">--%>
<%--            <div class="col-lg-12 col-md-12">--%>
<%--                <div class="card">--%>
<%--                    <div class="card-header card-header-primary">--%>
<%--                        <h4 class="card-title">Harmonogram</h4>--%>
<%--                        <p class="card-category"></p>--%>
<%--                    </div>--%>
<%--                    <div class="card-body table-responsive">--%>
<%--                        <table class="table table-hover">--%>
<%--                            <thead class="text-warning">--%>
<%--                            <th>Od godziny</th>--%>
<%--                            <th>Czas trwania</th>--%>
<%--                            <th>Nazwa usługi</th>--%>
<%--                            </thead>--%>
<%--                            <tbody>--%>
<%--                            <c:forEach var="schedule" items="${schedules}">--%>
<%--                                <tr>--%>
<%--&lt;%&ndash;                                    <td>${product.id}</td>&ndash;%&gt;--%>
<%--                                    <td>${schedule.startTimeWork}</td>--%>
<%--                                    <td>${schedule.service.durationInMinutes}</td>--%>
<%--                                    <td>${schedule.service.description}</td>--%>
<%--                                </tr>--%>
<%--                            </c:forEach>--%>
<%--                            </tbody>--%>
<%--                        </table>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
    </div>
</div>

<jsp:include page="footer.jsp"/>
