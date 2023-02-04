package com.example.sampleapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShowTextField() {
    var text by remember {
        mutableStateOf("")
    }
    var isError by remember {
        mutableStateOf(false)
    }

    MyOutlinedTextField(
        text = text,
        onValueChange = {
            text = it
            isError = text.length == 10
        },
        hint = "Hint",
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .clickable {

                    }
            )
        },
        isError = isError
    )
}

@Composable
fun MyOutlinedTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable() (() -> Unit)? = null,
    hint: String = "",
    isError: Boolean = false,
    isEnabled: Boolean = true
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                onValueChange(it)
            },
            trailingIcon = trailingIcon,
            label = {
                Text(text = hint)
            },
            isError = isError,
            enabled = isEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
}