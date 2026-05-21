# How to Interact with the Backend

## Architecture Overview

The backend follows a layered architecture:

```
UI Layer  ──►  FoodScannerViewModel  ──►  Controller  ──►  Data Sources
```

The recommended way to interact with the backend from the UI is through the `FoodScannerViewModel`. It manages state reactively using Kotlin Flows and handles coroutines for you. Direct use of the `Controller` is only necessary if you are working outside of a ViewModel context.

---

## FoodScannerViewModel (Recommended)

### Setup

Instantiate the ViewModel with a `Controller`:

```kotlin
val viewModel = FoodScannerViewModel(
    controller = Controller(
        productHistory = ProductHistory(
            storage = LocalStorage(context = applicationContext)
        )
    )
)
```

### State

The ViewModel exposes two read-only state flows you can collect in your UI:

| Property | Type | Description |
|---|---|---|
| `currentProduct` | `StateFlow<Product?>` | The most recently scanned product, or `null` |
| `historyState` | `StateFlow<List<Product>>` | The current product history |

### Methods

#### `fun scanBarcode(barcode: String)`

Triggers a barcode lookup asynchronously. On completion, `currentProduct` and `historyState` are updated automatically.

```kotlin
viewModel.scanBarcode("4000417025005")
```

#### `fun clearHistory()`

Clears the complete product history. `historyState` is updated automatically.

```kotlin
viewModel.clearHistory()
```

---

## Controller (Direct Use)

If you need to use the backend outside of a ViewModel context, you can instantiate and use the `Controller` directly.

### Setup

```kotlin
private var controller = Controller(
    productHistory = ProductHistory(
        storage = LocalStorage(context = applicationContext)
    )
)
```

> **Note:** This setup may change in future versions.

### Methods

#### `fun getProductFromBarcode(barcode: String): Product?`

Takes a barcode string as a parameter and returns a `Product` object, or `null` if not found.

#### `fun getProductHistory(): List<Product>`

Returns a list of previously scanned `Product` objects from local history.

#### `fun clearProductHistory()`

Clears the complete product history.

> **Note:** More methods will be added in the future.

---

## Data Classes

### Product

`getProductFromBarcode()` and `getProductHistory()` return `Product` objects. All values may be `null` due to missing data from the API.

| Getter | Return Type |
|---|---|
| `getName()` | `String?` |
| `getBrands()` | `String?` |
| `getAllergens()` | `List<String>?` |
| `getLabels()` | `List<String>?` |
| `getCategories()` | `List<String>?` |
| `getNutriScore()` | `String?` |
| `getNutriments()` | `Nutriments?` |
| `getIngredients()` | `List<Ingredient>?` |
| `getImageUrl()` | `String?` |
| `getCode()` | `String?` |
| `getDate()` | `String?` |

### Nutriments

Returned by `getNutriments()`. All values are per 100g and may be `null`.

| Getter | Return Type |
|---|---|
| `getEnergyKcals()` | `Double?` |
| `getFat()` | `Double?` |
| `getSaturatedFat()` | `Double?` |
| `getCarbohydrates()` | `Double?` |
| `getSugars()` | `Double?` |
| `getProteins()` | `Double?` |
| `getSalt()` | `Double?` |
| `getFiber()` | `Double?` |

### Ingredient

`getIngredients()` returns a `List<Ingredient>`. All values may be `null`.

| Getter | Return Type |
|---|---|
| `getName()` | `String?` |
| `getPercent()` | `Double?` |
| `getVegan()` | `String?` |
| `getVegetarian()` | `String?` |
| `getSubIngredients()` | `List<Ingredient>` |

> **Note:** The `Ingredient` class may be revised in a future version.
