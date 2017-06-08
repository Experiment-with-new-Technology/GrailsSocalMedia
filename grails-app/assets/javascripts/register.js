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
