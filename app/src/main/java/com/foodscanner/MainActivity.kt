package com.foodscanner

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foodscanner.storage.LocalStorage
import com.foodscanner.storage.ProductHistory
import com.foodscanner.ui.theme.FoodScannerTheme
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning


class MainActivity : ComponentActivity() {
    private lateinit var controller: Controller
    private lateinit var viewModel: FoodScannerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val TAG = "MainActivity" // for logging
        controller = Controller(productHistory = ProductHistory(storage = LocalStorage(context = applicationContext)))

        viewModel = FoodScannerViewModel(controller)
        enableEdgeToEdge()
        setContent {
            FoodScannerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                    val productList by viewModel.historyState.collectAsState()
                    val currentProduct by viewModel.currentProduct.collectAsState()
                    val firstName = productList.firstOrNull()?.getName()
                        ?.takeIf { it.isNotBlank() } ?: "Kein Produkt"
                    var productNameList = mutableListOf<String?>()
                    productList.forEach { product ->
                        productNameList.add(0, product.getName())
                    }

                    Column(
                        modifier = Modifier.padding(24.dp),
                    ) {
                        Button(
                            onClick = { startBarcodeScanner() },
                            modifier = Modifier.padding(34.dp),
                            shape = CircleShape,
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text("Scan Barcode")
                        }

                        Row {
                            productNameList.forEach { name->
                                if (name != null) {
                                    Text(text = name, modifier = Modifier.padding(12.dp))
                                }
                            }
                        }

                        Button(
                            onClick = { viewModel.clearHistory() },
                            modifier = Modifier.padding(34.dp),
                        ) {
                            Text(
                                text = "Clear History"
                            )
                        }
                    }
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
                    Toast.makeText(
                        this,
                        rawValue,
                        Toast.LENGTH_LONG
                    ).show()
                }
                    if(rawValue.isNullOrEmpty()){

                    } else  {
                        viewModel.scanBarcode(rawValue)
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