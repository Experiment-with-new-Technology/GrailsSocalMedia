/**
 * Created by rakib on 6/8/2017.
 */

var companyName, userType;
//Invoke login window
function liAuth(){
    IN.User.authorize(getProfileData);
}

// Setup an event listener to make an API call once auth is complete
function onLinkedInLoad() {
    IN.Event.on(IN, "auth", getProfileData);
}

// Handle the successful return from the API call
function onSuccess(data) {
    var accessToken = IN.ENV.auth.oauth_token;
    var fullName = data.values[0].firstName + " " + data.values[0].lastName;
    var userAccountId = data.values[0].id;
    var email = data.values[0].emailAddress;
    var expireIn = 3600;
    var providerName = "LINKEDIN";
    registrationWithProvider(email, fullName, userAccountId, providerName, accessToken, expireIn, companyName, userType);
}

// Handle an error response from the API call
function onError(error) {
    console.log(error);
}

function LinkedInLogin() {
    IN.User.authorize(getProfileData);
}

function getProfileData() {
    if (IN.User.isAuthorized() == true) {
       IN.API.Profile("me").fields(["id","firstName","lastName", "email-address"]).result(onSuccess).error(onError);
    }
}


function checkValidationSocialRegistration() {
    var form = $('#registerForm');
    var valid = form.validate(
        {
            rules: {
                companyName: "required"
            },
            message: {
                companyName: "Please provide company Name"
            }
        }
    );
    if(form.valid()) {
        companyName = $('#companyName').val();
        userType = $('#registerForm input[name=userType]:checked').val();
        liAuth();
    }
}

// login with Linkedin with extra permissions
function loginWithLinkedin() {
    IN.Event.on(IN, "auth", getProfileForLogin);
}

function getProfileForLogin() {
    if (IN.User.isAuthorized() == true) {
        IN.API.Profile("me").fields(["id","firstName","lastName", "email-address"]).result(onSuccessLogin).error(onError);
    }
}

function onSuccessLogin(data) {
    var accessToken = IN.ENV.auth.oauth_token;
    var fullName = data.values[0].firstName + " " + data.values[0].lastName;
    var userAccountId = data.values[0].id;
    var email = data.values[0].emailAddress;
    var expireIn = 3600;
    var providerName = "LINKEDIN";
    var paramsData = {userId: userId, accessToken: accessToken, expireIn: expireIn};
    $.post( "socialLogin", paramsData, function( data ) {
        if(data.hasError) {
            window.location.href='/GrailsSocial/register/register';
        } else {
            window.location.href='/GrailsSocial/home';
        }
        var datas = data;
    });
}