    function checkForm(form) {
    if(checkEmail(form)){
        return checkPassword(form);
    }
    return false;
    }

    function checkEmail(form){
    if (form.email.value === "") {
        alert("Fill email blank!");
        form.email.focus();
        return false;
    }
    re = /^\w+@\w+.\w+$/;
    if (!re.test(form.email.value)) {
        alert("Fill email by this template -> ###@##.##")
        form.email.focus();
        return false;
    }
    return true;
    }

    function checkPassword(form){
    if (form.password.value !== "") {
        if (form.password.value.length < 6) {
            alert("Password must contain at least six characters!");
            form.password.focus();
            return false;
        }
        re = /[0-9]/;
        if (!re.test(form.password.value)) {
            alert("Password must contain at least one number (0-9)!");
            form.password.focus();
            return false;
        }
        re = /[a-z]/;
        if (!re.test(form.password.value)) {
            alert("Password must contain at least one lowercase letter (a-z)!");
            form.password.focus();
            return false;
        }
        re = /[A-Z]/;
        if (!re.test(form.password.value)) {
            alert("Password must contain at least one uppercase letter (A-Z)!");
            form.password.focus();
            return false;
        }
        if(!form.password.value === form.repeatedPassword.value){
            alert("Passwords not equals");
            form.repeatedPassword.focus();
            return false;
        }
    } else {
        alert("Please check that you've entered and confirmed your password!");
        form.password.focus();
        return false;
    }
    return true;
    }

    function checkRegistration(form){
        if(form.userLogin.value === ""){
            alert("Fill login blank");
            form.repeatedPassword.focus();
            return false;
        }
        if(form.name.value === ""){
            alert("Fill name blank");
            form.name.focus();
            return false;
        }
        if(form.lastName.value === ""){
            alert("Fill lastName blank");
            form.lastName.focus();
            return false;
        }
        if(checkPassword(form)){
            if (checkEmail(form)){
                if(form.repeatedPassword.value !== form.password.value){
                    return false;
                }
                return checkCaptcha(form)
            }
        }

        return false;
    }

    function checkCaptcha(form) {
        if (form.captcha.value === "") {
            alert("Fill captcha blank!");
            form.captcha.focus();
            return false;
        }
        return true;
    }
