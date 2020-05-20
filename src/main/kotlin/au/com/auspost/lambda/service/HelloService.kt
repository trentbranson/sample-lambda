package au.com.auspost.lambda.service

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HelloService @Inject constructor(private val timeService: TimeService) {
    fun getMessageFor(person: String) = "Hello $person. ${timeService.getDateTimeString()}"
}
