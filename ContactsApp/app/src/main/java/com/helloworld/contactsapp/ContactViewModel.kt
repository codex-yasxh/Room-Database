package com.helloworld.contactsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ContactViewModel(
    private val DAO: ContactDAO // You can inject this via ViewModelFactory manually
) : ViewModel() {

    private val _state = MutableStateFlow(ContactState()) // Holds UI state
    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)

    // Fetch sorted contacts based on selected sortType
    private val _contacts = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                SortType.FIRST_NAME -> DAO.getContactOrderedByfirstName()
                SortType.LAST_NAME -> DAO.getContactOrderedByLastName()
                SortType.PHONE_NUMBER -> DAO.getContactOrderedByPhoneNumber()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    // Combine state, sortType, and contacts into a single flow for UI
    val state = combine(_state, _sortType, _contacts) { state, sortType, contacts ->
        state.copy(
            contacts = contacts,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())

    // Handle different user actions
    fun onEvent(event: ContactEvent) {
        when(event) {
            is ContactEvent.DeleteContact -> {
                viewModelScope.launch {
                    DAO.deleteContact(event.contact)
                }
            }

            ContactEvent.HideDialog -> {
                _state.update {
                    it.copy(isAddingContact = false)
                }
            }

            ContactEvent.SaveContact -> {
                val firstName = state.value.firstName
                val lastName = state.value.lastName
                val phoneNumber = state.value.phoneNumber

                // Validate input
                if (firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank()) {
                    return
                }

                // Create a new contact object
                val contact = Contact(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phoneNumber
                )

                // Save contact to the database
                viewModelScope.launch {
                    DAO.upsertContact(contact)
                }

                // Reset fields and close the dialog
                _state.update {
                    it.copy(
                        isAddingContact = false,
                        firstName = "",
                        lastName = "",
                        phoneNumber = ""
                    )
                }
            }

            is ContactEvent.SetFirstName -> {
                _state.update {
                    it.copy(firstName = event.firstName)
                }
            }

            is ContactEvent.SetLastName -> {
                _state.update {
                    it.copy(lastName = event.lastName)
                }
            }

            is ContactEvent.SetPhoneNumber -> {
                _state.update {
                    it.copy(phoneNumber = event.phoneNumber)
                }
            }

            ContactEvent.ShowDialog -> {
                _state.update {
                    it.copy(isAddingContact = true)
                }
            }

            is ContactEvent.SortContact -> {
                _sortType.value = event.sortType
            }
        }
    }
}
