package com.cpsl.has.rhs.di.core

//import okhttp3.logging.HttpLoggingInterceptor
import com.example.turbocaretestassignment.rhs.app.VehcileApp
import com.example.turbocaretestassignment.rhs.util.PrefsUtil
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author yash gupta
 * Network module to be used to provide dependencies for the retrofit/network apis calls
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(app: VehcileApp, gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(PrefsUtil.getApiBaseUrl(app))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }


    /**
     * Provides dependencies for the gson object
     */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val builder = GsonBuilder()//.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return builder.setLenient().create()
    }

    /**
     * Provides dependencies for the OkHttpClient for  Services
     */
    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder().build()
            chain.proceed(request)
        }

        setuInterceptor(httpClient)

        return httpClient.build()
    }

    private fun setuInterceptor(httpClient: OkHttpClient.Builder) {
        val logginInterceptor = HttpLoggingInterceptor()
        logginInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logginInterceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
    }


}