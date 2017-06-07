<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <meta name="layout" content="main"/>
    <asset:javascript src="facebook.js"></asset:javascript>
    <script>
    function registrationWithProvider(email, fullName, userAccountId, providerName, accessToken, expireIn) {
        $.post("${createLink(controller: 'login', action: 'providerRegistration')}",
            {   email: email,
                fullName: fullName,
                userAccountId: userAccountId,
                providerName: providerName,
                accessToken: accessToken,
                expireIn: expireIn
            },
            function(result) {
            if(result.hasError == true) {
                alert(result.message);
            } else {
                window.location.href = "${createLink(controller: 'home')}";
            }
        });
    }
    </script>
</head>
<body>
<div class="container">
    <div class="panel-start" id="panel-custom">
        <div id="loginbox" class="col-md-5 col-md-offset-3 col-sm-8 col-sm-offset-2">
            <div class="panel panel-info" >
                <div class="panel-heading">
                    <div class="panel-title panel-title-left">Register</div>
                    <div class="panel-title-right"><a href="${createLink(uri: '/')}" target="_self">${message(code:'app.project.name.title' )}</a></div>
                </div>
                <div class="panel-body" >
                    <g:if test='${flash.message}'>
                        <div class="errorHandler alert alert-danger">
                            <i class="fa fa-remove-sign"></i>
                            ${flash.message}
                        </div>
                    </g:if>
                    <form id="registerForm" class="form-horizontal" role="form" method='POST'>
                        <div class="form-group col-md-12">
                            <label>User Type</label> <br/>
                            <div class="col-md-6">
                                <label class="control inline">
                                    <input type="radio" name="userType" checked="checked" value="provider">
                                    Brand Owner
                                </label>
                            </div>

                            <div class="col-md-6">
                                <label class="control inline">
                                    <input type="radio" name="userType" value="seeker">
                                    Journalist
                                </label>
                            </div>
                        </div>
                        <div class="input-group input-margin-bottom">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-cutlery"></i></span>
                            <input id="companyName" type="text" class="form-control" name="companyName" value="" placeholder="Company Name">
                        </div>
                        <div class="margin-bottom-small btn btn-primary panel-padding-left" onclick="checkValidationSocialRegistration()">
                            <i class="fa fa-facebook-square fa-2x" aria-hidden="true"></i> Sign Up with Facebook
                        </div>
                        <h4 class="text-center">OR</h4>
                        <div class="input-margin-bottom">
                        </div>
                        <div class="input-group input-margin-bottom">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                            <input id="username" type="email" class="form-control" name="username" value="" placeholder="Email Address">
                        </div>
                        <div class="input-group input-margin-bottom">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input id="password" type="password" class="form-control" name="password" placeholder="password">
                        </div>
                            <button value="signup" class="btn btn-primary col-md-12 col-sm-12 col-xs-12">Sign up</button>
                        <div class="col-md-12">
                            Already Account?
                            <g:link controller="login" action="auth" >Login</g:link>
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
