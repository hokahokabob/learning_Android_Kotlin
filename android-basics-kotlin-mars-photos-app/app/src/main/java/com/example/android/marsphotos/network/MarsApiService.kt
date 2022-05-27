package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

/**
 * moshiでJSON解析
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
//    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto> //コルーチン内からこのメソッドを呼び出すことが可能に
//    suspend fun getPhotos(): String //コルーチン内からこのメソッドを呼び出すことが可能に
}

object MarsApi {
    val retrofitService: MarsApiService by lazy { //この遅延初期化により、プロパティが最初に使用されるときに初期化されるようにします。
        retrofit.create(MarsApiService::class.java)
    }
}