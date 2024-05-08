package com.miniproject.mnoutilityservice.exception;


import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String message ) {
        super(message);
    }


}