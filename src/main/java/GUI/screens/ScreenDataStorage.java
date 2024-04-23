Aqui está o código com comentários adicionados:

```java
package GUI.screens;

import GUI.util.Util;
import units.DataStorage;
import units.Unit;

import javax.swing.*;

// Classe ScreenDataStorage, que estende Screen
public class ScreenDataStorage extends Screen {

    // Sobrescreve o método getValues para retornar todas as unidades de armazenamento de dados
    @Override
    public Unit[] getValues() {
        return DataStorage.getAll();
    }

    // Sobrescreve o método getName para retornar o nome da tela (Armazenamento)
    @Override
    public String getName() {
        return "Armazenamento";
    }

    // Sobrescreve o método getImageIcon para retornar o ícone da tela de armazenamento de dados
    @Override
    public ImageIcon getImageIcon() {
        return Util.getImageIcon("images/datastorage.png");
    }
}
