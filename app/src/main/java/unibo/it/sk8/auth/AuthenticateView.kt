package unibo.it.sk8.auth

interface AuthenticateView {

    fun authenticate()

    fun signUp()

    fun signIn()

    fun toggleAuthenticationMode()

    fun setEmailAddress(emailAddress: String)

    fun setPassword(password: String)

}