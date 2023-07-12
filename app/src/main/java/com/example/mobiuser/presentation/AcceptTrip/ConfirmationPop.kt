package com.example.mobiuser.presentation.AcceptTrip

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.mobiuser.domain.model.AvailableTripsItem

@Composable
fun ConfirmationPopup(
    trip: Int,
    onConfirm: (Int) -> Unit
) {
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Do You Want This Trip?") },
            text = { Text(text = "Please confirm you want to accept this request") },
            confirmButton = {
                Button(
                    onClick = {
                        /**comment: pass trip id to completed trip**/
                        //amendment pass tripid
                        onConfirm(trip)
                        showDialog.value = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary
                    )
                ) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog.value = false }
                ) {
                    Text(text = "No")
                }
            }
        )
    }

    Button(
        onClick = { showDialog.value = true }
    ) {
        Text(text = " Trip Confirmation")
    }
}
