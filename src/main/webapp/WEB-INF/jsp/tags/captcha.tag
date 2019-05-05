<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="info" %>

<%@ attribute name="captchaId" required="false" %>
<%@ attribute name="image" required="true" %>

<div>
    <c:if test="${initParam.captchaHandler == 'hiddenFieldCaptchaHandler'}">
        <input type="hidden" name="CaptchaId" value="${CaptchaId}" >
    </c:if>
    <img src="${pageContext.request.contextPath}/images/registrCaptcha.jpeg"><br>
</div>
