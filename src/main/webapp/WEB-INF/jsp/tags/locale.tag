<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="aa-language">
    <div class="dropdown">
        <a class="btn dropdown-toggle" href="#" type="button"
            id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
            aria-expanded="true">Language<span class="caret"></span>
        </a>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
            <li><a href="${requestScope.url}?lang=ru-RU"><img src="img/flag/russian.jpg" alt="">RUSSIAN</a></li>
            <li><a href="${requestScope.url}?lang=en-US"><img src="img/flag/english.jpg" alt="">ENGLISH</a></li>
        </ul>
    </div>
</div>