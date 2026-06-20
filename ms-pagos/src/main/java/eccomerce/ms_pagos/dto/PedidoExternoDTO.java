package eccomerce.ms_pagos.dto;



public class PedidoExternoDTO {
     
    private Long id;
    private Double total;
    private Long usuarioId;
    private EstadoExternoDTO estado;
 
    public PedidoExternoDTO() {}
 
    public Long getId()                  { return id; }
    public Double getTotal()             { return total; }
    public Long getUsuarioId()           { return usuarioId; }
    public EstadoExternoDTO getEstado()  { return estado; }
 
    public void setId(Long id)                     { this.id = id; }
    public void setTotal(Double total)              { this.total = total; }
    public void setUsuarioId(Long usuarioId)        { this.usuarioId = usuarioId; }
    public void setEstado(EstadoExternoDTO estado)  { this.estado = estado; }

}
