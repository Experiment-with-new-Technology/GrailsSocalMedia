<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <meta name="layout" content="main"/>
    <asset:javascript src="facebook.js"></asset:javascript>
    <asset:javascript src="google.js"></asset:javascript>
    <asset:javascript src="linkedin.js"></asset:javascript>
</head>
<body>
<div class="container">
    <div class="panel-start" id="panel-custom">
        <div id="loginbox" class="col-md-5 col-md-offset-3 col-sm-8 col-sm-offset-2">
            <div class="panel panel-info" >
                <div class="panel-heading">
                    <div class="panel-title panel-title-left">Login</div>
                    <div class="panel-title-right"><a href="${createLink(uri: '/')}" target="_self">${message(code:'app.project.name.title' )}</a></div>
                </div>
                <div class="panel-body" >
                    <g:if test='${flash.message}'>
                        <div class="errorHandler alert alert-danger">
                            <i class="fa fa-remove-sign"></i>
                            ${flash.message}
                        </div>
                    </g:if>
                    <form class="form-horizontal" role="form" action='${postUrl}' method='POST' id="loginForm">
                        <div class="input-margin-bottom btn btn-primary" onclick="loginWithFacebook()">
                            <i class="fa fa-facebook-square fa-2x" aria-hidden="true"></i> Sign in with Facebook
                        </div>
                        <div class="input-margin-bottom">
                            <button class="btn btn-primary" onclick="loginWithLinkedin()">
                                Login With Linkedin</button>
                        </div>
                        <h4 class="text-center">OR</h4>
                        <div class="input-margin-bottom">
                        </div>
                        <div class="input-group input-margin-bottom">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                            <input id="username" type="text" class="form-control" name="j_username" value="" placeholder="Email Address">
                        </div>
                        <div class="input-group input-margin-bottom">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input id="password" type="password" class="form-control" name="j_password" placeholder="password">
                        </div>
                            <button class="btn btn-primary col-md-12 col-sm-12 col-xs-12" type="submit">Login</button>
                        <div class="col-md-12">
                            Have not any Account?
                            <g:link controller="register" action="register">Register</g:link>
                        </div>
                    </form>
                </div>
                <iframe src="https://mindmup.github.io/3rdpartycookiecheck/start.html" style="display:none" />
            </div>
        </div>
    </div>
</div>
</body>
</html>
