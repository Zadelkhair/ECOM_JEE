<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@ include file="components/header.jsp" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="containr">
        <h1>
        
        ${label}
        
        <c:out value="${user.username}"></c:out>
        
        </h1>
    </div>


    <%@ include file="components/footer.jsp" %>