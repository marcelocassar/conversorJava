package GUI.screens;

import GUI.util.Util;
import units.Length;
import units.Unit;

import javax.swing.*;

// Classe ScreenLength, que estende Screen
public class ScreenLength extends Screen {

    // Sobrescreve o método getValues para retornar todas as unidades de comprimento
    @Override
    public Unit[] getValues() {
        return Length.getAll();
    }

    // Sobrescreve o método getName para retornar o nome da tela (Comprimento)
    @Override
    public String getName() {
        return "Comprimento";
    }

    // Sobrescreve o método getImageIcon para retornar o ícone da tela de comprimento
    @Override
    public ImageIcon getImageIcon() {
        return Util.getImageIcon("images/distance.png");
    }
}
