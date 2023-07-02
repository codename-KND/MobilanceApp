package com.example.mobiuser.presentation.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mobiuser.domain.model.Hospital


@Composable
fun HospitalDropdown(
    hospitals: List<Hospital>,
    onHospitalSelected: (Double, Double) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedHospital by remember { mutableStateOf<Hospital?>(null) }

    OutlinedTextField(
        value = selectedHospital?.name ?: "",
        onValueChange = {},
        readOnly = true,
        label = { Text("Select Hospital") },
        trailingIcon = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Icon"
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            //contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.fillMaxWidth()
    ) {
        hospitals.forEach { hospital ->
            DropdownMenuItem(onClick = {
                selectedHospital = hospital
                onHospitalSelected(hospital.latitude, hospital.longitude)
                expanded = false
            }) {
                Text(text = hospital.name)
            }
        }
    }
}
