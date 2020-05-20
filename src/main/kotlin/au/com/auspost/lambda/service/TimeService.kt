package au.com.auspost.lambda.service

import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimeService @Inject constructor() { // Dagger requires @Inject annotation even when there is nothing to inject
    fun getDateTimeString() = "Current Date and Time is: ${LocalDateTime.now()}"
}
