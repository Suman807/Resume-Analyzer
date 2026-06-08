package com.sb.Resume.Analyzer.advisor;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class CustomAdvisorConfiguration implements CallAdvisor, StreamAdvisor {

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        log.info("Request: {}", chatClientRequest
                                        .prompt()
                                        .getContents());

        ChatClientResponse response = callAdvisorChain.nextCall(chatClientRequest);

        log.info("Response: {}", response
                                        .chatResponse()
                                        .getResult()
                                        .getOutput()
                                        .getText());

        log.info("Token consumed: {}", response
                                            .chatResponse()
                                            .getMetadata()
                                            .getUsage()
                                            .getTotalTokens());
        return response;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest,
            StreamAdvisorChain streamAdvisorChain) {
        // TODO Auto-generated method stub
        return null;
    }


}
