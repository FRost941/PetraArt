package com.example.petraart
import android.annotation.SuppressLint
import android.content.res.AssetManager
import android.os.Bundle
import android.view.Choreographer
import android.view.SurfaceView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.filament.View
import com.google.android.filament.android.UiHelper
import com.google.android.filament.utils.KTX1Loader
import com.google.android.filament.utils.ModelViewer
import com.google.android.filament.utils.Utils
import java.nio.ByteBuffer
import android.media.MediaPlayer

import androidx.compose.material3.*

import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.mutableIntStateOf

class MainActivity : ComponentActivity() {
    companion object {
        init {
            Utils.init()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                        //ImageWithDescription(lifecycle = lifecycle, imageId = 1)
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Route.LoginScreen){
                composable(route=Route.ImageWithDescription){
                    ImageWithDescription(lifecycle = lifecycle, navController = navController)
                }
                composable(route=Route.Map){
                    Map(navController = navController)
                }
                composable(route = Route.Profile){
                    Profile(navController = navController)
                }
                composable(route = Route.HomeScreen){
                    HomeScreen(navController=navController)
                }
                composable(route = Route.LoginScreen){
                    LoginScreen(navController=navController)
                }

            }
            }
        }
    }

object Route{
    const val Map ="Map"
    const val ImageWithDescription ="ImageWithDescription"
    const val Profile = "Profile"
    const val HomeScreen = "HomeScreen"
    const val LoginScreen = "LoginScreen"
}


@Composable
fun Map(navController: NavController){

        Box(modifier=Modifier.fillMaxSize()){

            Image(
                painter = painterResource(id = R.drawable.singlemap),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
                Text(
                    text = "Map",
                    fontWeight = FontWeight.Bold,
                    fontSize = 54.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color(0xff541b17),
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            SymbolButtonBar(modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),navController=navController)
            TextButton(
                onClick = { navController.navigate(Route.ImageWithDescription) },
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.TopStart)
                    .offset(x = (-25).dp, y = 75.dp)
            ) {
                Text("")
            }


        }
}

@Composable
fun Profile(navController: NavController){
    Box(modifier=Modifier.fillMaxSize()){

        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
        Image(
            painter = painterResource(id = R.drawable.profil),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )

        SymbolButtonBar(modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth(),navController=navController)
    }
/*        SymbolButtonBar(navController=navController, modifier = Modifier.align(
            Alignment.BottomCenter))*/
}


@Composable
fun HomeScreen(navController: NavController){
    var imageId by remember { mutableIntStateOf(1) }
    val titleResource = when (imageId) {
        1 -> R.drawable.addeirhome
        2 -> R.drawable.graves_bild
        3 -> R.drawable.addeir_laura
        else -> R.drawable.background
    }
    Box{
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Welcome to",
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color(0xff541b17)
                )
            Text(text = "PETRA",
                fontWeight = FontWeight.Bold,
                fontSize = 54.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color(0xff541b17)
            )

            Image(
                painter = painterResource(id = titleResource),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(42.dp))
            )
        }
        TextButton(
            onClick = {
                if (imageId > 1) {
                    imageId--
                } else {
                    imageId = 3
                }
            },
            modifier = Modifier
                .height(600.dp)
                .width(200.dp)
                .align(Alignment.CenterStart)
        ) {
            Text("")
        }
        TextButton(
            onClick = {
                if (imageId < 3) {
                    imageId++
                } else {
                    imageId = 1
                }
            },
            modifier = Modifier
                .height(600.dp)
                .width(200.dp)
                .align(Alignment.CenterEnd)
        ) {
            Text("")
        }
        SymbolButtonBar(navController = navController, modifier = Modifier.align(Alignment.BottomCenter))
    }



}

@Composable
fun DModelViewer(lifecycle: Lifecycle, modifier:Modifier=Modifier){
    Box(modifier.size(350.dp, 400.dp)){
    AndroidView(factory = { context ->
        val renderer = ModelRenderer1()
        SurfaceView(context).apply {
            renderer.onSurfaceAvailable(this, lifecycle)
        }
    }
    )
    }
}

@Composable
fun ImageModelSwitcher(switch: Boolean,  lifecycle: Lifecycle, imageId: Int){
    if(switch){
        DModelViewer(lifecycle = lifecycle)
    }
    else{
        ImageViewer(imageId=imageId)
    }
}

@Composable
fun ImageViewer(modifier:Modifier = Modifier, imageId: Int = 1){
    //var imageNumber by remember { mutableIntStateOf(value = 1) }val imageResource = when (imageNumber) {
    val imageResource = when (imageId) {
        1 -> R.drawable.monestary
        2 -> R.drawable.graves_bild
        else -> R.drawable.background
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
           contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(350.dp, 400.dp)
                .clip(
                    RoundedCornerShape(16.dp) // Hier kannst du auch weitere Modifikatoren hinzufügen, falls notwendig
                )
        )
    }
}
@Composable
fun ImageWithDescription(
    modifier: Modifier = Modifier,
    imageId: Int = 1,
    lifecycle: Lifecycle,
    navController: NavController
) {
    var is3DView by remember { mutableStateOf(false) } // Zustand für 3D oder Info
    var showTips by remember { mutableStateOf(false) } // Zustand für Tipps oder Beschreibung
    var isPlaying by remember { mutableStateOf(false) } // Zustand für Play/Stop
    var selectedLanguage by remember { mutableStateOf("Deutsch") } // Standard-Sprache

    val context = LocalContext.current
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    // Texte für die verschiedenen Sprachen
    val descriptions = mapOf(
        "Deutsch" to "Ad Deir (das Kloster) ist eine der faszinierendsten Sehenswürdigkeiten von Petra. Zum Bergplateau, das von der Felsfassade dominiert wird, gelangt man vom antiken Stadtzentrum aus auf einem alten Prozessionsweg. Der 1,6 km lange Aufstieg über knapp 200 m Höhenunterschied erfordert Kondition. Zwischen den insgesamt ca. 800 Treppenstufen gibt es auch flache Abschnitte.\n" +
                "\n" +
                "Die arabische Bezeichnung Ad Deir gaben einheimische Beduinen dem Ort wegen der in die Rückwand des Innenraums geritzten Kreuze, die von einer christlichen Nutzung in byzantinischer Zeit stammen. Die 47 m breite und 48 m hohe Fassade und die dahinter liegende Halle sind um die Mitte des 1. Jahrhunderts n. Chr. aus dem Berg gehauen worden.\n" +
                "\n" +
                "Der obere Teil mit dem gesprengten (offenen) Giebel, in dessen Mitte ein Tholos (Rundtempel) mit Urne steht, folgt dem Vorbild der Khazneh (Schatzhaus). Doch da es im Innern keine Grabstätten gibt, kann Ad Deir kein Mausoleum gewesen sein. In der erhöhten Nische der Rückwand fand man die Spuren eines später entfernten Betyls und auf beiden Seiten der Felshalle flache breite Bänke, was auf eine Kultstätte schließen lässt.\n" +
                "\n" +
                "Möglicherweise huldigte hier eine reiche Bruderschaft mit ihren Symposien (rituelles Festmahl) dem nach seinem Tod vergöttlichten nabatäischen König Obodas II. (regierte 30–9 v. Chr.). Diese Vermutung beruht auf einer Inschrift in der Nähe von Ad Deir: „Lasst uns Ubaydu, Sohn des Waqihel, und seinen Gefolgsleuten im Symposium Obodas, des Gottes, gedenken.“",
        "Englisch" to "Ad Deir (the Monastery) is one of the most fascinating sights in Petra. The mountain plateau, dominated by the rock-carved facade, can be reached from the ancient city center via an old processional route. The 1.6 km ascent, with an elevation gain of nearly 200 meters, requires endurance. Among the approximately 800 steps, there are also flat sections.\n" +
                "\n" +
                "The Arabic name Ad Deir was given to the site by local Bedouins due to the crosses carved into the back wall of the interior, which indicate Christian use during the Byzantine period. The 47-meter-wide and 48-meter-high facade, along with the hall behind it, was carved out of the mountain around the middle of the 1st century AD.\n" +
                "\n" +
                "The upper part, with its broken (open) pediment, in the center of which stands a tholos (circular temple) with an urn, follows the model of the Khazneh (Treasury). However, since there are no tombs inside, Ad Deir could not have been a mausoleum. In the elevated niche of the back wall, traces of a later removed betyl were found, and on both sides of the rock-hewn hall, wide flat benches suggest a cultic site.\n" +
                "\n" +
                "It is possible that a wealthy brotherhood honored the deified Nabataean king Obodas II (who reigned from 30 to 9 BC) here with their symposia (ritual banquets). This assumption is based on an inscription near Ad Deir: \"Let us remember Ubaydu, son of Waqihel, and his followers in the symposium of Obodas, the god.\"",
        "Spanisch" to "Ad Deir (el Monasterio) es una de las atracciones más fascinantes de Petra. Se llega a la meseta montañosa, dominada por la fachada rocosa, desde el centro de la antigua ciudad a través de un antiguo camino procesional. La subida de 1,6 km con un desnivel de casi 200 m requiere resistencia. Entre los aproximadamente 800 escalones, también hay tramos planos.\n" +
                "\n" +
                "Los beduinos locales dieron el nombre árabe de Ad Deir al lugar debido a las cruces grabadas en la pared trasera del interior, que indican un uso cristiano en la época bizantina. La fachada de 47 m de ancho y 48 m de alto y la sala situada detrás fueron talladas en la roca alrededor de mediados del siglo I d.C.\n" +
                "\n" +
                "La parte superior, con el frontón abierto (destruido), en cuyo centro se encuentra un tholos (templo circular) con una urna, sigue el modelo del Khazneh (el Tesoro). Sin embargo, como no hay tumbas en su interior, Ad Deir no pudo haber sido un mausoleo. En el nicho elevado de la pared trasera se encontraron rastros de un betilo que fue retirado posteriormente, y en ambos lados de la sala rocosa hay bancos anchos y planos, lo que sugiere un santuario.\n" +
                "\n" +
                "Es posible que una rica hermandad rindiera homenaje aquí a través de sus simposios (banquetes rituales) al rey nabateo Obodas II, quien fue deificado después de su muerte (reinó entre el 30 y el 9 a.C.). Esta hipótesis se basa en una inscripción cerca de Ad Deir: \"Recordemos a Ubaydu, hijo de Waqihel, y a sus seguidores en el simposio de Obodas, el dios\"."
    )

    val tips = mapOf(
        "Deutsch" to "Tipps für den Besuch:\n- Nimm genug Wasser mit!\n- Vermeide die Mittagshitze.\n- Bequeme Schuhe sind Pflicht!",
        "Englisch" to "Tips for your visit:\n- Bring enough water!\n- Avoid the midday heat.\n- Comfortable shoes are a must! Wear Proper Footwear: Choose sturdy, comfortable hiking shoes with good grip to navigate rocky and uneven paths.\n" +
                "Stay Hydrated: Carry enough water, especially during hot weather, to avoid dehydration.\n" +
                "Check the Weather: Avoid hiking during extreme heat or heavy rain, as trails can become slippery and dangerous.\n" +
                "Use Sun Protection: Wear a hat, sunglasses, and sunscreen to protect yourself from the strong sun.\n" +
                "Travel in Groups: It's safer to hike with others, especially in remote areas.\n" +
                "Follow Marked Trails: Stick to designated paths to prevent getting lost or encountering hazardous terrain.\n" +
                "Inform Someone About Your Plans: Let someone know your route and expected return time.\n" +
                "Pace Yourself: Take breaks as needed, especially when climbing the 800 steps to Ad Deir.\n" +
                "Emergency Contacts: Keep local emergency numbers saved on your phone for quick access.",
        "Spanisch" to "Consejos para la visita:\n- Lleva suficiente agua!\n- Evita el calor del mediodía.\n- Usa zapatos cómodos!"
    )

    // MP3-Dateien für verschiedene Sprachen
    val audioFiles = mapOf(
        "Deutsch" to R.raw.ad_deir_audio_de,
        "Englisch" to R.raw.ad_deir_audio_en,
        "Spanisch" to R.raw.ad_deir_audio_es
    )

    val displayedText = if (showTips) tips[selectedLanguage]!! else descriptions[selectedLanguage]!!

    Box {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Dynamischer Wechsel zwischen Bild und 3D-Ansicht
                ImageModelSwitcher(switch = is3DView, lifecycle = lifecycle, imageId = imageId)

                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = modifier.padding(18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Sprache: $selectedLanguage",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color(0xff541b17)
                        )

                        // Sprach-Menü
                        var expanded by remember { mutableStateOf(false) }
                        Box {
                            TextButton(onClick = { expanded = true }) {
                                Text("🌍 Sprache ändern", fontSize = 12.sp, color = Color(0xff541b17))
                            }
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                listOf("Deutsch", "Englisch", "Spanisch").forEach { language ->
                                    DropdownMenuItem(
                                        text = { Text(language) },
                                        onClick = {
                                            selectedLanguage = language
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    // Buttons in einer Zeile (Info/3D View, Tipps/Beschreibung, Play/Stop)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        // Info <-> 3D View Button (wechselt bei Klick)
                        TextButton(onClick = { is3DView = !is3DView }) {
                            Text(
                                text = if (is3DView) "Info" else "3D View",
                                color = Color(0xff541b17),
                                fontSize = 12.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp)) // Abstand zwischen den Buttons

                        // Tipps <-> Beschreibung Button (wechselt bei Klick)
                        TextButton(onClick = { showTips = !showTips }) {
                            Text(
                                text = if (showTips) "Beschreibung" else "Tipps",
                                color = Color(0xff541b17),
                                fontSize = 12.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // Dynamischer Play/Stop-Button
                        IconButton(
                            onClick = {
                                if (isPlaying) {
                                    // Stoppe das Audio
                                    mediaPlayer?.stop()
                                    mediaPlayer?.release()
                                    mediaPlayer = null
                                    isPlaying = false
                                } else {
                                    // Starte das Audio für die gewählte Sprache
                                    mediaPlayer?.release()
                                    mediaPlayer = MediaPlayer.create(context, audioFiles[selectedLanguage]!!)
                                    mediaPlayer?.start()
                                    isPlaying = true

                                    // Wenn das Audio zu Ende ist, wieder auf Play-Button umschalten
                                    mediaPlayer?.setOnCompletionListener {
                                        isPlaying = false
                                    }
                                }
                            },
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Filled.Close else Icons.Filled.PlayArrow,
                                contentDescription = if (isPlaying) "Stop Audio" else "Play Audio",
                                tint = Color(0xff541b17)
                            )
                        }
                    }

                    // Beschreibung oder Tipps anzeigen
                    Text(
                        displayedText, // Dynamischer Text (Beschreibung oder Tipps)
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .verticalScroll(rememberScrollState()),
                        color = Color(0xff541b17),
                        fontFamily = FontFamily.SansSerif,
                    )
                }
            }
            SymbolButtonBar(navController = navController)
        }
    }
}






@Composable
fun SymbolButtonBar(modifier: Modifier=Modifier, navController: NavController ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xff541b17)
                ),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SymbolButton(iconId = R.drawable.home, buttonColor = Color(0xffcc6a57), onClick = { navController.navigate(Route.HomeScreen)})
            SymbolButton(iconId = R.drawable.map, buttonColor = Color(0xffcc6a57), onClick = { navController.navigate(Route.Map)})
            SymbolButton(iconId = R.drawable.profile, buttonColor = Color(0xffcc6a57), onClick = { navController.navigate(Route.Profile)})
            SymbolButton(iconId = R.drawable.settings, buttonColor = Color(0xffcc6a57), onClick = { navController.navigate(Route.Map)})
        }
}

@Composable
fun SymbolButton(iconId:Int=R.drawable.home, buttonColor:Color = Color(0xffcc6a57),onClick: () -> Unit) {
    //val onClick: () -> Unit = remember { { /* Define the action to be performed when button is clicked */ } }
    TextButton(onClick = onClick, modifier = Modifier
        .height(58.dp)
        .width(58.dp)
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                tint = buttonColor,
                modifier = Modifier.
                fillMaxSize()


            )

    }
}
@Composable
fun LoginScreen(navController:NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box{
        Image(
            painter = painterResource(id = R.drawable.loginbackground),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize() // Hier kannst du auch weitere Modifikatoren hinzufügen, falls notwendig
        )
        Spacer(modifier=Modifier.size(20.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier=Modifier.size(200.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            FilledTonalButton(modifier = Modifier.width(200.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff541b17)), onClick = {
                if (email == "" && password == "") {
                    navController.navigate(Route.HomeScreen)
                } else {
                    // Optional: Show error message for invalid credentials
                }
            }) {
                Text("Login")
            }
        }
    }
}

class ModelRenderer1 {

    inner class FrameCallback : Choreographer.FrameCallback {
        override fun doFrame(frameTimeNanos: Long) {
            choreographer.postFrameCallback(this)
            modelViewer.render(frameTimeNanos)
        }
    }

    private lateinit var surfaceView: SurfaceView
    private lateinit var lifecycle: Lifecycle
    private lateinit var choreographer: Choreographer
    private lateinit var uiHelper: UiHelper
    private lateinit var modelViewer: ModelViewer
    private lateinit var assets: AssetManager

    private val frameScheduler = FrameCallback()

    private val lifecycleObserver = object : DefaultLifecycleObserver {
        override fun onResume(owner: LifecycleOwner) {
            choreographer.postFrameCallback(frameScheduler)
        }

        override fun onPause(owner: LifecycleOwner) {
            choreographer.removeFrameCallback(frameScheduler)
        }

        override fun onDestroy(owner: LifecycleOwner) {
            choreographer.removeFrameCallback(frameScheduler)
            lifecycle.removeObserver(this)
        }

    }

    private fun loadGlb(name: String) {
        val buffer = readAsset("${name}.glb")
        modelViewer.loadModelGlb(buffer)
        modelViewer.transformToUnitCube()
    }
/*
    private fun loadEnvironment(ibl: String) {
        // Create the indirect light source and add it to the scene.
        var buffer = readAsset("${ibl}_ibl.ktx")
        KTX1Loader.createIndirectLight(modelViewer.engine, buffer).apply {
            intensity = 70_000f
            modelViewer.scene.indirectLight = this
        }
    }*/

    private fun readAsset(assetName: String): ByteBuffer {
        val input = assets.open(assetName)
        val bytes = ByteArray(input.available())
        input.read(bytes)
        return ByteBuffer.wrap(bytes)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun onSurfaceAvailable(surfaceView: SurfaceView, lifecycle: Lifecycle) {
        this.surfaceView = surfaceView
        this.lifecycle = lifecycle
        assets = surfaceView.context.assets
        choreographer = Choreographer.getInstance()
        lifecycle.addObserver(lifecycleObserver)

        uiHelper = UiHelper(UiHelper.ContextErrorPolicy.DONT_CHECK).apply {
            // This is needed to make the background transparent
            isOpaque = false
        }
        modelViewer = ModelViewer(surfaceView = surfaceView, uiHelper = uiHelper)
        // This is needed so we can move the camera in the rendering
        surfaceView.setOnTouchListener { _, event -> modelViewer.onTouchEvent(event)
            true
        }
        // This is the other code needed to make the background transparent
        modelViewer.scene.skybox = null
        modelViewer.view.blendMode = View.BlendMode.TRANSLUCENT
        modelViewer.renderer.clearOptions = modelViewer.renderer.clearOptions.apply {
            clear = true
        }
        // This is needed to add some light to our Model
        val buffer = readAsset("venetian_crossroads_2k_ibl.ktx")
        KTX1Loader.createIndirectLight(modelViewer.engine, buffer).apply {
            intensity = 70_000f
            modelViewer.scene.indirectLight = this
        }
        // This part defines the quality of your model, feel free to change it or
        // add other options
        modelViewer.view.apply {
            renderQuality = renderQuality.apply {
                hdrColorBuffer = View.QualityLevel.HIGH
            }
        }
        loadGlb("final")
        //loadEnvironment("venetian_crossroads_2k")
    }
}



