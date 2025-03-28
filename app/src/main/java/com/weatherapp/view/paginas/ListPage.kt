package com.weatherapp.view.paginas

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.weatherapp.model.MainViewModel
import com.weatherapp.R
import com.weatherapp.model.entity.City
import com.weatherapp.ui.nav.Route

@Composable
fun ListPage(modifier: Modifier = Modifier,  viewModel: MainViewModel) {
    val activity = LocalContext.current as? Activity

    val cityList = viewModel.cities
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(cityList) { city ->


            if (city.weather == null) {
                viewModel.loadWeather(city)

            }
            CityItem(city = city,  onClose = { viewModel.remove(city);

        }, onClick = {
                viewModel.city = city
                viewModel.page = Route.Home
                Toast.makeText(activity, "aconteceu o onClick", Toast.LENGTH_LONG).show();
            })
        }
    }

}

@Composable
fun CityItem(
    city: City,
    onClick: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {



    Row(
        modifier = modifier.fillMaxWidth().padding(8.dp).clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = city.weather?.imgUrl,
            modifier = Modifier.size(75.dp),
            error = painterResource(id = R.drawable.loading),
            contentDescription = "Imagem"
        )


        Spacer(modifier = Modifier.size(12.dp))
        Column(modifier = modifier.weight(1f)) {
            Row {
                Text(modifier = Modifier,
                    text = city.name,
                    fontSize = 24.sp)
                Icon(
                    imageVector = Icons.Filled.Notifications, contentDescription = "Monitorada?",
                    modifier = Modifier.size(32.dp)
                )
            }

            Text(modifier = Modifier,
                text = city.weather?.desc?:"carregando...",
                fontSize = 16.sp)
        }
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}



