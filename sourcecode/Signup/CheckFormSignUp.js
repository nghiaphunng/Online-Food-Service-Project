function validateFormSignUp(){
	var passwordUser = document.form_sign_up.passwordUser.value;
	var nameLogin = document.form_sign_up.nameLogin.value;
	//kiểm tra dộ dài mật khẩu
	if(passwordUser.length < 6){
		alert("Độ dài mật khẩu tối thiểu 6 kí tự");
	}
	//kiểm tra tên tài khoản
	var userNamePattern = /^[a-zA-Z0-9]+$/;
	if (!userNamePattern.test(nameLogin)) {
        alert("Tên tài khoản chỉ được chứa các ký tự chữ cái và số, không có khoảng trắng và dấu");
    } 
	return (passwordUser.length >= 6 && userNamePattern.test(nameLogin));
}
