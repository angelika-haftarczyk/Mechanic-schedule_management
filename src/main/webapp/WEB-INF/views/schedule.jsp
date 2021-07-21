<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="headerDashboard.jsp"/>


<div id="content-wrapper">
</div>
<div class="content">
    <div class="container-fluid">
        <c:if test="${not empty error}"><p>${error}</p></c:if>
        <div id='calendar'></div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
