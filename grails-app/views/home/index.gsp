<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home Page</title>
</head>
<body>
<asset:javascript src="facebook.js"></asset:javascript>

<sec:ifLoggedIn>
    Welcome Back!
    <a href="${createLink(controller: 'logout')}" target="" title="Logout"><i
            class="fa fa-sign-out"></i>Logout</a>
    <br/>
    <div class="col-md-5">
        <div class="form-group">
            <label for="message">Message:</label>
            <textarea class="form-control" id="message"></textarea>
        </div>

        <div class="form-group facebook-pages">
            <label for="pages">Facebook Pages:</label>
            <select class="form-control" id="pages">
            </select>
            <button class="btn btn-primary" onclick="loadPages('${user?.companyName}', '${user?.userType}')">Load Pages</button>
        </div>

        <button class="btn btn-primary" onclick="postToPage()">Post</button>
    </div>
</sec:ifLoggedIn>

</body>
</html>
