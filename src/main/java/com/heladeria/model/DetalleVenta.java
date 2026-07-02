package com.heladeria.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    private BigDecimal precio;

    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public DetalleVenta() {
    }

    public DetalleVenta(Long id,
                        Integer cantidad,
                        BigDecimal precio,
                        BigDecimal subtotal,
                        Venta venta,
                        Producto producto) {

        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
        this.venta = venta;
        this.producto = producto;
    }

    @PrePersist
    @PreUpdate
    public void calcularSubtotal() {

        if(precio!=null && cantidad!=null){

            subtotal = precio.multiply(BigDecimal.valueOf(cantidad));

        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public Integer getCantidad(){
        return cantidad;
    }

    public void setCantidad(Integer cantidad){
        this.cantidad=cantidad;
    }

    public BigDecimal getPrecio(){
        return precio;
    }

    public void setPrecio(BigDecimal precio){
        this.precio=precio;
    }

    public BigDecimal getSubtotal(){
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal){
        this.subtotal=subtotal;
    }

    public Venta getVenta(){
        return venta;
    }

    public void setVenta(Venta venta){
        this.venta=venta;
    }

    public Producto getProducto(){
        return producto;
    }

    public void setProducto(Producto producto){
        this.producto=producto;
    }

}