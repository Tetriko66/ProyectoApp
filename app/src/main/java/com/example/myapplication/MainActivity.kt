package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MyApplicationApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyApplicationApp() {
    // Recuerda la pestaña en la que se encuentra el usuario
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach { destination ->
                item(
                    icon = {
                        Icon(
                            imageVector = destination.icon,
                            contentDescription = destination.label
                        )
                    },
                    label = { Text(destination.label) },
                    selected = destination == currentDestination,
                    onClick = { currentDestination = destination }
                )
            }
        }
    ) {
        // El contenedor principal de nuestra pantalla
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                // Dependiendo de la pestaña seleccionada, mostramos un contenido diferente
                when (currentDestination) {
                    AppDestinations.HOME -> HomeScreen()
                    AppDestinations.FAVORITES -> FavoritesScreen()
                    AppDestinations.PROFILE -> ProfileScreen()
                }
            }
        }
    }
}

// --- DEFINICIÓN DE LAS PESTAÑAS (Cambiamos Int por ImageVector) ---
enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Inicio", Icons.Default.Home),
    FAVORITES("Favoritos", Icons.Default.Favorite),
    PROFILE("Perfil", Icons.Default.Person),
}

// --- PANTALLES INDIVIDUALES DE LA APP ---

@Composable
fun HomeScreen() {
    Text(text = "¡Bienvenido a la Pantalla de Inicio!", style = MaterialTheme.typography.headlineMedium)
}

@Composable
fun FavoritesScreen() {
    Text(text = "Aquí aparecerán tus Favoritos ❤️", style = MaterialTheme.typography.headlineMedium)
}

@Composable
fun ProfileScreen() {
    Text(text = "Configuración del Perfil de Usuario", style = MaterialTheme.typography.headlineMedium)
}
