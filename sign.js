document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent form from submitting
 
    var password = document.getElementById('password').value;
    var correctPassword = 'your_correct_password'; // Replace with the correct password
 
    if (password === correctPassword) {
       alert('Đăng nhập thành công');
       // Optionally, you can submit the form here if needed
       // this.submit();
    } else {
       alert('Sai mật khẩu, vui lòng thử lại');
    }
 });
 