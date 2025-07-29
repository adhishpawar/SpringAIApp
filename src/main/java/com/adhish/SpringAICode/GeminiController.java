package com.adhish.SpringAICode;

import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GeminiController {

    @Autowired
    private VertexAiGeminiChatModel chatModel;

    @GetMapping("/api/{message}")
    public String getResponse(@PathVariable String message) {
        return chatModel.call(message);
    }
}
