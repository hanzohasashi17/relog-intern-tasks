package com.example.relogintern.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.relogintern.util.TextFieldType


@Composable
fun AuthTextField(
    value: String,
    label: String,
    placeholder: String,
    textFieldType: TextFieldType,
    isPasswordField: Boolean = false,
    onValueChange: (value: String) -> Unit
) {
    var showText by remember { mutableStateOf(!isPasswordField) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(start = 12.dp),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(6.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.onSurfaceVariant, RoundedCornerShape(16.dp)),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            placeholder = {
                Text(text = placeholder)
            },
            visualTransformation = if (showText) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                if (isPasswordField) {
                    if (!showText) {
                        IconButton(
                            onClick = { showText = true }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = null
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { showText = false }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    }
                }
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.onSurface,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = when(textFieldType) {
                    TextFieldType.EmailType -> {
                        KeyboardType.Email
                    }

                    TextFieldType.PasswordType -> {
                        KeyboardType.Password
                    }

                    TextFieldType.TextType -> {
                        KeyboardType.Text
                    }
                },
            ),
        )
    }
}