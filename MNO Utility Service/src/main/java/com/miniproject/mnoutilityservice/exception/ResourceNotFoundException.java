package com.miniproject.mnoutilityservice.exception;


import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message ) {
        super(message);
    }


}