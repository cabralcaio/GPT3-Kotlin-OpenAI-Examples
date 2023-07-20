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
        token = "API-KEY",
        timeout = Timeout(socket = 60.seconds)
    )

    val chatCompletionRequest = ChatCompletionRequest(
        model = ModelId("gpt-3.5-turbo"),
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

    val completion: ChatCompletion = openAi.chatCompletion(chatCompletionRequest)
    val question: String? = chatCompletionRequest.messages[1].content
    val answer: String? = completion.choices[0].message?.content
    println("User: $question")
    println("AI: $answer")
}