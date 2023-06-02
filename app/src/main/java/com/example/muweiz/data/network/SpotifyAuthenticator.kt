package com.example.muweiz.data.network

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.browser.customtabs.CustomTabsCallback
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession
import com.example.muweiz.BuildConfig
import com.example.muweiz.ui.view.SpotifyRequest
import net.openid.appauth.*

class SpotifyAuthenticator(private val context: Context) {

    private val CLIENT_ID = BuildConfig.API_C
    private val redirectUri = BuildConfig.URI
    private val scope = "app-remote-control user-read-private user-library-read user-read-email playlist-read-private"
    private val authorizationEndpoint = "https://accounts.spotify.com/authorize"
    private val tokenEndpoint = "https://accounts.spotify.com/api/token"
    private lateinit var authService: AuthorizationService
    private lateinit var accessToken: String
    private var customTabsClient: CustomTabsClient? = null
    private var customTabsSession: CustomTabsSession? = null
    private var customTabsServiceConnection: CustomTabsServiceConnection? = null
    private var authResultListener: AuthResultListener? = null
    private lateinit var mainActivity: Activity

    fun authenticate( callback: (Boolean) -> Unit) {

        val authConfig = AuthorizationServiceConfiguration(
            Uri.parse(authorizationEndpoint),
            Uri.parse(tokenEndpoint)
        )
        val authRequest = AuthorizationRequest.Builder(
            authConfig,
            CLIENT_ID,
            ResponseTypeValues.CODE,
            Uri.parse(redirectUri)
        )
            .setScope(scope)
            .setPrompt("login")
            .build()



        authService = AuthorizationService(context)

        setAuthResultListener(SpotifyRequest())

        val authIntent = authService.getAuthorizationRequestIntent(authRequest)
        //val authIntent = authService.createCustomTabsIntentBuilder(authRequest.toUri()).build()
        //authIntent.launchUrl(context, authRequest.toUri())
        (context as Activity).startActivityForResult(authIntent, AUTHENTICATION_REQUEST_CODE)


    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("PAPAnata2", "ENTRO AL ACTIVITY RESULT: $requestCode , $resultCode, $data")
        handleAuthResponse(requestCode, resultCode, data)

    }
    fun setAuthResultListener(listener: AuthResultListener) {
        authResultListener = listener
    }

    fun handleAuthResponse(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTHENTICATION_REQUEST_CODE) {
            val resp = AuthorizationResponse.fromIntent(data!!)
            val ex = AuthorizationException.fromIntent(data)
            Log.d("SpotifyAuthorization", "Authorization response: $resp")
            if (resultCode == Activity.RESULT_OK && resp != null) {
                exchangeAuthorizationCodeForAccessToken(resp.authorizationCode) { isAuthenticated ->
                    if (isAuthenticated) {
                        Log.d("SpotifyAuthorization", "Successful authorization")
                        // Cerrar la pestaña del navegador
                        // closeCustomTabsSession()
                    } else {
                        Log.e("SpotifyAuthorization", "Error during authorization")
                    }
                }
            } else {
                Log.e("SpotifyAuthorization", "Authorization failed: $ex")
                Log.e("SpotifyAuthorization", "Error during authorization: $ex")
            }
        }
    }

    private fun exchangeAuthorizationCodeForAccessToken(
        authorizationCode: String?,
        callback: (Boolean) -> Unit
    ) {
        val tokenRequest = TokenRequest.Builder(
            AuthorizationServiceConfiguration(Uri.parse(authorizationEndpoint), Uri.parse(tokenEndpoint)),
            CLIENT_ID
        )
            .setAuthorizationCode(authorizationCode)
            .setRedirectUri(Uri.parse(redirectUri))
            .build()

        authService.performTokenRequest(tokenRequest) { response, ex ->
            if (response != null) {
                accessToken = response.accessToken!!
                Log.d("SpotifyAuthorization", "Access Token: $accessToken")
                Log.d("SpotifyAuthorization", "Access token exchange response: $response")
                Log.d("SpotifyAuthorization", "Access token: $accessToken")
                authResultListener?.onAuthResult(AUTHENTICATION_REQUEST_CODE, Activity.RESULT_OK, null)
                obtenerToken(accessToken)
                callback.invoke(true)
            } else {
                Log.e("SpotifyAuthorization", "Error during token exchange: $ex")
                Log.e("SpotifyAuthorization", "Error during authorization: $ex")
                callback.invoke(false)
            }
        }
    }

    private fun obtenerToken(tokenSpotify: String) {
        val sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("tokenSpotify", tokenSpotify)
        editor.apply()
    }
    private fun closeCustomTabsSession() {
        customTabsSession?.mayLaunchUrl(Uri.EMPTY, null, null)
        customTabsSession = null
        customTabsServiceConnection?.let {
            context.unbindService(it)
            customTabsServiceConnection = null
        }

        val packageName = "com.android.chrome" // Nombre del paquete de Chrome
        val session: CustomTabsSession? = customTabsSession
        if (session != null) {
            session.mayLaunchUrl(null, null, null)
        }
        customTabsSession = null
        customTabsClient?.warmup(0)
        customTabsClient?.newSession(object : CustomTabsCallback() {
            override fun onNavigationEvent(navigationEvent: Int, extras: Bundle?) {
                super.onNavigationEvent(navigationEvent, extras)
                if (navigationEvent == NAVIGATION_FINISHED) {
                    customTabsClient?.let {
                        customTabsServiceConnection?.let { it1 -> context.unbindService(it1) }
                        customTabsClient = null
                    }
                }
            }
        })
        CustomTabsClient.bindCustomTabsService(context, packageName, object : CustomTabsServiceConnection() {
            override fun onCustomTabsServiceConnected(name: ComponentName, client: CustomTabsClient) {
                customTabsClient = client
                customTabsClient?.warmup(0)
            }

            override fun onServiceDisconnected(name: ComponentName) {
                customTabsClient = null
            }
        })
    }


    companion object {
        private const val AUTHENTICATION_REQUEST_CODE = 77
    }
}


