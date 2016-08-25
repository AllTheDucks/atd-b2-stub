<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/bbNG" prefix="bbNG"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>

<fmt:message var="toolSettingsStepTitle" key="${b2Handle}.configPage.toolSettingsStep.title" />
<fmt:message var="toolSettingsStepInstructions" key="${b2Handle}.configPage.toolSettingsStep.instructions" />
<fmt:message var="settingOneLabel" key="${basePackage}.ConfigAction.config.settingOne" />
<fmt:message var="settingTwoLabel" key="${basePackage}.ConfigAction.config.settingOne" />

<bbNG:genericPage bodyClass="normalBackground"
                  navItem="${vendorId}-${b2Handle}-nav-${b2Handle}config">
    <bbNG:cssBlock>
    <style type="text/css">
        span.fieldErrorText {
            margin-left: 1em;
            color: red;
        }
    </style>
    </bbNG:cssBlock>
    <stripes:form beanclass="${basePackage}.stripes.ConfigAction">
        <stripes:hidden name="saveConfiguration"/>

        <bbNG:dataCollection>
            <bbNG:step title="${r"${toolSettingsStepTitle}"}" instructions="${r"${toolSettingsStepInstructions}"}">
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