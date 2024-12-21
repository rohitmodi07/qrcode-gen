QR Code Generation App ------------------------

# Objectives

  1. generate a qr code for provided web address or url
  2. read a qr code and extract the details

# End point details
  1. /genQrCode
     This is a Get request where user provides web url and it returns a QR code. Upon scanning the QR code, it redirects user to the web page of web url

  2. /readQrCode
     This is POST request where user upload a QR code as a part of request and it returns the details such as web url.


# Project running Instruction

CLone the project
start the project - gradlew bootrun
open "http://localhost:8080/swagger-ui.html" and start executing the apis.

# technology uset
Java 17, Springboot 3.x, gradle 8.x
          
     
