package com.example.sampleapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ShowDialog() {
    val showDialog = remember {
        mutableStateOf(false)
    }

    if (showDialog.value) {
        CustomDialog(setShowDialog = {
            showDialog.value = it
        })
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.background(Color.White)
    ) {
        Button(onClick = {
            showDialog.value = true
        }) {
            Text(text = "Open Dialog")
        }
    }
}


@Composable
fun CustomDialog(setShowDialog: (Boolean) -> Unit) {

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(contentAlignment = Alignment.Center,
            modifier = Modifier.padding(20.dp)) {
                Column(Modifier.padding(20.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_eligibility_criteria),
                        contentDescription = null
                    )
                    Text(
                        text = "Eligibility criteria",
                        style = MaterialTheme.typography.h1
                    )
                }
            }
        }
    }
}