package au.com.auspost.lambda

import kotlinx.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.Charset

fun String.toInputStream(): InputStream = ByteArrayInputStream(toByteArray(Charset.forName("UTF-8")))
