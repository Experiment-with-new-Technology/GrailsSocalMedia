//facebook registration
// initialize and setup facebook js sdk
var accessToken, userId, expireIn;
window.fbAsyncInit = function() {
    FB.init({
        appId      : '1443930665645356',
        xfbml      : true,
        cookie     : true,
        version    : 'v2.9'
    });
};
(function(d, s, id){
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {return;}
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// login with facebook with extra permissions
function loginFacebook() {
    FB.login(function(response) {
        if (response.status === 'connected') {
            accessToken = response.authResponse.accessToken;
            userId = response.authResponse.userID;
            expireIn = response.authResponse.expiresIn;
            getFacebookInfo(accessToken, expireIn);
        } else if (response.status === 'not_authorized') {
            alert('You are not logged in.');
        } else {
            alert('You are not logged into Facebook.');
        }
    }, {scope: 'email'});
}

// getting basic user info
function getFacebookInfo(accessToken, expireIn) {
    FB.api('/me', 'GET', {fields: 'name,email,id'}, function(response) {
        var fullName = response.name;
        var email = response.email;
        var userAccountId = response.id;
        var providerName = 'FACEBOOK';
        if(email && fullName && userAccountId && providerName) {
            registrationWithProvider(email, fullName, userAccountId, providerName, accessToken, expireIn);
        } else {
            alert('Something error. Please try again');
        }
    });
}