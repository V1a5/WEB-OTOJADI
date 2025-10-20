package com.unj.basicscodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unj.basicscodelab.CalculatorScreen.SetupLayout

object CalculatorScreen {

    private const val TAG = "CalculatorScreen"
    //enum class CalculatorOption{ ADD, SUBTRACT, MULTIPLY, DIVIDE, PERCENT }
    @Composable
    fun SetupLayout(calculatorViewModel: CalculatorViewModel = CalculatorViewModel()){
        val uiState by calculatorViewModel.uiState.collectAsState()

        var calcValue by remember {mutableStateOf("0")};
        var prevValue by remember {mutableIntStateOf(0) };
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
            ){
            Row(){
                OutlinedEditableText(Modifier
                    .padding(16.dp)
                    .weight(1f), "$uiState.calcValue")
            }
            Row(){
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "AC", textColor = Color(0xFFFFA500),
                    color = Color.Gray,
                    onClick = {
                        //calcValue = "0"
                        calculatorViewModel.updateCalcValue(0)
                    })
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "<-",
                    textColor = Color(0xFFFFA500),
                    color = Color.Gray)
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "%",
                    textColor = Color(0xFFFFA500),
                    color = Color.Gray,
                    onClick = {
                        prevValue = calcValue.toInt()
                        //calculatorMode = CalculatorOption.PERCENT
                        calculatorViewModel.updateCalculatorMode(CalculatorOption.PERCENT)
                    })
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "/",
                    textColor = Color(0xFFFFA500),
                    color = Color.Gray,
                    onClick = {
                        calculatorViewModel.prevValue = calcValue.toInt()
                        //calculatorMode = CalculatorOption.DIVIDE
                        calculatorViewModel.updateCalculatorMode(CalculatorOption.DIVIDE)
                        calcValue = "0"
                    })
            }
            Row(){
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "7",
                    onClick = {calcValue += "7"}
                    )
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "8",
                    onClick = { calcValue += "8" })
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "9",
                    onClick = { calcValue += "9" })
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "x",
                    textColor = Color(0xFFFFA500),
                    color = Color.Gray,
                    onClick = {
                        prevValue = calcValue.toInt()
                        //calculatorMode = CalculatorOption.MULTIPLY
                        calculatorViewModel.updateCalculatorMode(CalculatorOption.MULTIPLY)
                        calcValue = "0"
                    })
            }
            Row(){
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "4",
                    onClick = {calcValue += "4"})
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "5",
                    onClick = {calcValue += "5"})
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "6",
                    onClick = {calcValue += "6"})
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "-",
                    textColor = Color(0xFFFFA500),
                    color = Color.Gray,
                    onClick = {
                        prevValue = calcValue.toInt()
                        //calculatorMode = CalculatorOption.SUBTRACT
                        calculatorViewModel.updateCalculatorMode(CalculatorOption.SUBTRACT)
                        calcValue = "0"
                    })
            }
            Row(){
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "1",
                    onClick = {calcValue += "1"})
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "2",
                    onClick = {calcValue += "2"})
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "3",
                    onClick = {calcValue += "3"})
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "+",
                    textColor = Color(0xFFFFA500),
                    color = Color.Gray,
                    onClick = {
                        //prevValue = calcValue.toInt()
                        calculatorViewModel.updatePrevValue(calcValue.toInt())
                        //calculatorMode = CalculatorOption.ADD
                        calculatorViewModel.updateCalculatorMode(CalculatorOption.ADD)
                        calcValue = "0"
                    })
            }
            Row(){
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(2f), "0",
                    onClick = {calcValue += "0"})
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), ".")
                CalculatorButton(modifier= Modifier
                    .padding(16.dp)
                    .weight(1f), "=", Color(0xFFFFA500),
                    textColor = Color.White,
                    onClick = {
                        calculatorViewModel.updateCalcValue(calculatorViewModel.computeValueByMode(calculatorViewModel.prevValue, uiState.calcValue, calculatorViewModel.calculatorMode))
                        calcValue = "$uiState.calcValue"
                        /*calcValue = calculatorViewModel.computeValueByMode(prevValue,
                            calcValue.toInt(),
                            calculatorViewModel.calculatorMode).toString()

                         */
                    })
            }
        }
    }

    @Composable
    fun CalculatorButton(modifier: Modifier, value : String, color : Color = Color.White,
                         textColor: Color = Color.Black,
                         fontSize: TextUnit = 24.sp, onClick: () -> Unit = {}){
        var buttonText = remember{mutableStateOf(value)}
        ElevatedButton (
            onClick = {
                onClick()
            },
            shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                containerColor = color, // This is the background color
                contentColor = Color.Green    // This is the text color
            ), modifier = modifier
                .widthIn(min = 12.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)// Adjust min width as needed
            ) {
            Text(buttonText.value, fontSize = fontSize, softWrap = false,
                maxLines = 1, textAlign = TextAlign.Center,
                color=textColor, modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Visible)
        }
    }

    @Composable
    fun OutlinedEditableText(modifier: Modifier = Modifier, value: String) {
        var textValue by remember { mutableStateOf(value) }

        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                textValue = newValue
            },
            label = { Text("Enter text (Outlined)") },
            modifier = modifier
        )
    }
}

@Preview
@Composable
fun CalculatorPreview(){
    SetupLayout()
}