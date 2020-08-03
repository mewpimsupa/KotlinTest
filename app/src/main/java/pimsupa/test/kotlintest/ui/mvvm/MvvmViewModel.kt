package pimsupa.test.kotlintest.ui.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pimsupa.test.kotlintest.respository.LoginRespository

class MvvmViewModel(private val respository:LoginRespository):ViewModel(){

    private var _toast = MutableLiveData<String>()
    var toast = _toast as LiveData<String>
    private var _textButton = MutableLiveData<String>()
    var textButton = _textButton as LiveData<String>

    private var count = 0

    fun testCallFunction(){
        viewModelScope.launch {
            _toast.value =  respository.testDatabase()
        }
    }

    fun onClickCount(){
        count++
        _textButton.value = count.toString()
    }



}