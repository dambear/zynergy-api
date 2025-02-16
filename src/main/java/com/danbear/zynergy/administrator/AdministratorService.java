package com.danbear.zynergy.administrator;

import com.danbear.zynergy.administrator.dto.AdministratorDto;

import java.util.List;


public interface AdministratorService {
  List<AdministratorDto> findAllAdministrators();
  AdministratorDto findAdministratorById(Long id);
  AdministratorDto createAdministrator(AdministratorDto administratorDto);
  AdministratorDto updateAdministrator(AdministratorDto administratorDto, Long id);
  void deleteAdministrator(Long id);
}