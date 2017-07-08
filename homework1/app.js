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





//Homework1

class Inventory {
    constructor(root, store) {
        this.root = root;
        this.store = store;
        this.init();
    }

    init() {
        this.render();
        // TODO: attach event listeners like click to remove items after rendering
        /* Event listeners added in render() function */
    }

    destroy() {
        // TODO: remove event listeners added from the init above
        let inventoryData = document.querySelector('.inventory_add_default_button');
        if (inventoryData) {
            inventoryData.removeEventListener("click", () => {
                this.addDefaultItems();
            });
        }

        let removeInventoryButtons = document.querySelectorAll('.remove_inventory_button');
        for (var i = 0; i < removeInventoryButtons.length; i++) {
            let btn = removeInventoryButtons[i];
            btn.removeEventListener('click', () => {
                let item = this.store.foods[parseInt(btn.dataset.index)];
                this.removeItem(item);
            });
        }
    }

    removeItem(itemName) {
        // TODO: function to remove item given item name from store
        if (this.store.foods !== null) {
            var updated_list = [];
            var to_compare = itemName.name;
            for (var i = 0; i < this.store.foods.length; i++) {
                if (this.store.foods[i].name !== to_compare) {
                    updated_list.push(this.store.foods[i]);
                }
            }
            this.store.foods = updated_list;
        }
        this.render();
    }

    addDefaultItems() {
        let storeFoods = this.store.foods || [];

        var pepperoni_pizza = { name: "Pepperoni Pizza", image: "https://3eyesdotme.files.wordpress.com/2012/12/pepperonipizza.png?w=300", description: "Our robust, tasty Pepperoni Pizza starts with our savory sauce of vine-ripened tomatoes, extra virgin olive oil,garlic, and spices spread over a baked-to-perfection thin and crispy gluten free crust. We top it off with premium mozzarella, a sprinkling of fresh-frozen basil and layer on uncured pepperoni for a hearty and satisfying indulgence.", price: "8.99$ + included taxes" };
        var thai_pizza = { name: "Thai Pizza", image: "https://static1.squarespace.com/static/55282462e4b008238c306c6e/t/5706b5141d07c09d2c5a2a8f/1463346750494/ultimate+thai+pizza+recipe?format=300w", description: "Serve this Thai twist on pizza as is or with optional toppings, such as thinly sliced basil, cilantro, chopped peanuts and/or crushed red pepper on the side.", price: "12$ + included taxes" };
        var margherita_pizza = { name: "Margherita Pizza", image: "http://pizzeriavecchia.com/wp-content/uploads/000-margherita-pizza-300x200.jpg", description: "Cherry tomatoes, fresh tomato, basil drizzle & mozzarella .", price: "14$ + included taxes" };
        var bbq_chicken_pizza = { name: "BBQ Chicken Pizza ", image: "http://www.foxspizza.com/wp-content/uploads/2013/04/barbecue-chicken-pizza.jpg", description: " BBQ Chicken pizza is like summer in a box. To create our  BBQ Chicken pizza, we use only premium grilled chicken, crunchy fresh onions, and not one, not two, but three cheeses: mozzarella, provolone and cheddar on our hand-tossed crust. The result is a traditional summer cookout treat in one perfect pizza. Whenever you’re thinking BBQ, don’t be afraid to think pizza place. In fact, our  BBQ Chicken pizza is a great way to beat back winter blues when you’re longing for the carefree living of warmer months.", price: "15$ + included taxes" };

        var updated_item_list = [pepperoni_pizza, thai_pizza, margherita_pizza, bbq_chicken_pizza];

        var newFoodItem = true;
        for (var i = 0; i < storeFoods.length; i++) {
            var newFoodItem = true;
            for (var j = 0; j < updated_item_list.length; j++) {
                if (storeFoods[i].name === updated_item_list[j].name) {
                    newFoodItem = false;
                    break;
                }
            }
            if (newFoodItem) {
                updated_item_list.push(storeFoods[i]);
            }
        }
        this.store.foods = updated_item_list;
        this.render();
    }

    render() {
        // using innerHTML to render inventory listing
        let tbody = this.root.querySelector('tbody');
        // using innerHTML to render a list of table row item under tbody
        tbody.innerHTML = ``;

        // display message to add more liquor if there is no new recipes
        if (this.store.foods === null) {
            this.store.foods = [];
        }

        // display all of the user-submitted brews
        for (var i = 0; i < this.store.foods.length; i++) {
            // for each item in local storage's FOODS, create a row with a cell for the item name and image, and one for description.
            var FoodItem_name = this.store.foods[i].name;
            var FoodItem_image = this.store.foods[i].image;
            var FoodItem_description = this.store.foods[i].description;
            var FoodItem_price = this.store.foods[i].price;

            tbody.innerHTML +=
                `<tr>
                    <td><h3>${FoodItem_name}</h3>
                    </td>
                    <td><img  src=${FoodItem_image}></td>
                    <td><p>${FoodItem_description}</p>
                    </td>
                    <td><h3>${FoodItem_price}</h3>
                    <button class="remove_inventory_button" data-index=${i}>Remove!</button>
                     </td>
                  </tr>`;
        }


        let inventoryAddDefaultButton = document.querySelector('.inventory_add_default_button');
        if (inventoryAddDefaultButton) {
            inventoryAddDefaultButton.addEventListener("click", () => {
                this.addDefaultItems();
            });
        }

        let removeInventoryButtons = document.querySelectorAll('.remove_inventory_button');
        for (var i = 0; i < removeInventoryButtons.length; i++) {
            let btn = removeInventoryButtons[i];
            btn.addEventListener('click', () => {
                let item = this.store.foods[parseInt(btn.dataset.index)];
                this.removeItem(item);
            });
        }
    }
}


class CreateFood {
    constructor(root, store) {
        this.root = root; // root should be the container of the form itself
        this.store = store;
        this.init();
    }

    init() {
        // attach click event to create button
        let createfoodItemlist = document.getElementById('add_menu_button');
        if (createfoodItemlist) {
            createfoodItemlist.addEventListener("click", () => {
                this.createFood();
            });
        }

    }

    createFood() {
        // will need to do querySelector to find out every single form element
        // to get their values before creating a new food
        // after creating a new food item, add it to store
        let storeFoods = this.store.foods || [];
        var FoodItem_name = document.getElementById('FoodItem_name').value;
        var FoodItem_image = document.getElementById('FoodItem_image').value;
        var FoodItem_description = document.getElementById('FoodItem_description').value;
        var FoodItem_price = document.getElementById('FoodItem_price').value;
        var to_push = { name: FoodItem_name, image: FoodItem_image, description: FoodItem_description, price: FoodItem_price };

        if (window.confirm("Do you Really want to add this Food Item?") == true) {

            // check to make sure submitted food is actually new
            var newFoodItem = true;
            for (var i = 0; i < storeFoods.length; i++) {
                if (to_push.name === storeFoods[i].name) {
                    newFoodItem = false;
                    break;
                }
            }
            if (newFoodItem) {
                storeFoods.push(to_push);
                this.store.foods = storeFoods;
            }
        }
    }
}
class Menu {
    constructor(root, store, cart) {
        this.root = root;
        this.store = store;
        this.cart = cart;
        this.init();
    }

    init() {
        this.render();
    }

    render() {

        let tbody = this.root.querySelector('tbody');

        for (var i = 0; i < this.store.foods.length; i++) {

            var FoodItem_name = this.store.foods[i].name;
            var FoodItem_image = this.store.foods[i].image;
            var FoodItem_description = this.store.foods[i].description;
            var FoodItem_price = this.store.foods[i].price;
            tbody.innerHTML +=
                `<tr >
                    <td>${FoodItem_name}</td>
                    <td><img  src=${FoodItem_image} ></td>
                    <td>${FoodItem_description}</td>
                    <td>$${FoodItem_price}</td>
                     <td>
							<button  class="checkout-button" data-name="${FoodItem_name}" data-image="${FoodItem_image}"  data-description="${FoodItem_description}" data-price="${FoodItem_price}"  data-quantity="1" >
								Add to the Cart
							</button>
                        </td>
                 </tr>`;
        }

        let checkoutButtons = document.querySelectorAll('.checkout-button');
        if (checkoutButtons && checkoutButtons.length) {
            for (var i = 0; i < checkoutButtons.length; i++) {
                new CheckoutButton(checkoutButtons[i], this.store, this.cart);
            }
        }
    }
}
class OrderStatusControlTable {
    constructor(root, store) {
        this.root = root;
        this.store = store;
        this.init();
    }

    init() {
        this.render();
    }

    destroy() {
        // removes EventListeners for updateOrderStatusControlTable buttons
    }

    render() {
        let tbody = this.root.querySelector('tbody');
        tbody.innerHTML = ``;
        if (this.store.queue === null) {
            this.store.queue = [];
        }
        for (var i = 0; i < this.store.queue.length; i++) {
           
            var status_name = Item + " Status";
            var Item = this.store.queue[i][0];
           
            var quantity = this.store.queue[i][1];
            var status = this.store.queue[i][2];

            tbody.innerHTML += `
                <tr class="order_status_control_table_td">
                   
                    <td class="order_status_control_table_td"><h4>${Item}</h4><br>
                   
                    
                    <td class="order_status_control_table_td"><h4>${quantity}</h4></td>
                    
                    <td class="order_status_control_table_td"><h4>${status}</h4></td>
                    
                    <td class="order_status_control_table_td"><input type="text" id="${status_name}"><br><br>
                    <button class="update_status_button" data-name="${Item}">Update Status!</button></td>
                </tr>
            `;
        }

        let updateStatusButtons = this.root.querySelectorAll('.update_status_button');

        if (updateStatusButtons) {
            for (var i = 0; i < updateStatusButtons.length; i++) {
                let btn = updateStatusButtons[i];
                btn.addEventListener("click", () => {
                    this.updateStatus(btn.dataset.name);
                });
            }
        }
    }

    updateStatus(name) {
        var status_name = Item + " Status";
        var inputToTrack = document.getElementById(status_name);
        if (this.store.queue !== null) {
            var newQueue = [];

            for (var i = 0; i < this.store.queue.length; i++) {
                // looks like cannot mutate value of this.store.queue directly, so will need to create a new array of queue items and then set this.store.queue equal to the new queue
                let queueItem = this.store.queue[i];
                if (name == queueItem[0]) {
                    queueItem[3] = inputToTrack.value;
                }
                newQueue.push(queueItem);
            }
            this.store.queue = newQueue;
        }
        
        this.render();
    }

}



// DOMContentLoaded event will allow us to run the following function when
// everything is ready. Think of the following code will only be executed by
// the end of document  
document.addEventListener('DOMContentLoaded', () => {
    let statusTable = document.querySelector('.order_status_table');
    let cart = document.querySelector('.cart-table');
    let CheckoutBtn = document.querySelectorAll('.checkout-button');
    let createFood = document.querySelector('#create_menu');
    let inventory = document.querySelector('#inventory_table');
    let menu = document.querySelector('#menu_table');
     let orderStatusControlTable = document.querySelector('#order_status_control_table');
     let adminStatusTable = document.querySelector('.order_status_table_admin');
    let store = new Store(window.localStorage);

    if (statusTable) {
        new StatusTable(statusTable, store);
    }
    if (cart) {
        var newCart = new Cart(cart, store);
    }
    if (CheckoutBtn && CheckoutBtn.length) {
        for (var i = 0; i < CheckoutBtn.length; i++) {
            new CheckoutButton(CheckoutBtn[i], store, newCart);
        }
    }

    if (createFood) {
        new CreateFood(createFood, store);
    }
    if (inventory) {
        new Inventory(inventory, store);
    }
    if (menu) {
        new Menu(menu, store, newCart);
    }
    if (orderStatusControlTable) {
        new OrderStatusControlTable(orderStatusControlTable, store);
    }
     if (adminStatusTable) {
        new AdminStatusTable(adminStatusTable, store);
    }

});