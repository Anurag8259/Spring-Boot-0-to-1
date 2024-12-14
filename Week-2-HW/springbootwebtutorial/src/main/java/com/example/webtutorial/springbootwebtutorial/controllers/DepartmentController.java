package com.example.webtutorial.springbootwebtutorial.controllers;

import com.example.webtutorial.springbootwebtutorial.dto.DepartmentDTO;
import com.example.webtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.example.webtutorial.springbootwebtutorial.entities.DepartmentEntity;
import com.example.webtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.example.webtutorial.springbootwebtutorial.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/departments")
public class DepartmentController {


    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService=departmentService;
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentsById(@PathVariable Long departmentId){

        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(departmentId);

        return departmentDTO.map(departmentDTO1 -> ok(departmentDTO1)).orElseThrow(()->new ResourceNotFoundException("Department not found with id "+departmentId));


    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        return ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO){
        DepartmentDTO dep=departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(dep,HttpStatus.CREATED);

    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> createDepartmentById(@RequestBody @Valid DepartmentDTO departmentDTO,@PathVariable Long departmentId){
        return ResponseEntity.ok(departmentService.createDepartmentById(departmentId,departmentDTO));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId){
        Boolean check=departmentService.deleteDepartmentById(departmentId);
        if(check) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> partiallyUpdateDepartmentById(@PathVariable Long departmentId, @RequestBody Map<String , Object> update){
        DepartmentDTO departmentDTO=departmentService.partiallyUpdateDepartmentById(departmentId,update);
        if(departmentDTO==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(departmentDTO);
    }
}
