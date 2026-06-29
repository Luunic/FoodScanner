package com.foodscanner.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foodscanner.ui.components.utility.VitalScanFooter
import com.foodscanner.ui.components.utility.VitalScanHeader
import com.foodscanner.ui.components.utility.customShadow
import com.foodscanner.ui.theme.FoodScannerTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.style.TextOverflow
import com.foodscanner.R
import com.foodscanner.service.allergenTranslations
import java.util.Locale
import androidx.compose.ui.platform.LocalLocale

@Composable
fun SettingsScreen(
    username: String,
    onUsernameChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var localUsername by remember { mutableStateOf(username) }
    val currentLanguage = if(LocalLocale.current.platformLocale.language == "de") "de" else "en"

    val translationMap = allergenTranslations[currentLanguage] ?: allergenTranslations["en"] ?: emptyMap()

    LaunchedEffect(username) {
        if (username != localUsername) {
            localUsername = username
        }
    }

    LaunchedEffect(localUsername) {
        kotlinx.coroutines.delay(500)
        onUsernameChange(localUsername)
    }

    var allergenMenuOpen by remember { mutableStateOf(false) }
    var selectedAllergens by remember { mutableStateOf(listOf<String>()) }


    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 26.dp,
                    end = 26.dp,
                    top = 24.dp,
                    bottom = 24.dp
                ),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(76.dp))

            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .customShadow(
                        color = Color(
                            red = 70,
                            green = 101,
                            blue = 101,
                            alpha = (255 * 0.22f).toInt()
                        ),
                        blurRadius = 20.dp,
                        offsetY = 8.dp
                    ),
                shape = RoundedCornerShape(32.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 22.dp, vertical = 18.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.person_24dp),
                            contentDescription = null,
                            tint = Color(0xFF246565),
                            modifier = Modifier.size(26.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = stringResource(R.string.profil),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1C1C)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(R.string.howtogreet),
                        fontSize = 14.sp,
                        color = Color(0xFF6F7978)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    OutlinedTextField(
                        value = localUsername,
                        onValueChange = { newName ->
                            if (newName.length <= 9) {
                                localUsername = newName
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text(stringResource(R.string.yourname))
                        },
                        placeholder = {
                            Text(stringResource(R.string.examplename))
                        },
                        shape = RoundedCornerShape(18.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color(0xFF1A1C1C),
                            unfocusedTextColor = Color(0xFF1A1C1C),

                            focusedBorderColor = Color(0xFF246565),
                            unfocusedBorderColor = Color(0xFFBFC8C7),

                            focusedLabelColor = Color(0xFF246565),
                            unfocusedLabelColor = Color(0xFF246565),

                            cursorColor = Color(0xFF246565),

                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,

                            focusedPlaceholderColor = Color(0xFF6F7978),
                            unfocusedPlaceholderColor = Color(0xFF6F7978)
                        )
                    )
                }
            }

            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .customShadow(
                        color = Color(
                            red = 70,
                            green = 101,
                            blue = 101,
                            alpha = (255 * 0.22f).toInt()
                        ),
                        blurRadius = 20.dp,
                        offsetY = 8.dp
                    ),
                shape = RoundedCornerShape(32.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 22.dp, vertical = 18.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.warning_24dp_ffffff_fill0_wght400_grad0_opsz24),
                            contentDescription = null,
                            tint = Color(0xFF246565),
                            modifier = Modifier.size(26.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = stringResource(R.string.allergens),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1C1C)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(R.string.chooseallergens),
                        fontSize = 14.sp,
                        color = Color(0xFF6F7978)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(18.dp))
                                .background(Color(0xFFF4F3F3))
                                .clickable { allergenMenuOpen = true }
                                .padding(horizontal = 16.dp, vertical = 15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (selectedAllergens.isEmpty()) {
                                    stringResource(R.string.nodropdownselection)
                                } else {
                                    selectedAllergens.joinToString(", ")
                                },
                                color = if (selectedAllergens.isEmpty()) {
                                    Color(0xFF6F7978)
                                } else {
                                    Color(0xFF1A1C1C)
                                },
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f)
                            )

                            Icon(
                                painter = painterResource(R.drawable.chevron_right_24dp_ffffff_fill0_wght400_grad0_opsz24),
                                contentDescription = "Allergene öffnen",
                                tint = Color(0xFF246565),
                                modifier = Modifier.size(26.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = allergenMenuOpen,
                            onDismissRequest = { allergenMenuOpen = false },
                            modifier = Modifier.background(Color.White)
                        ) {
                            translationMap.keys.forEach { allergenKey ->
                                val isSelected = selectedAllergens.contains(allergenKey)

                                // 4. Den übersetzten Namen für die Anzeige aus der Map holen
                                val translatedName = translationMap[allergenKey] ?: allergenKey

                                DropdownMenuItem(
                                    text = {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Checkbox(
                                                checked = isSelected,
                                                onCheckedChange = null,
                                                colors = CheckboxDefaults.colors(
                                                    checkedColor = Color(0xFF246565),
                                                    uncheckedColor = Color(0xFF6F7978)
                                                )
                                            )

                                            Spacer(modifier = Modifier.width(8.dp))

                                            Text(
                                                text = translatedName, // <--- Zeigt z.B. "Erdnüsse" oder "Peanuts" an
                                                color = Color(0xFF1A1C1C),
                                                fontSize = 15.sp
                                            )
                                        }
                                    },
                                    onClick = {
                                        // In selectedAllergens wird weiterhin nur der rohe Key ("peanuts") gespeichert!
                                        selectedAllergens = if (isSelected) {
                                            selectedAllergens - allergenKey
                                        } else {
                                            selectedAllergens + allergenKey
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }


        //      Preview Header + Footer - disable when running app
//        VitalScanHeader(
//            modifier = Modifier.align(Alignment.TopCenter)
//        )
//        VitalScanFooter(
//            modifier = Modifier.align(Alignment.BottomCenter),
//            onScanClick = {},
//            onProductClick = {},
//            onHistoryClick = {},
//            onFavoritesClick = {}
//        )
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    FoodScannerTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFF9F9F9)
        ){
            SettingsScreen(
                username = "Luis",
                onUsernameChange =  {}
            )
        }
    }
}