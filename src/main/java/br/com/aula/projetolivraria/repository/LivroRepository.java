package br.com.aula.projetolivraria.repository;

import br.com.aula.projetolivraria.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}