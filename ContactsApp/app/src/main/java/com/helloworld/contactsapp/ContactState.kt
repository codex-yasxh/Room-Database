package com.helloworld.contactsapp

data class ContactState(
    val contacts: List<Contact> = emptyList(),   // List of contacts
    val firstName: String = "",                 // First name input
    val lastName: String = "",                  // Last name input
    val phoneNumber: String = "",               // Phone number input
    val isDialogVisible: Boolean = false,       // Show/hide dialog
    val sortType: SortType = SortType.FIRST_NAME // Current sorting type

)
