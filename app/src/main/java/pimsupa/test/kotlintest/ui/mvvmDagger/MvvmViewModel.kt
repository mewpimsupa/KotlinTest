package pimsupa.test.kotlintest.ui.mvvmDagger

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pimsupa.test.kotlintest.respository.LoginRespository
import pimsupa.test.kotlintest.utils.Event

class MvvmViewModel(private val respository: LoginRespository) : ViewModel() {

    private var _toast = MutableLiveData<Event<String>>()
    var toast = _toast as LiveData<Event<String>>
    private var _textButton = MutableLiveData<String>()
    var textButton = _textButton as LiveData<String>

    private var count = 0

    fun testCallFunction() {
        viewModelScope.launch {
            _toast.value = Event(respository.testDatabase())
        }
    }

    fun onClickCount() {
        count++
        _textButton.value = count.toString()
    }

    fun onClickLogin(username: String, password: String) {
        viewModelScope.launch {
            val loggedIn = respository.login(username, password)
            if (loggedIn) {
                _toast.value = Event("success")
                _textButton.value = "success"
            } else {
                _toast.value = Event("fail")
                _textButton.value = "Login"
            }
        }
    }


}