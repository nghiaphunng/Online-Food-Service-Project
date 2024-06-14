document.getElementById('cart-button').addEventListener('click', function() {
    document.getElementById('cart').style.display = 'block';
    document.getElementById('now-list-restaurant').style.display = 'none';
    document.getElementById('main').style.display = 'none';
});

document.getElementById('close-cart-button').addEventListener('click', function() {
    document.getElementById('cart').style.display = 'none';
    document.getElementById('now-list-restaurant').style.display = 'block';
    document.getElementById('main').style.display = 'block';
});

const btn = document.querySelectorAll('.add-button');
btn.forEach(function(button, index) {
    button.addEventListener("click", function(event) {
        var restaurantItem = event.target.closest('.item-retaurant');
        var productImg = restaurantItem.querySelector('.cover').src;
        var productName = restaurantItem.querySelector('.name-res').innerText;
        var productPrice = restaurantItem.querySelector('.price').innerText;
        var ResName = restaurantItem.querySelector('.nameRes').innerText;
        addCart(productImg, productName, productPrice, ResName);
        saveCartToLocalStorage();
    });
});

function addCart(productImg, productName, productPrice, ResName) {
    var cart = document.querySelector('#cart');
    var restaurantSection = cart.querySelector(`[data-name="${ResName}"]`);

    if (!restaurantSection) {
        restaurantSection = document.createElement('div');
        restaurantSection.setAttribute('data-name', ResName);
        restaurantSection.innerHTML = `
            <h3>${ResName}</h3>
            <table>
                <thead>
                    <tr>
                        <th>Tên sản phẩm</th>
                        <th>Hình ảnh</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Tổng</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
            <div class="restaurant-total">
                <span>Tổng tiền: </span>
                <span class="total-price">0d</span>
                <button class="pay-button">Thanh toán</button>
            </div>`;
        cart.appendChild(restaurantSection);
    }

    var newProduct = document.createElement('tr');
    newProduct.innerHTML = `
        <tr>
            <td>${productName}</td>
            <td><img src="${productImg}" alt="${productName}" width="50"></td>
            <td>${productPrice}</td>
            <td><input type="number" value="1" min="1" class="quantity"></td>
            <td class="total">${productPrice}</td>
            <td><button class="remove-button">Xóa</button></td>
        </tr>`;
    restaurantSection.querySelector('tbody').appendChild(newProduct);

    // Update total price for the product
    updateTotalPrice(newProduct);

    // Add event listener for quantity change
    newProduct.querySelector('.quantity').addEventListener('change', function(event) {
        updateTotalPrice(newProduct);
        updateRestaurantTotal(restaurantSection);
        saveCartToLocalStorage();
    });

    // Add event listener for remove button
    newProduct.querySelector('.remove-button').addEventListener('click', function(event) {
        newProduct.remove();
        updateRestaurantTotal(restaurantSection);
        saveCartToLocalStorage();
        // If no more products in restaurant section, remove the restaurant section
        if (restaurantSection.querySelector('tbody').children.length === 0) {
            restaurantSection.remove();
        }
    });

    // Update the restaurant total after adding a new product
    updateRestaurantTotal(restaurantSection);
    saveCartToLocalStorage();
}

function updateTotalPrice(productRow) {
    var quantity = productRow.querySelector('.quantity').value;
    var price = parseFloat(productRow.children[2].innerText.replace('??.000d', '').replace(',', ''));
    var total = quantity * price;
    productRow.querySelector('.total').innerText = `${total.toFixed(3)}d`;
}

function updateRestaurantTotal(restaurantSection) {
    var totalPrice = 0;
    var productRows = restaurantSection.querySelectorAll('tbody tr');
    productRows.forEach(function(row) {
        var productTotal = parseFloat(row.querySelector('.total').innerText.replace('d', '').replace(',', ''));
        totalPrice += productTotal;
    });
    restaurantSection.querySelector('.total-price').innerText = `${totalPrice.toFixed(3)}d`;
}

function saveCartToLocalStorage() {
    var cart = document.querySelector('#cart');
    var cartContent = [];
    var restaurantSections = cart.querySelectorAll('[data-name]');
    restaurantSections.forEach(function(section) {
        var restaurantName = section.getAttribute('data-name');
        var products = [];
        var productRows = section.querySelectorAll('tbody tr');
        productRows.forEach(function(row) {
            var productName = row.children[0].innerText;
            var productImg = row.children[1].querySelector('img').src;
            var productPrice = row.children[2].innerText;
            var quantity = row.querySelector('.quantity').value;
            products.push({ productName, productImg, productPrice, quantity });
        });
        cartContent.push({ restaurantName, products });
    });
    localStorage.setItem('cartContent', JSON.stringify(cartContent));
}

function loadCartFromLocalStorage() {
    var cartContent = localStorage.getItem('cartContent');
    if (cartContent) {
        cartContent = JSON.parse(cartContent);
        cartContent.forEach(function(section) {
            section.products.forEach(function(product) {
                addCart(product.productImg, product.productName, product.productPrice, section.restaurantName);
                var restaurantSection = document.querySelector(`[data-name="${section.restaurantName}"]`);
                var productRows = restaurantSection.querySelectorAll('tbody tr');
                productRows.forEach(function(row) {
                    if (row.children[0].innerText === product.productName) {
                        row.querySelector('.quantity').value = product.quantity;
                        updateTotalPrice(row);
                        updateRestaurantTotal(restaurantSection);
                    }
                });
            });
        });
    }
}

// Load the cart content from localStorage when the page loads
document.addEventListener('DOMContentLoaded', loadCartFromLocalStorage);
