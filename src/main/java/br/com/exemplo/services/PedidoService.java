package br.com.exemplo.services;


import br.com.exemplo.dto.PedidoDTO;
import br.com.exemplo.model.Pedido;
import br.com.exemplo.model.Status;
import br.com.exemplo.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PedidoDTO> obterTodos(Pageable paginacao){
        return pedidoRepository
                .findAll(paginacao).map(
                p-> modelMapper.map(p, PedidoDTO.class)
                );

    }
    public PedidoDTO obterPorId(Long id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow( () -> new EntityNotFoundException());
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    public PedidoDTO criarPedido(PedidoDTO dto){
        Pedido pedido = modelMapper.map(dto, Pedido.class);
        pedido.setStatus(Status.CRIADO);
        pedido = pedidoRepository.save(pedido);
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    public PedidoDTO atualizarPedido(Long id, PedidoDTO dto){
        Pedido pedido = modelMapper.map(dto, Pedido.class);
        pedido.setId(id);
        pedido = pedidoRepository.save(pedido);
        return modelMapper.map(pedido, PedidoDTO.class);

    }

    public void excluirPedido(Long id){
        pedidoRepository.deleteById(id);
    }
}
