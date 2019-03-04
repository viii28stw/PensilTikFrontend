package com.viii28stw.pensiltikfrontend.util;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * @author Plamedi L. Lusembo
 */
public class Notificacoes {

    private final ImageView imageView;
    private final Label label;
    private final static StringBuilder FX_TEXT_FILL = new StringBuilder("-fx-text-fill: #");

    public Notificacoes(ImageView imageView, Label label) {
        this.imageView = imageView;
        this.label = label;
    }

    public void notificaSalvo() {
        imageView.setImage(null);
        label.setText("Salvo");
        label.setStyle(FX_TEXT_FILL.append("6AC259").append(";").toString());
        notifica();
    }

    public void notificaAtualizado() {
        imageView.setImage(null);
        label.setText("Atualizado");
        label.setStyle(FX_TEXT_FILL.append("6AC259").append(";").toString());
        notifica();
    }

    public void notificaExcluido() {
        imageView.setImage(null);
        label.setText("Exclusão bem sucedida!");
        label.setStyle(FX_TEXT_FILL.append("bf4646").append(";").toString());
        notifica();
    }

    public void notificaImprimindo() {
        imageView.setImage(null);
        label.setText("Imprimindo relatório...");
        label.setStyle(FX_TEXT_FILL.append("42064d").append(";").toString());
        notifica();
    }

    public void notificaAdvertencia(String text) {
        imageView.setImage(null);
        label.setText(text);
        label.setStyle(FX_TEXT_FILL.append("42064d").append(";").toString());
        notifica();
    }

    public void notificaInformacao(String txt, String iconResource) {
        imageView.setImage(null);
        label.setText(txt);
        label.setStyle(FX_TEXT_FILL.append("c00d0d").append(";").toString());
        notifica();
    }

    private void notifica() {
        Runnable rnbl = () -> {
            label.setVisible(true);
            imageView.setVisible(true);
            try {
                Thread.sleep(1800);
            } catch (InterruptedException ex) {
            }
            label.setVisible(false);
            imageView.setVisible(false);
        };
        Thread thrd = new Thread(rnbl);
        thrd.start();
    }

}
