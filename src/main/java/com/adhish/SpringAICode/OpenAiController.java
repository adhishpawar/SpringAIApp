package com.adhish.SpringAICode;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OpenAiController {

    private final ChatClient chatClient;

    //For more than one models are in use
//   public OpenAiController(OpenAiChatModel chatModel){
//       this.chatClient = ChatClient.create(chatModel);
//   }

    ChatMemory chatMemory = MessageWindowChatMemory.builder().build();

    //For single model in use
    public OpenAiController(ChatClient.Builder builder){
        this.chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor
                        .builder(chatMemory)
                        .build())
                .build();
    }

    @PostMapping("/api/{message}")
    public ResponseEntity<String> getAnswer(@PathVariable String message) {
        ChatResponse chatResponse = chatClient
                .prompt(message)
                .call()
                .chatResponse();

        System.out.println(chatResponse.getMetadata().getModel());


        String response = chatResponse
                .getResult()
                .getOutput()
                .getText();

        return  ResponseEntity.ok(response);
    }
}
