package com.example.webtutorial.springbootwebtutorial.services;


import com.example.webtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.example.webtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.example.webtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.example.webtutorial.springbootwebtutorial.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// All the business logic is written here !!
@Service
public class EmployeeService {


    private final ModelMapper mapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository,ModelMapper mapper){
        this.employeeRepository=employeeRepository;
        this.mapper=mapper;
    }

    public Optional<EmployeeDTO> getEmployeeId(Long employeeId) {


        return employeeRepository.findById(employeeId).map(employeeEntity -> mapper.map(employeeEntity,EmployeeDTO.class));
//        EmployeeEntity employeeEntity= employeeRepository.findById(employeeId).orElse(null);
//        ModelMapper mapper=new ModelMapper();
//        return mapper.map(employeeEntity,EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployee() {
        List<EmployeeEntity> employeeEntities= employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> mapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity employeeEntity= mapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity emp =employeeRepository.save(employeeEntity);
        return mapper.map(emp,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeId(Long employeeId, EmployeeDTO employeeDTO) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity=mapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity save=employeeRepository.save(employeeEntity);
        return mapper.map(save,EmployeeDTO.class);
    }

    public void isExistsByEmployeeId(Long employeeId){
        Boolean exists=employeeRepository.existsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee not found with id "+employeeId);
//        return employeeRepository.existsById(employeeId);

    }

    public Boolean deleteEmployeeId(Long employeeId) {
        isExistsByEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeId(Long employeeId, Map<String, Object> update) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity=employeeRepository.findById(employeeId).get();
        update.forEach((field,value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
        return mapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }
}
