
package com.lkm.utils;

import com.lkm.entity.StudentDTO;
import com.lkm.entity.StudentEntity;
import org.springframework.beans.BeanUtils;

public class ConverterUtils {

    //convert dto to entity
    public static StudentEntity convertDTOToEntity(StudentDTO dto){
        StudentEntity entity=new StudentEntity();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }

    //convert entity to dto
    public static StudentDTO convertEntityToDTO(StudentEntity entity){
        StudentDTO dto=new StudentDTO();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }

}
