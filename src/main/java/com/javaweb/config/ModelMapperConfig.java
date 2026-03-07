package com.javaweb.config;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.javaweb")
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setAmbiguityIgnored(true);
        modelMapper.typeMap(BuildingDTO.class, BuildingEntity.class)
                .addMappings(mapper ->
                        mapper.map(BuildingDTO::getRentPrice,
                                BuildingEntity::setRentprice)
                );



        return modelMapper;
    }
}
