package au.com.auspost.lambda

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.BufferedReader
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.Charset

private val mapper = Json(JsonConfiguration.Stable)

fun <T> InputStream.readAsJson(serializer: DeserializationStrategy<T>): T {
    val bufferedReader = BufferedReader(this.reader(Charset.forName("UTF-8")))
    val jsonString = bufferedReader.use { it.readText() }
    return mapper.parse(serializer, jsonString);
}

fun <T> OutputStream.writeAsJson(serializer: SerializationStrategy<T>, content: T) {
    val jsonString = mapper.stringify(serializer, content)
    write(jsonString.toByteArray(Charset.forName("UTF-8")))
}
