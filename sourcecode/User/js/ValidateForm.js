function validateUpdatePassForm() {
    var newPassword = document.getElementById("newPasswordUpdateForm").value;
    var confirmPassword = document.getElementById("newConfirmPasswordUpdateForm").value;

    if (newPassword !== confirmPassword) {
        alert("Mật khẩu mới và xác nhận mật khẩu mới không trùng khớp");
        return false;
    }
    return true;
}
