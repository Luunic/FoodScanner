# How to Interact with the Backend

## Setup the Controller
To start interacting with the Backend you need to initiate a Controller Object.

`private var controller = Controller(productHistory = ProductHistory(storage = LocalStorage(context = applicationContext)))`

Note: This code will probably be different in future when.

## The Controller
The Controller Class has following methods you can use: `getProductFromBarcode()` `getProductHistory()` `clearProductHistory()`

Note: More Functions will be added in the Future.

In the Following you will get a closer look on each of these functions.
### fun getProductFormBarcode(barcode: String): Product?
This function takes the barcodeString as a parameter and returns a Product Object. More on that later.
### fun getProductHistory(): List<Product>
This function takes noting as a parameter and returns a List of Product Object, which it gets from the Local history.
### fun clearProductHistory()
This function clears the complete Product History.

## Product
The getProductFromBarcode() function returns a Product Object. In the Following you will learn how to get the data from that product.

Product has the following attributes: 

`name: String?` `brands: String?` `allergens: List<String>?` `labels: List<String>?` `categories: List<String>?` `nutriScore: String?` `nutriments: Nutriments?` `ingredients: List<Ingredient>?` `imageUrl: String?` `code: String?` `date: String?`

You can get these values by calling the equivalent get Function: 

`getName()` `getBrands()` `getAllergens()` `getLabels()` `getCategories()` `getNutriScore()` `getNutriments()` `getIngredients()` `getImageUrl()` `getCode()` `getDate()`

All of the values could be null because of missing data from the Api so pls consider this.

### Nutriments
If you call the getNutriments() function you get a Nutriments Object. 

Nutriments has the following attributes(all per 100g): 

`energyKcals: Double?` `fat: Double?` `saturatedFat: Double?` `carbohydrates: Double?` `sugars: Double?` `proteins: Double?` `salt: Double?` `fiber: Double?`

You can get these values by calling the equivalent get Function: 

`getEnergyKcals()` `getFat()` `getSaturatedFat()` `getCarbohydrates()` `getSugars()` `getProteins()` `getSalt()` `getFiber()`

All of the values could be null because of missing data from the Api so pls consider this.

### Ingredient
If you call the getIngredients() function you get a List of Ingriedent Objects.

Ingredient has the following attributes:

`name: String?` `percent: Double?` `vegan: String?` `vegetarian: String?` `subIngredients: List<Ingredient>`

You can get these values by calling the equivalent get Function:

`getName()` `getPercent()` `getVegan()` `getVegetarian()` `getSubIngredients()`

All of the values could be null because of missing data from the Api so pls consider this.

Note: The Ingredient class could be remade in the future.
