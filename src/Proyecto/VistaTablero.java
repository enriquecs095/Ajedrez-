package Proyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class VistaTablero extends javax.swing.JFrame {

    public static Tablero tablero;
    public static IPieza pieza;
    public static IJugador jugador;
    public Posicion posicion;
    public String jugador1;
    public String jugador2;
    public JTextArea informacion = new JTextArea();
    public JList historial;
    public static boolean piezaPulsada = false;
    public static String seleccionAnterior;
    public static Posicion posicionAnterior, posicionActual;
    File CarpetaEmpates;
    File CarpetaVictorias;
    File CarpetaDerrotas;
    File ContarVictorias1;
    File ContarVictorias2;
    File ContarDerrotas1;
    File ContarDerrotas2;
    File ContarEmpates1;
    File ContarEmpates2;
    //Dependiendo del check nos dirá si vamos a jugar contra la máquina
    //o contra otro usuario
    public static boolean contraPersona = false;
    public static JButton casilla[][] = new JButton[8][8];
    public Movimientos movimientos = new Movimientos();
    private DefaultListModel listModel;
    private boolean turno = true;
    private String UserLog;
    private String UserSecondary;

    public VistaTablero(Tablero tab, String usuarioa, String usuariob) {
        tablero = tab;
        initComponents();
        this.setLocationRelativeTo(null);
        this.UserLog = usuarioa;
        this.UserSecondary = usuariob;
        setSize(600, 500);
        tableroPanel.setLayout(new GridLayout(8, 8));
        seleccionAnterior = " ";
        posicionAnterior = new Posicion(0, 0);
        posicionActual = new Posicion(0, 0);
        Usuario1.setText(UserLog);
        Usuario2.setText(UserSecondary);
       final Maquina maq = new Maquina(tablero);

        for (int i = 0; i < 8; i++) {
            final int fila = i;
            for (int j = 0; j < 8; j++) {
                final int columna = j;
                casilla[i][j] = new JButton();
                casilla[i][j].setSize(new Dimension(50, 50));
                casilla[fila][columna].setActionCommand("action" + i + j);
                //Comprobación de jugar con ordenador o persona

                casilla[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        posicionActual.setFila(fila);
                        posicionActual.setColumna(columna);
                        Pieza piezaAnterior, piezaActual;

                        if (turno) {

                            if (casilla[fila][columna].getIcon() == null && !piezaPulsada) {

                                colorCasilla(casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()], posicionAnterior);
                                piezaPulsada = false;
                                posicionAnterior.setFila(posicionActual.getFila());
                                posicionAnterior.setColumna(posicionActual.getColumna());
                            } else if (casilla[fila][columna].getIcon() == null && piezaPulsada) {

                                piezaAnterior = tablero.comprobarPosicion(posicionAnterior);

                                if (tablero.esMovimientoPosible(new Movimiento(piezaAnterior.color, piezaAnterior.posicion, posicionActual), piezaAnterior)) {
                                    setEstado(posicionAnterior.toString() + " " + posicionActual.toString(), piezaAnterior + ": " + posicionAnterior.toString() + " " + posicionActual.toString(), event.getActionCommand());
                                    movimientos.anadirMovimiento(piezaAnterior, new Movimiento(piezaAnterior.color, piezaAnterior.posicion, posicionActual));
                                    if (turno) {
                                        // listModel.addElement(jugador1+": "+movimientos.getUltimoMovimiento());

                                        //listModel.addElement(jugador2+": "+movimientos.getUltimoMovimiento());
                                    }
                                    tablero.actualizarEstado(posicionAnterior, posicionActual);

                                    piezaAnterior.actualizarPosicion(posicionActual);
                                    casilla[fila][columna].setIcon(casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()].getIcon());
                                    casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()].setIcon(null);
                                    // deseleccionamos la casilla
                                    colorCasilla(casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()], posicionAnterior);
                                    piezaPulsada = false;
                                    posicionAnterior.setFila(posicionActual.getFila());
                                    posicionAnterior.setColumna(posicionActual.getColumna());

                                    tablero.comprobarJaque(piezaAnterior);

                                    if (contraPersona == false) {
                                        Pieza p;
                                        Movimiento m = maq.hacerMovimiento();
                                        p = tablero.estado.get((m.posActual).toString());
                                        if (tablero.esMovimientoPosible(m, p)) {
                                            if (tablero.estado.get(m.posDestino.toString()) != null) {

                                                if (tablero.estado.get(m.posDestino.toString()).color != p.color) {
//                                          
                                                }
                                            }
                                            setEstado(m.posActual.toString() + " " + posicionActual.toString(), piezaAnterior + ": " + m.posActual.toString() + " " + m.posDestino.toString(), event.getActionCommand());
                                            movimientos.anadirMovimiento(p, m);
                                            if (turno) {
                                            } else {
                                            }
                                            tablero.actualizarEstado(m.posActual, m.posDestino);

                                            casilla[m.posDestino.fila][m.posDestino.columna].setIcon(casilla[m.posActual.getFila()][m.posActual.getColumna()].getIcon());
                                            casilla[m.posActual.getFila()][m.posActual.getColumna()].setIcon(null);
                                            colorCasilla(casilla[m.posActual.getFila()][m.posActual.getColumna()], m.posActual);
                                            piezaPulsada = false;
                                            m.posActual.setFila(m.posDestino.getFila());
                                            m.posActual.setColumna(m.posDestino.getColumna());
                                            turno = true;
                                        }
                                    } else {
                                        // Mensaje("Turno de las negras.");
                                        turno = true;
                                    }
                                } else {
                                    colorCasilla(casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()], posicionAnterior);
                                    piezaPulsada = false;
                                    posicionAnterior.setFila(posicionActual.getFila());
                                    posicionAnterior.setColumna(posicionActual.getColumna());
                                    //  Mensaje("Turno de las blancas.");
                                    turno = true;
                                }
                            } else if (casilla[fila][columna].getIcon() != null && !piezaPulsada) {

                                if (tablero.estado.get((new Posicion(fila, columna)).toString()).color == Proyecto.Color.blanca) {
                                    casilla[fila][columna].setBackground(Color.GRAY);
                                    piezaPulsada = true;
                                }
                                posicionAnterior.setFila(posicionActual.getFila());
                                posicionAnterior.setColumna(posicionActual.getColumna());
                            } else if (casilla[fila][columna].getIcon() != null && piezaPulsada) {

                                piezaAnterior = tablero.comprobarPosicion(posicionAnterior);
                                piezaActual = tablero.comprobarPosicion(posicionActual);
                                if (piezaAnterior.color != piezaActual.color && tablero.esMovimientoPosible(new Movimiento(piezaAnterior.color, piezaAnterior.posicion, posicionActual), piezaAnterior)) {

                                    if (piezaAnterior.tipoPieza().equals("Peon")) {
                                        int f_aux, c_aux, x, y;
                                        f_aux = posicionAnterior.getFila();
                                        c_aux = posicionAnterior.getColumna();
                                        x = posicionActual.getFila();
                                        y = posicionActual.getColumna();

                                        if (piezaAnterior.color == Proyecto.Color.blanca) {
                                            if ((((x == f_aux - 1) && (y == c_aux + 1))) || (((x == f_aux - 1) && (y == c_aux - 1)))) {
                                                matar(posicionAnterior, posicionActual, piezaAnterior, fila, columna);
                                            }
                                        } else if (piezaAnterior.color == Proyecto.Color.negra) {
                                            if ((((x == f_aux + 1) && (y == c_aux - 1))) || (((x == f_aux + 1) && (y == c_aux + 1)))) {
                                                matar(posicionAnterior, posicionActual, piezaAnterior, fila, columna);
                                            }
                                        }
                                    }
                                    if (piezaAnterior.tipoPieza().equals("Alfil")) {
                                        int f_aux, c_aux, x, y;
                                        f_aux = posicionAnterior.getFila();
                                        c_aux = posicionAnterior.getColumna();
                                        x = posicionActual.getFila();
                                        y = posicionActual.getColumna();
                                        if (((x >= f_aux + 1) && (y >= c_aux + 1)) || ((x >= f_aux + 1) && (y <= c_aux - 1)) || ((x <= f_aux - 1) && (y <= c_aux - 1)) || ((x <= f_aux - 1) && (y >= c_aux + 1))) {
                                            if (Math.abs((y - c_aux) / (x - f_aux)) == 1) {
                                                matar(posicionAnterior, posicionActual, piezaAnterior, fila, columna);
                                            }
                                        }
                                    }

                                    if (piezaAnterior.tipoPieza().equals("Torre")) {
                                        int f_aux, c_aux, x, y;
                                        f_aux = posicionAnterior.getFila();
                                        c_aux = posicionAnterior.getColumna();
                                        x = posicionActual.getFila();
                                        y = posicionActual.getColumna();
                                        if ((x == f_aux) || (y == c_aux)) {
                                            matar(posicionAnterior, posicionActual, piezaAnterior, fila, columna);
                                        }
                                    }
                                    if (piezaAnterior.tipoPieza().equals("Reyna")) {
                                        int f_aux, c_aux, x, y;
                                        f_aux = posicionAnterior.getFila();
                                        c_aux = posicionAnterior.getColumna();
                                        x = posicionActual.getFila();
                                        y = posicionActual.getColumna();
                                        try {
                                            if ((x == f_aux) || (y == c_aux) || (Math.abs((y - c_aux) / (x - f_aux)) == 1)) {
                                                matar(posicionAnterior, posicionActual, piezaAnterior, fila, columna);
                                            }
                                        } catch (ArithmeticException e) {

                                        }
                                    }
                                    if (piezaAnterior.tipoPieza().equals("Rey")) {
                                        int f_aux, c_aux, x, y;
                                        f_aux = posicionAnterior.getFila();
                                        c_aux = posicionAnterior.getColumna();
                                        x = posicionActual.getFila();
                                        y = posicionActual.getColumna();
                                        if (((x == f_aux + 1) && (y == c_aux + 1)) || ((x == f_aux + 1) && (y == c_aux)) || ((x == f_aux + 1) && (y == c_aux - 1)) || ((x == f_aux) && (y == c_aux - 1))
                                                || ((x == f_aux - 1) && (y == c_aux - 1)) || ((x == f_aux - 1) && (y == c_aux)) || ((x == f_aux - 1) && (y == c_aux + 1)) || ((x == f_aux) && (y == c_aux + 1))) {
                                            matar(posicionAnterior, posicionActual, piezaAnterior, fila, columna);
                                        }
                                    }
                                    if (piezaAnterior.tipoPieza().equals("Caballo")) {
                                        int f_aux, c_aux, x, y;
                                        f_aux = posicionAnterior.getFila();
                                        c_aux = posicionAnterior.getColumna();
                                        x = posicionActual.getFila();
                                        y = posicionActual.getColumna();
                                        matar(posicionAnterior, posicionActual, piezaAnterior, fila, columna);
                                    }

                                    colorCasilla(casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()], posicionAnterior);
                                    piezaPulsada = false;
                                    posicionAnterior.setFila(posicionActual.getFila());
                                    posicionAnterior.setColumna(posicionActual.getColumna());
                                    if (contraPersona == false) {
                                        Pieza p;
                                        Movimiento m = maq.hacerMovimiento();
                                        p = tablero.estado.get((m.posActual).toString());
                                        if (tablero.esMovimientoPosible(m, p)) {
                                            if (tablero.estado.get(m.posActual.toString()).color != p.color) {
                                                matar(m.posActual, m.posDestino, p, m.posDestino.fila, m.posDestino.columna);
                                            }
                                            setEstado(m.posActual.toString() + " " + posicionActual.toString(), piezaAnterior + ": " + m.posActual.toString() + " " + m.posDestino.toString(), event.getActionCommand());
                                            movimientos.anadirMovimiento(p, m);
                                            if (turno) {
                                                listModel.addElement(jugador1 + ": " + movimientos.getUltimoMovimiento());
                                            } else {
                                                listModel.addElement(jugador2 + ": " + movimientos.getUltimoMovimiento());
                                            }
                                            tablero.actualizarEstado(m.posActual, m.posDestino);
                                            casilla[m.posDestino.fila][m.posDestino.columna].setIcon(casilla[m.posActual.getFila()][m.posActual.getColumna()].getIcon());
                                            casilla[m.posActual.getFila()][m.posActual.getColumna()].setIcon(null);
                                            colorCasilla(casilla[m.posActual.getFila()][m.posActual.getColumna()], m.posActual);
                                            piezaPulsada = false;
                                            m.posActual.setFila(m.posDestino.getFila());
                                            m.posActual.setColumna(m.posDestino.getColumna());
                                            //  Mensaje("Turno de las blancas.");
                                            turno = true;
                                        }
                                    } else {
                                        // Mensaje("Turno de las negras.");
                                        turno = false;
                                    }
                                } else {
                                    if (tablero.estado.get((new Posicion(fila, columna)).toString()).color == Proyecto.Color.blanca) {
                                        colorCasilla(casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()], posicionAnterior);
                                        casilla[fila][columna].setBackground(Color.GRAY);
                                        piezaPulsada = true;
                                    }
                                    posicionAnterior.setFila(posicionActual.getFila());
                                    posicionAnterior.setColumna(posicionActual.getColumna());
                                }

                            }
                        } else {
                            if (casilla[fila][columna].getIcon() == null && !piezaPulsada) {

                                colorCasilla(casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()], posicionAnterior);
                                piezaPulsada = false;
                                posicionAnterior.setFila(posicionActual.getFila());
                                posicionAnterior.setColumna(posicionActual.getColumna());
                            } else if (casilla[fila][columna].getIcon() == null && piezaPulsada) {

                                piezaAnterior = tablero.comprobarPosicion(posicionAnterior);

                                if (tablero.esMovimientoPosible(new Movimiento(piezaAnterior.color, piezaAnterior.posicion, posicionActual), piezaAnterior)) {
                                    setEstado(posicionAnterior.toString() + " " + posicionActual.toString(), piezaAnterior + ": " + posicionAnterior.toString() + " " + posicionActual.toString(), event.getActionCommand());
                                    movimientos.anadirMovimiento(piezaAnterior, new Movimiento(piezaAnterior.color, piezaAnterior.posicion, posicionActual));
                                    if (turno) {
                                        listModel.addElement(jugador1 + ": " + movimientos.getUltimoMovimiento());
                                    } else {
                                        listModel.addElement(jugador2 + ": " + movimientos.getUltimoMovimiento());
                                    }
                                    tablero.actualizarEstado(posicionAnterior, posicionActual);

                                    piezaAnterior.actualizarPosicion(posicionActual);
                                    casilla[fila][columna].setIcon(casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()].getIcon());
                                    casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()].setIcon(null);
                                    // deseleccionamos la casilla
                                    colorCasilla(casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()], posicionAnterior);
                                    piezaPulsada = false;
                                    posicionAnterior.setFila(posicionActual.getFila());
                                    posicionAnterior.setColumna(posicionActual.getColumna());

                                    if (tablero.comprobarJaque(piezaAnterior)) //  Mensaje("Turno de las blancas.");
                                    {
                                        turno = true;
                                    }

                                } else {
                                
                                    colorCasilla(casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()], posicionAnterior);
                                    posicionAnterior.setFila(posicionActual.getFila());
                                    posicionAnterior.setColumna(posicionActual.getColumna());
                                    //  Mensaje("Turno de las negras.");
                                    turno = false;
                                    piezaPulsada = false;
                                }
                            } else if (casilla[fila][columna].getIcon() != null && !piezaPulsada) {

                                if (tablero.estado.get((new Posicion(fila, columna)).toString()).color == Proyecto.Color.negra) {
                                    casilla[fila][columna].setBackground(Color.GRAY);
                                    piezaPulsada = true;
                                }
                                posicionAnterior.setFila(posicionActual.getFila());
                                posicionAnterior.setColumna(posicionActual.getColumna());
                            } else if (casilla[fila][columna].getIcon() != null && piezaPulsada) {

                                piezaAnterior = tablero.comprobarPosicion(posicionAnterior);
                                piezaActual = tablero.comprobarPosicion(posicionActual);
                                if (piezaAnterior.color != piezaActual.color && tablero.esMovimientoPosible(new Movimiento(piezaAnterior.color, piezaAnterior.posicion, posicionActual), piezaAnterior)) {

                                    if (piezaAnterior.tipoPieza().equals("Peon")) {// es un peon
                                        int f_aux, c_aux, x, y;
                                        f_aux = posicionAnterior.getFila();
                                        c_aux = posicionAnterior.getColumna();
                                        x = posicionActual.getFila();
                                        y = posicionActual.getColumna();

                                        if (piezaAnterior.color == Proyecto.Color.blanca) {
                                            if ((((x == f_aux - 1) && (y == c_aux + 1))) || (((x == f_aux - 1) && (y == c_aux - 1)))) {
                                                matar(posicionAnterior, posicionActual, piezaAnterior, fila, columna);
                                            }
                                        } else if (piezaAnterior.color == Proyecto.Color.negra) {
                                            if ((((x == f_aux + 1) && (y == c_aux - 1))) || (((x == f_aux + 1) && (y == c_aux + 1)))) {
                                                //Si se cumple ésta condición, el peón podrá matarlo
                                                matar(posicionAnterior, posicionActual, piezaAnterior, fila, columna);
                                            }
                                        }
                                    }
                                    if (piezaAnterior.tipoPieza().equals("Alfil")) {
                                        int f_aux, c_aux, x, y;
                                        f_aux = posicionAnterior.getFila();
                                        c_aux = posicionAnterior.getColumna();
                                        x = posicionActual.getFila();
                                        y = posicionActual.getColumna();
                                        if (((x >= f_aux + 1) && (y >= c_aux + 1)) || ((x >= f_aux + 1) && (y <= c_aux - 1)) || ((x <= f_aux - 1) && (y <= c_aux - 1)) || ((x <= f_aux - 1) && (y >= c_aux + 1))) {
                                            if (Math.abs((y - c_aux) / (x - f_aux)) == 1) {
                                                matar(posicionAnterior, posicionActual, piezaAnterior, fila, columna);
                                            }
                                        }

                                    }

                                    if (piezaAnterior.tipoPieza().equals("Torre")) {
                                        int f_aux, c_aux, x, y;
                                        f_aux = posicionAnterior.getFila();
                                        c_aux = posicionAnterior.getColumna();
                                        x = posicionActual.getFila();
                                        y = posicionActual.getColumna();
                                        if ((x == f_aux) || (y == c_aux)) {
                                            matar(posicionAnterior, posicionActual, piezaAnterior, fila, columna);
                                        }
                                    }
                                    if (piezaAnterior.tipoPieza().equals("Reyna")) {
                                        int f_aux, c_aux, x, y;
                                        f_aux = posicionAnterior.getFila();
                                        c_aux = posicionAnterior.getColumna();
                                        x = posicionActual.getFila();
                                        y = posicionActual.getColumna();
                                        try {
                                            if ((x == f_aux) || (y == c_aux) || (Math.abs((y - c_aux) / (x - f_aux)) == 1)) {
                                                matar(posicionAnterior, posicionActual, piezaAnterior, fila, columna);
                                            }
                                        } catch (ArithmeticException e) {

                                        }
                                    }
                                    if (piezaAnterior.tipoPieza().equals("Rey")) {
                                        int f_aux, c_aux, x, y;
                                        f_aux = posicionAnterior.getFila();
                                        c_aux = posicionAnterior.getColumna();
                                        x = posicionActual.getFila();
                                        y = posicionActual.getColumna();
                                        if (((x == f_aux + 1) && (y == c_aux + 1)) || ((x == f_aux + 1) && (y == c_aux)) || ((x == f_aux + 1) && (y == c_aux - 1)) || ((x == f_aux) && (y == c_aux - 1))
                                                || ((x == f_aux - 1) && (y == c_aux - 1)) || ((x == f_aux - 1) && (y == c_aux)) || ((x == f_aux - 1) && (y == c_aux + 1)) || ((x == f_aux) && (y == c_aux + 1))) {
                                            matar(posicionAnterior, posicionActual, piezaAnterior, fila, columna);
                                        }

                                    }
                                    if (piezaAnterior.tipoPieza().equals("Caballo")) {
                                        int f_aux, c_aux, x, y;
                                        f_aux = posicionAnterior.getFila();
                                        c_aux = posicionAnterior.getColumna();
                                        x = posicionActual.getFila();
                                        y = posicionActual.getColumna();
                                        matar(posicionAnterior, posicionActual, piezaAnterior, fila, columna);
                                    }
                                    // deseleccionamos la casilla
                                    colorCasilla(casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()], posicionAnterior);
                                    piezaPulsada = false;
                                    posicionAnterior.setFila(posicionActual.getFila());
                                    posicionAnterior.setColumna(posicionActual.getColumna());
                                    // Mensaje("Turno de las blancas.");
                                    turno = true;
                                } else {
                                    if (tablero.estado.get((new Posicion(fila, columna)).toString()).color == Proyecto.Color.negra) {
                                        // deseleccionamos la casilla
                                        colorCasilla(casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()], posicionAnterior);
                                        casilla[fila][columna].setBackground(Color.GRAY);
                                        piezaPulsada = true;
                                    }
                                    posicionAnterior.setFila(posicionActual.getFila());
                                    posicionAnterior.setColumna(posicionActual.getColumna());
                                }
                            }
                        }
                    }
                });
                // pone el color a las casillas
                if ((i + j) % 2 == 0) {
                    casilla[i][j].setBackground(Color.white);
                } else {
                    casilla[i][j].setBackground(new Color(0x00a6ac));
                }
                tableroPanel.add(casilla[i][j]);
                casilla[i][j].setEnabled(false);
            }
        }
        mostrarTablero();
        setVisible(true);
    }

    public void colorCasilla(JButton boton, Posicion posicion) {
        if ((posicion.getFila() + posicion.getColumna()) % 2 == 0) {
            boton.setBackground(Color.white);
        } else {
            boton.setBackground(new Color(0x00a6ac));
        }
    }

    public void matar(Posicion posicionAnterior, Posicion posicionActual, Pieza piezaAnterior, int fila, int columna) {
        tablero.estado.remove(posicionActual.toString()); // sacamos la pieza del diccionario
        casilla[posicionActual.getFila()][posicionActual.getColumna()].setIcon(null);
        tablero.actualizarEstado(posicionAnterior, posicionActual);
        piezaAnterior.actualizarPosicion(posicionActual);
        movimientos.anadirMovimiento(piezaAnterior, new Movimiento(piezaAnterior.color, piezaAnterior.posicion, posicionActual));
        if (turno) {
       }
        casilla[fila][columna].setIcon(casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()].getIcon());
        casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()].setIcon(null);
        casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()].setBackground(casilla[posicionAnterior.getFila()][posicionAnterior.getColumna()].getBackground());
        tablero.comprobarJaque(piezaAnterior);
    }

    public void moverFicha(String seleccionActual, String seleccionAnterior) {

    }

    public final void mostrarTablero() {
        tablero.colocarPiezas();
        casilla[0][0].setIcon(new ImageIcon(VistaTablero.class.getResource("Columna1.png")));
        casilla[0][1].setIcon(new ImageIcon(VistaTablero.class.getResource("Caballo1.png")));
        casilla[0][2].setIcon(new ImageIcon(VistaTablero.class.getResource("Alfil1.png")));
        casilla[0][3].setIcon(new ImageIcon(VistaTablero.class.getResource("Rey1.png")));
        casilla[0][4].setIcon(new ImageIcon(VistaTablero.class.getResource("Reina1.png")));
        casilla[0][5].setIcon(new ImageIcon(VistaTablero.class.getResource("Alfil1.png")));
        casilla[0][6].setIcon(new ImageIcon(VistaTablero.class.getResource("Caballo1.png")));
        casilla[0][7].setIcon(new ImageIcon(VistaTablero.class.getResource("Columna1.png")));
        casilla[7][0].setIcon(new ImageIcon(VistaTablero.class.getResource("Columna2.png")));
        casilla[7][1].setIcon(new ImageIcon(VistaTablero.class.getResource("Caballo2.png")));
        casilla[7][2].setIcon(new ImageIcon(VistaTablero.class.getResource("Alfil2.png")));
        casilla[7][3].setIcon(new ImageIcon(VistaTablero.class.getResource("Rey2.png")));
        casilla[7][4].setIcon(new ImageIcon(VistaTablero.class.getResource("Reina2.png")));
        casilla[7][5].setIcon(new ImageIcon(VistaTablero.class.getResource("Alfil2.png")));
        casilla[7][6].setIcon(new ImageIcon(VistaTablero.class.getResource("Caballo2.png")));
        casilla[7][7].setIcon(new ImageIcon(VistaTablero.class.getResource("Columna2.png")));

        for (int i = 0; i < 8; i++) {
            casilla[1][i].setIcon(new ImageIcon(VistaTablero.class.getResource("Peon1.png")));
            casilla[6][i].setIcon(new ImageIcon(VistaTablero.class.getResource("Peon2.png")));
        }
    }

    public void setEstado(String ultima, String turno, String pulsada) {
        String estado = "Último movimiento: " + ultima + "\nJuegan " + turno + "\nSeleccionada: " + pulsada;
        informacion.setText(estado);
        validate();

        repaint();

    }

    void Mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statusPanel = new javax.swing.JPanel();
        Usuario1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Usuario2 = new javax.swing.JTextField();
        jugarBoton = new javax.swing.JButton();
        Empate = new javax.swing.JButton();
        Derrota = new javax.swing.JButton();
        Victoria = new javax.swing.JButton();
        tableroPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Usuario1.setToolTipText("Nombre jugador 1");
        Usuario1.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Ajedrez");

        Usuario2.setToolTipText("Nombre jugador 2");
        Usuario2.setEnabled(false);

        jugarBoton.setText("Jugar");
        jugarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugarBotonActionPerformed(evt);
            }
        });

        Empate.setText("Empate");
        Empate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmpateActionPerformed(evt);
            }
        });

        Derrota.setText("Derrota");
        Derrota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DerrotaActionPerformed(evt);
            }
        });

        Victoria.setText("Victoria");
        Victoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VictoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Usuario1)
                            .addComponent(Usuario2, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(jugarBoton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(statusPanelLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Empate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Derrota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Victoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addComponent(Usuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(Usuario2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jugarBoton)
                .addGap(52, 52, 52)
                .addComponent(Empate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Derrota)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Victoria)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        getContentPane().add(statusPanel, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout tableroPanelLayout = new javax.swing.GroupLayout(tableroPanel);
        tableroPanel.setLayout(tableroPanelLayout);
        tableroPanelLayout.setHorizontalGroup(
            tableroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );
        tableroPanelLayout.setVerticalGroup(
            tableroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 433, Short.MAX_VALUE)
        );

        getContentPane().add(tableroPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jugarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarBotonActionPerformed
        // Activamos los botones
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                casilla[i][j].setEnabled(true);
            }
        }

     //   remove(statusPanel);

        // nuevo panel con borde y titulo
        JPanel estado = new JPanel(new BorderLayout());
        validate();
        setVisible(true);
    }//GEN-LAST:event_jugarBotonActionPerformed

    private void EmpateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmpateActionPerformed
        FileSystem fileSystem = new FileSystem();
        MenuAdministrador Administrador = new MenuAdministrador(UserLog);
        MenuJugador Jugador = new MenuJugador(UserLog);
        MenuAnalista Analista = new MenuAnalista(UserLog);
        try {
            CarpetaEmpates = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + UserLog + "\\Partidas\\Empates\\"+UserLog+" vrs "+UserSecondary+" " +(Administrador.LongitudEmpates(UserLog)+1)+".txt");
            CarpetaEmpates.createNewFile();
            CarpetaEmpates = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + UserSecondary + "\\Partidas\\Empates\\"+UserSecondary+" vrs "+UserLog+" "+(Administrador.LongitudEmpates(UserSecondary)+1)+".txt");
            CarpetaEmpates.createNewFile();
            switch (fileSystem.BuscarTipoUsuarioB(UserLog)) {
                case 1:
                    Administrador.setVisible(true);
                    this.dispose();
                    break;
                case 2:
                    Analista.setVisible(true);
                    this.dispose();
                    break;
                default:
                    Jugador.setVisible(true);
                    this.dispose();
                    break;
            }
        } catch (IOException e) {
            Mensaje(e.getMessage());
        }

    }//GEN-LAST:event_EmpateActionPerformed

    private void DerrotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DerrotaActionPerformed
       FileSystem fileSystem = new FileSystem();
        MenuAdministrador Administrador = new MenuAdministrador(UserLog);
        MenuJugador Jugador = new MenuJugador(UserLog);
        MenuAnalista Analista = new MenuAnalista(UserLog);
        try {
            CarpetaDerrotas = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + UserLog + "\\Partidas\\Derrotas\\"+UserLog+" vrs "+UserSecondary+" "+(Administrador.LongitudDerrotas(UserLog)+1)+".txt");
            CarpetaDerrotas.createNewFile();
            CarpetaDerrotas = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + UserSecondary + "\\Partidas\\Victorias\\"+UserSecondary+" vrs "+UserLog+" "+(Administrador.LongitudDerrotas(UserSecondary)+1)+".txt");
            CarpetaDerrotas.createNewFile();
            switch (fileSystem.BuscarTipoUsuarioB(UserLog)) {
                case 1:
                    Administrador.setVisible(true);
                    this.dispose();
                    break;
                case 2:
                    Analista.setVisible(true);
                    this.dispose();
                    break;
                default:
                    Jugador.setVisible(true);
                    this.dispose();
                    break;
            }
        } catch (IOException e) {
            Mensaje(e.getMessage());
        }
      
    }//GEN-LAST:event_DerrotaActionPerformed

    private void VictoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VictoriaActionPerformed
  FileSystem fileSystem = new FileSystem();
        MenuAdministrador Administrador = new MenuAdministrador(UserLog);
        MenuJugador Jugador = new MenuJugador(UserLog);
        MenuAnalista Analista = new MenuAnalista(UserLog);
        try {
            CarpetaVictorias = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + UserLog + "\\Partidas\\Victorias\\"+UserLog+" vrs "+UserSecondary+" "+(Administrador.LongitudVictorias(UserLog)+1)+".txt");
            CarpetaVictorias.createNewFile();
            CarpetaVictorias = new File("C:\\Users\\Enrique\\Documents\\NetBeansProjects\\ProyectoLab2Progra2\\RegistroUsuarios\\" + UserSecondary + "\\Partidas\\Derrotas\\"+UserSecondary+" vrs "+UserLog+" "+(Administrador.LongitudVictorias(UserSecondary)+1)+".txt");
            CarpetaVictorias.createNewFile();
            switch (fileSystem.BuscarTipoUsuarioB(UserLog)) {
                case 1:
                    Administrador.setVisible(true);
                    this.dispose();
                    break;
                case 2:
                    Analista.setVisible(true);
                    this.dispose();
                    break;
                default:
                    Jugador.setVisible(true);
                    this.dispose();
                    break;
            }
        } catch (IOException e) {
            Mensaje(e.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_VictoriaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Derrota;
    private javax.swing.JButton Empate;
    private javax.swing.JTextField Usuario1;
    private javax.swing.JTextField Usuario2;
    private javax.swing.JButton Victoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jugarBoton;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JPanel tableroPanel;
    // End of variables declaration//GEN-END:variables

}
