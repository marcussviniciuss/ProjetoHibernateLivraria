package br.com.aula.projetolivraria.service;

import br.com.aula.projetolivraria.entity.Livro;
import br.com.aula.projetolivraria.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LivroService {

    private final LivroRepository livroRepository;

    @Transactional
    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    @Transactional(readOnly = true)
    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id).orElseThrow(
                () -> new RuntimeException("## LIVRO NÃO ENCONTRADO ##")
        );
    }

    @Transactional(readOnly = true)
    public List<Livro> buscarTodos() {
        return livroRepository.findAll();
    }

    @Transactional
    public Livro editarInformacoes(Long id, Livro livroAtualizado) {
        Livro livro = buscarPorId(id);
        if (livroAtualizado.getTitulo() != null) {
            livro.setTitulo(livroAtualizado.getTitulo());
        }

        if (livroAtualizado.getAutor() != null) {
            livro.setAutor(livroAtualizado.getAutor());
        }

        if (livroAtualizado.getAnoPublicacao() != null) {
            livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
        }
        return livroRepository.save(livro);
    }

    @Transactional
    public void excluirPorId(Long id) {
        Livro livro = buscarPorId(id);

        if (livro == null) {
            throw new RuntimeException("Livro com ID " + id + " não encontrado.");
        }

        livroRepository.delete(livro);
    }

    @Transactional
    public Livro atualizarLivro(Long id, Livro livroAtualizado) {
        Livro livro = buscarPorId(id);

        if (livro == null) {
            throw new RuntimeException("Livro com ID " + id + " não encontrado.");
        }
        if (
                livroAtualizado.getTitulo() != null &&
                        livroAtualizado.getAutor() != null &&
                        livroAtualizado.getAnoPublicacao() != null
        ) {

            livro.setTitulo(livroAtualizado.getTitulo());
            livro.setAutor(livroAtualizado.getAutor());
            livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());

            return livroRepository.save(livro);
        } else {
            throw new IllegalArgumentException("Todos os campos devem ser fornecidos para atualização.");
        }
    }

}