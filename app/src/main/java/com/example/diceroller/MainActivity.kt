package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.diceroller.ui.theme.DiceRollerTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRoller()
                }
            }
        }
    }
}

fun randomNumberGenerator(diceNumber: Int): Int{
    val result = (1..diceNumber).random()
    return result
}
@Composable
fun DiceRoller(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)){
    var result by remember { mutableStateOf(1) }
    val listDiceNumbers = listOf<Int>(4,6,8,10,12,20)
    var expanded by remember { mutableStateOf(false) }
    var diceType by remember { mutableStateOf(4) }
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.clickable { expanded = true },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Choose your dice type:")
            Icon(imageVector = Icons.Filled.ArrowDropDown , contentDescription = null)
            DropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}) {
                listDiceNumbers.forEach(){
                    DropdownMenuItem(text = { Text(text = it.toString(), fontSize = 15.sp)}, onClick = { expanded = false; diceType = it})
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "You chose dice type: $diceType")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {result = randomNumberGenerator(diceNumber = diceType)}) {
            Text(text = "Roll Dice Button")
            
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Dice rolled to $result")


    }
}