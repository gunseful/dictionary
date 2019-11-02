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
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Dictionary</title>
</head>
<body>
<table>
    <tr>
        <td>
             Russian
        </td>
        <td>
            English
        </td>
        <td>
            Spanish
        </td>
        <td>
            French
        </td>
        <td> </td>
        <td> </td>
    </tr>
    <tr>
        <form method="post" accept-charset="ISO-8859-1">
            <td>
                <input type="text" name="russian" placeholder="на русском" required>
            </td>
            <td>
                <input type="text" name="english" placeholder="on english" required>
            </td>
            <td>
                <input type="text" name="spanish" placeholder="en español" required>
            </td>
            <td>
                <input type="text" name="french" placeholder="en français" required>
            </td>
            <td>

            </td>
            <td>
                <input type="submit" value="add">
            </td>
        </form>
    </tr>
</table>
</body>
</html>
