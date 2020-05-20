package au.com.auspost.lambda

import au.com.auspost.lambda.service.HelloService
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import dagger.Component
import kotlinx.serialization.Serializable
import java.io.*
import javax.inject.Singleton

@Serializable
data class HelloRequest(val name: String)
@Serializable
data class HelloResponse(val test: String)

@Singleton
@Component
interface ControllerComponent {
    fun helloService(): HelloService
}

class Controller() : RequestStreamHandler {
    private var helloService = DaggerControllerComponent.create().helloService()

    // this secondary constructor is purely for testing, it's much nicer/easier than trying to spy on the above call
    constructor(helloService: HelloService) : this() {
        this.helloService = helloService
    }

    override fun handleRequest(input: InputStream?, output: OutputStream?, context: Context?) {
        if (input == null || output == null) throw RuntimeException()
        val helloRequest = input.readAsJson(HelloRequest.serializer())

        output.writeAsJson(HelloResponse.serializer(),
            HelloResponse(test = helloService.getMessageFor(helloRequest.name)))
    }
}


