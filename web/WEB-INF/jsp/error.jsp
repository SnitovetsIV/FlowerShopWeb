<%@page isErrorPage="true" language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.labels" var="bundleLabel" scope="session"/>
<fmt:setBundle basename="resources.config" var="bundleConf" scope="session"/>
<fmt:setBundle basename="resources.messages" var="bundleMess" scope="session"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="labels.jsp.error.title" bundle="${bundleLabel}"/></title>
</head>
<body>
<fmt:message key="${errorMessage}" bundle="${bundleMess}"/><br/>

<form name="returnForm" action="controller" method="post">
    <input type="hidden" name="command" value="backFromError"/>
    <button name="return">
        <fmt:message key="labels.jsp.error.return" bundle="${bundleLabel}"/>
    </button>
</form>
<hr/>
<form name="changeLangForm" action="controller" method="post">
    <input type="hidden" name="command" value="changeLanguage"/>
    <!--<input type="hidden" name="errorMessage" value="${errorMessage}" />-->
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
