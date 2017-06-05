<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home Page</title>
</head>
<body>


<sec:ifLoggedIn>
    Welcome Back!
    <a href="${createLink(controller: 'logout')}" target="" title="Logout"><i
            class="fa fa-sign-out"></i>Logout</a>
</sec:ifLoggedIn>

</body>
</html>
