package org.unlogged.springwebfluxdemo.controller;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.exception.WebfluxError;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/others")
public class Others {

    private static final Set<String> ALLOWED_CONTENT_TYPES =
            Set.of(MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE);

    private Boolean checkImageValidity(DataBuffer dataBuffer, MediaType contentType) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(dataBuffer.asInputStream());
        dataBuffer.readPosition(0);
        if (bufferedImage == null) {
            if (ALLOWED_CONTENT_TYPES.contains(contentType.toString())) {
                return false;
            }
        }
        return true;
    }

    public Mono<String> getStingO1(boolean capitalize, Optional<WebfluxError> webfluxError) {
        return Mono.just(webfluxError)
                .map(optional ->
                {
                    String returnVal = "";
                    if (optional.isEmpty()) {
                        returnVal = "Empty";
                    } else {
                        returnVal = webfluxError.get().getMessage();
                    }

                    if (capitalize) {
                        returnVal = returnVal.toUpperCase();
                    }
                    return returnVal;
                });
    }
}
