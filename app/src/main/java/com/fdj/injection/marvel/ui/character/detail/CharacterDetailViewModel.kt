package com.fdj.injection.marvel.ui.character.detail

import androidx.lifecycle.*
import com.fdj.injection.base.data.BaseResponse
import com.fdj.injection.marvel.business.GetCharacterDetailUseCase
import com.fdj.injection.marvel.ui.character.mapper.CharacterUIMapper
import com.fdj.injection.marvel.ui.character.model.CharacterModel
import com.fdj.injection.utils.ui.UiRequestState
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    private val _characterLiveData: MutableLiveData<CharacterModel> = MutableLiveData()
    val character: LiveData<CharacterModel>
        get() = _characterLiveData

    private val _updateUiState: MutableLiveData<UiRequestState> = MutableLiveData()
    val updateUiStateUi: LiveData<UiRequestState>
        get() = _updateUiState

    fun showCharacterDetail(characterId: String) {
        viewModelScope.launch {
            sendUiState(UiRequestState.SHOW_LOADING)
            getCharacterDetailUseCase.run(
                GetCharacterDetailUseCase.Params(characterId)
            ).also { response ->
                sendUiState(UiRequestState.HIDE_LOADING)
                when (response) {
                    is BaseResponse.Success -> {
                        response.obj?.let {
                            val character = CharacterUIMapper.mapCharacter(it)
                            _characterLiveData.value = character
                        } ?: run {
                            sendUiState(UiRequestState.ERROR)
                        }
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