package example.alaa.registration

fun String.isValidEmail(): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    return emailRegex.matches(this)
}

fun String.phoneNumberIsValidate(numberLength : Int , prefix : String) : Boolean {
    return this.length == numberLength && this.startsWith(prefix)
}