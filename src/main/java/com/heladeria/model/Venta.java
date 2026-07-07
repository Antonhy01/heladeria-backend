package com.heladeria.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.00", message = "El total no puede ser negativo")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    public Venta() {
    }

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDateTime.now();
    }

    public Venta(Long id, LocalDateTime fecha, BigDecimal total, Cliente cliente) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}