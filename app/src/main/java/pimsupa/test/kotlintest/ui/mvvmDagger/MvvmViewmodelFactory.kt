package pimsupa.test.kotlintest.ui.mvvmDagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pimsupa.test.kotlintest.respository.LoginRespository
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class MvvmViewmodelFactory (
    private val respository: LoginRespository
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MvvmViewModel(respository) as T
    }
}