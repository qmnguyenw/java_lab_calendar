<%-- 
    Document   : calendar
    Created on : Oct 20, 2021, 4:06:09 PM
    Author     : admin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calendar</title>
        <style>
            body {
                text-align: center;
                font-size: 25px;
                font-weight: bold;
                font-family: Arial;
            }
            table {
                margin: auto;
                padding-top: 50px;
            }
            th {
                font-size: 40px;
                padding-bottom: 5px;
            }
            input {
                text-align: center;
                font-size: 25px;
                font-weight: bold;
                border: none;
            }
            input.day {
                height: 40px;
                width: 120px;
                background-color: gray;
            }
            input.date {
                height: 90px;
                width: 120px;
                color: white;
            }
            a {
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <table>
            <tr>
                <th>
                    <c:if test="${month-1==0}">
                        <a href="CalendarController?month=12&year=${year-1}"><<</a>
                    </c:if>
                    <c:if test="${month-1!=0}">
                        <a href="CalendarController?month=${month-1}&year=${year}"><<</a>
                    </c:if>
                </th>
                <th></th>
                <th>${monthName}</th>
                <th>
                    <a href="CalendarController?month=${currMonth}&year=${currYear}">==</a>
                </th>
                <th>${year}</th>
                <th></th>
                <th>
                    <c:if test="${month+1==13}">
                        <a href="CalendarController?month=1&year=${year+1}">>></a>
                    </c:if>
                    <c:if test="${month+1!=13}">
                        <a href="CalendarController?month=${month+1}&year=${year}">>></a>
                    </c:if>
                </th>
            </tr>
            <tr>
                <td>
                    <input type="text" class="day" value="Sun" readonly />
                </td>
                <td>
                    <input type="text" class="day" value="Mon" readonly />
                </td>
                <td>
                    <input type="text" class="day" value="Tue" readonly />
                </td>
                <td>
                    <input type="text" class="day" value="Wed" readonly />
                </td>
                <td>
                    <input type="text" class="day" value="Thu" readonly />
                </td>
                <td>
                    <input type="text" class="day" value="Fri" readonly />
                </td>
                <td>
                    <input type="text" class="day" value="Sat" readonly />
                </td>
                <c:forEach var="i" begin="0" end="${numberOfWeek-1}">
                <tr>
                    <c:forEach var="j" begin="0" end="6">
                        <td>
                            <input type="text" class="date" readonly />
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </tr>
    </table>
    <script>
        var listDay = ${listDay};
        var month = ${month};
        var year = ${year};
        var currMonth = ${currMonth};
        var currYear = ${currYear};
        var currDay = ${currDay};

        document.body.onload = function () {
            updateDate();
        };

        function updateDate() {
            var maxArray = document.querySelectorAll('input.date').length;
            for (var i = 0; i < maxArray; i++) {
                if (listDay[i] === 0) {
                    document.getElementsByClassName('date')[i].value = "";
                    document.getElementsByClassName('date')[i].style.backgroundColor = "#c7c0c0";
                } else {
                    document.getElementsByClassName('date')[i].value = listDay[i];
                    if (i % 7 === 0) {
                        document.getElementsByClassName('date')[i].style.backgroundColor = "#f44336";
                    } else {
                        document.getElementsByClassName('date')[i].style.backgroundColor = "#4d63dd";
                    }
                }

                if (listDay[i] === currDay && month === currMonth && year === currYear) {
                    document.getElementsByClassName('date')[i].style.backgroundColor = "#39c53f";
                }
            }
        }
    </script>
</body>

</html>
