package com.helloworld.contactsapp

sealed interface ContactEvent {
    // Event to save a contact, now passes a Contact object
    data class SaveContact(val contact: Contact) : ContactEvent

    // Events to update the contact details
    data class SetFirstName(val firstName: String) : ContactEvent
    data class SetLastName(val lastName: String) : ContactEvent
    data class SetPhoneNumber(val phoneNumber: String) : ContactEvent

    // Events to show or hide the dialog
    data object ShowDialog : ContactEvent
    data object HideDialog : ContactEvent

    // Event for sorting contacts
    data class SortContact(val sortType: SortType) : ContactEvent

    // Event to delete a contact
    data class DeleteContact(val contact: Contact) : ContactEvent
}
