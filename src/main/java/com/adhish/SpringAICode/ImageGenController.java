package com.adhish.SpringAICode;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

        ImagePrompt prompt = new ImagePrompt(query, OpenAiImageOptions.builder()
                .quality("hd")
                .height(1024)
                .width(1024)
                .style("natural")
                .build());

        ImageResponse response = OpenAiImageModel.call(prompt);
        return response.getResult().getOutput().getUrl();
    }

    @PostMapping("image/describe")
    public String descImage(@RequestParam String query, @RequestParam MultipartFile file){
        // User uses consumer object UserSpec
        return chatClient.prompt()
                .user(us -> us.text(query)
                        .media(MimeTypeUtils.IMAGE_JPEG, file.getResource()))
                .call()
                .content();

    }
}
