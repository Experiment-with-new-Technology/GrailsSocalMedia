/**
 * Created by rakib on 6/8/2017.
 */

function beforeSubmit() {
    var form = $('#registerForm');
    var valid = form.validate(
        {
            rules: {
                companyName: "required",
                username: {
                    required: true,
                    email: true
                },
                password: "required"
            },
            message: {
                companyName: "Please provide company Name",
                username: {
                    required: "Please provide user name",
                    email: "Please Enter valid email address"
                },
                password: "Please provide password"
            }
        }
    );
    if(form.valid()) {
        form.submit();
    }
}

function registrationWithProvider(email, fullName, userAccountId, providerName, accessToken, expireIn, companyName, userType) {
    $.post("/GrailsSocial/register/registerWithProvider",
        {   email: email,
            fullName: fullName,
            userAccountId: userAccountId,
            providerName: providerName,
            accessToken: accessToken,
            expireIn: expireIn,
            companyName: companyName,
            userType: userType
        },
        function(result) {
            if(result.hasError == true) {
                alert(result.message);
            } else {
                window.location.href = "/GrailsSocial/home";
            }
        });
}
