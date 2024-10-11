package com.khidrew.task.ui

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khidrew.domian.entities.InfoModel
import com.khidrew.domian.useCases.AddNewDataUseCase
import com.khidrew.domian.useCases.GetAllDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllDataUseCase: GetAllDataUseCase,
    private val addNewDataUseCase: AddNewDataUseCase
) : ViewModel() {


    private val _state: MutableStateFlow<ViewState> = MutableStateFlow(ViewState())
     val state = _state.asStateFlow()

    var name = ObservableField("")
    var age = ObservableField("")
    var jobTitle = ObservableField("")
    var gender = ObservableField("")


    var isSaveClicked = ObservableBoolean(false)


    fun validate(): Boolean {
        return name.get().toString().isNotEmpty() && age.get().toString()
            .isNotEmpty() && jobTitle.get().toString().isNotEmpty() && gender.get().toString()
            .isNotEmpty()
    }

    fun saveUser() {
        viewModelScope.launch(Dispatchers.IO) {
            sendAction(Action.Loading)
            try {
                addNewDataUseCase.invoke(
                    InfoModel(
                        name = name.get().toString(),
                        age = age.get().toString().toInt(),
                        job = jobTitle.get().toString(),
                        gender = gender.get().toString()
                    )
                )
                sendAction(Action.SuccessInserted(true))
                resetFields()
            } catch (e: Exception) {
                sendAction(Action.Failure(e.message, null))
            }
        }
    }

    private fun resetFields() {
        isSaveClicked.set(false)
        name.set("")
        age.set("")
        jobTitle.set("")
        gender.set("")
    }

    fun getAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            sendAction(Action.Loading)
            try {
                sendAction(Action.Success(true, getAllDataUseCase.invoke()))
            } catch (e: Exception) {
                sendAction(Action.Failure(e.message, null))
            }
        }
    }


    sealed class Action {
        data object Loading : Action()
        class Failure(val message: String?, val stringResource: Int?) : Action()
        class Success(val status: Boolean, val data: List<InfoModel>) : Action()
        class SuccessInserted(val status: Boolean) : Action()
    }

    data class ViewState(
        var isError: Boolean = false,
        val message: String? = null,
        val isLoading: Boolean = false,
        val status: Boolean = false,
        val statusInserted: Boolean = false,
        val data: List<InfoModel>? = null
    )

    fun sendAction(viewAction: Action) {
        onReduceState(viewAction)
    }

    private fun onReduceState(viewAction: Action) {
        _state.value = when (viewAction) {
            is Action.Failure -> ViewState(
                isError = true,
                message = viewAction.message,
                status = false,
                isLoading = false
            )

            is Action.Loading -> ViewState(
                isLoading = true,
                isError = false,
                status = false,
                message = null
            )

            is Action.Success -> ViewState(
                isLoading = false,
                isError = false,
                status = viewAction.status,
                data = viewAction.data
            )

            is Action.SuccessInserted -> ViewState(
                isLoading = false,
                isError = false,
                statusInserted = true,
                status = false
            )
        }
    }
}