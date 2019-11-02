<%--
  Created by IntelliJ IDEA.
  User: Ares
  Date: 02.11.2019
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Translator</title>
</head>
<body>

<table>
    <tr>
        <form method="post" accept-charset="ISO-8859-1">
            <td>
                <select name="first">
                    <option value="russian">Русский</option>
                    <option value="english">English</option>
                    <option value="spanish">Espanol</option>
                    <option value="french">Le français</option>
                </select>
            </td>
            <td>
                <select name="second">
                    <option value="russian">Русский</option>
                    <option value="english">English</option>
                    <option value="spanish">Espanol</option>
                    <option value="french">Le français</option>
                </select>
            </td>
            <td>

            </td>
    <tr>
    <td>
        <input type="text" name="wordToTranslate" placeholder="write word that u want to translate">
    </td>
    <td>
<c:if test="${word != null}">
    ${word}
</c:if>
</td>
    <td>
        <input type="submit">
    </td>
    </tr>
    </form>
    </tr>
</table>

</body>
</html>
