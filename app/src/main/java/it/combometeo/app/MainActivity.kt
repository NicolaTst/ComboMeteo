
package it.combometeo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF111923)
private val Panel = Color(0xFF202D3B)
private val Blue = Color(0xFF65B9F3)
private val Muted = Color(0xFFAAB9C9)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComboMeteoApp()
        }
    }
}

@Composable
fun ComboMeteoApp() {
    var city by remember { mutableStateOf("Torino") }
    val uriHandler = LocalUriHandler.current

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Background,
            surface = Panel,
            primary = Blue
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .verticalScroll(rememberScrollState())
                .padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "COMBO",
                        color = Color.White,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "METEO",
                        color = Blue,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Icon(
                    Icons.Default.Cloud,
                    contentDescription = "Meteo",
                    tint = Blue,
                    modifier = Modifier.size(43.dp)
                )
            }

            Spacer(Modifier.height(28.dp))

            Text(
                "IL TEMPO, A COLPO D'OCCHIO",
                color = Muted,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text("Cerca una città") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
                },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Blue,
                    unfocusedBorderColor = Muted
                )
            )

            Spacer(Modifier.height(12.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Panel
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Blue
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            city.ifBlank { "Nessuna città" },
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    Text(
                        "--°",
                        color = Color.White,
                        fontSize = 54.sp,
                        fontWeight = FontWeight.Light
                    )
                    Text(
                        "In attesa dei dati meteorologici",
                        color = Muted,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                "CONFRONTO PREVISIONI",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                WeatherSource(
                    name = "3Bmeteo",
                    description = "Previsioni",
                    modifier = Modifier.weight(1f)
                ) {
                    uriHandler.openUri("https://www.3bmeteo.com/")
                }

                WeatherSource(
                    name = "iLMeteo",
                    description = "Previsioni",
                    modifier = Modifier.weight(1f)
                ) {
                    uriHandler.openUri("https://www.ilmeteo.it/")
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                "MAPPA INTERATTIVA",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        uriHandler.openUri("https://www.windy.com/")
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Panel
                ),
                shape = RoundedCornerShape(18.dp)
            ) {
                Row(
                    modifier = Modifier.padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Map,
                        contentDescription = null,
                        tint = Blue,
                        modifier = Modifier.size(42.dp)
                    )
                    Spacer(Modifier.width(14.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            "Windy",
                            color = Color.White,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Apri la cartina meteo interattiva",
                            color = Muted,
                            fontSize = 12.sp
                        )
                    }
                    Icon(
                        Icons.Default.OpenInNew,
                        contentDescription = "Apri",
                        tint = Blue
                    )
                }
            }

            Spacer(Modifier.height(28.dp))

            Text(
                "COMBOMETEO 1.0",
                color = Muted,
                fontSize = 11.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                "Nessuna pubblicità · Nessun abbonamento",
                color = Muted,
                fontSize = 11.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
fun WeatherSource(
    name: String,
    description: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Panel
        ),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Icon(
                Icons.Default.Cloud,
                contentDescription = null,
                tint = Blue,
                modifier = Modifier.size(28.dp)
            )
            Spacer(Modifier.height(10.dp))
            Text(
                name,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                description,
                color = Muted,
                fontSize = 12.sp
            )
            Spacer(Modifier.height(10.dp))
            Text(
                "Apri sito →",
                color = Blue,
                fontSize = 12.sp
            )
        }
    }
}
