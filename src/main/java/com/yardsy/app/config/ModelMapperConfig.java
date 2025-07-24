package com.yardsy.app.config;

import com.yardsy.app.dto.ItemRequestDto;
import com.yardsy.app.dto.YardSaleRequestDto;
import com.yardsy.app.model.Item;
import com.yardsy.app.model.YardSale;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
       ModelMapper modelMapper = new ModelMapper();

       modelMapper.createTypeMap(ItemRequestDto.class, Item.class)
               .addMappings(mapper -> mapper.skip(Item::setId));
        modelMapper.createTypeMap(YardSaleRequestDto.class, YardSale.class)
                .addMappings(mapper -> mapper.skip(YardSale::setId));
       return modelMapper;
    }
}
