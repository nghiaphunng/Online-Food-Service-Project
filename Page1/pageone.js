document.addEventListener('DOMContentLoaded', () => {

    const filterForm = document.querySelector('.filter-box form'); // Changed to use class
    const resultsContainer = document.querySelector('.results');
    let items = []; // This will hold your items data, you can populate this from your server or define it here
    const filterButton = document.querySelector('.filter-button');
    const filterBox = document.querySelector('.filter-box');
    // Mock data for demonstration
    items = [
        {
            title: "Cơm Gà Xối Mỡ 142",
            location: "142 Đa Kao, P. Tân Định, TP HCM",
            city: "hcm",
            price: "50.000",
            image: "comga.jpg",
            category: "food",
            popularity: 120
        },
        {
            title: "Bánh Tráng Chấm Cô Gánh",
            location: "Lô D01 Chung Cư A4, P. Tân Định, TP HCM",
            city: "hcm",
            price: "40.000",
            image: "banhtrang.jpg",
            category: "food",
            popularity: 85
        },
        {
            title: "Cơm Gà Xối Mỡ 142",
            location: "142 Đa Kao, P. Tân Định, TP HCM",
            city: "hcm",
            price: 50000,
            image: "comga.jpg",
            category: "food",
            popularity: 120
        },
        {
            title: "Bánh Tráng Chấm Cô Gánh",
            location: "Lô D01 Chung Cư A4, P. Tân Định, TP HCM",
            city: "hcm",
            price: 40000,
            image: "banhtrang.jpg",
            category: "food",
            popularity: 85
        },
        {
            title: "Bánh Mì Huỳnh Hoa",
            location: "26 Lê Thị Riêng, P. Bến Thành, Quận 1",
            city: "hcm",
            price: 30000,
            image: "banhmi.jpg",
            category: "food",
            popularity: 150
        },
        {
            title: "Phở Bát Đàn",
            location: "49 Bát Đàn, P. Cửa Đông, Quận Hoàn Kiếm",
            city: "hn",
            price: 60000,
            image: "pho.jpg",
            category: "food",
            popularity: 200
        },
        {
            title: "Nước Dừa Cắt Lát",
            location: "15 Lê Lợi, Quận 1, TP HCM",
            city: "hcm",
            price: 25000,
            image: "nuocdua.jpg",
            category: "drink",
            popularity: 75
        },
        {
            title: "Bún Thịt Nướng",
            location: "76 Nguyễn Văn Cừ, Quận 1, TP HCM",
            city: "hcm",
            price: 45000,
            image: "bunthitnuong.jpg",
            category: "food",
            popularity: 150
        },
        {
            title: "Sữa Chua Nếp Cẩm",
            location: "25 Đinh Tiên Hoàng, Quận Hoàn Kiếm, Hà Nội",
            city: "hn",
            price: 25000,
            image: "suachuancam.jpg",
            category: "food",
            popularity: 80
        },
        {
            title: "Nem Lụi Huế",
            location: "89 Nguyễn Đình Chiểu, Huế",
            city: "hue",
            price: 35000,
            image: "nemluihue.jpg",
            category: "food",
            popularity: 100
        },
        {
            title: "Nước Dừa Lọc",
            location: "10 Nguyễn Huệ, Quận 1, TP HCM",
            city: "hcm",
            price: 20000,
            image: "nuocdualoc.jpg",
            category: "drink",
            popularity: 70
        },
        {
            title: "Bún Mắm Thanh Hóa",
            location: "34 Đường Trần Hưng Đạo, Thanh Hóa",
            city: "hn",
            price: 40000,
            image: "bunmam.jpg",
            category: "food",
            popularity: 120
        },
        {
            title: "Chè Thái",
            location: "50 Nguyễn Huệ, Quận 1, TP HCM",
            city: "hcm",
            price: 30000,
            image: "chethai.jpg",
            category: "drink",
            popularity: 90
        },
        {
            title: "Bánh Canh Cua",
            location: "120 Lý Tự Trọng, Quận 1, TP HCM",
            city: "hcm",
            price: 55000,
            image: "banhcanhcua.jpg",
            category: "food",
            popularity: 140
        },
        {
            title: "Nước Dừa Mát",
            location: "76 Lê Lợi, Quận 1, TP HCM",
            city: "hcm",
            price: 15000,
            image: "nuocduamat.jpg",
            category: "drink",
            popularity: 60
        },
        {
            title: "Gỏi Cuốn",
            location: "90 Nguyễn Cao, Quận 1, TP HCM",
            city: "hcm",
            price: 25000,
            image: "goicuon.jpg",
            category: "food",
            popularity: 80
        },
        {
            title: "Bún Thang",
            location: "67 Hàng Bạc, Quận Hoàn Kiếm, Hà Nội",
            city: "hn",
            price: 35000,
            image: "bunthang.jpg",
            category: "food",
            popularity: 100
        },
        {
            title: "Trà Sữa Thái Xanh",
            location: "21 Trần Hưng Đạo, Quận 1, TP HCM",
            city: "hcm",
            price: 40000,
            image: "trasuathai.jpg",
            category: "drink",
            popularity: 110
        },
        {
            title: "Bánh Xèo",
            location: "52 Nguyễn Du, Quận 1, TP HCM",
            city: "hcm",
            price: 30000,
            image: "banhxeo.jpg",
            category: "food",
            popularity: 90
        },
        {
            title: "Cháo Long",
            location: "78 Nguyễn Đình Chiểu, Quận 1, TP HCM",
            city: "hcm",
            price: 25000,
            image: "chaolong.jpg",
            category: "food",
            popularity: 80
        },
        {
            title: "Trà Đào",
            location: "63 Trần Hưng Đạo, Quận 1, TP HCM",
            city: "hcm",
            price: 25000,
            image: "tradao.jpg",
            category: "drink",
            popularity: 70
        },
        {
            title: "Bánh Gối",
            location: "88 Đồng Khởi, Quận 1, TP HCM",
            city: "hcm",
            price: 20000,
            image: "banhgoidongkhoi.jpg",
            category: "food",
            popularity: 75
        },
        {
            title: "Sinh Tố Bơ Chuối",
            location: "36 Nguyễn Thị Minh Khai, Quận 1, TP HCM",
            city: "hcm",
            price: 30000,
            image: "sinhtobochuoi.jpg",
            category: "drink",
            popularity: 85
        },
        {
            title: "Bún Chả Hà Nội",
            location: "15 Hàng Mành, Quận Hoàn Kiếm, Hà Nội",
            city: "hn",
            price: 40000,
            image: "buncha.jpg",
            category: "food",
            popularity: 110
        },
        {
            title: "Cà Phê Sữa Nóng",
            location: "34 Hàng Bông, Quận Hoàn Kiếm, Hà Nội",
            city: "hn",
            price: 20000,
            image: "cafesuanong.jpg",
            category: "drink",
            popularity: 65
        },
        {
            title: "Nem Nướng Đà Lạt",
            location: "67 Nguyễn Thái Học, Quận 1, TP HCM",
            city: "hcm",
            price: 35000,
            image: "nemnuongdalat.jpg",
            category: "food",
            popularity: 95
        },
        {
            title: "Nước Dừa Cắt",
            location: "43 Nguyễn Huệ, Quận 1, TP HCM",
            city: "hcm",
            price: 25000,
            image: "nuocduacat.jpg",
            category: "drink",
            popularity: 75
        },
        // Add more items as needed
    ];

    // Function to create a card for each item
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
        price.textContent = item.price + ' VNĐ'; // Append VNĐ to indicate currency
        cardFooter.appendChild(price);

        const badge = document.createElement('span');
        badge.classList.add('badge');
        badge.textContent = "Thêm vào giỏ hàng";
        cardFooter.appendChild(badge);

        card.appendChild(cardFooter);

        return card;
    }

    // Function to display items based on filter criteria
    function displayFilteredItems(filteredItems) {
        resultsContainer.innerHTML = '';
        filteredItems.forEach(item => {
            const card = createCard(item);
            resultsContainer.appendChild(card);
        });
    }

    // Event listener for filter button click
    filterButton.addEventListener('click', () => {
        if (filterBox.style.display === 'none') {
            filterBox.style.display = 'block';
        } else {
            filterBox.style.display = 'none';
        }
    });

    // Event listener for filter form submission
    filterForm.addEventListener('submit', (e) => {
        e.preventDefault();

        console.log("Form submitted!"); // Check if form submission is captured

        const area = document.querySelector('.filter-box #area').value; // Changed to use class
        const priceRange = document.querySelector('.filter-box #price-range').value; // Changed to use class
        const type = document.querySelector('.filter-box #type').value; // Changed to use class

        console.log(`Area: ${area}, Price Range: ${priceRange}, Type: ${type}`); // Check values of filters

        // Filter items based on selected criteria
        let filteredItems = items.filter(item => {
            if (area !== 'all' && item.city !== area) {
                return false;
            }
            const priceRangeLimits = priceRange.split('-').map(Number);
            const itemPrice = parseFloat(item.price);
            if (!(itemPrice >= priceRangeLimits[0] && itemPrice <= priceRangeLimits[1])) {
                return false;
            }
            if (type !== 'all' && item.category !== type) {
                return false;
            }
            return true;
        });

        console.log("Filtered items:", filteredItems); // Check filtered items

        // Display filtered items
        displayFilteredItems(filteredItems);
    });

    // Initial display of all items
    displayFilteredItems(items);
});
