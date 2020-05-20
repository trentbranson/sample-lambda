package au.com.auspost.lambda.service

import io.mockk.every
import io.mockk.mockkStatic
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class TimeServiceTest {
    private val timeService: TimeService = TimeService()

    @Test
    fun `should return the hello message`() {

        val now = LocalDateTime.of(2020, 1, 1, 1, 1)

        mockkStatic(LocalDateTime::class)
        every { LocalDateTime.now() } returns now

        assertEquals(timeService.getDateTimeString(), "Current Date and Time is: $now")
    }
}
