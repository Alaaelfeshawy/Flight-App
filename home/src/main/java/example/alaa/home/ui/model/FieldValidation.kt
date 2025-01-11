package example.alaa.home.ui.model

data class FieldValidation(
    val value : String?=null,
    val isValid : Boolean?=null,
    val errorMessage : String?=null
)

interface ayhaga
sealed class test : ayhaga {
    data object test2 : test()
}