package com.gimnasio.views;

import com.gimnasio.model.ClienteDto;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPicker;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Proof of concept of how to handle webcam video stream from Java
 *
 * @author Bartosz Firyn (SarXos)
 */
public class WebcamViewer extends JFrame implements ActionListener, Runnable, WebcamListener, WindowListener, UncaughtExceptionHandler, ItemListener, WebcamDiscoveryListener {

    private static final long serialVersionUID = 1L;
    protected ClienteDto clienteDto;
    protected frmClientes frmCliente;
    private Webcam webcam = null;
    private WebcamPanel panel = null;
    private WebcamPicker picker = null;
    private JButton btnTomarFoto = new JButton("Tomar foto");

    @Override
    public void run() {

        Webcam.addDiscoveryListener(this);

        setTitle("Java Webcam Capture POC");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        addWindowListener(this);

        picker = new WebcamPicker();
        picker.addItemListener(this);

        webcam = picker.getSelectedWebcam();

        if (webcam == null) {
            System.out.println("No webcams found...");
            System.exit(1);
        }

        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.addWebcamListener(WebcamViewer.this);

        panel = new WebcamPanel(webcam, false);
        panel.setFPSDisplayed(true);

        add(picker, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(btnTomarFoto, BorderLayout.SOUTH);

        btnTomarFoto.addActionListener(this);

        pack();
        setVisible(true);

        Thread t = new Thread() {

            @Override
            public void run() {
                panel.start();
            }
        };
        t.setName("example-starter");
        t.setDaemon(true);
        t.setUncaughtExceptionHandler(this);
        t.start();
    }

    public static void main(String[] args) {
        // SwingUtilities.invokeLater(new WebcamViewer());
    }

    @Override
    public void webcamOpen(WebcamEvent we) {
        System.out.println("webcam open");
    }

    @Override
    public void webcamClosed(WebcamEvent we) {
        System.out.println("webcam closed");
    }

    @Override
    public void webcamDisposed(WebcamEvent we) {
        System.out.println("webcam disposed");
    }

    @Override
    public void webcamImageObtained(WebcamEvent we) {
        // do nothing
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        webcam.close();
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("webcam viewer resumed");
        panel.resume();
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("webcam viewer paused");
        panel.pause();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.err.println(String.format("Exception in thread %s", t.getName()));
        e.printStackTrace();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getItem() != webcam) {
            if (webcam != null) {

                panel.stop();

                remove(panel);

                webcam.removeWebcamListener(this);
                webcam.close();

                webcam = (Webcam) e.getItem();
                webcam.setViewSize(WebcamResolution.VGA.getSize());
                webcam.addWebcamListener(this);

                System.out.println("selected " + webcam.getName());

                panel = new WebcamPanel(webcam, false);
                panel.setFPSDisplayed(true);

                add(panel, BorderLayout.CENTER);
                pack();

                Thread t = new Thread() {

                    @Override
                    public void run() {
                        panel.start();
                    }
                };
                t.setName("example-stoper");
                t.setDaemon(true);
                t.setUncaughtExceptionHandler(this);
                t.start();
            }
        }
    }

    @Override
    public void webcamFound(WebcamDiscoveryEvent event) {
        if (picker != null) {
            picker.addItem(event.getWebcam());
        }
    }

    @Override
    public void webcamGone(WebcamDiscoveryEvent event) {
        if (picker != null) {
            picker.removeItem(event.getWebcam());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTomarFoto) {
            boolean reemplaza = true;
            BufferedImage image = webcam.getImage();
            try {
                String nameFile = "fotos/" + this.getClienteDto().getPersonaDto().getNumeroIdentificacion() + ".JPG";
                File file = new File(nameFile);
                if (file.exists()) {
                    int choice = JOptionPane.showConfirmDialog(this,
                            String.format("La foto para el cliente \"%1$s\" realmente existe.\nDesea reemplazarla?", this.clienteDto.getPersonaDto().getNombreCompleto()),
                            "Información de captura de foto",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (choice == JOptionPane.NO_OPTION) {
                        reemplaza = false;
                    } else if (choice == JOptionPane.CANCEL_OPTION) {
                        reemplaza = false;
                    }
                }
                if (reemplaza) {
                    if (ImageIO.write(image, "JPG", file)) {
                        this.clienteDto.getPersonaDto().setFotoPerfil(file.getName());
                        ImageIcon imagen = new ImageIcon(this.clienteDto.getPersonaDto().getFotoPerfil());
                        this.frmCliente.getLblFotoCliente().setIcon(new ImageIcon(
                                image.getScaledInstance(this.frmCliente.getLblFotoCliente().getWidth(), this.frmCliente.getLblFotoCliente().getHeight(), Image.SCALE_DEFAULT)));
                        this.frmCliente.getLblFotoCliente().repaint();
                        webcam.close();
                        this.setVisible(false);
                        System.out.println("Guardado");
                    } else {
                        JLabel label = new JLabel("La huella no se ha podido guardar por favor verífique si la camara está bien conectada");
                        label.setFont(new Font("consolas", Font.PLAIN, 14));
                        JOptionPane.showMessageDialog(this, label, "Alerta de verificación de camara web", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (IOException ex) {
                JLabel label = new JLabel("Se ha presentado un error, intente nuevamente");
                label.setFont(new Font("consolas", Font.PLAIN, 14));
                JOptionPane.showMessageDialog(this, label, "Alerta de error", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(WebcamViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ClienteDto getClienteDto() {
        return clienteDto;
    }

    public void setClienteDto(ClienteDto clienteDto) {
        this.clienteDto = clienteDto;
    }

    public frmClientes getFrmCliente() {
        return frmCliente;
    }

    public void setFrmCliente(frmClientes frmCliente) {
        this.frmCliente = frmCliente;
    }
}
