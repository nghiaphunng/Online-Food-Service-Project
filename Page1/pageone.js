const resultsContainer = document.querySelector('.results');
const locationSelect = document.getElementById('location-select');
const categorySelect = document.getElementById('category-select');
const sortSelect = document.getElementById('sort-select');
const searchInput = document.getElementById('search-input');

const items = [
    {
        title: "Cơm Gà Xối Mỡ 142",
        location: "142 Đa Kao, P. Tân Định, TP HCM",
        city: "hcm",
        discount: "TECHCOMBANK giảm 50K",
        image: "comga.jpg",
        category: "food",
        popularity: 120,
        price: 50000
    },
    {
        title: "Bánh Tráng Chấm Cô Gánh",
        location: "Lô D01 Chung Cư A4, P. Tân Định, TP HCM",
        city: "hcm",
        discount: "Flash Sale",
        image: "banhtrang.jpg",
        category: "food",
        popularity: 85,
        price: 30000
    },
    {
        title: "Bánh Mì Huỳnh Hoa",
        location: "26 Lê Thị Riêng, P. Bến Thành, Quận 1",
        city: "hcm",
        discount: "Giảm món",
        image: "banhmi.jpg",
        category: "food",
        popularity: 150,
        price: 40000
    },
    {
        title: "Phở Bát Đàn",
        location: "49 Bát Đàn, P. Cửa Đông, Quận Hoàn Kiếm",
        city: "hn",
        discount: "Giảm 20%",
        image: "pho.jpg",
        category: "food",
        popularity: 200,
        price: 60000
    },
    // Add more items here
];

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
    
    const discount = document.createElement('span');
    discount.textContent = item.discount;
    cardFooter.appendChild(discount);
    
    const badge = document.createElement('span');
    badge.classList.add('badge');
    badge.textContent = "Yêu thích";
    cardFooter.appendChild(badge);
    
    card.appendChild(cardFooter);
    
    return card;
}

function renderItems(filteredItems) {
    resultsContainer.innerHTML = ''; // Clear previous results
    filteredItems.forEach(item => {
        const card = createCard(item);
        resultsContainer.appendChild(card);
    });
}

function filterItems(city, category, searchQuery, sortOption) {
    let filteredItems = items;
    
    if (city !== 'all') {
        filteredItems = filteredItems.filter(item => item.city === city);
    }

    if (category !== 'all') {
        filteredItems = filteredItems.filter(item => item.category === category);
    }

    if (searchQuery) {
        filteredItems = filteredItems.filter(item => item.title.toLowerCase().includes(searchQuery.toLowerCase()));
    }

    if (sortOption === 'popularity') {
        filteredItems.sort((a, b) => b.popularity - a.popularity);
    } else if (sortOption === 'price-asc') {
        filteredItems.sort((a, b) => a.price - b.price);
    } else if (sortOption === 'price-desc') {
        filteredItems.sort((a, b) => b.price - a.price);
    }

    return filteredItems;
}

locationSelect.addEventListener('change', function() {
    const selectedCity = locationSelect.value;
    const selectedCategory = categorySelect.value;
    const searchQuery = searchInput.value;
    const sortOption = sortSelect.value;
    const filteredItems = filterItems(selectedCity, selectedCategory, searchQuery, sortOption);
    renderItems(filteredItems);
});

categorySelect.addEventListener('change', function() {
    const selectedCity = locationSelect.value;
    const selectedCategory = categorySelect.value;
    const searchQuery = searchInput.value;
    const sortOption = sortSelect.value;
    const filteredItems = filterItems(selectedCity, selectedCategory, searchQuery, sortOption);
    renderItems(filteredItems);
});

searchInput.addEventListener('input', function() {
    const selectedCity = locationSelect.value;
    const selectedCategory = categorySelect.value;
    const searchQuery = searchInput.value;
    const sortOption = sortSelect.value;
    const filteredItems = filterItems(selectedCity, selectedCategory, searchQuery, sortOption);
    renderItems(filteredItems);
});

sortSelect.addEventListener('change', function() {
    const selectedCity = locationSelect.value;
    const selectedCategory = categorySelect.value;
    const searchQuery = searchInput.value;
    const sortOption = sortSelect.value;
    const filteredItems = filterItems(selectedCity, selectedCategory, searchQuery, sortOption);
    renderItems(filteredItems);
});

// Initial render
renderItems(items);
