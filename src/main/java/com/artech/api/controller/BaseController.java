package com.artech.api.controller;

import com.artech.api.converts.Convert;
import com.artech.api.entity.BaseEntity;
import com.artech.api.entity.Users;
import com.artech.api.exceptions.NotFoundException;
import com.artech.api.reponse.APIResponse;
import com.artech.api.reponse.ProductDTO;
import com.artech.api.request.UserRequest;
import com.artech.api.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class BaseController<M> {

    protected M model;

    private BaseService service;

    abstract protected void setModel();

    @Autowired
    private Convert convert;

    public BaseController(BaseService service){
        this.setModel();
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<APIResponse<Iterable<M>>> getAllCate(){
        Iterable<M> datas = service.findAll();
        APIResponse<Iterable<M>> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("lay du lieu thanh cong");
        apiResponse.setData(datas);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<M> getById(@PathVariable("id") int id){
//        Optional<BaseEntity> data = service.findById(id);
//        return response(convert.toResponse(data.get()));
//
//    }


    protected <D> ResponseEntity<APIResponse<D>> response(D data, M model) {
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
