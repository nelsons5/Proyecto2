package com.springboot.inventarioapp.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.inventarioapp.models.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
