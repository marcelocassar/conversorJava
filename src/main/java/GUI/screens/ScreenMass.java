package GUI.screens;

import GUI.util.Util;
import units.Mass;
import units.Unit;

import javax.swing.*;

// Classe ScreenMass, que estende Screen
public class ScreenMass extends Screen {

    // Sobrescreve o método getValues para retornar todas as unidades de massa
    @Override
    public Unit[] getValues() {
        return Mass.getAll();
    }

    // Sobrescreve o método getName para retornar o nome da tela (Massa)
    @Override
    public String getName() {
        return "Massa";
    }

    // Sobrescreve o método getImageIcon para retornar o ícone da tela de massa
    @Override
    public ImageIcon getImageIcon() {
        return Util.getImageIcon("images/mass.png");
    }
}
