package com.miniproject.mnoutilityservice.exception;


import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message ) {
        super(message);
    }


}