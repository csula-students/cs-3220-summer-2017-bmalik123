// single state store
class Store {
    constructor(storage) {
        this.storage = storage; // assuming local storage will be passed in as storage
        // these are the key name you can use in Store
        this.CART_KEY = 'CART';
        this.QUEUE_KEY = 'QUEUE';
        this.FOODS_KEY = 'FOODS';
    }
    get cartItems() {
        return JSON.parse(this.storage.getItem(this.CART_KEY));
    }
    set cartItems(cartItems) {
        this.storage.setItem(this.CART_KEY, JSON.stringify(cartItems));
    }

    get queue() {
        return JSON.parse(this.storage.getItem(this.QUEUE_KEY));
    }

    set queue(queue) {
        this.storage.setItem(this.QUEUE_KEY, JSON.stringify(queue));
    }

    get foods() {
        return JSON.parse(this.storage.getItem(this.FOODS_KEY));
    }

    set foods(foods) {
        this.storage.setItem(this.FOODS_KEY, JSON.stringify(foods));
    }
}

class Cart {
    constructor(root, store) {
        this.root = root;
        this.store = store;
        this.items = this.store.cartItems;
        this.init();
        this.order_placed = false;
    }
    init() {

        this.render();
       
    }
    destroy() {
     
        let removeButtons = document.querySelectorAll('.remove_button');
        for (var i = 0; i < removeButtons.length; i++) {
            let btn = removeButtons[i];
            btn.removeEventListener('click', () => {
                let item = this.items[parseInt(btn.dataset.index)];
                this.removeItem(item);
            });
        }
        let removeAllButton = document.querySelector('.remove_all_button');
        removeAllButton.removeEventListener('click', () => {
             this.removeAllItems();
        });
        let confirmOrderButton = document.querySelector('.confirm_order_button');
        confirmOrderButton.removeEventListener('click', () => {
            this.placeOrder();
            confirm("Your order is placed!");
        });
    }
    removeItem(item) {
        if (this.items != null) {
            var updated_list = [];
            var to_compare = item[0];
            for (var i = 0; i < this.items.length; i++) {
                if (this.items[i][0] != to_compare) {
                    updated_list.push(this.items[i]);
                }
            }
            this.store.cartItems = updated_list;
            this.items = updated_list;
        }
        this.render();
    }
    removeAllItems() {
        this.store.cartItems = [];
        this.items = [];
        this.render();
    }
    placeOrder() {
        if (this.items !== null) {
            var queueItems = [];
            if (this.store.queue !== null) {
                for (var i = 0; i < this.store.queue.length; i++) {
                    queueItems.push(this.store.queue[i]);
                }
            }
            for (var i = 0; i < this.items.length; i++) {
                queueItems.push([this.items[i][0], this.items[i][1], this.items[i][2], Number(this.items[i][3])]);
            }
            this.store.queue = queueItems;
            this.order_placed = true;
            this.removeAllItems();
        }
    }
    render() {
        let tbody = this.root.querySelector('tbody');

        tbody.innerHTML = ``;
        if (this.items === null) {
            this.items = [];
        } else if ((this.items.length == 0) && (this.order_placed)) {
            tbody.innerHTML +=
                `<tr class="cart-table">
                <td colspan="4" class="cart-table">
                    <p class="title">Order Placed Successfully!</p>
                    <h3>Track your Order<a href="index.html" class="link">Here</a></h3>
                </td>
            </tr>`;
            this.order_placed = false;
            return;
        } else if (this.items.length == 0) {
            tbody.innerHTML +=
                `<tr class="cart-table">
                
                    <h2>Nothing is there in the Cart</h2>
                   <h3>Go back to Menu<a href="menu.html" class="link">Here</a></h3>
               
            </tr>`;
            return;
        }
        for (var i = 0; i < this.items.length; i++) {
            var item_name = this.items[i][0];
            var image_name = this.items[i][1];
            var item_price = this.items[i][2];
            var item_quantity = Number(this.items[i][3]);
            tbody.innerHTML +=
                `<tr class="cart-table">
                    <td class="cart-table">
                        <h4>${item_name}</h4>
                        <img src=${image_name} class="small">
                    </td>
                    <td class="cart-table">
                    <h4>${item_price}</h4>
                    </td>
                    <td class="cart-table">
                        <h4>${item_quantity}</h4>
                    </td>

                    <td class="cart-table">
                        <button class="remove_button" data-name=${item_name} data-index=${i}>Remove</button>
                    </td>
                </tr>`;
        }
        tbody.innerHTML +=
            `
                    <button class="remove_all_button" display="inline">Clear History</button>
                    <br><br>
                    <button class="confirm_order_button" float="right">Submit</button>
               `;

        let removeButtons = document.querySelectorAll('.remove_button');
        for (var i = 0; i < removeButtons.length; i++) {
            let btn = removeButtons[i];
            btn.addEventListener('click', () => {
                let item = this.items[parseInt(btn.dataset.index)];
                this.removeItem(item);
            });
        }

        let removeAllButton = document.querySelector('.remove_all_button');
        removeAllButton.addEventListener('click', () => {
            this.removeAllItems();
        });

        let confirmOrderButton = document.querySelector('.confirm_order_button');
        confirmOrderButton.addEventListener('click', () => {
            this.placeOrder();
        });
    }
}
class CheckoutButton {
    constructor(root, store) {
        this.root = root;
        this.store = store;
        this.onClick = () => this.addItemToCart();
        this.init();
    }

    init() {
        this.root.addEventListener("click", this.onClick);
    }

    destroy() {
        this.root.removeEventListener("click", this.onClick);
    }

    addItemToCart() {
        // hint: you can use `dataset` to access data attributes
        // See passing data from HTML to JavaScript from course note
        let cartItems = this.store.cartItems || [];
        // TODO: replace with actual item
        var new_cart_item = true;
        for (var i = 0; i < cartItems.length; i++) {
            // go through each item name in cartItems. If they match, increase the quantity of existing item in cartItems by 1. Otherwise, add the item as a new entry in cartItems.
            var existing_cart_item_name = cartItems[i][0]
            if (this.root.dataset.name === existing_cart_item_name) {
                var amount_to_add = Number(this.root.dataset.quantity);
                cartItems[i][3] += amount_to_add;
                new_cart_item = false;
            }
        }
        if (new_cart_item) {
            cartItems.push([this.root.dataset.name, this.root.dataset.image, this.root.dataset.price, Number(this.root.dataset.quantity)]);
        }
        this.store.cartItems = cartItems;
    }
}
// continue from Lab2 with Store, CheckoutButton, Cart components
class Inventory {
    constructor(root, store) {
        this.root = root;
        this.store = store;
        this.init();
    }

    init () {
        this.render();
        // TODO: attach event listeners like click to remove items after rendering
    }

    destroy () {
        // TODO: remove event listeners added from the init above
    }

    removeItem (itemName) {
        // TODO: function to remove item given item name from store
    }

    render () {
        // using innerHTML to render inventory listing
    }
}

class Menu {
    constructor(root, store) {
        this.root = root;
        this.store = store;
        this.init();
    }

    init () {
        this.render();
    }

    render () {
        // TODO: render a list of food menu from store using innerHTML
    }
}

class CreateFood {
    constructor(root, store) {
        this.root = root; // root should be the container of the form itself
        this.store = store;
        this.init();
    }

    init () {
        // attach click event to create button
    }

    createFood () {
        // will need to do querySelector to find out every single form element
        // to get their values before creating a new food
        // after creating a new food item, add it to store
    }
}


class StatusTable {
    // take dom element into JavaScript class to attachment events
    constructor(root, store) {
        this.root = root;
        this.store = store;
        this.init();
    }

    init() {
        // attach click event listener to table header row on each column
        this.render();
        // not doing sorting as of now
    }

    destroy() {
        // remove all the events attached from init
        let HistoryClearButton = document.querySelector('.clear_history_button');
        HistoryClearButton.removeEventListener('click', () => {
            this.clearHistory();
        });
    }

    sort(columnName) {
       
        this.render();
    }

    clearHistory() {
        this.store.queue = [];
        this.render();
    }
    

    // render rows of items under table using root.innerHTML
    render() {
        let tbody = this.root.querySelector('tbody');
        // using innerHTML to render a list of table row item under tbody
        tbody.innerHTML = ``;
        if (this.store.queue === null) {
            this.store.queue = [];
        }
        if (this.store.queue.length == 0) {
            tbody.innerHTML +=
                `<tr class="order_status_table">
                <td  colspan="4" class="order_status_table" >
                    <p>You have purchased nothing! <br>
                    -> Click to choose Menu <a href="menu.html" class="link">here</a>.</p>
                    <img src="http://is3.mzstatic.com/image/thumb/Purple122/v4/d4/b5/e4/d4b5e4af-708a-2099-e842-a3094fe114ac/source/1200x630bb.jpg" width=150px height=120px>
                </td>
            </tr>`;
            return;
        }
        for (var i = 0; i < this.store.queue.length; i++) {
            // for each item in local storage's QUEUE, create a row with a cell for the item name and image, a cell for quantity, and a cell for status (In Progress for now).
            var item_name = this.store.queue[i][0];
            var image_name = this.store.queue[i][1];
            var item_quantity = Number(this.store.queue[i][3]);
            var current_date = new Date();
            tbody.innerHTML +=
                `<tr class="order_status_table">
                <td class="order_status_table">
            <h4>${current_date}</h4>
                    <td class="order_status_table">
                        <h4>${item_name}</h4>
                        <img src=${image_name} class="small">
                    </td>
                    <td class="order_status_table">
                        <h4>${item_quantity}</h4>
                    </td>
                    <td class="order_status_table">
                        <h4>In Progress!</h4>
                    </td>
                </tr>`;
        }
        tbody.innerHTML +=
            `<tr class="cart-table">
                <td class="cart-table" colspan="4">
                    <button class="clear_history_button">Clear the History</button>
                </td>
            </tr>`;

        let HistoryClearButton = document.querySelector('.clear_history_button');
        HistoryClearButton.addEventListener('click', () => {
            this.clearHistory();
        });
    }
}
document.addEventListener('DOMContentLoaded', () => {
    let statusTable = document.querySelector('.order_status_table');
    let cart = document.querySelector('.cart-table');
    let CheckoutBtn = document.querySelectorAll('.checkout-button');

    let store = new Store(window.localStorage);

    if (statusTable) {
        new StatusTable(statusTable, store);
    }
    if (cart) {
        new Cart(cart, store);
    }
    if (CheckoutBtn && CheckoutBtn.length) {
        for (var i = 0; i < CheckoutBtn.length; i++) {
            new CheckoutButton(CheckoutBtn[i], store);
        }
    }
});