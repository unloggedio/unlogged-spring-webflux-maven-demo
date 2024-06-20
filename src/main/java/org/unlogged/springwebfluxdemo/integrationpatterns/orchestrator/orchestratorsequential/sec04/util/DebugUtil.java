package org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.dto.OrchestrationRequestContext;

public class DebugUtil {

    public static void print(OrchestrationRequestContext ctx){
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ctx));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
