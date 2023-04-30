package carrera;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class AthleticRaceInterface extends JFrame implements IMostrarVentana {

    JTextField inputRegistro = new JTextField();
    JButton botonRegistrar = new JButton("Registrar");
    JButton botonIniciar = new JButton("Iniciar");
    JButton botonReiniciar = new JButton("Reiniciar");
    JButton botonTerminar = new JButton("Terminar");
    JTextArea areaCorredores = new JTextArea();
    JTextArea areaResultados = new JTextArea();

    public Runner[] runner = new Runner[5];
    private int conteo = 0;

    public void ventana() {
        // atributos de la ventana
        setTitle("Carrera atletica");
        setSize(600, 520);
        setLayout(null);

        // Primera sección
        JLabel label1 = new JLabel("Registrar corredor");
        label1.setBounds(10, 10, 580, 30);
        inputRegistro.setBounds(10, 40, 440, 30);
        botonRegistrar.setBounds(450, 40, 140, 30);
        TeclaEnter teclaEnter = new TeclaEnter();
        inputRegistro.addKeyListener(teclaEnter);

        EventoRegistro eventoRegistro = new EventoRegistro();
        botonRegistrar.addActionListener(eventoRegistro);

        getContentPane().add(label1);
        getContentPane().add(inputRegistro);
        getContentPane().add(botonRegistrar);
        getContentPane().add(botonIniciar);
        getContentPane().add(botonReiniciar);
        getContentPane().add(botonTerminar);

        // Segunda sección
        JLabel label2 = new JLabel("Corredores registrados");
        label2.setBounds(10, 80, 580, 30);
        areaCorredores.setBounds(10, 120, 430, 150);
        areaCorredores.setEnabled(false);

        getContentPane().add(label2);
        getContentPane().add(areaCorredores);

        // Tercera sección
        JLabel label3 = new JLabel("Resultados");
        label3.setBounds(10, 280, 440, 30);
        areaResultados.setEnabled(false);
        areaResultados.setBounds(10, 320, 430, 150);
        botonIniciar.setBounds(450, 320, 140, 40);
        EventoIniciar eventoIniciar = new EventoIniciar();
        botonIniciar.addActionListener(eventoIniciar);
        botonIniciar.setEnabled(false);
        botonReiniciar.setBounds(450, 370, 140, 40);
        EventoReiniciar eventoReiniciar = new EventoReiniciar();
        botonReiniciar.addActionListener(eventoReiniciar);
        botonReiniciar.setEnabled(false);
        botonTerminar.setBounds(450, 420, 140, 40);
        EventoTerminar eventoTerminar = new EventoTerminar();
        botonTerminar.addActionListener(eventoTerminar);

        getContentPane().add(label3);
        getContentPane().add(areaResultados);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void inicializarBotones() {
        botonIniciar.setEnabled(false);
        botonReiniciar.setEnabled(false);
        botonRegistrar.setEnabled(true);
    }

    public void agregarCorredor() {
        String nombre = inputRegistro.getText().trim();

        if (!nombre.isEmpty()) {

            runner[conteo] = new Runner(nombre, (int) (Math.random() * 30));

            areaCorredores.append((conteo + 1) + " - " + nombre + "\n");

            conteo++;
            inputRegistro.requestFocus();

        } else {

            JOptionPane.showMessageDialog(null, "Favor de ingresar el nombre del corredor");

        }

        if (conteo == 5) {
            botonRegistrar.setEnabled(false);
            botonIniciar.setEnabled(true);
            inputRegistro.setEnabled(false);
        }

        inputRegistro.setText("");
        botonReiniciar.setEnabled(true);
    }

    public class EventoRegistro implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            agregarCorredor();
        }
    }

    public class TeclaEnter implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                agregarCorredor();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    public class EventoIniciar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < 5; i++) {
                ThreadRunner runnerThread = new ThreadRunner(runner[i], areaResultados);
                runnerThread.start();
            }

            botonIniciar.setEnabled(false);

        }
    }

    public class EventoReiniciar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            inicializarBotones();
            areaCorredores.setText("");
            areaResultados.setText("");
            for (int i = 0; i < 5; i++) {
                runner[i].setName("");
                runner[i].setSpeed(0);
            }

            conteo = 0;
            
            inputRegistro.setEnabled(true);
            inputRegistro.requestFocus();

        }
    }

    public class EventoTerminar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.exit(0);

        }
    }

    @Override
    public void imprimirVentana() {
        ventana();
    }

}
