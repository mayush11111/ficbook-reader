package ru.blays.ficbook.reader.shared.di

import okhttp3.Cache
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.blays.ficbook.reader.shared.data.cookieStorage.DynamicCookieJar
import ru.blays.ficbook.reader.shared.platformUtils.getCacheDir
import ru.blays.ficbook.reader.shared.platformUtils.getPlatformInterceptors
import ru.blays.ficbook.reader.shared.proxy.IProxyHolder
import ru.blays.ficbook.reader.shared.proxy.ProxyHolder
import ru.blays.ficbook.reader.shared.utils.BypassInterceptor

val okHttpModule = module {
    singleOf(::DynamicCookieJar) bind CookieJar::class
    singleOf(::ProxyHolder) bind IProxyHolder::class
    single {
        val proxyHolder: ProxyHolder = get()
        val platformInterceptors = getPlatformInterceptors()
        OkHttpClient.Builder().apply {
            cache(
                cache = Cache(
                    directory = getCacheDir(),
                    maxSize = 15 * 1024 * 1024
                )
            )
            //dns(BypassDns())
            addInterceptor(BypassInterceptor())
            platformInterceptors.forEach(::addInterceptor)
            proxySelector(proxyHolder)
            proxyAuthenticator(proxyHolder.authenticator)
            cookieJar(get())
        }.build()
    }
}
