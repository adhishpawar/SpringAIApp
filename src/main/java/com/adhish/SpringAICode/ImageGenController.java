package com.adhish.SpringAICode;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ImageGenController {

    private ChatClient chatClient;

    private OpenAiImageModel OpenAiImageModel;

    public ImageGenController(OpenAiImageModel OpenAiImageModel, OpenAiChatModel chatModel){
        this.OpenAiImageModel= OpenAiImageModel;
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("image/{query}")
    public String genImage(@PathVariable String query){

        ImagePrompt prompt = new ImagePrompt(query);
        ImageResponse response = OpenAiImageModel.call(prompt);
        return response.getResult().getOutput().getUrl();
    }
}
