package br.ifpe.mobile.logSolidario.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifpe.mobile.logSolidario.models.Beneficiario;

@Repository
public interface BeneficiarioDAO extends JpaRepository<Beneficiario, Integer>{

}
