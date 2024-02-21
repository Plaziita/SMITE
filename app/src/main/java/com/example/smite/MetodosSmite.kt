package com.example.smite

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.smite.data.Dios

@Composable
fun DiosItem(dios : Dios,modifier: Modifier){
    var rotated by remember { mutableStateOf(false) }
    val rotar by animateFloatAsState(
        targetValue = if(rotated) 360f else 0f,
        animationSpec = tween(900)
    )

    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .padding(16.dp)
            .graphicsLayer {
                rotationY = rotar
                cameraDistance = 8 * density
            }
            .clickable { rotated = !rotated }
    ) {

        if (!rotated){
            DiosDelante(dios)
        }else{
            DiosDetras(dios,rotar)
        }

    }
}


@Composable
fun DiosDetras(dios: Dios, rotar: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                rotationY = rotar
            }) {
        Image(
            painter = painterResource(id = dios.avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.6f)
                .size(600.dp)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationBar(dios)
        }
    }
}

@Composable
fun NavigationBar(dios: Dios) {
    var selectedTab by remember { mutableStateOf(NavTab.HABILIDADES) }
    var isDarkTheme by remember { mutableStateOf(false) }
    val backgroundColor = if (isDarkTheme) Color.Black else Color.Black
    Column {
        TabRow(
            selectedTabIndex = selectedTab.ordinal,
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            NavTab.values().forEach { tab ->
                Tab(
                    text = {
                        Text(
                            text = tab.title,
                            color = if (selectedTab == tab) Color.Yellow else Color.White,

                        )
                    },
                    selected = selectedTab == tab,
                    onClick = {
                        selectedTab = tab
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .clickable { selectedTab = tab }
                        .background(backgroundColor)
                )
            }
        }

        when (selectedTab) {
            NavTab.HABILIDADES -> {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.espacio_medium)))
                DiosAtributos(dios)
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.espacio_medium)))
                DiosHabilidades(dios)
            }
            NavTab.LORE -> {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.espacio_medium)))
                Text(
                    text = stringResource(id = dios.lore),
                    color = Color.White,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}

enum class NavTab(val title: String) {
    HABILIDADES("Habilidades"),
    LORE("Lore"),
}



@Composable
fun DiosAtributos(dios: Dios) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = dios.tipo),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.imagen_habilidad))
            )
            Text(
                text = stringResource(id = dios.tipotxt),
                color = Color.White,
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = dios.rol),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.imagen_habilidad))
            )
            Text(
                text = stringResource(id = dios.roltxt),
                color = Color.White,
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = dios.ataque),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.imagen_habilidad))
            )
            Text(
                text = stringResource(id = dios.ataquetxt),
                color = Color.White,
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = dios.dmg),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.imagen_habilidad))
            )
            Text(
                text = stringResource(id = dios.dmgtxt),
                color = Color.White,
            )
        }
    }
}

@Composable
fun DiosDelante(dios : Dios){
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = dios.avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.6f)
                .size(600.dp)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = stringResource(dios.apodo),
                color = Color.White,
                style = MaterialTheme.typography.displayMedium,

                )
            Spacer(modifier = Modifier.height(18.dp))
            Image(
                painter = painterResource(id = dios.avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(450.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(dios.nombre),
                color = Color.White,
                style = MaterialTheme.typography.displayMedium,
            )
        }
    }
}

@Composable
fun DiosHabilidades(dios: Dios) {
    var zoomedImage by remember { mutableStateOf(1) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        (1..5).forEach { index ->
            val isSelected = zoomedImage == index
            val painter = painterResource(id = when (index) {
                1 -> dios.habilidad1
                2 -> dios.habilidad2
                3 -> dios.habilidad3
                4 -> dios.habilidad4
                5 -> dios.pasiva
                else -> 0
            })

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.imagen_habilidad))
                    .weight(1f)
                    .graphicsLayer(
                        scaleX = if (isSelected) 1.2f else 1f,
                        scaleY = if (isSelected) 1.2f else 1f
                    )
                    .clickable {
                        zoomedImage = if (zoomedImage == index) -1 else index
                    }
            )
        }
    }

    if (zoomedImage != -1) {
        val selectedDescription = when (zoomedImage) {
            1 -> dios.descripcionHabilidad1
            2 -> dios.descripcionHabilidad2
            3 -> dios.descripcionHabilidad3
            4 -> dios.descripcionHabilidad4
            5 -> dios.descripcionPasiva
            else -> 0
        }

        val selectedTitle = when (zoomedImage) {
            1 -> stringResource(R.string.habilidad_1)
            2 -> stringResource(R.string.habilidad_2)
            3 -> stringResource(R.string.habilidad_3)
            4 -> stringResource(R.string.habilidad_4)
            5 -> stringResource(R.string.pasiva)
            else -> ""
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = selectedTitle,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .fillMaxWidth(),
            style = MaterialTheme.typography.displaySmall,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(selectedDescription),
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
            color = Color.White,
            textAlign = TextAlign.Justify
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmiteTopBar(){
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_medium))
            )
        }
    )
}
