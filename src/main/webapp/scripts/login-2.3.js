$(document).ready(function(){
	$("#login").click(function() {
		var email = $("#email").val();
		var password = $("#password").val();
		var name = $("#name").val();
		var lastName = $("#lastName").val();
		var captcha = $("#captcha").val();
		var login = $("#userLogin").val();
		var repeatedPassword = $("#repeatedPassword").val();
		if(login === ''){
		    $('input[id="userLogin"]').css("border", "2px solid red");
        	alert("Fill field name, please");
		} else {
		    $('input[id="userLogin"]').css("border", "2px solid green");
		}
		if (name === '') {
			$('input[id="name"]').css("border", "2px solid red");
			alert("Fill field name, please");
		} else {
			$('input[id="name"]').css("border", "2px solid green");
		}
		if (lastName === '') {
			$('input[id="lastName"]').css("border", "2px solid red");
			alert("Fill field last name, please");
		} else {
			$('input[id="lastName"]').css("border", "2px solid green");
		}
		if (password === '') {
			$('input[id="password"]').css("border", "2px solid red");
			alert("Fill field password, please");
		} else {
			$('input[id="password"]').css("border", "2px solid green");
		}
		if(repeatedPassword === ''){
		    $('input[id="repeatedPassword"]').css("border", "2px solid red");
		    alert("Fill field repeated password, please")
		} else {
		    $('input[id="repeatedPassword"]').css("border", "2px solid green");
		}
		if(repeatedPassword !== password){
		    $('input[id="repeatedPassword"]').css("border", "2px solid red");
		    alert("Passwords not equals")
		} else {
		    $('input[id="repeatedPassword"]').css("border", "2px solid green");
		}
		if (email === '') {
			$('input[id="email"]').css("border", "2px solid red");
//			alert("Fill field email, please");
		} else {
			$('input[id="email"]').css("border", "2px solid green");
		}
		if (captcha === ''){
			$('input[id="captcha"]').css("border", "2px solid red");
//			alert("Fill captcha, please")
		} else {
			$('input[id="email"]').css("border", "2px solid green");
		}
	});
});