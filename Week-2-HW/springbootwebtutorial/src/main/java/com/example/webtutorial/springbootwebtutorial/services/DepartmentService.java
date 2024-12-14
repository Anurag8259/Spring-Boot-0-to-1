package com.example.webtutorial.springbootwebtutorial.services;

import ch.qos.logback.core.model.Model;
import com.example.webtutorial.springbootwebtutorial.dto.DepartmentDTO;
import com.example.webtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.example.webtutorial.springbootwebtutorial.entities.DepartmentEntity;
import com.example.webtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.example.webtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.example.webtutorial.springbootwebtutorial.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final ModelMapper mapper;
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository,ModelMapper mapper){
        this.departmentRepository=departmentRepository;
        this.mapper=mapper;
    }


    public Optional<DepartmentDTO> getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).map(departmentEntity->mapper.map(departmentEntity, DepartmentDTO.class));


    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities=departmentRepository.findAll();

        return departmentEntities
                .stream()
                .map(departmentEntity -> mapper.map(departmentEntity,DepartmentDTO.class))
                .collect(Collectors.toList());
    }


    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity=mapper.map(departmentDTO,DepartmentEntity.class);
        departmentRepository.save(departmentEntity);
        return mapper.map(departmentEntity,DepartmentDTO.class);
    }


    public DepartmentDTO createDepartmentById(Long id,DepartmentDTO  departmentDTO) {
        isExistByDepartmentId(id);
        DepartmentEntity departmentEntity=mapper.map(departmentDTO,DepartmentEntity.class);
        departmentEntity.setId(id);
        DepartmentEntity save=departmentRepository.save(departmentEntity);
        return mapper.map(save,DepartmentDTO.class);
    }

    private void isExistByDepartmentId(Long id) {
        Boolean find=departmentRepository.existsById(id);
        if(!find) throw new ResourceNotFoundException("Department not found with id "+id);
    }

    public Boolean deleteDepartmentById(Long id) {
        isExistByDepartmentId(id);
        departmentRepository.deleteById(id);
        return true;
    }

    public DepartmentDTO partiallyUpdateDepartmentById(Long departmentId, Map<String, Object> update) {
        isExistByDepartmentId(departmentId);
        DepartmentEntity departmentEntity=departmentRepository.findById(departmentId).get();
        update.forEach((field,value)->{
            Field fieldToBeUpdated= ReflectionUtils.findField(DepartmentEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,departmentEntity,value);
        });
        return mapper.map(departmentRepository.save(departmentEntity),DepartmentDTO.class);
    }
}
