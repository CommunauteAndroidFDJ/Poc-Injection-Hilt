package com.fdj.injection.business

import com.fdj.injection.base.data.BaseResponse
import com.fdj.injection.marvel.business.GetAllCharactersUseCase
import com.fdj.injection.marvel.business.model.MarvelCharacter
import com.fdj.injection.marvel.repository.MarvelRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetAllCharactersUseCaseTest {

    @Mock
    lateinit var newsRepository: MarvelRepository

    private lateinit var getAllCharactersUseCase: GetAllCharactersUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getAllCharactersUseCase = GetAllCharactersUseCase(newsRepository)
    }

    @Test
    fun `should Return list of MarvelCharacter when succes`() = runBlockingTest {
        val resutSuccess =
            listOf(
                MarvelCharacter(
                    name = "Ab",
                    description = "teste",
                    thumbnail = "UrlOfThumb",
                    id = 1
                )
            )

        whenever(
            newsRepository.getAllCharacters(
                GetAllCharactersUseCase.OrderByType.NAME.orderBy
            )
        ).thenReturn(BaseResponse.Success(resutSuccess))

        getAllCharactersUseCase.run(
            GetAllCharactersUseCase.Params(
                GetAllCharactersUseCase.OrderByType.NAME
            )
        ).let { useCaseResponse ->
            if (useCaseResponse is BaseResponse.Success<List<MarvelCharacter>?>) {
                Assert.assertEquals(BaseResponse.Success(resutSuccess).obj, useCaseResponse.obj)
            }
        }
    }
}