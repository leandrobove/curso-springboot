package br.com.meudominio.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.meudominio.course.entities.Order;
import br.com.meudominio.course.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	@Autowired
	private OrderService service;

	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		List<Order> listOrders = service.findAll();

		return ResponseEntity.ok().body(listOrders);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order objOrder = service.findById(id);

		return ResponseEntity.ok().body(objOrder);
	}
}
