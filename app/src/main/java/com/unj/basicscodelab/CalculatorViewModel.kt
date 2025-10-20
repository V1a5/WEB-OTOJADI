package com.unj.basicscodelab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class CalculatorOption{ ADD, SUBTRACT, MULTIPLY, DIVIDE, PERCENT }
class CalculatorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorModel())
    val uiState: StateFlow<CalculatorModel> = _uiState.asStateFlow()
    var prevValue : Int = 0;

    var calculatorMode: CalculatorOption = CalculatorOption.ADD;

    fun updateCalcValue(calcValue: Int){
        viewModelScope.launch {
            _uiState.update { it.copy(calcValue = calcValue) }
            delay(100)
        }
    }

    fun updatePrevValue(prevValue: Int){
        viewModelScope.launch {
            //_uiState.update { it.copy(prevValue = prevValue) }
            //delay(100)
            this@CalculatorViewModel.prevValue = prevValue
        }
    }

    fun updateCalculatorMode(calculatorMode: CalculatorOption){
        viewModelScope.launch {
            this@CalculatorViewModel.calculatorMode = calculatorMode
        }
    }

    fun computeValueByMode(value1: Int, value2: Int, mode: CalculatorOption) : Int{
        when(mode){
            CalculatorOption.ADD -> return value1 + value2
            CalculatorOption.SUBTRACT -> return value1 - value2
            CalculatorOption.MULTIPLY -> return value1 * value2
            CalculatorOption.DIVIDE -> return value1 / value2
            CalculatorOption.PERCENT -> return value1 % value2
        }
    }
}