<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="enter" %>

<%@ attribute name="userAvatar" required="true" %>
<%@ attribute name="userName" required="false" %>

<c:choose>
    <c:when test="${userAvatar == ''}">
        <li class="text-center border-right text-white">
             <a href="#">
                <img src="${pageContext.request.contextPath}/images/siteEnter/no-avatar-png-3.png" border="100" height="20" width="20">
             </a>
        </li>
    </c:when>
    <c:otherwise>
        <li class="text-center border-right text-white">
             <a href="#">
                <img src="${pageContext.request.contextPath}/${userAvatar}" border="100" height="20" width="20">
             </a>
        </li>
    </c:otherwise>
</c:choose>