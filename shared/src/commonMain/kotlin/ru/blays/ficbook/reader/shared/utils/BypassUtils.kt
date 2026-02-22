package ru.blays.ficbook.reader.shared.utils

import com.russhwolf.settings.boolean
import okhttp3.Dns
import okhttp3.Interceptor
import okhttp3.Response
import ru.blays.ficbook.api.FICBOOK_HOST
import ru.blays.ficbook.reader.shared.preferences.SettingsKeys
import ru.blays.ficbook.reader.shared.preferences.settings
import java.net.InetAddress
import java.net.UnknownHostException

class BypassInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val bypassBlock = settings.getBoolean(
            key = SettingsKeys.BYPASS_BLOCK,
            defaultValue = false
        )
        var request = chain.request()
        var builder = request.newBuilder()
        var userAgent = USER_AGENT

        if (bypassBlock && request.url.host == FICBOOK_HOST)
        {
            userAgent = BYPASS_USER_AGENT
            builder = builder
                .url(
                    request.url.newBuilder()
                        .host(BYPASS_FICBOOK_HOST)
                        .build()
                )
                .header("Host", BYPASS_FICBOOK_HOST)
        }

        builder = builder.header("User-Agent", userAgent)
        request = builder.build()
        return chain.proceed(request)
    }
}

fun String.toAbsoluteAssetUrl(): String {
    return when {
        this.startsWith("/static_app/") -> {
            this.replaceFirst("/static_app/", FICBOOK_ASSETS_URL)
        }
        this.startsWith("static_app/") -> {
            this.replaceFirst("static_app/", FICBOOK_ASSETS_URL)
        }
        else -> this
    }
}

private const val USER_AGENT = "AppleWebKit/605.1"
/*"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3346.8 Safari/537.36"*/

const val FICBOOK_USER_AGENT = "Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.6723.102 Mobile Safari/537.36"

const val FICBOOK_ASSETS_URL = "https://assets.teinon.net/"

const val BYPASS_FICBOOK_HOST = "fanficlets.xyz"

const val BYPASS_APP_VERSION = "1.4.2"

const val BYPASS_DEVICE_ID = "ao2gvvnhkv0t22lo"

const val BYPASS_USER_AGENT = "$FICBOOK_USER_AGENT ||| ficbook ($BYPASS_APP_VERSION) | Android (29) | oriole | $BYPASS_DEVICE_ID"
