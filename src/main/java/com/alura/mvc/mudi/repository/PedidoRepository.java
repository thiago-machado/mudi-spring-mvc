package com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alura.mvc.mudi.model.Pedido;
import com.alura.mvc.mudi.model.StatusPedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long>{

	List<Pedido> findAll();

	List<Pedido> findByStatus(StatusPedido status);
}
