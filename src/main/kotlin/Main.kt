import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import kotlin.time.Duration.Companion.seconds

@OptIn(BetaOpenAI::class)
suspend fun main() {
    val openAi = OpenAI(
        token = System.getenv("OPENAI_API_KEY"),
        timeout = Timeout(socket = 60.seconds)
    )

    val chatCompletionRequest = ChatCompletionRequest(
        model = ModelId("gpt-3.5-turbo"),
        maxTokens = 256,
        temperature = 0.0,
        messages = listOf(
            ChatMessage(
                role = ChatRole.System,
                content = "You will be provided with statements, and your task is to convert them to standard English."
            ),
            ChatMessage(
                role = ChatRole.User,
                content = "She no went to the market."
            )
        )
    )

    val chatCompletionRequest2 = ChatCompletionRequest(
        model = ModelId("gpt-3.5-turbo"),
        maxTokens = 256,
        temperature = 0.0,
        messages = listOf(
            ChatMessage(
                role = ChatRole.System,
                content = "Summarize content you are provided with for a second-grade student."
            ),
            ChatMessage(
                role = ChatRole.User,
                content = "Jupiter is the fifth planet from the Sun and the largest in the Solar System. It is a gas giant with a mass one-thousandth that of the Sun, but two-and-a-half times that of all the other planets in the Solar System combined. Jupiter is one of the brightest objects visible to the naked eye in the night sky, and has been known to ancient civilizations since before recorded history. It is named after the Roman god Jupiter.[19] When viewed from Earth, Jupiter can be bright enough for its reflected light to cast visible shadows,[20] and is on average the third-brightest natural object in the night sky after the Moon and Venus."
            )
        )
    )

    val completion: ChatCompletion = openAi.chatCompletion(chatCompletionRequest)
    val question: String? = chatCompletionRequest.messages[1].content
    val answer: String? = completion.choices[0].message?.content
    println("User: $question")
    println("AI: $answer")

    val completion2: ChatCompletion = openAi.chatCompletion(chatCompletionRequest2)
    val question2: String? = chatCompletionRequest2.messages[1].content
    val answer2: String? = completion2.choices[0].message?.content
    println("User2: $question2")
    println("AI2: $answer2")
}