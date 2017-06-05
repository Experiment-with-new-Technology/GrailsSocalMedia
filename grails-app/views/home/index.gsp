<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home Page</title>
</head>
<body>


<sec:ifLoggedIn>
    <li><a href="#" target="" title="${sec.loggedInUserInfo(field: 'username')} Profile"><i
            class="fa fa-user"></i> ${sec.loggedInUserInfo(field: 'username')} </a></li>
    <li><a href="${createLink(controller: 'logout')}" target="" title="Logout"><i
            class="fa fa-sign-out"></i>Logout</a></li>
</sec:ifLoggedIn>
</body>
</html>
