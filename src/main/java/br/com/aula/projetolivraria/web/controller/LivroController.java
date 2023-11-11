package br.com.aula.projetolivraria.web.controller;

import br.com.aula.projetolivraria.entity.Livro;
import br.com.aula.projetolivraria.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("livros")
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    public ResponseEntity<Livro> create(@RequestBody Livro livro) {
        Livro livroCriado = livroService.salvar(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCriado);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> getAll() {
        List<Livro> livros = livroService.buscarTodos();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getById(@PathVariable Long id) {
        Livro livro = livroService.buscarPorId(id);
        return ResponseEntity.ok(livro);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Livro> updateInfo(@PathVariable Long id, @RequestBody Livro livroAtualizado) {
        Livro livro = livroService.editarInformacoes(id, livroAtualizado);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            livroService.excluirPorId(id);
            return ResponseEntity.ok("Livro excluído com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> updateById(@PathVariable Long id, @RequestBody Livro livroAtualizado) {
        try {
            Livro livro = livroService.atualizarLivro(id, livroAtualizado);
            return ResponseEntity.ok(livro);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
