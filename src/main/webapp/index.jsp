<%--
  Created by IntelliJ IDEA.
  User: Ares
  Date: 02.11.2019
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html lang="ru">
<html>
<head>
    <title>Dictionary</title>
</head>
<body>
<table>
    <tr>
        <td>
          <button onclick="location.href='/new'" type="submit">Add new word</button>
        </td>
    </tr>
    <tr>
        <td>
            <button onclick="location.href='/translate'" type="submit">Translate Word</button>
        </td>
    </tr>
    <tr>
      <c:if test="${all == null}">

      <td>
            <form name="input" method="post">
                <input class="ui-button" type="submit" name="allwords" value="All Words">
            </form>
        </td>
      </c:if>

    </tr>
    <c:if test="${all != null}">
        <table border="1">
            <tr>
                <td align="center" >ID</td>
                <td align="center">Русский</td>
                <td align="center">English</td>
                <td align="center">Spanish</td>
                <td align="center">French</td>
            </tr>
            <c:forEach var="word" items="${all}">
                <tr>
                    <td align="center">${word.getId()}</td>
                    <td align="center">${word.getRussian()}</td>
                    <td align="center">${word.getEnglish()}</td>
                    <td align="center">${word.getSpanish()}</td>
                    <td align="center">${word.getFrench()}</td>
                    <td align="center">
                        <form method="post">
                            <input type="hidden" value="${word.getId()}" name="Delete">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</table>
</body>
</html>
