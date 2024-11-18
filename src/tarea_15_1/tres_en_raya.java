package tarea_15_1;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class  tres_en_raya extends JFrame {
    private JButton[][] botones;
    private boolean turnoJugador = true;

    public tres_en_raya() {
        setTitle("Tres en Raya");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        botones = new JButton[3][3];
        inicializarBotones();

        setVisible(true);
    }

   
	
	private void inicializarBotones() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j] = new JButton("");
                botones[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                botones[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (turnoJugador) {
                            JButton boton = (JButton) e.getSource();
                            if (boton.getText().isEmpty()) {
                                boton.setText("X");
                                turnoJugador = false;
                                verificarGanador();
                                movimientoMaquina();
                            }
                        }
                    }
                });
                add(botones[i][j]);
            }
        }
    }

    private void movimientoMaquina() {
        if (!turnoJugador) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (botones[i][j].getText().isEmpty()) {
                        botones[i][j].setText("O");
                        turnoJugador = true;
                        verificarGanador();
                        return;
                    }
                }
            }
        }
    }

    private void verificarGanador() {
        String ganador = obtenerGanador();
        if (ganador != null) {
            JOptionPane.showMessageDialog(this, "Ganador: " + ganador);
            reiniciarJuego();
        } else if (esEmpate()) {
            JOptionPane.showMessageDialog(this, "¡Empate!");
            reiniciarJuego();
        }
    }

    private String obtenerGanador() {
        for (int i = 0; i < 3; i++) {
            if (!botones[i][0].getText().isEmpty() && botones[i][0].getText().equals(botones[i][1].getText())
                    && botones[i][1].getText().equals(botones[i][2].getText())) {
                return botones[i][0].getText();
            }
            if (!botones[0][i].getText().isEmpty() && botones[0][i].getText().equals(botones[1][i].getText())
                    && botones[1][i].getText().equals(botones[2][i].getText())) {
                return botones[0][i].getText();
            }
        }
        if (!botones[0][0].getText().isEmpty() && botones[0][0].getText().equals(botones[1][1].getText())
                && botones[1][1].getText().equals(botones[2][2].getText())) {
            return botones[0][0].getText();
        }
        if (!botones[0][2].getText().isEmpty() && botones[0][2].getText().equals(botones[1][1].getText())
                && botones[1][1].getText().equals(botones[2][0].getText())) {
            return botones[0][2].getText();
        }
        return null;
    }

    private boolean esEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (botones[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void reiniciarJuego() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setText("");
            }
        }
        turnoJugador = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(tres_en_raya::new);
    }
}
