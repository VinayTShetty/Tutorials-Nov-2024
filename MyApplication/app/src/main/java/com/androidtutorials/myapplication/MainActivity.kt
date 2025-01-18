package com.androidtutorials.myapplication

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                getAllMapsSymbol()
            }
        }
    }


    @Composable
    fun getAllMapsSymbol() {
        val context= LocalContext.current
        val maplist = listOf(
            MapsSymbol("Bike Scooter", "12.54, 23.87", R.drawable.baseline_bike_scooter_24),
            MapsSymbol("Add Location", "12.54, 23.87", R.drawable.baseline_add_location_24),
            MapsSymbol("Outline 360", "12.54, 23.87", R.drawable.outline_360_24)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(maplist) { mapitem ->
                Column {
                    mapItemDesign(mapsSymbol = mapitem,context)
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }

    @Composable()
    fun mapItemDesign(mapsSymbol: MapsSymbol,context: Context) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp).clickable {
                         Toast.makeText(context,"Clicked ${mapsSymbol.name}",Toast.LENGTH_SHORT).show()
                },
            elevation = CardDefaults.cardElevation(8.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = mapsSymbol.image),
                    contentDescription = "Outline 360",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(12.dp),
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = mapsSymbol.name,
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = mapsSymbol.lat,
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
        }

    }
}