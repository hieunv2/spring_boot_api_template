package com.artech.api.controller;

import com.artech.api.services.ProductService;
import com.artech.api.entity.Product;
import com.artech.api.reponse.APIResponse;
import com.artech.api.reponse.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private static final int OK = 200;
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>>  getAllCate(){
        return ResponseEntity.status(OK)
                .body(productService.getAll());

    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ProductDTO>> getById(@PathVariable("id") int id){
        ProductDTO productDTO = productService.getById(id);
        APIResponse<ProductDTO> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("lay du lieu thanh cong");
        apiResponse.setData(productDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<APIResponse<ProductDTO>> create(@RequestBody ProductDTO productDTO){

        ProductDTO proDTO = productService.create(productDTO);
        APIResponse<ProductDTO> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("them moi thanh cong");
        apiResponse.setData(proDTO);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ProductDTO>> update(@RequestBody ProductDTO productDTO,@PathVariable("id") int id){
        ProductDTO proDTO = productService.update(productDTO,id);
        APIResponse<ProductDTO> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("cap nhat thanh cong");
        apiResponse.setData(proDTO);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<ProductDTO>> delete(@PathVariable("id") int id){
        ProductDTO productDTO = productService.delete(id);
        APIResponse<ProductDTO> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("xoa thanh cong");
        apiResponse.setData(productDTO);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }
    @GetMapping("/page")
    public ResponseEntity<APIResponse<Page<ProductDTO>>> getAllProductByPage(
            @RequestParam(defaultValue = "",required = false) String searchValue,
            @RequestParam(defaultValue = "0",required = false) Integer page,
            @RequestParam(defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(defaultValue = "productId",required = false) String sortBy
    ){
        Page<ProductDTO> productListDTO = productService.getAllByPage(searchValue,page,pageSize,sortBy);
        APIResponse<Page<ProductDTO>> pageAPIResponse =new APIResponse<>();
        pageAPIResponse.setData(productListDTO);
        pageAPIResponse.setMessage("get theo page thanh cong");
        pageAPIResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(pageAPIResponse, HttpStatus.OK);

    }
    @GetMapping("listInteger/{productId}")
    public ResponseEntity<APIResponse<List<Integer>>> getAllCategoryByProductId ( @PathVariable("productId") int productId){
        List<Integer> integers =  productService.getAllCategoryByProductId(productId);
        APIResponse<List<Integer>> response = new APIResponse<>();
        response.setData(integers);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("get all category thanh cong");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
