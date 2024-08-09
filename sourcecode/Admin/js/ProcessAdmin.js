document.querySelector(".tong-quan").style.display = "block";
document.querySelector(".quan-ly-user").style.display = "none";
document.querySelector(".quan-ly-res").style.display = "none";
document.querySelector(".quan-ly-err").style.display = "none";
document.querySelector(".quan-ly-errU").style.display = "none";
document.querySelector(".thong-bao-user").style.display = "none";
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
      if (linkText === "Tổng quan") {
        document.querySelector(".tong-quan").style.display = "block";
        document.querySelector(".quan-ly-user").style.display = "none";
        document.querySelector(".quan-ly-res").style.display = "none";
        document.querySelector(".quan-ly-err").style.display = "none";
        document.querySelector(".quan-ly-errU").style.display = "none";
        document.querySelector(".thong-bao-user").style.display = "none";
      } else if (linkText === "Quản lý khách hàng") {
        document.querySelector(".tong-quan").style.display = "none";
        document.querySelector(".quan-ly-user").style.display = "block";
        document.querySelector(".quan-ly-res").style.display = "none";
        document.querySelector(".quan-ly-err").style.display = "none";
        document.querySelector(".quan-ly-errU").style.display = "none";
        document.querySelector(".thong-bao-user").style.display = "none";
      } else if (linkText === "Quản lý nhà hàng") {
        document.querySelector(".tong-quan").style.display = "none";
        document.querySelector(".quan-ly-user").style.display = "none";
        document.querySelector(".quan-ly-res").style.display = "block";
        document.querySelector(".quan-ly-err").style.display = "none";
        document.querySelector(".quan-ly-errU").style.display = "none";
        document.querySelector(".thong-bao-user").style.display = "none";
      } else if (linkText === "Lỗi hệ thống") {
        document.querySelector(".tong-quan").style.display = "none";
        document.querySelector(".quan-ly-user").style.display = "none";
        document.querySelector(".quan-ly-res").style.display = "none";
        document.querySelector(".quan-ly-err").style.display = "block";
        document.querySelector(".quan-ly-errU").style.display = "none";
        document.querySelector(".thong-bao-user").style.display = "none";
      } else if (linkText === "Thông báo người dùng") {
        document.querySelector(".tong-quan").style.display = "none";
        document.querySelector(".quan-ly-user").style.display = "none";
        document.querySelector(".quan-ly-res").style.display = "none";
        document.querySelector(".quan-ly-err").style.display = "none";
        document.querySelector(".quan-ly-errU").style.display = "none";
        document.querySelector(".thong-bao-user").style.display = "block";
      } else if (linkText === "Báo lỗi từ người dùng") {
        document.querySelector(".tong-quan").style.display = "none";
        document.querySelector(".quan-ly-user").style.display = "none";
        document.querySelector(".quan-ly-res").style.display = "none";
        document.querySelector(".quan-ly-err").style.display = "none";
        document.querySelector(".quan-ly-errU").style.display = "block";
        document.querySelector(".thong-bao-user").style.display = "none";
      }
    });
  });
});