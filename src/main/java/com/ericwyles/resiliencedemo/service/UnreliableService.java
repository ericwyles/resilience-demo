package com.ericwyles.resiliencedemo.service;

import com.ericwyles.resiliencedemo.exception.UnreliableServiceException;
import org.springframework.stereotype.Service;

@Service
public class UnreliableService {

    public String success() {
        return "This is the success path";
    }

    public String exception() {
        throw new UnreliableServiceException("threw an exception from exeption()");
    }

}
