<%@ page import="anton.sample.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="anton.sample.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}</h2>
    <c:if test="${not empty resume.homePage}">
        Домашняя страница: ${resume.homePage}<br/>
    </c:if>
    <c:if test="${not empty resume.location}">
        Проживание: ${resume.location}<br/>
    </c:if>
    <p>
        <c:forEach var="type" items="${resume.contacts.keySet()}">
            <jsp:useBean id="type" type="anton.sample.model.ContactType"/>
            <%=HtmlUtil.getContact(resume, type)%><br/>
        </c:forEach>
    </p>
    <table cellpadding="8">
        <c:forEach var="entry" items="${resume.sections.entrySet()}">
            <jsp:useBean id="entry" type="java.util.Map.Entry"/>
            <%
                SectionType sectionType = ((SectionType) entry.getKey());
                Section section = ((Section) entry.getValue());
            %>
            <tr>
                <td><h3><a name="<%=sectionType.name()%>"><%=sectionType.getTitle()%>
                </a></h3></td>
                <%
                    switch (sectionType) {
                        case OBJECTIVE:
                %>
                <td><h3><%=((TextWithTitleSection) entry.getValue()).getTitle()%>
                </h3></td>
                <td><h4><%=((TextWithTitleSection) entry.getValue()).getComment()%>
                </h4></td>
            </tr>
            <%
                    break;
                case ACHIEVEMENT:
                case QUALIFICATION:
            %>
            <td>
                <ul>
                    <c:forEach var="item" items="<%=((MultiTextSection) entry.getValue()).getValues()%>">
                        <li>${item}</li>
                    </c:forEach>
                </ul>
            </td>
            </tr>
            <% break;
                case EXPERIENCE:
                case EDUCATION:
            %>
            </tr>
            <c:forEach var="org" items="<%=((OrganizationSection) entry.getValue()).getOrganizations()%>">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${empty org.link.url}">
                                ${org.link.name}
                            </c:when>
                            <c:otherwise>
                                <a href="${org.link.url}">${org.link.name}</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <c:forEach var="period" items="${org.periods}">
                    <jsp:useBean id="period" type="anton.sample.model.OrganizationPeriod"/>
                    <tr>
                        <td><%=HtmlUtil.format(period.getStartDate())%> - <%=HtmlUtil.format(period.getEndDate())%>
                        </td>
                        <td><b>${period.position}</b><br>${period.content}</td>
                    </tr>
                </c:forEach>
            </c:forEach>
            <%
                }
            %>
        </c:forEach>
    </table>
    <button onclick="window.history.back()">ОК</button>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
