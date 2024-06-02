document.addEventListener('DOMContentLoaded', () => {
    const filterButton = document.querySelector('.filter-button');
    const filterBox = document.querySelector('.filter-box');
    const filterForm = document.getElementById('filter-form');
    const resultsContainer = document.querySelector('.results');
    const areaSelect = document.getElementById('area');
    const priceRangeSelect = document.getElementById('price-range');
    const typeSelect = document.getElementById('type');
    const searchInput = document.getElementById('search-input');

    // Function to toggle filter box visibility
    filterButton.addEventListener('click', () => {
        if (filterBox.style.display === 'block') {
            filterBox.style.display = 'none';
        } else {
            filterBox.style.display = 'block';
        }
    });

    // Function to filter items based on selection
    filterForm.addEventListener('submit', (event) => {
        event.preventDefault();
        const areaValue = areaSelect.value;
        const priceRangeValue = priceRangeSelect.value;
        const typeValue = typeSelect.value;
        const searchTerm = searchInput.value.toLowerCase();

        const filteredItems = items.filter(item => {
            const matchesArea = areaValue === 'all' || item.city === areaValue;
            const matchesPriceRange = priceRangeValue === 'all' || checkPriceRange(item.price, priceRangeValue);
            const matchesType = typeValue === 'all' || item.category === typeValue;
            const matchesSearch = item.title.toLowerCase().includes(searchTerm);

            return matchesArea && matchesPriceRange && matchesType && matchesSearch;
        });

        displayItems(filteredItems);
    });

    // Function to check if item price falls within selected range
    function checkPriceRange(price, range) {
        const [min, max] = range.split('-').map(Number);
        return price >= min && price <= max;
    }

    // Function to display items in the results container
    function displayItems(items) {
        resultsContainer.innerHTML = '';
        items.forEach(item => {
            const card = createCard(item);
            resultsContainer.appendChild(card);
        });
    }

    // Initial display of all items
    displayItems(items);

    // Optional: Click outside the filter box to close it
    document.addEventListener('click', (event) => {
        if (!filterButton.contains(event.target) && !filterBox.contains(event.target)) {
            filterBox.style.display = 'none';
        }
    });
});
