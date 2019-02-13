package com.viii28stw.pensiltikfrontend.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */

@AllArgsConstructor
@Getter
public enum SexoEnum {
    MASCULINO('M', "Masculino"),
    FEMININO('F', "Feminino");

    private final char id;
    private final String descricao;

    public static List<SexoEnum> list() {
        return Arrays.asList(SexoEnum.values());
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }

}
