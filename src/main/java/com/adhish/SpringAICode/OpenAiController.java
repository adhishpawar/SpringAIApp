package com.adhish.SpringAICode;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



@RestController
public class OpenAiController {

    private final ChatClient chatClient;

    @Autowired
    @Qualifier("openAiEmbeddingModel")
    private EmbeddingModel embeddingModel;



    //For more than one models are in use
   public OpenAiController(OpenAiChatModel chatModel){
       this.chatClient = ChatClient.create(chatModel);
   }

//    ChatMemory chatMemory = MessageWindowChatMemory.builder().build();

    //For single model in use
//    public OpenAiController(ChatClient.Builder builder){
//        this.chatClient = builder
//                .defaultAdvisors(MessageChatMemoryAdvisor
//                        .builder(chatMemory)
//                        .build())
//                .build();
//    }

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


    @PostMapping("api/recommend")
    public String recommend(@RequestParam String type, @RequestParam String year, @RequestParam String lang){

       String tempt = """
                            I want to watch a {type} movie tonight with good rating,
                             looking for movies around this year {year}.
                             The language i am looking for is {lang}
                             Suggest one specific movie and tell me the cast and length of the movie.
                             
                             response format should be:
                             1.movie name
                             2.basic plot
                             3. cast
                             4.length
                             5.IMDB rating
                  
                      """;

        PromptTemplate promptTemplate = new PromptTemplate(tempt);
        Prompt prompt = promptTemplate.create(Map.of("type", type,  "year", year,"lang", lang));

        String response = chatClient
                .prompt(prompt)
                .call()
                .content();


        return response;
    }

    @PostMapping("/api/embedding")
    public float[] embedding(@RequestParam String text){
            return embeddingModel.embed(text);
    }


}
