<%@ page import="anton.sample.model.ContactType" %>
<%@ page import="anton.sample.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="../css/style.css">
    <style type="text/css">
        th, td {
            padding: 4px 10px 4px 0;
            vertical-align: top;
        }

        tr {
            border-bottom: 1px solid #DDDDDD;
        }

        table {
            margin-bottom: 1.4em;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
        }
    </style>
    <jsp:useBean id="resume" type="anton.sample.model.Resume" scope="request"/>
    <title>Resume ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form id="resume" method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dt>
                <input type="text" name="name" size=50 value="${resume.fullName}">
            </dt>
        </dl>
        <dl>
            <dt>Проживание:</dt>
            <dt>
                <input type="text" name="location" size=50 value="${resume.location}">
            </dt>
        </dl>
        <dl>
            <dt>Домашняя страница:</dt>
            <dt>
                <input type="text" name="home_page" size=50 value="${resume.homePage}">
            </dt>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd>
                    <input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}">
                </dd>
            </dl>
        </c:forEach>

        <c:forEach var="type" items="<%=SectionType.values()%>">
            <h3><a>${type.title}</a></h3>
            ${type.htmlType.toHtml(resume.getSection(type), type)}
        </c:forEach>
        <hr>

        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
