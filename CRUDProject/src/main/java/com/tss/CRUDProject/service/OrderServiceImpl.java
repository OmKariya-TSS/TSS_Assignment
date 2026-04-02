package com.tss.CRUDProject.service;

import com.tss.CRUDProject.dao.CustomerRepository;
import com.tss.CRUDProject.dao.OrderRepository;
import com.tss.CRUDProject.dao.ProductRepository;
import com.tss.CRUDProject.dto.request.OrderRequestDTO;
import com.tss.CRUDProject.dto.response.OrderResponseDTO;
import com.tss.CRUDProject.entity.Customer;
import com.tss.CRUDProject.entity.Order;
import com.tss.CRUDProject.entity.Product;
import com.tss.CRUDProject.exception.ResourceNotFoundException;
import com.tss.CRUDProject.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Customer customer = customerRepository.findById(orderRequestDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found", orderRequestDTO.getCustomerId()));

        List<Product> products = productRepository.findAllById(orderRequestDTO.getProductIds());

        Order order = new Order();
        order.setStatus(orderRequestDTO.getStatus());
        order.setCustomer(customer);
        order.setProducts(products);

        return orderMapper.toOrderResponseDTO(orderRepository.save(order));
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        return orderMapper.toOrderResponseDTO(orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order not found",id)));
    }

    @Override
    @Transactional
    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO orderRequestDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found", id));
        order.setStatus(orderRequestDTO.getStatus());
        if (orderRequestDTO.getProductIds() != null) {
            List<Product> products = productRepository.findAllById(orderRequestDTO.getProductIds());
            order.setProducts(products);
        }
        return orderMapper.toOrderResponseDTO(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(Long id) {
        boolean exists = orderRepository.existsById(id);
        if(!exists){
            throw new ResourceNotFoundException("Order not found",id);
        }
        orderRepository.deleteById(id);
    }

    @Override
    public Page<OrderResponseDTO> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).map(orderMapper::toOrderResponseDTO);
    }

    @Transactional
    @Override
    public OrderResponseDTO addProductToOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", productId));
        order.getProducts().add(product);
        return orderMapper.toOrderResponseDTO(orderRepository.save(order));
    }

    @Transactional
    @Override
    public OrderResponseDTO removeProductFromOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

        order.getProducts().removeIf(p -> p.getProductId().equals(productId));

        return orderMapper.toOrderResponseDTO(orderRepository.save(order));
    }
}
