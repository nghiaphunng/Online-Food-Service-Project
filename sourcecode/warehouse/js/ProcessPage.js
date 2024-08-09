document.querySelector(".danhsachsp").style.display = "block";
    document.querySelector(".themsp").style.display = "none";

    document.addEventListener("DOMContentLoaded", function() {
    const links = document.querySelectorAll(".sidebar li a");
  
    links.forEach(link => {
      link.addEventListener("click", function(event) {
        event.preventDefault();
  
        // Remove active class from currently active link
        const currentActive = document.querySelector(".sidebar li a.active");
        if (currentActive) {
          currentActive.classList.remove("active");
        }
  
        // Add active class to clicked link
        link.classList.add("active");
  
        // Determine which section to display based on link text
        const linkText = link.querySelector(".links_name").textContent.trim();
        if (linkText === "Danh sách sản phẩm") {
            document.querySelector(".danhsachsp").style.display = "block";
            document.querySelector(".themsp").style.display = "none";
          
        } else if (linkText === "Thêm sản phẩm") {
            document.querySelector(".danhsachsp").style.display = "none";
            document.querySelector(".themsp").style.display = "block";
        } 
      });
    });
  });
  function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function(){
        var output = document.getElementById('display-img');
        output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
}