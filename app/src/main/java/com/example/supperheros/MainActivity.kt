package com.example.supperheros

import SuperheroesTheme
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.supperheros.ui.theme.HeroesRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    HeroList(heroes = HeroesRepository.heroes)
                }
            }
        }
    }
}

@Composable
fun HeroList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier
) {
    Scaffold (
        modifier = modifier
            .fillMaxSize(),
        topBar = { TopAppBar() }
        ) {
        LazyColumn {
            itemsIndexed(heroes) { _, hero ->
                SuperHeroApp(
                    hero = hero,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .sizeIn(minHeight = 56.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun SuperHeroApp(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 2.dp,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 74.dp)
        ) {
            Column(modifier = Modifier
                .weight(1f)
            ) {
                Text(
                    text = stringResource(hero.nameRes),
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = stringResource(hero.descriptionRes),
                    style = MaterialTheme.typography.body1
                )
            }
            Spacer(modifier = modifier.width(16.dp))
            Box (
                modifier = Modifier
                    .size(74.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(hero.imageRes),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@Preview("LightTheme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HelloPreivew() {
    val hero = Hero(
        R.string.hero1,
        R.string.description1,
        R.drawable.android_superhero1
    )
    SuperheroesTheme {
        SuperHeroApp(hero = hero)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperheroesTheme (darkTheme = true){
        HeroList(heroes = HeroesRepository.heroes)
    }
}