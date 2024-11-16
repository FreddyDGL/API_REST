package com.freddy.rest.rest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freddy.rest.rest.Entities.Producto;

public interface ProductoRepository extends JpaRepository <Producto, Long>{

}
