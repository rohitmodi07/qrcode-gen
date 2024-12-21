package com.qrcodegen.qrcodegen.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QRCodeGenService {

    public byte[] generateQRCode(String webAddress) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(webAddress, BarcodeFormat.QR_CODE, 220, 220);

        BufferedImage bufferedImage = new BufferedImage(220, 220, BufferedImage.TYPE_INT_RGB);
        bufferedImage.createGraphics().fillRect(0, 0, 220, 220);
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < 220; i++) {
            for (int j = 0; j < 220; j++) {
                if (bitMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ByteArrayOutputStream byteArrOS = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrOS);
        return byteArrOS.toByteArray();
    }
}
