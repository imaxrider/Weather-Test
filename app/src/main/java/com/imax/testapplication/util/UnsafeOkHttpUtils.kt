package com.imax.testapplication.util

import android.content.Context
import okhttp3.*
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLPeerUnverifiedException
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException


class UnsafeOkHttpUtils {

    companion object {
        fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
            try {

                // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String,
                    ) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String,
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                })
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())


                val builder = OkHttpClient.Builder()
                builder.hostnameVerifier { _, _ -> true }
                builder.connectTimeout(90, TimeUnit.SECONDS)
                builder.readTimeout(90, TimeUnit.SECONDS)
                builder.writeTimeout(90, TimeUnit.SECONDS)
                builder.connectionSpecs(
                    listOf(
                        ConnectionSpec.MODERN_TLS,
                        ConnectionSpec.COMPATIBLE_TLS
                    )
                )
                builder.sslSocketFactory(
                    sslContext.socketFactory,
                    trustAllCerts[0] as X509TrustManager
                )

                return builder
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}