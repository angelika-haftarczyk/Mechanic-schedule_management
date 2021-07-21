<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../headerDashboard.jsp"/>

<body class="bg-dark">

<div class="container">
    <div class="card card-register mx-auto mt-5" >
        <%--        <div class="card-header">Register an Account</div>--%>
        <div class="card-header card-header-primary">
            <h4 class="card-title">Edytuj dane</h4>
        </div>
        <div class="card-body">
            <form:form method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="firstName" name="firstName" class="form-control" value="${user.firstName}" placeholder="Wpisz imię" required="required" >
                        <label for="firstName">Imię</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="lastName" name="lastName" class="form-control" value="${user.lastName}" placeholder="Wpisz nazwisko" required="required" >
                        <label for="lastName">Nazwisko</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="tel" id="phoneNumber" name="numberPhone" class="form-control" value="${user.numberPhone}" placeholder="Wpisz numer telefonu" required="required" >
                        <label for="phoneNumber">Numer telefonu</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="email" id="inputEmail" name="email" class="form-control" value="${user.email}" placeholder="Wpisz e-mail" required="required">
                        <label for="inputEmail">E-mail</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" id="inputActualPassword" name="actualPassword" class="form-control" placeholder="Wpisz poprzednie hasło jeśli chcesz zmienić" >
                        <label for="inputActualPassword">Stare hasło</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" id="inputPassword" name="newPassword" class="form-control" placeholder="Wpisz nowe hasło jeśli chcesz zmienić" >
                        <label for="inputPassword">Nowe hasło</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="Wpisz nowe hasło jeśli chcesz zmienić" >
                        <label for="confirmPassword">Powtórz hasło</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="checkbox" id="invoice" name="invoice" class="form-control"  <c:if test="${user.invoice}">checked='checked'</c:if> />
                        <label for="confirmPassword">Faktura</label>
                    </div>
                </div>
        <div id="invoiceData">
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="nip" name="nip" class="form-control" placeholder="Wpisz NIP" >
                        <label for="nip">Wpisz NIP</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="regon" name="regon" class="form-control" placeholder="Wpisz REGON" >
                        <label for="regon">Wpisz REGON (pole nieobowiązkowe)</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="companyName" name="companyName" class="form-control" placeholder="Wpisz nazwę firmy" >
                        <label for="companyName">Nazwa firmy</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="companyAddress" name="companyAddress" class="form-control" placeholder="Wpisz adres firmy" >
                        <label for="companyAddress">Adres firmy</label>
                    </div>
                </div>
        </div>

                <input type="submit" class="btn btn-primary btn-lg" value="Zapisz"/>
                <div class="col-md-6">
                    <div class="form-label-group">
                        <form:errors path="firstName" />
                        <form:errors path="lastName" />
                        <form:errors path="login" />
                        <form:errors path="numberPhone" />
                        <form:errors path="email" />
                        <form:errors path="actualPassword"/>
                        <form:errors path="newPassword" />
                        <form:errors path="confirmPassword" />
                        <form:errors path="nip" />
                        <form:errors path="regon" />
                        <form:errors path="companyName" />
                        <form:errors path="companyAddress" />
                    </div>
                </div>
            </form:form>

        </div>
    </div>
</div>
<script>
const invoice = document.querySelector('#invoice');
const invoiceData = document.querySelector('#invoiceData');
const inputs = invoiceData.querySelectorAll('input');

invoiceData.classList.add('d-none');

invoice.addEventListener('click', function (event) {
    if (invoice.checked) {
        invoiceData.classList.remove('d-none');
        for (let i = 0; i < inputs.length; i++) {
            inputs[i].setAttribute('required', 'required');
            inputs[i].setAttribute('disable', 'disable');
        }
    } else {
        invoiceData.classList.add('d-none');
        for (let i = 0; i < inputs.length; i++) {
            inputs[i].removeAttribute('required');
            inputs[i].removeAttribute('disable');
        }
    };
});
</script>
<jsp:include page="../footer.jsp"/>

