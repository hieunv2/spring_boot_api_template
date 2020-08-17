package com.artech.api.controller;

import com.artech.api.entity.Category;
import com.artech.api.reponse.APIResponse;
import com.artech.api.reponse.CategoryDTO;
import com.artech.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private static final int OK = 200;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>>  getAll(){
        return ResponseEntity.status(OK)
                .body(categoryService.getAll());

    }
    @GetMapping("/status")
    public ResponseEntity<APIResponse<List<CategoryDTO>>> getCategoryByStatus(){
        List<CategoryDTO> categoryList = categoryService.getCategoryByStatus();
        APIResponse<List<CategoryDTO>> listAPIResponse = new APIResponse<>();
        listAPIResponse.setStatus(HttpStatus.OK.value());
        listAPIResponse.setMessage("get all thanh cong");
        listAPIResponse.setData(categoryList);
        return new ResponseEntity<>(listAPIResponse, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<CategoryDTO>> getById(@PathVariable("id") int id){
        CategoryDTO cateDTO = categoryService.getById(id);
        APIResponse<CategoryDTO> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("lay du lieu thanh cong");
        apiResponse.setData(cateDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<APIResponse<CategoryDTO>> create(@RequestBody CategoryDTO categoryDTO){

       CategoryDTO cateDTO = categoryService.create(categoryDTO);
        APIResponse<CategoryDTO> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("them moi thanh cong");
        apiResponse.setData(cateDTO);
       return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<CategoryDTO>> update(@RequestBody CategoryDTO categoryDTO,@PathVariable("id") int id){
        CategoryDTO cateDTO = categoryService.update(categoryDTO,id);
        APIResponse<CategoryDTO> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("cap nhat thanh cong");
        apiResponse.setData(cateDTO);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<CategoryDTO>> delete(@PathVariable("id") int id){
        CategoryDTO categoryDTO = categoryService.delete(id);
        APIResponse<CategoryDTO> apiResponse = new APIResponse<>();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("xoa thanh cong");
        apiResponse.setData(categoryDTO);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }
    @GetMapping("/page")
    public ResponseEntity<APIResponse<Page<CategoryDTO>>> getAllCateByPage(
            @RequestParam(defaultValue = "",required = false) String searchValue,
            @RequestParam(defaultValue = "0",required = false) Integer page,
            @RequestParam(defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(defaultValue = "categoryId",required = false) String sortBy
    ){
        Page<CategoryDTO> categoryListDTO = categoryService.getAllByPage(searchValue,page,pageSize,sortBy);
        APIResponse<Page<CategoryDTO>> pageAPIResponse =new APIResponse<>();
        pageAPIResponse.setData(categoryListDTO);
        pageAPIResponse.setMessage("get theo page thanh cong");
        pageAPIResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(pageAPIResponse, HttpStatus.OK);

    }


}
