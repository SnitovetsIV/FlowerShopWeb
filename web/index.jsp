<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.labels" var="bundleLabel" scope="session"/>
<fmt:setBundle basename="resources.config" var="bundleConf" scope="session"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="labels.jsp.index.title" bundle="${bundleLabel}"/></title>
</head>
<body>

<h1><fmt:message key="labels.jsp.index.header" bundle="${bundleLabel}"/></h1>

<form name="parserForm" action="controller" method="post">
    <input type="hidden" name="command" value="showFloralComposition"/>
    <button name="parser" value="SAX">
        <fmt:message key="labels.jsp.index.label_sax" bundle="${bundleLabel}"/>
    </button>
    <br/>
    <button name="parser" value="STAX">
        <fmt:message key="labels.jsp.index.label_stax" bundle="${bundleLabel}"/>
    </button>
    <br/>
    <button name="parser" value="DOM">
        <fmt:message key="labels.jsp.index.label_dom" bundle="${bundleLabel}"/>
    </button>
    <br/>
    <button name="parser" value="BADPARSER">
        BAD PARSER
    </button>
</form>
<p style="color:#ff0000">${warning}</p>
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
