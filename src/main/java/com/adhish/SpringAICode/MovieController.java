package com.adhish.SpringAICode;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;

import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class MovieController {

    private  ChatClient chatClient;

    //For more than one models are in use
    public MovieController(OpenAiChatModel chatModel){
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("movies")
    public List<String> getMovies(@RequestParam String name){

            String message = """
                                   List Top 5 movies of {name}
                                   {format}
                    """;

        ListOutputConverter opCon = new ListOutputConverter(new DefaultConversionService());

        PromptTemplate template = PromptTemplate.builder()
                .template(message)
                .variables(Map.of(
                        "name", name,
                        "format", opCon.getFormat()
                ))
                .build();

        Prompt prompt = template.create();
        List<String> movies = opCon.convert(chatClient.prompt(prompt).call().content());

        return movies;
    }

}
