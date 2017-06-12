var thirthPartyCookieSupport = false;
var firstTime = true;
var googleUserAttr;
var receiveMessage = function (evt) {
    if (evt.data === 'MM:3PCunsupported') {
        thirthPartyCookieSupport = false;
    } else if (evt.data === 'MM:3PCsupported') {
        thirthPartyCookieSupport = true;
    }
};
window.addEventListener("message", receiveMessage, false);

function googleLogin() {
    var form = $('#registerForm');
    var profile;
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
        profile = googleUserAttr.getBasicProfile();
        if(profile) {
            var userAccountId = profile.getId();
            var providerName = 'GOOGLE';
            var fullName = profile.getName();
            var email = profile.getEmail();
            var authentication = googleUserAttr['Zi'];
            var accessToken = authentication['access_token'];
            var expireIn = authentication['expires_in'];
            var companyName = $('#companyName').val();
            var userType = $('#registerForm input[name=userType]:checked').val();
            if(userAccountId && providerName) {
                registrationWithProvider(email, fullName, userAccountId, providerName, accessToken, expireIn, companyName, userType);
            } else {
                alert('Something error. Please try again');
            }
        }
    }
}

function onSignIn(googleUser) {
    googleUserAttr = googleUser;
}