package org.unlogged.springwebfluxdemo.controller;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
}
