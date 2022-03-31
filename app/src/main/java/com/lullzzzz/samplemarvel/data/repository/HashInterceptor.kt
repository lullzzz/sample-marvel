package com.lullzzzz.samplemarvel.data.repository

import com.lullzzzz.samplemarvel.BuildConfig
import okhttp3.*
import java.math.BigInteger
import java.security.MessageDigest


class HashInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request? = chain.request()

        if (request != null) {
            val requestURL: HttpUrl = request.url()

            val apikey = BuildConfig.MarvelApiKey
            val privateKey = BuildConfig.MarvelPrivateKey
            val timestamp = System.currentTimeMillis().toString()
            val hash = md5(timestamp + privateKey + apikey)

            val refreshUrl = requestURL.newBuilder()
                .addQueryParameter("apikey", apikey)
                .addQueryParameter("hash", hash)
                .addQueryParameter("ts", timestamp)
                .build()
            request = request.newBuilder()
                .url(refreshUrl)
                .build()
        }
        return chain.proceed(request)
    }

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}