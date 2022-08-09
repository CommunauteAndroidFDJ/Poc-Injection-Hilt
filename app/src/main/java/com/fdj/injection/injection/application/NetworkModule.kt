package com.fdj.injection.injection.application

import com.fdj.injection.marvel.repository.MarvelApi
import com.fdj.injection.utils.NetworkUtils.API_MARVEL_API_KEY
import com.fdj.injection.utils.NetworkUtils.API_MARVEL_API_KEY_VALUE
import com.fdj.injection.utils.NetworkUtils.API_MARVEL_BASE_URL
import com.fdj.injection.utils.NetworkUtils.API_MARVEL_HASH_KEY
import com.fdj.injection.utils.NetworkUtils.API_MARVEL_HASH_VALUE
import com.fdj.injection.utils.NetworkUtils.API_MARVEL_TIMESTAMP_KEY
import com.fdj.injection.utils.NetworkUtils.API_MARVEL_TIMESTAMP_VALUE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideMarvelApiInterface(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }

    @Singleton
    @Provides
    internal fun provideRetrofitInterface(httpClient: OkHttpClient): Retrofit {
        return getRetrofitInterface(httpClient)
    }

    /**
     * Retrofit instance only for marvel Api , we can create other
     * named instance for other Api and use it with other Module like Marvel Module
     */
    private fun getRetrofitInterface(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_MARVEL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Singleton
    @Provides
    internal fun provideOkHttpClient(
    ): OkHttpClient {
        return getOkHttpClient()
    }

    private fun getOkHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(createLoggingInterceptor())
        builder.addInterceptor(createQueryParamsInterceptor())

        return builder.build()
    }

    private fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    /**
     * okHttp interceptor used to add global query params in every marvel request
     */
    private fun createQueryParamsInterceptor(): Interceptor {
        return Interceptor {
            val original: Request = it.request()
            val originalHttpUrl: HttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(API_MARVEL_API_KEY, API_MARVEL_API_KEY_VALUE)
                .addQueryParameter(API_MARVEL_HASH_KEY, API_MARVEL_HASH_VALUE)
                .addQueryParameter(API_MARVEL_TIMESTAMP_KEY, API_MARVEL_TIMESTAMP_VALUE)
                .build()

            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)

            val request: Request = requestBuilder.build()
            it.proceed(request)
        }
    }

}