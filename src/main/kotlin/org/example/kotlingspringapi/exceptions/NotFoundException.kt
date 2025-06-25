package org.example.kotlingspringapi.exceptions

import java.lang.RuntimeException

class NotFoundException(message: String) : RuntimeException(message) {
}