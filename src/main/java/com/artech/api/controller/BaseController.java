package com.artech.api.controller;

import com.artech.api.exceptions.NotFoundException;
import com.artech.api.reponse.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public abstract class BaseController<S,C> {

    @Autowired
    protected S service;

    @Autowired
    protected C convert;

    protected <D> ResponseEntity<APIResponse<D>> response(D data) {
        APIResponse<D> response = toResult(data);
        return ResponseEntity.ok(response);
    }

    private <D> APIResponse<D> toResult(D data) {
        if (data == null) throw new NotFoundException("Not found");

        APIResponse<D> response = new APIResponse<>();
        response.setStatus(200);
        response.setMessage("Success");
        response.setData(data);
        return response;
    }
}
