import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.helloworld.contactsapp.ContactDAO
import com.helloworld.contactsapp.ContactViewModel

class ContactViewModelFactory(private val contactDAO: ContactDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            return ContactViewModel(contactDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
