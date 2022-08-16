package br.ifpe.mobile.logSolidario.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Doacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nomeDoador;

	@OneToMany
	private List<Item> itens;
	
	private Integer totalDeItens;
	
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private LocalDate data;
	
	@ManyToOne
	private Usuario usuario;
	
	public Doacao() {}
	
	public Doacao(Integer id, String nomeDoador, List<Item> itens, Integer totalDeItens, LocalDate data) {
		super();
		this.id = id;
		this.nomeDoador = nomeDoador;
		this.itens = itens;
		this.totalDeItens = totalDeItens;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeDoador() {
		return nomeDoador;
	}

	public void setNomeDoador(String nomeDoador) {
		this.nomeDoador = nomeDoador;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Integer getTotalDeItens() {
		return totalDeItens;
	}

	public void setTotalDeItens(Integer totalDeItens) {
		this.totalDeItens = totalDeItens;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, id, itens, nomeDoador, totalDeItens);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doacao other = (Doacao) obj;
		return Objects.equals(data, other.data) && Objects.equals(id, other.id) && Objects.equals(itens, other.itens)
				&& Objects.equals(nomeDoador, other.nomeDoador) && Objects.equals(totalDeItens, other.totalDeItens);
	}
	
	
	
	
	
}
