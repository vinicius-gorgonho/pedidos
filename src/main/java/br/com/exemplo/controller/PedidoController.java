package br.com.exemplo.controller;


import br.com.exemplo.dto.PedidoDTO;
import br.com.exemplo.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public Page<PedidoDTO> listar(@PageableDefault(size = 10) Pageable paginacao){
            return service.obterTodos(paginacao);
    }

    @GetMapping("/id")
   public ResponseEntity<PedidoDTO> detalhar(@PathVariable @NotNull Long id){
        PedidoDTO dto = service.obterPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> cadastrar(@RequestBody @Valid PedidoDTO dto, UriComponentsBuilder uriBuilder){
        PedidoDTO pedidoDTO = service.criarPedido(dto);

        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pedidoDTO.getId()).toUri();
        return ResponseEntity.created(endereco).body(pedidoDTO);

    }

    @PutMapping
    public ResponseEntity<PedidoDTO> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid PedidoDTO dto){
        PedidoDTO atualizado = service.atualizarPedido(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PedidoDTO> remover(@PathVariable @NotNull Long id){
        service.excluirPedido(id);
        return ResponseEntity.noContent().build();
    }



}
