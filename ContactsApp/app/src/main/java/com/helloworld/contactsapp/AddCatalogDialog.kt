package com.helloworld.contactsapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddContactDialog(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    if (state.isAddingContact) {  // Show only if dialog is open
        AlertDialog(
            onDismissRequest = { onEvent(ContactEvent.HideDialog) },
            title = { Text("Add Contact") },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = state.firstName,
                        onValueChange = { onEvent(ContactEvent.SetFirstName(it)) },
                        label = { Text("First Name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = state.lastName,
                        onValueChange = { onEvent(ContactEvent.SetLastName(it)) },
                        label = { Text("Last Name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = state.phoneNumber,
                        onValueChange = { onEvent(ContactEvent.SetPhoneNumber(it)) },
                        label = { Text("Phone Number") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    // Create the contact object and pass it to SaveContact event
                    val newContact = Contact(
                        firstName = state.firstName,
                        lastName = state.lastName,
                        phoneNumber = state.phoneNumber
                    )
                    onEvent(ContactEvent.SaveContact(newContact))
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { onEvent(ContactEvent.HideDialog) }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewAddContactDialog() {
    AddContactDialog(
        state = ContactState(isAddingContact = true),
        onEvent = { }
    )
}
