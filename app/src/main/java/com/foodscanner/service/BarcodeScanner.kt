package com.foodscanner.service

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.foodscanner.FoodScannerViewModel
import com.google.mlkit.common.MlKitException
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

//Old Google Scanner - not used anymore

fun startBarcodeScanner(context: Context, viewModel: FoodScannerViewModel) {
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_EAN_13,
            Barcode.FORMAT_EAN_8
        )
        .enableAutoZoom()
        .build()
    val scanner = GmsBarcodeScanning.getClient(context,options)

    scanner.startScan()
        .addOnSuccessListener { barcode ->
            val rawValue = barcode.rawValue
            if (rawValue != null) {
                Toast.makeText(
                    context,
                    rawValue,
                    Toast.LENGTH_LONG
                ).show()
            }
            if (!rawValue.isNullOrEmpty()) {
                viewModel.scanBarcode(rawValue)
            }

        }

        .addOnCanceledListener {

        }
        .addOnFailureListener { exception ->

            Toast.makeText(
                context,
                "Scan fehlgeschlagen: ${exception.message}",
                Toast.LENGTH_LONG
            ).show()
        }
}

