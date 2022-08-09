package com.fdj.injection.injection.application

import android.content.Context
import com.fdj.injection.FdjInjectionApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContextModule {
    @Provides
    @Singleton
    internal fun provideContext(fdjInjectionApplication: FdjInjectionApplication): Context =
        fdjInjectionApplication.applicationContext
}
