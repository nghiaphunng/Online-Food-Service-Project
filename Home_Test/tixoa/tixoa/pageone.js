// script.js
document.addEventListener('DOMContentLoaded', () => {
    const filterButton = document.querySelector('.filter-button');
    const filterBox = document.querySelector('.filter-box');

    filterButton.addEventListener('click', () => {
        if (filterBox.style.display === 'block') {
            filterBox.style.display = 'none';
        } else {
            filterBox.style.display = 'block';
        }
    });

    // Optional: Click outside the filter box to close it
    document.addEventListener('click', (event) => {
        if (!filterButton.contains(event.target) && !filterBox.contains(event.target)) {
            filterBox.style.display = 'none';
        }
    });
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
})
});