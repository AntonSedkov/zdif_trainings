<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add advertisement</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/adv/add" method="post">
        <div class="form-group"><h3>New advertisement</h3></div>
        <div class="form-group"><input type="text" class="form-control" name="name" placeholder="Name"/></div>
        <div class="form-group"><input type="text" class="form-control" name="shortDesc" placeholder="Summary"/></div>
        <div class="form-group"><input type="text" class="form-control" name="longDesc" placeholder="Description"/></div>
        <div class="form-group"><input type="text" class="form-control" name="phone" placeholder="Phone"/></div>
        <div class="form-group"><input type="text" class="form-control" name="price" placeholder="Price"/></div>
        <div class="form-group">Photo (max size 50 kB): <input type="file" name="photo"/></div>

        <div class="form-group"><input type="submit" class="btn btn-primary" value="Add"/></div>
    </form>
</div>
</body>
</html>
