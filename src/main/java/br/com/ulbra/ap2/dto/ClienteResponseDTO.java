package br.com.ulbra.ap2.dto;

import lombok.Data;

@Data
public class ClienteResponseDTO {
    private String nome;
    private int idade;
    private String profissao;

    // getters e setters
}
