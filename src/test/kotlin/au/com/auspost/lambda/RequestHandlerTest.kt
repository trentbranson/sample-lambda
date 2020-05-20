package au.com.auspost.lambda

import au.com.auspost.lambda.service.HelloService
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import io.mockk.every
import io.mockk.mockk
import kotlinx.io.ByteArrayOutputStream
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RequestHandlerTest {
    private val helloService: HelloService = mockk()
    private val controller: RequestStreamHandler = Controller(helloService)

    @Test
    fun `should return the hello message`() {
        val person = "person"
        val inputStream = """{"name": "$person"}""".toInputStream()
        val outputStream = ByteArrayOutputStream()

        every { helloService.getMessageFor(person) } returns "message"

        controller.handleRequest(inputStream, outputStream, null)

        assertEquals("""{"test":"message"}""", outputStream.toString("UTF-8"))
    }
}
