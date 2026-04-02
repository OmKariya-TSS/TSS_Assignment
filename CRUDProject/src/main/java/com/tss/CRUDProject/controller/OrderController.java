package com.tss.CRUDProject.controller;


import com.tss.CRUDProject.dto.request.OrderRequestDTO;
import com.tss.CRUDProject.dto.response.OrderResponseDTO;
import com.tss.CRUDProject.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<OrderResponseDTO> addOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.status(201).body(orderService.createOrder(orderRequestDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<OrderResponseDTO>> getAllOrders(@RequestParam(defaultValue = "0") Integer pageNumber,
                                               @RequestParam(defaultValue = "3") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.status(200).body(orderService.getAllOrders(pageable));
    }
    @GetMapping("id/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(orderService.getOrderById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long id, @RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.status(200).body(orderService.updateOrder(id, orderRequestDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(200).body("Order has been deleted");
    }
    @PutMapping("/{orderId}/products/{productId}/add")
    public ResponseEntity<OrderResponseDTO> addProductToOrder(
            @PathVariable Long orderId,
            @PathVariable Long productId) {
        return ResponseEntity.status(200).body(orderService.addProductToOrder(orderId, productId));
    }

    @PutMapping("/{orderId}/products/{productId}/remove")
    public ResponseEntity<OrderResponseDTO> removeProductFromOrder(
            @PathVariable Long orderId,
            @PathVariable Long productId) {
        return ResponseEntity.status(200).body(orderService.removeProductFromOrder(orderId, productId));
    }

}
