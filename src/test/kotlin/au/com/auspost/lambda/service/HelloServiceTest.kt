package au.com.auspost.lambda.service

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class HelloServiceTest {
    private val timeService: TimeService = mockk()
    private val helloService: HelloService = HelloService(timeService)

    @Test
    fun `should return the hello message`() {
        every { timeService.getDateTimeString() } returns "Current Date"

        assertEquals(helloService.getMessageFor("Person"),"Hello Person. Current Date")
    }
}
