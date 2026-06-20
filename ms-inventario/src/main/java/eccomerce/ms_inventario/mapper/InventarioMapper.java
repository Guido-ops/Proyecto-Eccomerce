package eccomerce.ms_inventario.mapper;

import eccomerce.ms_inventario.dto.InventarioCreacionRequestDTO;
import eccomerce.ms_inventario.dto.InventarioResumenResponseDTO;
import eccomerce.ms_inventario.dto.MovimientoInventarioResponseDTO;
import eccomerce.ms_inventario.model.Inventario;
import eccomerce.ms_inventario.model.MovimientoInventario;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    // ── Inventario → InventarioResumenResponseDTO ─────────────────────────────

    public InventarioResumenResponseDTO toResumenResponseDTO(Inventario inventario) {
        Boolean stockBajo = inventario.getStockActual() <= inventario.getStockMinimo();
        return new InventarioResumenResponseDTO(
            inventario.getId(),
            inventario.getProductoId(),
            inventario.getStockActual(),
            inventario.getStockMinimo(),
            stockBajo
        );
    }

    // ── MovimientoInventario → MovimientoInventarioResponseDTO ────────────────

    /**
     * @param movimiento  entidad del movimiento
     * @param stockActual stock resultante después del movimiento (calculado en el service)
     */
    public MovimientoInventarioResponseDTO toMovimientoResponseDTO(
            MovimientoInventario movimiento, Integer stockActual) {

        return new MovimientoInventarioResponseDTO(
            movimiento.getId(),
            movimiento.getInventario().getId(),
            movimiento.getTipo(),
            movimiento.getCantidad(),
            movimiento.getFecha(),
            stockActual
        );
    }

    // ── InventarioCreacionRequestDTO → Inventario (entidad nueva) ────────────

    public Inventario toEntity(InventarioCreacionRequestDTO dto) {
        Inventario inventario = new Inventario();
        inventario.setProductoId(dto.getProductoId());
        inventario.setStockActual(dto.getStockActual());
        inventario.setStockMinimo(dto.getStockMinimo());
        return inventario;
    }
}