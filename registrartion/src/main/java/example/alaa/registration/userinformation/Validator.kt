package example.alaa.registration.userinformation

import example.alaa.registration.R

object Validator {

    fun getPasswordCriteriaList(): List<PasswordCriteria> {
        return PasswordCriteria.entries
    }

    enum class PasswordCriteria(val nameRes: Int, var isMet: Boolean) {
        SPECIAL_CHAR(R.string.password_validation_special_characters_label, false),
        CAPITAL_LETTER_CHAR(R.string.password_at_least_one_capital_error_label, false),
        LONG(R.string.password_validation_characters_count_label, false),
        NO_SPACE(R.string.password_validation_spaces_label, false),
        IS_MATCH_CONFIRM_PASSWORD(R.string.password_should_match_confirm_password, false),
    }
}