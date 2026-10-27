package com.simpleart.journal.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.simpleart.journal.ui.theme.LocalSpacing
import com.simpleart.journal.ui.theme.PaperLine
import com.simpleart.journal.ui.theme.editorTextFieldColors
import com.simpleart.journal.ui.theme.promptPlaceholder
import com.simpleart.journal.ui.theme.transparentTitleTextFieldColors

@Composable
fun DailyEditorCard(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
    onTitleChange: (String) -> Unit = {},
    onContentChange: (String) -> Unit = {}
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.large,
        tonalElevation = 2.dp,
        border = BorderStroke(1.dp, PaperLine)
    ) {
        Column(
            modifier = Modifier.padding(LocalSpacing.current.medium)
        ) {
            InputText(
                value = title,
                onValueChange = onTitleChange,
                placeholder = {
                    Text(
                        text = "Today's title or main memory...",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
            )

            Spacer(modifier = Modifier.height(LocalSpacing.current.small))

            InputText(
                value = content,
                onValueChange = onContentChange,
                placeholder = {
                    Text(
                        text = "What happened today? (e.g., watched a great movie, read a chapter, visited a cafe...)",
                        style = MaterialTheme.typography.promptPlaceholder
                    )
                },
                textStyle = MaterialTheme.typography.bodyLarge,
                colors = editorTextFieldColors(),
                isShortText = false,
                hasRoundedCorners = true
            )
        }
    }
}

@Composable
private fun InputText(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: @Composable (() -> Unit)? = null,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    colors: TextFieldColors = transparentTitleTextFieldColors(),
    isShortText: Boolean = true,
    hasRoundedCorners: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        textStyle = textStyle,
        singleLine = isShortText,
        minLines = if (isShortText) 1 else 6,
        maxLines = if (isShortText) 1 else 14,
        modifier = Modifier.fillMaxWidth(),
        colors = colors,
        shape = if (hasRoundedCorners) {
            MaterialTheme.shapes.medium
        } else {
            OutlinedTextFieldDefaults.shape
        }
    )
}
