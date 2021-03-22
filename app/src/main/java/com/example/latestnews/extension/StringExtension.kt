package com.example.latestnews.extension

private const val NUMBER_PATTERN = "{/number}"
private const val SLASH = "/"
private const val HYPHEN = "-"


fun String.replaceNumber(): String {
    return this.replace(NUMBER_PATTERN, "", true)
}

fun String.replaceSlash(): String {
    return this.replace(SLASH, HYPHEN)
}