Aqui está o código com comentários adicionados para explicar as diferentes partes do código:

```java
package GUI.screens;

import GUI.ScreenProperties;
import GUI.util.DistanceRenderer;
import GUI.util.Util;
import units.Money;
import units.Unit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

public abstract class Screen extends JPanel implements ScreenProperties {

    // Componentes da interface do usuário
    private JTextField input;
    private JTextField output;
    protected JComboBox<Unit> selectInputUnit;
    protected JComboBox<Unit> selectOutputUnit;
    protected JPanel panelForm;
    private JTextPane jpanel;

    // Construtor
    public Screen() {
        init(); // Inicializa a interface do usuário
        events(); // Configura os eventos
    }

    // Métodos abstratos
    public abstract Unit[] getValues(); // Retorna os valores das unidades
    public abstract String getName(); // Retorna o nome da tela
    public abstract ImageIcon getImageIcon(); // Retorna o ícone da tela

    // Método para obter o título da tela
    @Override
    public String getTitle() {
        return "Conversor de " + getName();
    }

    // Método de inicialização da interface do usuário
    protected void init() {
        initPanelForm(); // Inicializa o painel de formulário
        setLayout(new BorderLayout()); // Define o layout
        setBorder(new EmptyBorder(15, 15, 15, 15)); // Define a borda
        setName(getName()); // Define o nome da tela
        add(panelForm, BorderLayout.PAGE_START); // Adiciona o painel de formulário à parte superior
        add(getJpanel(), BorderLayout.PAGE_END); // Adiciona o painel inferior
    }

    // Método para criar a tabela com todas as conversões
    private String createTableWithAllConversion(BigDecimal amount, Unit unit) {
        StringBuilder sb = new StringBuilder(); // StringBuilder para construir a tabela
        String value = unit.getFormattedValue(String.valueOf(amount)); // Obtém o valor formatado da unidade
        sb.append("<table><th colspan=2 align=left>" + value + " (" + unit.getName() + ") equivale a:</th>"); // Adiciona cabeçalho à tabela
        for (Unit u : getValues()) { // Loop sobre todas as unidades
            String convert = unit.convert(amount, u).toPlainString(); // Converte o valor para a unidade atual
            sb.append("<tr>"); // Adiciona uma linha à tabela
            sb.append("<td>" + u.getFormattedValue(convert) + "</td>"); // Adiciona o valor formatado à tabela
            sb.append("<td>" + u.getName() + "</td>"); // Adiciona o nome da unidade à tabela
            sb.append("</td>"); // Fecha a célula
        }
        sb.append("</table>"); // Fecha a tabela
        return sb.toString(); // Retorna a tabela como uma string
    }

    // Método para obter o painel inferior
    public JTextPane getJpanel() {
        if (Objects.nonNull(jpanel)) return jpanel; // Retorna o painel se já estiver inicializado
        jpanel = new JTextPane(); // Cria um novo painel
        jpanel.setContentType("text/html"); // Define o tipo de conteúdo como HTML
        jpanel.setOpaque(false); // Torna o painel transparente
        jpanel.setEditable(false); // Torna o painel não editável
        return jpanel; // Retorna o painel
    }

    // Método de inicialização do painel de formulário
    private void initPanelForm() {
        GridLayout gl_divForm = new GridLayout(0, 2); // Layout do painel de formulário
        gl_divForm.setVgap(15); // Espaçamento vertical
        gl_divForm.setHgap(15); // Espaçamento horizontal
        JPanel divForm = new JPanel(gl_divForm); // Cria o painel de formulário com o layout
        divForm.add(getInput()); // Adiciona a entrada de texto
        divForm.add(getSelectInputUnit()); // Adiciona a lista suspensa da unidade de entrada
        divForm.add(getOutput()); // Adiciona a saída de texto
        divForm.add(getSelectOutputUnit()); // Adiciona a lista suspensa da unidade de saída
        panelForm = divForm; // Define o painel de formulário
    }

    // Método para obter a entrada de texto
    public JTextField getInput() {
        if (Objects.nonNull(input)) return input; // Retorna a entrada de texto se já estiver inicializada
        input = new JTextField(); // Cria uma nova entrada de texto
        Util.increaseFont(input, 16); // Aumenta o tamanho da fonte
        return input; // Retorna a entrada de texto
    }

    // Método para obter a lista suspensa da unidade de entrada
    public JComboBox<Unit> getSelectInputUnit() {
        if (Objects.nonNull(selectInputUnit)) return selectInputUnit; // Retorna a lista suspensa se já estiver inicializada
        selectInputUnit = new JComboBox<>(getValues()); // Cria uma nova lista suspensa com as unidades disponíveis
        selectInputUnit.setRenderer(new DistanceRenderer()); // Define o renderizador da lista suspensa
        return selectInputUnit; // Retorna a lista suspensa
    }

    // Método para obter a lista suspensa da unidade de saída
    public JComboBox<Unit> getSelectOutputUnit() {
        if (Objects.nonNull(selectOutputUnit)) return selectOutputUnit; // Retorna a lista suspensa se já estiver inicializada
        selectOutputUnit = new JComboBox<>(getValues()); // Cria uma nova lista suspensa com as unidades disponíveis
        selectOutputUnit.setSelectedIndex(1); // Define o índice selecionado
        selectOutputUnit.setRenderer(new DistanceRenderer()); // Define o renderizador da lista suspensa
        return selectOutputUnit; // Retorna a lista suspensa
    }

    // Método para obter a saída de texto
    public JTextField getOutput() {
        if (Objects.nonNull(output)) return output; // Retorna a saída de texto se já estiver inicializada
        output = new JTextField(); // Cria uma nova saída de texto
        output.setBorder(null); // Remove a borda
        output.setEditable(false); // Torna a saída de texto não editável
        output.setForeground(new Color(0, 180, 0)); // Define a cor do texto
        Util.increaseFont(output, 18); // Aumenta o tamanho da fonte
        return output; // Retorna a saída de texto
    }

    // Método para configurar os eventos
    public void events() {
        input.getDocument().addDocumentListener(new DocumentListener() { // Adiciona um ouvinte de documentos à entrada de texto
            @Override
            public void insertUpdate(DocumentEvent e) {
                btnCalcularClick(); // Chama

 o método btnCalcularClick quando o texto é inserido
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                btnCalcularClick(); // Chama o método btnCalcularClick quando o texto é removido
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        selectInputUnit.addActionListener((e) -> btnCalcularClick()); // Adiciona um ouvinte de ação à lista suspensa da unidade de entrada
        selectOutputUnit.addActionListener((e) -> btnCalcularClick()); // Adiciona um ouvinte de ação à lista suspensa da unidade de saída
    }

    // Método acionado quando o botão de calcular é clicado
    protected void btnCalcularClick() {
        Unit sourceUnit = (Unit) selectInputUnit.getSelectedItem(); // Obtém a unidade de entrada selecionada
        Unit targetUnit = (Unit) selectOutputUnit.getSelectedItem(); // Obtém a unidade de saída selecionada
        String resultString;
        try {
            BigDecimal amount = new BigDecimal(input.getText()); // Obtém o valor digitado como um BigDecimal
            if (sourceUnit instanceof Money) { // Se a unidade de entrada for uma moeda
                resultString = ((Money) sourceUnit)
                        .updateAndConvert(amount, (Money) targetUnit) // Atualiza e converte o valor para a unidade de saída
                        .toPlainString(); // Converte o resultado para uma string
            } else {
                resultString = sourceUnit.convert(amount, targetUnit).toPlainString(); // Converte o valor para a unidade de saída
            }
            getOutput().setText(targetUnit.getFormattedValue(resultString)); // Define o texto de saída formatado
            getJpanel().setText(createTableWithAllConversion(amount, sourceUnit)); // Cria a tabela com todas as conversões
        } catch (NumberFormatException nfe) { // Se ocorrer uma exceção de formato inválido
            if (!getInput().getText().isEmpty()) { // Se o campo de entrada não estiver vazio
                getOutput().setText("Invalid input value!"); // Define o texto de saída como "Valor de entrada inválido!"
            } else {
                getOutput().setText(null); // Limpa o texto de saída
            }
            getJpanel().setText(null); // Limpa o painel inferior
            getInput().grabFocus(); // Define o foco de volta para a entrada de texto
        } catch (ArithmeticException ae) { // Se ocorrer uma exceção de aritmética
            getOutput().setText("Arithmetic error!"); // Define o texto de saída como "Erro aritmético!"
            getJpanel().setText(ae.getMessage()); // Define a mensagem de erro no painel inferior
        } catch (Exception e) { // Se ocorrer outra exceção
            getOutput().setText(null); // Limpa o texto de saída
            getJpanel().setText(e.getMessage()); // Define a mensagem de erro no painel inferior
        }
    }

}
