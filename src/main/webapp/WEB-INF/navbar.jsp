<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <title>Navbar</title>
  <meta charset="ISO-8859-1" />
  <link rel="stylesheet" type="text/css" th:href="@{static/css/home.css}"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="/home">CarHire</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li sec:authorize="isAuthenticated()" class="nav-item">
            <a class="nav-link active" aria-current="page" th:href="@{/mainPage/page/1?sort-field=id}"
               th:text="#{nav.buttons.mainPage}">Main
              page</a>
          </li>
          <li class="nav-item">
            <a class="nav-link"
               th:href="@{/carAddPage}"
               th:text="#{nav.buttons.addCar}"
               sec:authorize="hasAuthority('ADMIN')"></a>
          </li>
          <li class="nav-item">
            <a class="nav-link"
               th:href="@{/insides/allUsers/page/1?sort-field=id}"
               th:text="#{nav.buttons.allUsers}"
               sec:authorize="hasAuthority('ADMIN')">Users</a>
          </li>
          <li class="nav-item">
            <a class="nav-link"
               th:href="@{/insides/allOrders/page/1}"
               th:text="#{nav.buttons.allOrders}"
               sec:authorize="hasAnyAuthority('USER', 'MANAGER')">Orders</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#"
               id="navbarDropdown" role="button"
               data-bs-toggle="dropdown" aria-expanded="false"
               th:text="#{nav.buttons.lang}">
            </a>

            <!--                        old lang-->
            <!--                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">-->
            <!--                            <li><a class="dropdown-item" th:href="@{'?lang=en'}">EN</a></li>-->
            <!--                            <li><a class="dropdown-item" th:href="@{'?lang=ua'}">UA</a></li>-->
            <!--                        </ul>-->


            <ul class="dropdown-menu" aria-labelledby="navbarDropdown"
                th:with="urlBuilder=${T(org.springframework.web.servlet.support
                                        .ServletUriComponentsBuilder).fromCurrentRequest()}">
              <li><a class="dropdown-item"
                     th:href="${urlBuilder.replaceQueryParam('lang', 'en').toUriString()}">EN</a></li>
              <li><a class="dropdown-item"
                     th:href="${urlBuilder.replaceQueryParam('lang', 'ua').toUriString()}">UA</a></li>
            </ul>
          </li>
        </ul>

        <form sec:authorize="isAuthenticated()" class="d-flex" th:action="@{/logout}" method="get">
          <button class="btn btn-outline-danger" name="registration"
                  type="Submit" th:text="#{nav.buttons.logout}">Logout</button>
        </form>
      </div>
    </div>
  </nav>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
