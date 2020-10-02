package com.springboot.inventarioapp.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.inventarioapp.models.entity.Tienda;

public interface TiendaRepository extends CrudRepository<Tienda, Long> {

}
