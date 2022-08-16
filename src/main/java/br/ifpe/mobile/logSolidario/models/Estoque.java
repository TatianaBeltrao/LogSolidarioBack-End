package br.ifpe.mobile.logSolidario.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany
	private List<Item> itens;
	
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private LocalDate dataModificacao;
	
	@OneToOne
	private Usuario usuario;
	
	public Estoque() {}
	
	public Estoque(Integer id, List<Item> itens, LocalDate dataModificacao) {
		this.id = id;
		this.itens = itens;
		this.dataModificacao = dataModificacao;
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
	public LocalDate getDataModificacao() {
		return dataModificacao;
	}
	public void setDataModificacao(LocalDate dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataModificacao, id, itens);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estoque other = (Estoque) obj;
		return Objects.equals(dataModificacao, other.dataModificacao) && Objects.equals(id, other.id)
				&& Objects.equals(itens, other.itens);
	}
	
	
	
	
}
