/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frm;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Sarah Foster
 */
@Entity
@Table(name = "os", catalog = "tecas", schema = "")
@NamedQueries({
    @NamedQuery(name = "Os.findAll", query = "SELECT o FROM Os o")
    , @NamedQuery(name = "Os.findByOs", query = "SELECT o FROM Os o WHERE o.os = :os")
    , @NamedQuery(name = "Os.findByDataOS", query = "SELECT o FROM Os o WHERE o.dataOS = :dataOS")
    , @NamedQuery(name = "Os.findByTipo", query = "SELECT o FROM Os o WHERE o.tipo = :tipo")
    , @NamedQuery(name = "Os.findBySituacao", query = "SELECT o FROM Os o WHERE o.situacao = :situacao")
    , @NamedQuery(name = "Os.findByImei1", query = "SELECT o FROM Os o WHERE o.imei1 = :imei1")
    , @NamedQuery(name = "Os.findByImei2", query = "SELECT o FROM Os o WHERE o.imei2 = :imei2")
    , @NamedQuery(name = "Os.findByEntrega", query = "SELECT o FROM Os o WHERE o.entrega = :entrega")
    , @NamedQuery(name = "Os.findByOutros", query = "SELECT o FROM Os o WHERE o.outros = :outros")
    , @NamedQuery(name = "Os.findBySenha", query = "SELECT o FROM Os o WHERE o.senha = :senha")
    , @NamedQuery(name = "Os.findByEquipamento", query = "SELECT o FROM Os o WHERE o.equipamento = :equipamento")
    , @NamedQuery(name = "Os.findByDefeito", query = "SELECT o FROM Os o WHERE o.defeito = :defeito")
    , @NamedQuery(name = "Os.findByValor", query = "SELECT o FROM Os o WHERE o.valor = :valor")
    , @NamedQuery(name = "Os.findByIdCliente", query = "SELECT o FROM Os o WHERE o.idCliente = :idCliente")})
public class Os implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "os")
    private Integer os;
    @Column(name = "dataOS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataOS;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "situacao")
    private String situacao;
    @Basic(optional = false)
    @Column(name = "imei1")
    private String imei1;
    @Basic(optional = false)
    @Column(name = "imei2")
    private String imei2;
    @Basic(optional = false)
    @Column(name = "entrega")
    private String entrega;
    @Basic(optional = false)
    @Column(name = "outros")
    private String outros;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @Column(name = "equipamento")
    private String equipamento;
    @Basic(optional = false)
    @Column(name = "defeito")
    private String defeito;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @Column(name = "id_cliente")
    private int idCliente;

    public Os() {
    }

    public Os(Integer os) {
        this.os = os;
    }

    public Os(Integer os, String tipo, String situacao, String imei1, String imei2, String entrega, String outros, String senha, String equipamento, String defeito, int idCliente) {
        this.os = os;
        this.tipo = tipo;
        this.situacao = situacao;
        this.imei1 = imei1;
        this.imei2 = imei2;
        this.entrega = entrega;
        this.outros = outros;
        this.senha = senha;
        this.equipamento = equipamento;
        this.defeito = defeito;
        this.idCliente = idCliente;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        Integer oldOs = this.os;
        this.os = os;
        changeSupport.firePropertyChange("os", oldOs, os);
    }

    public Date getDataOS() {
        return dataOS;
    }

    public void setDataOS(Date dataOS) {
        Date oldDataOS = this.dataOS;
        this.dataOS = dataOS;
        changeSupport.firePropertyChange("dataOS", oldDataOS, dataOS);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        String oldTipo = this.tipo;
        this.tipo = tipo;
        changeSupport.firePropertyChange("tipo", oldTipo, tipo);
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        String oldSituacao = this.situacao;
        this.situacao = situacao;
        changeSupport.firePropertyChange("situacao", oldSituacao, situacao);
    }

    public String getImei1() {
        return imei1;
    }

    public void setImei1(String imei1) {
        String oldImei1 = this.imei1;
        this.imei1 = imei1;
        changeSupport.firePropertyChange("imei1", oldImei1, imei1);
    }

    public String getImei2() {
        return imei2;
    }

    public void setImei2(String imei2) {
        String oldImei2 = this.imei2;
        this.imei2 = imei2;
        changeSupport.firePropertyChange("imei2", oldImei2, imei2);
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        String oldEntrega = this.entrega;
        this.entrega = entrega;
        changeSupport.firePropertyChange("entrega", oldEntrega, entrega);
    }

    public String getOutros() {
        return outros;
    }

    public void setOutros(String outros) {
        String oldOutros = this.outros;
        this.outros = outros;
        changeSupport.firePropertyChange("outros", oldOutros, outros);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        String oldSenha = this.senha;
        this.senha = senha;
        changeSupport.firePropertyChange("senha", oldSenha, senha);
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        String oldEquipamento = this.equipamento;
        this.equipamento = equipamento;
        changeSupport.firePropertyChange("equipamento", oldEquipamento, equipamento);
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        String oldDefeito = this.defeito;
        this.defeito = defeito;
        changeSupport.firePropertyChange("defeito", oldDefeito, defeito);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        BigDecimal oldValor = this.valor;
        this.valor = valor;
        changeSupport.firePropertyChange("valor", oldValor, valor);
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        int oldIdCliente = this.idCliente;
        this.idCliente = idCliente;
        changeSupport.firePropertyChange("idCliente", oldIdCliente, idCliente);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (os != null ? os.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Os)) {
            return false;
        }
        Os other = (Os) object;
        if ((this.os == null && other.os != null) || (this.os != null && !this.os.equals(other.os))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "frm.Os[ os=" + os + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
