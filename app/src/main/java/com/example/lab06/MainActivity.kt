package com.example.lab06

import androidx.compose.foundation.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab06.ui.theme.Lab06Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab06Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("profile") {UserProfileScreen(navController)}
                    composable("home") { ScreenHome(navController) }
                    composable("contacts") { ScreenContacts(navController) }
                    composable("tasks") { ScreenTasks(navController) }
                    composable("settings") { ScreenSettings(navController) }
                }

            }
        }
    }
}

//Ventana Inicio
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHome(navController: NavHostController) {
    var eventoEspecialAgregado by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { CustomTopBar(navController) },
        bottomBar = { CustomBottomBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { eventoEspecialAgregado = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar Evento Especial")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFE3F2FD))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Resumen de tu Agenda",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF6200EA)
            )

            if (eventoEspecialAgregado) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFCC80)),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "🎉 Evento Especial Agregado",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD1C4E9))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Contactos Registrados",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Total: 8 contactos",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Button(
                        onClick = { navController.navigate("contacts") },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Ver Contactos")
                    }
                }
            }

            Text(
                text = "Estado de Tareas",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF6200EA)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item { TaskStatusCard("Pendientes", "4 tareas", Color(0xFFFFA726)) }
                item { TaskStatusCard("En Progreso", "2 tareas", Color(0xFF64B5F6)) }
                item { TaskStatusCard("Terminadas", "5 tareas", Color(0xFF81C784)) }
            }
        }
    }
}


//Mostrar el estado de las tareas
@Composable
fun TaskStatusCard(title: String, subtitle: String, color: Color) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(120.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = subtitle, style = MaterialTheme.typography.bodyMedium, color = Color.White)
        }
    }
}

//Ventana de Contactos
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContacts(navController: NavHostController) {
    // Lista real de contactos
    val contactos = remember { mutableStateListOf(
        "George Miky - +51 987654321",
        "Ana Perez - +51 999888777",
        "Carlos Ruiz - +51 988776655"
    ) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contactos", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6200EA))
            )
        },
        bottomBar = { CustomBottomBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Simulamos agregar un nuevo contacto rápido
                    contactos.add("Nuevo Contacto - +51 900000000")
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar Contacto")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFE1F5FE))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Tus Contactos",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF6200EA)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(contactos) { index, contacto ->
                    ContactCardDynamic(
                        contacto = contacto,
                        onDelete = { contactos.removeAt(index) }
                    )
                }
            }
        }
    }
}

//Tarjetas de Contacto Dinámicas
@Composable
fun ContactCardDynamic(contacto: String, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = contacto.split("-")[0], style = MaterialTheme.typography.titleMedium)
                Text(text = contacto.split("-")[1], style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Filled.Delete, contentDescription = "Eliminar", tint = Color.Red)
            }
        }
    }
}

//Ventana de Tareas
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTasks(navController: NavHostController) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Pendientes", "En Progreso", "Terminadas")

    val tareasPendientes = remember { mutableStateListOf("Tarea 1", "Tarea 2") }
    val tareasEnProgreso = remember { mutableStateListOf("Tarea A") }
    val tareasTerminadas = remember { mutableStateListOf("Tarea Finalizada 1") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tareas", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6200EA))
            )
        },
        bottomBar = { CustomBottomBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    when (selectedTabIndex) {
                        0 -> tareasPendientes.add("Nueva Pendiente")
                        1 -> tareasEnProgreso.add("Nueva en Progreso")
                        2 -> tareasTerminadas.add("Nueva Terminada")
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar Tarea")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFFFF9C4))
        ) {
            // Tabs
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color(0xFF6200EA),
                contentColor = Color.White
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Contenido según pestaña
            when (selectedTabIndex) {
                0 -> TaskListScreen(tareasPendientes, Color(0xFFFFA726))
                1 -> TaskListScreen(tareasEnProgreso, Color(0xFF64B5F6))
                2 -> TaskListScreen(tareasTerminadas, Color(0xFF81C784))
            }
        }
    }
}


@Composable
fun TaskListScreen(tareas: MutableList<String>, color: Color) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        itemsIndexed(tareas) { index, tarea ->
            TaskCardDynamic(
                titulo = tarea,
                color = color,
                onDelete = { tareas.removeAt(index) }
            )
        }
    }
}

//Tarjetas Dinámicas
@Composable
fun TaskCardDynamic(titulo: String, color: Color, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = titulo, style = MaterialTheme.typography.titleMedium, color = Color.White)
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Eliminar tarea",
                    tint = Color.White
                )
            }
        }
    }
}

//Ventana de Configuraciones
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSettings(navController: NavHostController) {
    var isDarkModeEnabled by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var vibrationEnabled by remember { mutableStateOf(true) }
    var fontSize by remember { mutableStateOf(16f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Ajustes", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EA)
                )
            )
        },
        bottomBar = { CustomBottomBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF1F8E9))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Configuraciones Generales",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF6200EA)
            )

            // Switch: Tema oscuro
            SettingSwitch(
                title = "Modo Oscuro",
                checked = isDarkModeEnabled,
                onCheckedChange = { isDarkModeEnabled = it }
            )

            // Switch: Notificaciones
            SettingSwitch(
                title = "Notificaciones",
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it }
            )

            // Switch: Vibración
            SettingSwitch(
                title = "Vibración",
                checked = vibrationEnabled,
                onCheckedChange = { vibrationEnabled = it }
            )

            // Slider: Tamaño de fuente
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "Tamaño de Fuente: ${fontSize.toInt()}sp")
                    Slider(
                        value = fontSize,
                        onValueChange = { fontSize = it },
                        valueRange = 12f..30f,
                        steps = 6
                    )
                }
            }
        }
    }
}

@Composable
fun SettingSwitch(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD7CCC8))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            androidx.compose.material3.Switch(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

//Barra Superior Personalizada
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(navController: NavHostController) {
    TopAppBar(
        title = {
            Text(
                text = "Agenda Personal",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Inicio",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Perfil de Usuario",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6200EA))
    )
}

//Ventana de Perfil de Usuario
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Perfil de Usuario", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EA)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFF3E5F5)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.perfil_avatar_bg),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "George Miky Guerra Pacheco",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF6200EA)
            )

            Text(
                text = "Estudiante de Diseño y Desarrollo de Software",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("📧 Correo: george.guerra@tecsup.edu.pe", style = MaterialTheme.typography.bodyLarge)
                    Text("📞 Teléfono: +51 907275278", style = MaterialTheme.typography.bodyLarge)
                    Text("🏢 Instituto: TECSUP", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

//Barra Inferior Personalizada
@Composable
fun CustomBottomBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = Color(0xFF6200EA)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(Icons.Filled.Home, contentDescription = "Inicio", tint = Color.White)
            }
            IconButton(onClick = { navController.navigate("contacts") }) {
                Icon(Icons.Filled.Face, contentDescription = "Contactos", tint = Color.White)
            }
            IconButton(onClick = { navController.navigate("tasks") }) {
                Icon(Icons.Filled.Edit, contentDescription = "Tareas", tint = Color.White)
            }
            IconButton(onClick = { navController.navigate("settings") }) {
                Icon(Icons.Filled.Settings, contentDescription = "Ajustes", tint = Color.White)
            }
        }
    }
}


