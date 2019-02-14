package com.viii28stw.pensiltikfrontend.model.dto;

import com.viii28stw.pensiltikfrontend.enumeration.SexoEnum;
import lombok.*;

/**
 * @author Plamedi L. Lusembo
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class UsuarioDto{

    private String id;
    private String nome;
    private String sobreNome;
    private String email;
    private String senha;
    private SexoEnum sexoEnum;
}
