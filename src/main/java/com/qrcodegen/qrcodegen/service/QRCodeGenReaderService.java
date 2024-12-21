package com.qrcodegen.qrcodegen.service;

import com.google.zxing.MultiFormatReader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class QRCodeGenReaderService {
    public String readQRCode(byte[] imageBytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);

        BufferedImageLuminanceSource imageSource = new BufferedImageLuminanceSource(bufferedImage);
        HybridBinarizer hybridBinarizer = new HybridBinarizer(imageSource);
        com.google.zxing.BinaryBitmap bitmap = new com.google.zxing.BinaryBitmap(hybridBinarizer);
        MultiFormatReader reader = new MultiFormatReader();

        try {
            Result result = reader.decode(bitmap);
            return result.getText();
        } catch (ReaderException e) {
            return "Error occurred while reading uploaded QR code image";
        }
    }
}
