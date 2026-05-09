package com.foodscanner

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foodscanner.data.Product
import com.foodscanner.storage.LocalStorage
import com.foodscanner.storage.ProductHistory
import com.foodscanner.ui.theme.FoodScannerTheme
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var controller: Controller
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val TAG = "MainActivity"
        controller = Controller(productHistory = ProductHistory(storage = LocalStorage(context = applicationContext)))
        enableEdgeToEdge()
        setContent {
            FoodScannerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    Button(
                        onClick = { startBarcodeScanner() },
                        modifier = Modifier.padding(34.dp),
                        shape = CircleShape,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("Scan Barcode")
                    }

                    // Example use of History
                    var fileContent by remember { mutableStateOf("Lade...") }
                    LaunchedEffect(Unit) {
                        val productList = controller.getProductHistory()
                        Log.d(TAG,"ProductListSize: ${productList.size}")
                        val product: Product
                        if(productList.isNotEmpty()) {
                            product = productList.first()
                            val name: String? = product.getName()
                            if(name.isNullOrBlank()){ } else {
                                fileContent = name
                            }
                        }
                    }
                    Text(text=fileContent,
                        modifier = Modifier.padding(64.dp)
                        )



                }
            }
        }
    }

    // TODO move this code to a other class or smth
    private fun startBarcodeScanner() {
        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_EAN_13,
                Barcode.FORMAT_EAN_8
            )
            .enableAutoZoom()
            .build()
        val scanner = GmsBarcodeScanning.getClient(this,options)

        scanner.startScan()
            .addOnSuccessListener { barcode ->
                val rawValue = barcode.rawValue
                if (rawValue != null) {
                    onBarcodeScanned(rawValue)
                }
            }
            .addOnCanceledListener {

            }
            .addOnFailureListener { exception ->

                Toast.makeText(
                    this,
                    "Scan fehlgeschlagen: ${exception.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
    }
    // TODO move this function with the startBarcodeScanner function
    private fun onBarcodeScanned(ean: String) {
        // TODO Do Something with the barcode
        // Toast.makeText(this, "EAN: $ean", Toast.LENGTH_SHORT).show()

        CoroutineScope(Dispatchers.IO).launch {
            val product = controller.getProductFromBarcode(ean)
            val name = product?.getName()
            runOnUiThread {
                Toast.makeText(this@MainActivity, name?: "Kein Produkt", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodScannerTheme {
        Greeting("Android")
    }
}