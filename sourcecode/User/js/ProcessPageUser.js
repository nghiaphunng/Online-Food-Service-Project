document.querySelector(".infoUser").style.display = "block";
	document.querySelector(".changePass").style.display = "none";
	document.querySelector(".thongbaofrom").style.display = "none";
	document.querySelector(".thongbaoto").style.display = "none";
	document.querySelector(".lichsu").style.display = "none";
	document.addEventListener("DOMContentLoaded", function() {
	    const links = document.querySelectorAll(".sidebar li a");
	  
	    links.forEach(link => {
	      link.addEventListener("click", function(event) {
	        event.preventDefault();
	  
	        const currentActive = document.querySelector(".sidebar li a.active");
	        if (currentActive) {
	          currentActive.classList.remove("active");
	        }
	  
	        link.classList.add("active");
	  
	        const linkText = link.querySelector(".links_name").textContent.trim();
	        if (linkText == "Thông tin tài khoản") {
	          document.querySelector(".infoUser").style.display = "block";
	          document.querySelector(".changePass").style.display = "none";
	          document.querySelector(".thongbaofrom").style.display = "none";
	          document.querySelector(".thongbaoto").style.display = "none";
	          document.querySelector(".lichsu").style.display = "none";
	        } else if (linkText == "Thay đổi mật khẩu") {
	          document.querySelector(".infoUser").style.display = "none";
	          document.querySelector(".changePass").style.display = "block";
	          document.querySelector(".thongbaofrom").style.display = "none";
	          document.querySelector(".thongbaoto").style.display = "none";
	    
	          document.querySelector(".lichsu").style.display = "none";
	        } else if (linkText == "Thông báo từ hệ thống") {
	          document.querySelector(".infoUser").style.display = "none";
	          document.querySelector(".changePass").style.display = "none";
	          document.querySelector(".thongbaofrom").style.display = "block";
	          document.querySelector(".thongbaoto").style.display = "none";
	    
	          document.querySelector(".lichsu").style.display = "none";
	        } else if (linkText == "Gửi thông báo tới hệ thống") {
	          document.querySelector(".infoUser").style.display = "none";
	          document.querySelector(".changePass").style.display = "none";
	          document.querySelector(".thongbaofrom").style.display = "none";
	          document.querySelector(".thongbaoto").style.display = "block";
	    
	          document.querySelector(".lichsu").style.display = "none";
	        } 
	        else if (linkText == "Đặt bàn tiệc") {
	          document.querySelector(".infoUser").style.display = "none";
	          document.querySelector(".changePass").style.display = "none";
	          document.querySelector(".thongbaofrom").style.display = "none";
	          document.querySelector(".thongbaoto").style.display = "none";
	         
	          document.querySelector(".lichsu").style.display = "block";
	        }
	      });
	    });
	  });
	
	function triggerFileInput() {
        document.getElementById('avatar').click();
    }

    function previewAvatar(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById('avatarPreview').src = e.target.result;
            }
            reader.readAsDataURL(file);
        }
    }