package br.com.exemplo.dto;

import br.com.exemplo.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class PedidoDTO {

    private Long id;
    private BigDecimal valor;
    private String nomeCliente;
    private Status status;
    private Long pedidoId;
    private Long formaDePagamento;
}
