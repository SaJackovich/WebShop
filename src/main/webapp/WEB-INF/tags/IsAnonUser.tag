<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="enter"%>

<%@ attribute name="userId" required="false" %>
<%@ attribute name="userAvatar" required="true" %>
<%@ attribute name="userName" required="false" %>

<c:choose>
	<c:when test="${sessionScope.locale!=null}">
		<fmt:setLocale value="${sessionScope.locale}" />
	</c:when>
	<c:otherwise>
		<fmt:setLocale value="${requestScope.locale}" />
	</c:otherwise>
</c:choose>
<fmt:setBundle basename="resources" />

    <c:choose>
          <c:when test="${userId == 0}">
                <li class="text-center border-right text-white">
                    <a href="${pageContext.request.contextPath}/login" class="text-white">
                        <i class="fas fa-sign-in-alt mr-2"></i> <fmt:message key="log.in"/> </a>
                </li>
                <li class="text-center text-white">
                    <a href="${pageContext.request.contextPath}/registration" class="text-white">
                        <i class="fas fa-sign-out-alt mr-2"></i><fmt:message key="register"/></a>
                </li>
          </c:when>
          <c:otherwise>
                <li class="text-center border-right text-white">
                    <a href="${pageContext.request.contextPath}/logout"  class="text-white">
                        <i class="fas fa-sign-in-alt mr-2"></i><fmt:message key="log.out"/></a>
                </li>
                <li class="text-center border-right text-white">
                    <a href="#" class="text-white">
                        <i class="fas fa-sign-in-alt mr-2"></i> ${userName} </a>
                </li>

                <enter:userWithAvatar userAvatar="${userAvatar}" userName="${userName}"/>

          </c:otherwise>
    </c:choose>
