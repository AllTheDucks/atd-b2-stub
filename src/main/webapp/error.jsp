<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/bbNG" prefix="bbNG"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="bbng" uri="/bbNG" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message var="errorPageTitle" key="myapp.errorPage.title" />
<fmt:message var="errorPageMessage" key="myapp.errorPage.message" />


<bbNG:learningSystemPage ctxId="ctx" title="${errorPageTitle}">
    <h2>${errorPageMessage}</h2>
</bbNG:learningSystemPage>