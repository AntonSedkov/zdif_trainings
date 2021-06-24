<%@ page import="anton.sample.WebAppConfig" %>
<%@ page import="anton.sample.model.ContactType" %>
<%@ page import="anton.sample.model.Resume" %>
<%@ page import="java.util.Collection" %>
<%@ page import="anton.sample.exception.StorageException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
<section>
    <table>
        <tr>
            <td colspan="5" style="text-align: right"><a href="resume?action=create"><img src="img/add.png"> Добавить
                Резюме</a></td>
        </tr>
        <tr>
            <td>
                <table border="1" cellpadding="8" cellspacing="0">
                    <tr>
                        <th>Имя</th>
                        <th>Проживание</th>
                        <th>Email</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                    </tr>
                    <%
                        Collection<Resume> resumes = null;
                        try {
                            resumes = WebAppConfig.getInstance().getStorage().getAllSorted();
                        } catch (StorageException e) {
                            e.printStackTrace();
                        }
                        request.setAttribute("resumeList", resumes);
                    %>
                    <c:forEach items="${resumeList}" var="resume">
                        <jsp:useBean id="resume" type="anton.sample.model.Resume"/>
                        <tr>
                            <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                            <td><%=HtmlUtil.mask(resume.getLocation())%></td>
                            <td><%=HtmlUtil.getContact(resume, ContactType.MAIL)%></td>
                            <td><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png"></a></td>
                            <td><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
