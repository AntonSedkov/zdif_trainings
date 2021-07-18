<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Advertisements</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

<div class="container">
    <h3>Advertisements List</h3>

    <form class="form-inline" role="form" action="/adv/search" method="post">
        <input type="text" class="form-control" name="pattern" placeholder="Search">
        <input type="submit" class="btn btn-default" value="Search">
    </form>

    <table class="table table-striped">
        <thead>
            <tr>
                <td><b>Image</b></td>
                <td><b>Name</b></td>
                <td><b>Summary</b></td>
                <td><b>Description</b></td>
                <td><b>Phone</b></td>
                <td><b>Price</b></td>
                <td><b>Action</b></td>
            </tr>
        </thead>
    <c:forEach items="${advs}" var="adv">
        <tr>
            <td><img height="40" width="40" src="/adv/image/${adv.photo.id}"/> </td>
            <td>${adv.name}</td>
            <td>${adv.shortDesc}</td>
            <td>${adv.longDesc}</td>
            <td>${adv.phone}</td>
            <td>${adv.price}</td>
            <td><a href="/adv/delete?id=${adv.id}" methods="post">Delete</a></td>
        </tr>
    </c:forEach>
    </table>

    <form class="form-inline" role="form" action="/adv/add_adv" method="post">
        <input type="submit" class="btn btn-default" value="Add new">
    </form>

</div>
</body>
</html>
