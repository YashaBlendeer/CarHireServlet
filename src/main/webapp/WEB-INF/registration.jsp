<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><html>
<head>
    <title>Registration</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="container">
    <form autocomplete="off" action="#" action="/registration"
          method="post" class="form-signin"
          role="form">

        <div class="form-group">
            <div class="col-sm-9">
                <label class="validation-message">Name</label>
                <input type="text" placeholder="Name" name="name"
                       class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-9">
                <label class="validation-message">Last name</label>
                <input type="text" placeholder="Last name" name="lastName"
                       class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-9">
                <label class="validation-message">Email</label>
                <input type="text" placeholder="email" name="email"
                       class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-9">
                <label class="validation-message">Username</label>
                <input type="text" placeholder="username" name="userName"
                       class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-9">
                <label class="validation-message">Pass</label>
                <input type="password" placeholder="password" name="password"
                       class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-9">
                <button style="width: 200px; margin-top: 10px"  type="submit" class="btn btn-primary btn-block">Sign
                    up</button>
            </div>
        </div>

        <h2><span class="text-success" th:utext="${successMessage}"></span></h2>

    </form>
</div>
</body>
</html>