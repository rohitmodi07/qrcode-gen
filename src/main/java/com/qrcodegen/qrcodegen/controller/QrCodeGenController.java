package com.qrcodegen.qrcodegen.controller;

import com.google.zxing.WriterException;
import com.qrcodegen.qrcodegen.service.QRCodeGenReaderService;
import com.qrcodegen.qrcodegen.service.QRCodeGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class QrCodeGenController {
    @Autowired
    private QRCodeGenService qrCodeGenService;
    @Autowired
    private QRCodeGenReaderService qrCodeGenReaderService;

    @GetMapping("/genQrCode")
    public ResponseEntity<byte[]> generateQRCode(@RequestParam String webAddress) throws IOException, WriterException {
        byte[] qrCode = qrCodeGenService.generateQRCode(webAddress);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return ResponseEntity.ok().headers(headers).body(qrCode);
    }
    @PostMapping(value = "/readQrCode", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> readQRCode(@RequestPart("file") MultipartFile file) throws IOException {
        String result = qrCodeGenReaderService.readQRCode(file.getBytes());
        return ResponseEntity.ok(result);
    }
}
