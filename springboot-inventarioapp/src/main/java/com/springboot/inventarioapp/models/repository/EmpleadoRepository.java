package com.springboot.inventarioapp.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.inventarioapp.models.entity.Empleado;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {

}
