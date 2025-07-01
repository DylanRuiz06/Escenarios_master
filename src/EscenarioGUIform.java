import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscenarioGUIform {
    private JButton btnIzquierdaButton;
    private JButton btnDerechaButton;
    private JTextArea texto;
    private JPanel Pnael;

    private ArbolEscenarios arbol;
    private EscenarioNode nodoActual;

    private boolean simulacionFinalizada = false;


    public EscenarioGUIform() {
        arbol = new ArbolEscenarios();
        nodoActual = arbol.getRaiz();

        mostrarNodoActual();

        btnIzquierdaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (simulacionFinalizada) {
                    reiniciarSimulacion();
                } else {
                    avanzar(nodoActual.getIzquierda());
                }
            }
        });

        // El ActionListener del botón derecho ahora solo funciona si la simulación NO ha terminado.
        btnDerechaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!simulacionFinalizada) {
                    avanzar(nodoActual.getDerecha());
                }
            }
        });
    }

    private void avanzar(EscenarioNode siguiente) {
        if (siguiente != null) {
            nodoActual = siguiente;
            mostrarNodoActual();

            // Mostrar resultado final si existe
            if (nodoActual.getIzquierda() != null &&
                    nodoActual.getIzquierda().getDescripcion().startsWith("Resultado")) {

                texto.append("\n\n" + nodoActual.getIzquierda().getDescripcion());

                simulacionFinalizada = true;

                btnIzquierdaButton.setText("Reiniciar Simulación"); //cambia el texto del botón
                btnDerechaButton.setText("---");
                btnDerechaButton.setEnabled(false); //deshabilita el botón derecho.
            }
        }
    }


    // reiniciar la simulación a su estado inicial.
    private void reiniciarSimulacion() {
        simulacionFinalizada = false;
        nodoActual = arbol.getRaiz();
        mostrarNodoActual();
    }


    private void mostrarNodoActual() {
        String mensaje = "Situación: " + nodoActual.getDescripcion();

        // Casos especiales automáticos
        if (nodoActual.getDescripcion().equals("Reparar con kit de emergencia")) {
            mensaje += "\n\nEl kit de emergencia está dañado.";
        }

        if (nodoActual.getDescripcion().equals("Volver a la nave")) {
            mensaje += "\n\nTe encuentras con un obstáculo inesperado en el camino.";
        }

        texto.setText(mensaje);

        // Esta lógica restaura los botones correctamente después de un reinicio.
        if (nodoActual.getIzquierda() != null) {
            btnIzquierdaButton.setText(nodoActual.getIzquierda().getDescripcion());
            btnIzquierdaButton.setEnabled(true);
        } else {
            btnIzquierdaButton.setText("Sin opción");
            btnIzquierdaButton.setEnabled(false);
        }

        if (nodoActual.getDerecha() != null) {
            btnDerechaButton.setText(nodoActual.getDerecha().getDescripcion());
            btnDerechaButton.setEnabled(true);
        } else {
            btnDerechaButton.setText("Sin opción");
            btnDerechaButton.setEnabled(false);
        }
    }


    public JPanel getMainPanel() {
        return Pnael;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Simulador de Entrenamiento Espacial");
        frame.setContentPane(new EscenarioGUIform().Pnael);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}