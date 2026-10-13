package ni.edu.uam.practicahilosjavafx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ProcesadorController {
    @FXML
    private Label lblEstadoProductos;
    @FXML
    private Label lblPorcentajeProductos;
    @FXML
    private Label lblPorcentajeClientes;
    @FXML
    private Label lblEstadoClientes;
    @FXML
    private Button btnIniciarBoth;
    @FXML
    private Button btnDetenerProductos;
    @FXML
    private Button btnReiniciarProductos;
    @FXML
    private Button btnBloquearProductos;
    @FXML
    private Button btnDetenerClientes;
    @FXML
    private Button btnReiniciarClientes;
    @FXML
    private Button btnBloquearClientes;
    @FXML
    private ProgressBar barraProgresoProductos;

    @FXML
    private ProgressBar barraProgresoClientes;

    @FXML
    private Label lblEstado;

    @FXML
    private Label lblPorcentaje;

    @FXML
    private ProgressBar barraProgreso;

    @FXML
    private Button btnIniciar;

    @FXML
    private Button btnDetener;

    @FXML
    private Button btnReiniciar;

    @FXML
    private Button btnBloquear;

    private Thread hiloProceso;

    private Thread hiloCliente;

    private Thread hiloProducto;



    @FXML
    private void iniciarProceso() {


        if (hiloProceso != null &&
                hiloProceso.isAlive()) {
            return;
        }

        barraProgreso.setProgress(0);
        lblPorcentaje.setText("0%");
        lblEstado.setText("Procesando registros...");

        btnIniciar.setDisable(true);
        btnDetener.setDisable(false);
        btnReiniciar.setDisable(true);

        Runnable tarea = () -> {

            try {
                for (int i = 1; i <= 100; i++) {

                    if (Thread.currentThread()
                            .isInterrupted()) {
                        throw new InterruptedException();
                    }

                    Thread.sleep(50);

                    final int avance = i;

                    Platform.runLater(() -> {
                        barraProgreso.setProgress(
                                avance / 100.0
                        );

                        lblPorcentaje.setText(
                                avance + "%"
                        );
                    });
                }

                Platform.runLater(() -> {
                    lblEstado.setText(
                            "Proceso finalizado"
                    );
                    habilitarBotones();
                });

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                Platform.runLater(() -> {
                    lblEstado.setText(
                            "Proceso detenido"
                    );
                    habilitarBotones();
                });
            }
        };

        hiloProceso = new Thread(tarea);

        hiloProceso.setName("Hilo-Procesamiento");
        hiloProceso.setDaemon(true);
        hiloProceso.start();
    }

    @FXML
    private void detenerProceso() {

        if (hiloProceso != null &&
                hiloProceso.isAlive()) {

            btnDetener.setDisable(true);

            lblEstado.setText(
                    "Solicitando detención..."
            );

            hiloProceso.interrupt();
        }
    }

    @FXML
    private void reiniciarProceso() {

        if (hiloProceso != null &&
                hiloProceso.isAlive()) {
            return;
        }

        barraProgreso.setProgress(0);
        lblPorcentaje.setText("0%");
        lblEstado.setText("Esperando inicio");

        habilitarBotones();
    }
    @FXML
    private void procesoBloqueante() {


        lblEstado.setText("Procesando...");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        lblEstado.setText("Finalizado");
    }

    private void habilitarBotones() {
        btnIniciar.setDisable(false);
        btnDetener.setDisable(true);
        btnReiniciar.setDisable(false);
    }

    public void detenerAlCerrar() {

        if (hiloProceso != null &&
                hiloProceso.isAlive()) {

            hiloProceso.interrupt();
        }
    }

    @FXML
    private void iniciarAmbos() {


        if (hiloCliente != null &&
                hiloCliente.isAlive()) {
            return;
        }

        barraProgresoClientes.setProgress(0);
        lblPorcentajeClientes.setText("0%");
        lblEstadoClientes.setText("Procesando registros...");

        btnIniciarBoth.setDisable(true);
        btnDetenerClientes.setDisable(false);
        btnReiniciarClientes.setDisable(true);
        btnDetenerProductos.setDisable(false);
        btnReiniciarProductos.setDisable(true);

        Runnable tarea = () -> {

            try {
                for (int i = 1; i <= 100; i++) {

                    if (Thread.currentThread()
                            .isInterrupted()) {
                        throw new InterruptedException();
                    }

                    Thread.sleep(100);

                    final int avance = i;

                    Platform.runLater(() -> {
                        barraProgresoClientes.setProgress(
                                avance / 100.0
                        );

                        lblPorcentajeClientes.setText(
                                avance + "%"
                        );
                    });
                }

                Platform.runLater(() -> {
                    lblEstadoClientes.setText(
                            "Proceso finalizado"
                    );
                    habilitarBotones2();
                });

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                Platform.runLater(() -> {
                    lblEstadoClientes.setText(
                            "Proceso detenido"
                    );
                    habilitarBotones2();
                });
            }
        };

        hiloCliente = new Thread(tarea);

        hiloCliente.setName("Hilo-Procesamiento");
        hiloCliente.setDaemon(true);
        hiloCliente.start();

        if (hiloProducto != null &&
                hiloProducto.isAlive()) {
            return;
        }

        barraProgresoProductos.setProgress(0);
        lblPorcentajeProductos.setText("0%");
        lblEstadoProductos.setText("Procesando registros...");

        btnIniciarBoth.setDisable(true);
        btnDetenerClientes.setDisable(false);
        btnReiniciarClientes.setDisable(true);
        btnDetenerProductos.setDisable(false);
        btnReiniciarProductos.setDisable(true);

        Runnable tarea2 = () -> {

            try {
                for (int i = 1; i <= 100; i++) {

                    if (Thread.currentThread()
                            .isInterrupted()) {
                        throw new InterruptedException();
                    }

                    Thread.sleep(200);

                    final int avance = i;

                    Platform.runLater(() -> {
                        barraProgresoProductos.setProgress(
                                avance / 100.0
                        );

                        lblPorcentajeProductos.setText(
                                avance + "%"
                        );
                    });
                }

                Platform.runLater(() -> {
                    lblEstadoProductos.setText(
                            "Proceso finalizado"
                    );
                    habilitarBotones2();
                });

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                Platform.runLater(() -> {
                    lblEstadoProductos.setText(
                            "Proceso detenido"
                    );
                    habilitarBotones2();
                });
            }
        };

        hiloProducto = new Thread(tarea2);

        hiloProducto.setName("Hilo-Procesamiento");
        hiloProducto.setDaemon(true);
        hiloProducto.start();
    }

    @FXML
    private void detenerProcesoCliente() {

        if (hiloCliente != null &&
                hiloCliente.isAlive()) {

            btnDetenerClientes.setDisable(true);

            lblEstadoClientes.setText(
                    "Solicitando detención..."
            );

            hiloCliente.interrupt();
        }
    }

    @FXML
    private void detenerProcesoProductos() {

        if (hiloProducto != null &&
                hiloProducto.isAlive()) {

            btnDetenerProductos.setDisable(true);

            lblEstadoProductos.setText(
                    "Solicitando detención..."
            );

            hiloProducto.interrupt();
        }
    }
    @FXML
    private void reiniciarProcesoCliente() {

        if (hiloCliente != null &&
                hiloCliente.isAlive()) {
            return;
        }

        barraProgresoClientes.setProgress(0);
        lblPorcentajeClientes.setText("0%");
        lblEstadoClientes.setText("Esperando inicio");

        habilitarBotones();
    }
    @FXML
    private void reiniciarProcesoProducto() {

        if (hiloProducto != null &&
                hiloProducto.isAlive()) {
            return;
        }

        barraProgresoProductos.setProgress(0);
        lblPorcentajeProductos.setText("0%");
        lblEstadoProductos.setText("Esperando inicio");

        habilitarBotones();
    }


    private void habilitarBotones2() {
        btnIniciarBoth.setDisable(false);
        btnDetenerClientes.setDisable(true);
        btnReiniciarClientes.setDisable(false);
        btnDetenerProductos.setDisable(true);
        btnReiniciarProductos.setDisable(false);
    }
}