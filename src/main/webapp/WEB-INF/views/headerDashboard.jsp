<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="pl">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <link href='/lib/main.css' rel='stylesheet' />
    <script src='/lib/main.js'></script>
    <script src='/lib/locales/pl.js'></script>

    <script>

        document.addEventListener('DOMContentLoaded', function() {
            var startDate = new Date();
            var endDate = new Date();
            endDate.setMonth(endDate.getMonth() + 4);
            endDate.setDate(-1);
            var calendarEl = document.getElementById('calendar');
            if(calendarEl != null) {
                var calendar = new FullCalendar.Calendar(calendarEl, {
                    locale: 'pl',
                    initialView: 'dayGridMonth',
                    validRange: {
                        start: startDate,
                        end: endDate,
                    },
                    headerToolbar: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'dayGridMonth,timeGridWeek,timeGridDay'
                    },
                    dateClick: function (info) {
                        if(info.date.getHours() === 0) {
                            calendar.changeView('timeGridDay', info.date);
                        } else if (${not empty username}) { //TODO if (zalogowany)
                            var date = new Date(info.date);
                            date.setMinutes( date.getMinutes() - info.date.getTimezoneOffset());
                            window.location.href = '/schedule/add?date=' + date.toISOString();
                        } else {
                            alert('W celu umówienia wizyty, proszę się zalogować');
                        }
                    },
                    eventClick: function (info) {
                        if(info.event.title !== 'niedostępne') {
                            var date = new Date(info.event.start);
                            date.setMinutes( date.getMinutes() - info.event.start.getTimezoneOffset());
                            const location = '/schedule/edit?date=' + date.toISOString();
                            window.location.href = location;
                        }
                    },
                    events: [
                        <c:forEach  var="schedule" items="${schedules}" varStatus="status">
                        {
                            title: '${schedule.name}',
                            start: '${schedule.startTime}',
                            end: '${schedule.endTime}'
                        }
                        <c:if test="${not status.last}">,</c:if>
                        </c:forEach>

                    ]
                });
                calendar.setOption('locale', 'pl');
                calendar.render();
            }

        });

    </script>

    <link rel="apple-touch-icon" sizes="76x76" href="../theme/img/apple-icon.png">
    <link rel="icon" type="image/png" href="../theme/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>
        Doktor Autko
    </title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
    <!--     Fonts and icons     -->
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
    <!-- CSS Files -->
    <link href="../theme/css/material-dashboard.css?v=2.1.0" rel="stylesheet" />
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <%--    <link href="../assets/demo/demo.css" rel="stylesheet" />--%>
</head>

<body class="dark-edition">
<div class="wrapper ">
    <div class="sidebar" data-color="purple" data-background-color="black" data-image="../theme/img/sidebar-2.jpg">
        <!--
          Tip 1: You can change the color of the sidebar using: data-color="purple | azure | green | orange | danger"

          Tip 2: you can also add an image using data-image tag
      -->
        <div class="logo"><a href="/" class="simple-text logo-normal">
            Doktor Autko
        </a></div>
        <div class="sidebar-wrapper">
            <ul class="nav">
                <li class="nav-item  ">
                    <a class="nav-link" href="/">
                        <i class="material-icons">dashboard</i>
                        <p>Strona główna</p>
                    </a>
                </li>
                <c:if test="${empty username}">
                <li class="nav-item ">
                    <a class="nav-link" href="/login">
                        <i class="material-icons">person</i>
                        <p>Zaloguj się</p>
                    </a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="/register">
                        <i class="material-icons">person_add</i>
                        <p>Zarejestruj się</p>
                    </a>
                </li>
                </c:if>
                <li class="nav-item ">
                    <a class="nav-link" href="/schedule">
                        <i class="material-icons">calendar_today</i>
                        <p>Kalendarz</p>
                    </a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="/product">
                        <i class="material-icons">handyman</i>
                        <p>Usługi</p>
                    </a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="./map.html">
                        <i class="material-icons">location_ons</i>
                        <p>Maps</p>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <div class="main-panel">
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top " id="navigation-example">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation" data-target="#navigation-example">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="navbar-toggler-icon icon-bar"></span>
                    <span class="navbar-toggler-icon icon-bar"></span>
                    <span class="navbar-toggler-icon icon-bar"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end">
                    <ul class="navbar-nav">
                    <c:if test="${not empty username}">
                        <li class="nav-item">
                            <a class="nav-link" href="/user">
                                <span style="text-transform: capitalize"> ${username} </span>
                                <i class="material-icons">person</i>
                            </a>
                        </li>
                        <li class="nav-item">
                            <form method="post" action="/logout">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <div class="nav-link" onclick="this.parentNode.submit()"><i class="material-icons">logout</i></div>
                            </form>

                        </li>
                    </c:if>
                    <c:if test="${empty username}">
                            <li class="nav-item">
                                <a class="nav-link" href="/login">
                                    <i class="material-icons">login</i>
                                </a>
                            </li>
                    </c:if>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- End Navbar -->
