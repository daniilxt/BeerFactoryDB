package encryptor

import java.util.*

object BaseCoder {
    fun decode(encoded: String): String {
        val decodedBytes = Base64.getDecoder().decode(encoded)
        return decodedBytes.toString()
    }

    fun encode(decoded: String): String? {
        return Base64.getEncoder().encodeToString(decoded.toByteArray())

    }
}