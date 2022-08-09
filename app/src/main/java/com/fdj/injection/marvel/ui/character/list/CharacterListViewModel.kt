package com.fdj.injection.marvel.ui.character.list

import androidx.lifecycle.*
import com.fdj.injection.base.data.BaseResponse
import com.fdj.injection.marvel.business.GetAllCharactersUseCase
import com.fdj.injection.marvel.ui.character.mapper.CharacterUIMapper
import com.fdj.injection.marvel.ui.character.model.CharacterModel
import com.fdj.injection.utils.ui.UiRequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    private val _charactersLiveData: MutableLiveData<List<CharacterModel>> = MutableLiveData()
    val characters: LiveData<List<CharacterModel>>
        get() = _charactersLiveData

    private val _updateUiState: MutableLiveData<UiRequestState> = MutableLiveData()
    val updateUiStateUi: LiveData<UiRequestState>
        get() = _updateUiState

    init {
        showAllCharacters()
    }

    fun showAllCharacters() {
        viewModelScope.launch {
            sendUiState(UiRequestState.SHOW_LOADING)
            getAllCharactersUseCase.run(
                GetAllCharactersUseCase.Params(GetAllCharactersUseCase.OrderByType.NAME)
            ).also { response ->
                sendUiState(UiRequestState.HIDE_LOADING)
                when (response) {
                    is BaseResponse.Success -> {
                        val characters = CharacterUIMapper.mapCharacterList(response.obj)
                        _charactersLiveData.value = characters
                    }
                    else -> {
                        sendUiState(UiRequestState.ERROR)
                    }
                }
            }
        }
    }

    private fun sendUiState(stateUi: UiRequestState) {
        _updateUiState.value = stateUi
    }

}
