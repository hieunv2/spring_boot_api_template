package com.artech.api.controller;
import com.artech.api.entity.Users;
import com.artech.api.exceptions.NotFoundException;
import com.artech.api.reponse.APIResponse;
import com.artech.api.reponse.ProductDTO;
import com.artech.api.services.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


public abstract class BaseController<M> {


    private BaseService service;


    public BaseController(BaseService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<APIResponse<Iterable<M>>> getAllCate(){
        Iterable<M> datas = service.findAll();
        APIResponse<Iterable<M>> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Success");
        apiResponse.setData(datas);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<M>> getById(@PathVariable("id") int id){
        Optional<M> data = service.findById(id);
        APIResponse<M> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Success");
        apiResponse.setData(data.get());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity create(@RequestBody M request) {
        Optional<M> opt = service.save(request);

        if (!opt.isPresent()) throw new NotFoundException("Cannot add new User");

        APIResponse<M> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Success");
        apiResponse.setData(opt.get());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<M>> update(@RequestBody M modal, @PathVariable("id") int id){
        Optional<M> opt = service.update(modal,id);

        if (!opt.isPresent()) throw new NotFoundException("Cannot update entity");

        APIResponse<M> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Success");
        apiResponse.setData(opt.get());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<M>> delete(@PathVariable("id") int id){
        Optional<M> data = service.findById(id);
        if (!data.isPresent()) throw new NotFoundException("Cannot delete entity");
        service.delete(data.get());
        APIResponse<M> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Success");
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }




//    protected <D> ResponseEntity<APIResponse<D>> response(D data, M model) {
//        APIResponse<D> response = toResult(data);
//        return ResponseEntity.ok(response);
//    }

//    private <D> APIResponse<D> toResult(D data) {
//        if (data == null) throw new NotFoundException("Not found");
//
//        APIResponse<D> response = new APIResponse<>();
//        response.setStatus(200);
//        response.setMessage("Success");
//        response.setData(data);
//        return response;
//    }
}
