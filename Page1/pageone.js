document.addEventListener('DOMContentLoaded', () => {
    let items = []; // This will hold your items data, you can populate this from your server or define it here
    const filterButton = document.querySelector('.filter-button');
    const filterBox = document.querySelector('.filter-box');
    const filterForm = document.querySelector('.filter-box form'); // Changed to use class
    const resultsContainer = document.querySelector('.results');

    // Mock data for demonstration
    items = [
        {
            title: "Cơm Gà Xối Mỡ 142",
            location: "142 Đa Kao, P. Tân Định, TP HCM",
            city: "hcm",
            price: "50.000",
            image: "ganuong.jpg",
            category: "Đồ ăn",
            popularity: 120
        },
        {
            title: "Bánh Tráng Chấm Cô Gánh",
            location: "Lô D01 Chung Cư A4, P. Tân Định, TP HCM",
            city: "hcm",
            price: "40.000",
            image: "ganuong.jpg",
            category: "Đồ ăn",
            popularity: 85
        },
        {
            title: "Cơm Gà Xối Mỡ 142",
            location: "142 Đa Kao, P. Tân Định, TP HCM",
            city: "hcm",
            price: 50000,
            image: "ganuong.jpg",
            category: "Đồ ăn",
            popularity: 120
        },
        {
            title: "Bánh Tráng Chấm Cô Gánh",
            location: "Lô D01 Chung Cư A4, P. Tân Định, TP HCM",
            city: "hcm",
            price: 40000,
            image: "ganuong.jpg",
            category: "Đồ ăn",
            popularity: 85
        },
        {
            title: "Bánh Mì Huỳnh Hoa",
            location: "26 Lê Thị Riêng, P. Bến Thành, Quận 1",
            city: "hcm",
            price: 30000,
            image: "ganuong.jpg",
            category: "Đồ ăn",
            popularity: 150
        },
        {
            title: "Phở Bát Đàn",
            location: "49 Bát Đàn, P. Cửa Đông, Quận Hoàn Kiếm",
            city: "hn",
            price: 60000,
            image: "ganuong.jpg",
            category: "Đồ ăn",
            popularity: 200
        },
        {
            title: "Nước Dừa Cắt Lát",
            location: "15 Lê Lợi, Quận 1, TP HCM",
            city: "hcm",
            price: 25000,
            image: "ganuong.jpg",
            category: "Đồ uống",
            popularity: 75
        },
        {
            title: "Bún Thịt Nướng",
            location: "76 Nguyễn Văn Cừ, Quận 1, TP HCM",
            city: "hcm",
            price: 45000,
            image: "ganuong.jpg",
            category: "Đồ ăn",
            popularity: 150
        },
        {
            title: "Sữa Chua Nếp Cẩm",
            location: "25 Đinh Tiên Hoàng, Quận Hoàn Kiếm, Hà Nội",
            city: "hn",
            price: 25000,
            image: "ganuong.jpg",
            category: "Đồ ăn",
            popularity: 80
        },
        {
            title: "Nem Lụi Huế",
            location: "89 Nguyễn Đình Chiểu, Huế",
            city: "hue",
            price: 35000,
            image: "ganuong.jpg",
            category: "Đồ ăn",
            popularity: 100
        },
        {
            title: "Nước Dừa Lọc",
            location: "10 Nguyễn Huệ, Quận 1, TP HCM",
            city: "hcm",
            price: 20000,
            image: "nuocdualoc.jpg",
            category: "Đồ uống",
            popularity: 70
        },
        {
            title: "Bún Mắm Thanh Hóa",
            location: "34 Đường Trần Hưng Đạo, Thanh Hóa",
            city: "hn",
            price: 40000,
            image: "bunmam.jpg",
            category: "Đồ ăn",
            popularity: 120
        },
        {
            title: "Chè Thái",
            location: "50 Nguyễn Huệ, Quận 1, TP HCM",
            city: "hcm",
            price: 30000,
            image: "chethai.jpg",
            category: "Đồ uống",
            popularity: 90
        },
        {
            title: "Bánh Canh Cua",
            location: "120 Lý Tự Trọng, Quận 1, TP HCM",
            city: "hcm",
            price: 55000,
            image: "banhcanhcua.jpg",
            category: "Đồ ăn",
            popularity: 140
        },
        {
            title: "Nước Dừa Mát",
            location: "76 Lê Lợi, Quận 1, TP HCM",
            city: "hcm",
            price: 15000,
            image: "nuocduamat.jpg",
            category: "Đồ uống",
            popularity: 60
        },
        {
            title: "Gỏi Cuốn",
            location: "90 Nguyễn Cao, Quận 1, TP HCM",
            city: "hcm",
            price: 25000,
            image: "goicuon.jpg",
            category: "Đồ ăn",
            popularity: 80
        },
        {
            title: "Bún Thang",
            location: "67 Hàng Bạc, Quận Hoàn Kiếm, Hà Nội",
            city: "hn",
            price: 35000,
            image: "bunthang.jpg",
            category: "Đồ ăn",
            popularity: 100
        },
        {
            title: "Trà Sữa Thái Xanh",
            location: "21 Trần Hưng Đạo, Quận 1, TP HCM",
            city: "hcm",
            price: 40000,
            image: "trasuathai.jpg",
            category: "Đồ uống",
            popularity: 110
        },
        {
            title: "Bánh Xèo",
            location: "52 Nguyễn Du, Quận 1, TP HCM",
            city: "hcm",
            price: 30000,
            image: "banhxeo.jpg",
            category: "Đồ ăn",
            popularity: 90
        },
        {
            title: "Cháo Long",
            location: "78 Nguyễn Đình Chiểu, Quận 1, TP HCM",
            city: "hcm",
            price: 25000,
            image: "chaolong.jpg",
            category: "Đồ ăn",
            popularity: 80
        },
        {
            title: "Trà Đào",
            location: "63 Trần Hưng Đạo, Quận 1, TP HCM",
            city: "hcm",
            price: 25000,
            image: "tradao.jpg",
            category: "Đồ uống",
            popularity: 70
        },
        {
            title: "Bánh Gối",
            location: "88 Đồng Khởi, Quận 1, TP HCM",
            city: "hcm",
            price: 20000,
            image: "banhgoidongkhoi.jpg",
            category: "Đồ ăn",
            popularity: 75
        },
        {
            title: "Sinh Tố Bơ Chuối",
            location: "36 Nguyễn Thị Minh Khai, Quận 1, TP HCM",
            city: "hcm",
            price: 30000,
            image: "sinhtobochuoi.jpg",
            category: "Đồ uống",
            popularity: 85
        },
        {
            title: "Bún Chả Hà Nội",
            location: "15 Hàng Mành, Quận Hoàn Kiếm, Hà Nội",
            city: "hn",
            price: 40000,
            image: "buncha.jpg",
            category: "Đồ ăn",
            popularity: 110
        },
        {
            title: "Cà Phê Sữa Nóng",
            location: "34 Hàng Bông, Quận Hoàn Kiếm, Hà Nội",
            city: "hn",
            price: 20000,
            image: "cafesuanong.jpg",
            category: "Đồ uống",
            popularity: 65
        },
        {
            title: "Nem Nướng Đà Lạt",
            location: "67 Nguyễn Thái Học, Quận 1, TP HCM",
            city: "hcm",
            price: 35000,
            image: "nemnuongdalat.jpg",
            category: "Đồ ăn",
            popularity: 95
        },
        {
            title: "Nước Dừa Cắt",
            location: "43 Nguyễn Huệ, Quận 1, TP HCM",
            city: "hcm",
            price: 25000,
            image: "nuocduacat.jpg",
            category: "Đồ uống",
            popularity: 75
        },
        
    ];

    // Hàm tạo card
function createCard(item) {
    const card = document.createElement('div');
    card.classList.add('card');

    const img = document.createElement('img');
    img.src = item.image;
    card.appendChild(img);

    const cardContent = document.createElement('div');
    cardContent.classList.add('card-content');

    const title = document.createElement('h3');
    title.textContent = item.title;
    cardContent.appendChild(title);

    const location = document.createElement('p');
    location.textContent = item.location;
    cardContent.appendChild(location);

    card.appendChild(cardContent);

    const cardFooter = document.createElement('div');
    cardFooter.classList.add('card-footer');

    const price = document.createElement('span');
    price.textContent = item.price + ' VNĐ';
    cardFooter.appendChild(price);

    const badge = document.createElement('a');
    badge.classList.add('badge');
    badge.href = "#";
    badge.textContent = "Thêm";
    cardFooter.appendChild(badge);

    card.appendChild(cardFooter);

    // Bắt sự kiện mouseenter để hiển thị modal
    card.addEventListener('mouseenter', function() {
        const modal = createModal(item);
        document.body.appendChild(modal);
    });

    // Bắt sự kiện mouseleave để xóa modal
    card.addEventListener('mouseleave', function() {
        const modal = document.querySelector('.modal');
        if (modal) {
            modal.remove();
        }
    });

    return card;
}

// Hàm tạo modal hiển thị chi tiết sản phẩm
function createModal(item) {
    const modal = document.createElement('div');
    modal.classList.add('modal');

    const modalContent = document.createElement('div');
    modalContent.classList.add('modal-content');

    const closeBtn = document.createElement('span');
    closeBtn.classList.add('close');
    closeBtn.innerHTML = '&times;';
    closeBtn.addEventListener('click', function() {
        modal.remove();
    });

    const title = document.createElement('h2');
    title.textContent = item.title;

    const location = document.createElement('p');
    location.textContent = item.location;

    const price = document.createElement('p');
    price.textContent = 'Giá: ' + item.price + ' VNĐ';

    modalContent.appendChild(closeBtn);
    modalContent.appendChild(title);
    modalContent.appendChild(location);
    modalContent.appendChild(price);

    modal.appendChild(modalContent);

    return modal;
}


    // Hàm hiển thị các sản phẩm đã lọc
    function displayFilteredItems(filteredItems) {
        resultsContainer.innerHTML = '';
        filteredItems.forEach(item => {
            const card = createCard(item);
            resultsContainer.appendChild(card);
        });
    }

    // Hàm lọc sản phẩm
    function filterItems(event) {
        event.preventDefault(); // Ngăn chặn form submit mặc định

        // Lấy các giá trị lọc từ form
        const area = document.getElementById('area').value;
        const priceRange = document.getElementById('price-range').value;
        const type = document.getElementById('type').value;

        // Lọc các sản phẩm theo các tiêu chí đã chọn
        const filteredItems = items.filter(item => {
            let isAreaMatch = (area === "0" || area === item.city);
            let isPriceMatch = false;
            let isTypeMatch = (type === "0" || type === item.category);
            console.log(isAreaMatch);
            console.log(isPriceMatch);
            console.log(isTypeMatch);
            // Kiểm tra khoảng giá của sản phẩm
            if (priceRange === "0") {
                isPriceMatch = true;
            } else if (priceRange === "1" && item.price <= 30000) {
                isPriceMatch = true;
            } else if (priceRange === "2" && item.price > 30000 && item.price <= 50000) {
                isPriceMatch = true;
            } else if (priceRange === "3" && item.price > 50000 && item.price <= 100000) {
                isPriceMatch = true;
            }

            return isAreaMatch && isPriceMatch && isTypeMatch;
        });

        // Hiển thị các sản phẩm đã lọc
        displayFilteredItems(filteredItems);
        console.log(filteredItems)
    }

    // Bắt sự kiện khi form lọc được submit
    filterForm.addEventListener('submit', filterItems);

    // Bắt sự kiện khi click vào nút "Bộ Lọc"
    filterButton.addEventListener('click', () => {
        if (filterBox.style.display === 'block') {
            filterBox.style.display = 'none';
        } else {
            filterBox.style.display = 'block';
        }
    });

    // Initial display of all items
    displayFilteredItems(items);
});

