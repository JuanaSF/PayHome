package ar.com.ada.api.payhome.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "servicio")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servicio_id")
    private Integer servicioId;
    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "empresa_id")
    private Empresa empresa;
    @ManyToOne
    @JoinColumn(name = "deudor_id", referencedColumnName = "deudor_id")
    private Deudor deudor;
    @ManyToOne
    @JoinColumn(name = "tipo_servicio_id", referencedColumnName = "tipo_servicio_id")
    private TipoServicio tipoServicio;
    @Column(name = "tipo_comprobante_id")
    private TipoComprobanteEnum tipoComprobante;
    private String numero;
    @Column(name = "fecha_emision")
    private Date fechaEmision;
    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;
    private BigDecimal importe;
    private String moneda;
    @Column(name = "codigo_barras")
    private String codigoBarras;
    @Column(name = "estado_id")
    private EstadoEnum estado;
    @OneToOne(mappedBy = "servicio", cascade = CascadeType.ALL)
    private Pago pago;
   
    public enum TipoComprobanteEnum{
        FACTURA, CONTRATO, IMPUESTO
    }

    public enum EstadoEnum{
        PENDIENTE, PAGADO, ANULADO
    }

    public Integer getServicioId() {
        return servicioId;
    }

    public void setServicioId(Integer servicioId) {
        this.servicioId = servicioId;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Deudor getDeudor() {
        return deudor;
    }

    public void setDeudor(Deudor deudor) {
        this.deudor = deudor;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
        this.tipoServicio.getServiciosEmitidos().add(this);
    }

    public TipoComprobanteEnum getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobanteEnum tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }

    public Pago getPago() {
        return pago;
    }

    // Relacion bidireccional
    public void setPago(Pago pago) {
        this.pago = pago; // le pongo el pago al servicio
        pago.setServicio(this); // Le pongo el servicio al pago
    }

}