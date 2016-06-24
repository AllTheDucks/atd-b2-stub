<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/bbUI" prefix="bbUI" %>
<%@ taglib uri="/bbNG" prefix="bbNG" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="bbng" uri="/bbNG" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:message var="toolSettingsStepTitle" key="${b2Handle}.configPage.toolSettingsStep.title" />
<fmt:message var="settingOneLabel" key="${b2Handle}.configPage.settingOne.label" />
<fmt:message var="settingTwoLabel" key="${b2Handle}.configPage.settingTwo.label" />

<bbNG:genericPage bodyClass="normalBackground"
                  navItem="${vendorId}-${b2Handle}-nav-${b2Handle}config">

    <style type="text/css">
        span.fieldErrorText {
            margin-left: 1em;
            color: red;
        }
    </style>
    <stripes:form beanclass="${basePackage}.stripes.ConfigAction">
        <stripes:hidden name="saveConfiguration"/>

        <bbNG:dataCollection>
            <bbNG:step title="${r"${toolSettingsStepTitle}"}">
                <bbNG:dataElement isRequired="true" label="${r"${settingOneLabel}"}">
                    <stripes:text name="config.settingOne"></stripes:text>
                    <stripes:errors field="config.settingOne"></stripes:errors>
                </bbNG:dataElement>
                <bbNG:dataElement isRequired="true" label="${r"${settingTwoLabel}"}">
                    <stripes:text name="config.settingTwo"></stripes:text>
                    <stripes:errors field="config.settingTwo"></stripes:errors>
                </bbNG:dataElement>
            </bbNG:step>
            <bbNG:stepSubmit></bbNG:stepSubmit>
        </bbNG:dataCollection>
    </stripes:form>

</bbNG:genericPage>