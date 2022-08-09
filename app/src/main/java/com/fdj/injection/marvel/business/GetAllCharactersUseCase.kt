package com.fdj.injection.marvel.business

import com.fdj.injection.base.data.BaseResponse
import com.fdj.injection.base.domain.UseCase
import com.fdj.injection.marvel.business.model.MarvelCharacter
import com.fdj.injection.marvel.repository.MarvelRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

/**
 * This use cas will provide only All marvel Characters ,
 * we can define also an interface (implementation) for every Use case to define the use case
 */
@ViewModelScoped
class GetAllCharactersUseCase @Inject constructor(
    private val marvelRepository: MarvelRepository
) : UseCase<BaseResponse<List<MarvelCharacter>?>, GetAllCharactersUseCase.Params>() {

    override suspend fun run(params: Params): BaseResponse<List<MarvelCharacter>?> =
        marvelRepository.getAllCharacters(params.orderByType.orderBy)


    enum class OrderByType(val orderBy: String) {
        NAME("name")
    }

    data class Params(val orderByType: OrderByType)
}