<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/showtag" prefix="tag" %>
<!DOCTYPE html>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.labels" var="bundleLabel" scope="session"/>
<fmt:setBundle basename="resources.config" var="bundleConf" scope="session"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="labels.jsp.show.title" bundle="${bundleLabel}"/></title>
</head>
<body>
<table style="text-align:center" border="1">
    <thead>
    <tr>
        <td> ${parser} </td>
    </tr>
    <tr>
        <th><fmt:message key="labels.jsp.show.column.type" bundle="${bundleLabel}"/></th>
        <th><fmt:message key="labels.jsp.show.column.id" bundle="${bundleLabel}"/></th>
        <th><fmt:message key="labels.jsp.show.column.price" bundle="${bundleLabel}"/></th>
        <th><fmt:message key="labels.jsp.show.column.characteristics" bundle="${bundleLabel}"/></th>
    </tr>
    </thead>
    <tag:showxml/>
</table>
<form name="returnForm" action="controller" method="post">
    <input type="hidden" name="command" value="backFromShow"/>
    <button name="return">
        <fmt:message key="labels.jsp.show.return" bundle="${bundleLabel}"/>
    </button>
</form>
<hr/>
<form name="changeLangForm" action="controller" method="post">
    <input type="hidden" name="command" value="changeLanguage"/>
    <button name="lang" value="ru">
        <fmt:message key="config.jsp.label.languageRU" bundle="${bundleConf}"/>
    </button>
    <br/>
    <button name="lang" value="en">
        <fmt:message key="config.jsp.label.languageEN" bundle="${bundleConf}"/>
    </button>
    <br/>
</form>
</body>
</html>
