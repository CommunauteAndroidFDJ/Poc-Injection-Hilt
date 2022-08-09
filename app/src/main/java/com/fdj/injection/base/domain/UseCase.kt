package com.fdj.injection.base.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<out Type, in Params>() where Type : Any? {

    abstract suspend fun run(params: Params): Type

    open suspend operator fun invoke(params: Params): Type {
        return withContext(Dispatchers.IO) {
            run(params)
        }
    }

    class None
}