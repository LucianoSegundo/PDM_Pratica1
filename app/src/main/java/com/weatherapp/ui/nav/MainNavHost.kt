package com.weatherapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.weatherapp.view.HomePage
import com.weatherapp.view.ListPage
import com.weatherapp.MainViewModel
import com.weatherapp.view.MapPage

@Composable
fun MainNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController, startDestination = Route.Home) {
        composable<Route.Home> { HomePage(viewModel = viewModel) }
        composable<Route.List> { ListPage(viewModel = viewModel) }
        composable<Route.Map> { MapPage(viewModel = viewModel) }
    }
}

