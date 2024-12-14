package com.example.webtutorial.springbootwebtutorial.controllers;

import com.example.webtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.example.webtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.example.webtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.example.webtutorial.springbootwebtutorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {


    private final EmployeeService employeeService;
//    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }


//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret Message: shgfhdhehdh";
//    }



    @GetMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeId(@PathVariable Long employeeId){
//        return new EmployeeDTO(employeeId,"Anurag","abc@gmail.com",22, LocalDate.of(2024,2,24),Boolean.TRUE);

        Optional<EmployeeDTO> employeeDTO= employeeService.getEmployeeId(employeeId);
//        if(employeeDTO==null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(employeeDTO);
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1)).orElseThrow(()->new ResourceNotFoundException("Employee not found with id "+employeeId));
    }

//    @ExceptionHandler(NoSuchElementException.class) //only for this controller
//    public ResponseEntity<String> handleEmployeeNotFound(NoSuchElementException exception){
//        return new ResponseEntity<>("Employee not found",HttpStatus.NOT_FOUND);
//    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(@RequestParam(required = false) Integer age,
                                 @RequestParam(required = false) String gender){
//        return "Hi age = " + age + " Gender = " + gender;
//        http://localhost:8080/employee?age=22&gender=F  ( both required)

         return ResponseEntity.ok(employeeService.getAllEmployee());
//        return employeeService.getAllEmployee();
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
//        inputEmployee.setId(100L);
//        return inputEmployee;
        EmployeeDTO employeeDTO=employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
//        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO,@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeId(employeeId,employeeDTO));
    }

    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean getDeleted=employeeService.deleteEmployeeId(employeeId);
        if(getDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
//        return employeeService.deleteEmployeeId(employeeId);
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String,Object> update, @PathVariable Long employeeId){
//        return employeeService.updatePartialEmployeeId(employeeId,update);

        EmployeeDTO employeeDTO=employeeService.updatePartialEmployeeId(employeeId,update);
        if(employeeDTO==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}


