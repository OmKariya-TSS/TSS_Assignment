package com.tss.CRUDProject.service;

import com.tss.CRUDProject.dto.request.OrderRequestDTO;
import com.tss.CRUDProject.dto.response.OrderResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);
    OrderResponseDTO getOrderById(Long id);
    OrderResponseDTO updateOrder(Long id,OrderRequestDTO orderRequestDTO);
    void deleteOrder(Long id);
    Page<OrderResponseDTO> getAllOrders(Pageable pageable);
    OrderResponseDTO removeProductFromOrder(Long orderId, Long productId);
    OrderResponseDTO addProductToOrder(Long orderId, Long productId);
}
