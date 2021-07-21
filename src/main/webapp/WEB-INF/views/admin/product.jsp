<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../headerDashboard.jsp"/>


<div id="content-wrapper">
    Hello!
</div>
<div class="content">
    <div class="container-fluid">


        <div class="row">
            <div class="col-lg-6 col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title">Usługi</h4>
                        <p class="card-category"></p>
                    </div>
                    <div class="card-body table-responsive">
                        <table class="table table-hover">
                            <thead class="text-warning">
                            <th>Nr usługi</th>
                            <th>Nazwa usługi</th>
                            <th>Opis usługi</th>
                            <th>Koszt usługi*</th>
                            <th>Czas trwania</th>
                            </thead>
                            <tbody>
                            <c:forEach var="product" items="products">
                                <tr>
                                    <td>${product.id}</td>
                                    <td>${product.serviceName}</td>
                                    <td>${product.description}</td>
                                    <td>${product.price}</td>
                                    <td>${product.durationInMinutes}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
