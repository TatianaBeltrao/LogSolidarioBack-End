package br.ifpe.mobile.logSolidario.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import br.ifpe.mobile.logSolidario.enums.Status;

@Entity
public class Entrega {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany
	private List<Item> itens;
	
	@ManyToMany
	private List<Beneficiario> beneficiarios;
	
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private LocalDate data;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	

	@ManyToOne
	private Usuario usuario;
	
	public Entrega() {}
	
	public Entrega(Integer id, List<Item> itens, LocalDate data, Status status) {
		super();
		this.id = id;
		this.itens = itens;
		this.data = data;
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	public List<Beneficiario> getBeneficiarios() {
		return beneficiarios;
	}

	public void setBeneficiarios(List<Beneficiario> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, id, itens, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrega other = (Entrega) obj;
		return Objects.equals(data, other.data) && Objects.equals(id, other.id) && Objects.equals(itens, other.itens)
				&& status == other.status;
	}
	
	
	
}
