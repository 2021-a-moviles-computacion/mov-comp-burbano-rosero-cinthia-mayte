import java.awt.Dimension
import java.awt.Window
import java.awt.event.ActionListener
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import java.nio.channels.ClosedByInterruptException
import javax.swing.*
import javax.swing.plaf.basic.BasicInternalFrameTitlePane

class VentanaInicio(titulo:String):JFrame(titulo) {
    init{
        val tam = Dimension(400,150)
        this.size =tam
        defaultCloseOperation=WindowConstants.EXIT_ON_CLOSE
        this.setLocation(300,200)
        var contenedor= JPanel()
        var panel1= JPanel()
        var panel2= JPanel()
        var Inicio = JLabel("INICIO")

        this.contentPane.add(contenedor)
        contenedor.add(panel1)
        contenedor.add(panel2)
        var btnPaciente = JButton("PACIENTE")
        var btnDoctor = JButton("DOCTOR")
        var btnCancelar = JButton("CANCELAR")
        contenedor.layout= BoxLayout(contenedor, BoxLayout.Y_AXIS)
        panel1.add(Inicio)
        Inicio.setBounds(100,200,160,20)
        panel2.add(btnPaciente)
        panel2.add(btnDoctor)
        panel2.add(btnCancelar)

        var evento  = ActionListener {
            System.exit(0)
        }
        btnCancelar.addActionListener(evento)
        var evento1  = ActionListener {
            var ventdoctor  = GUIDoctor("DOCTOR")
            ventdoctor.isVisible=true

        }
        btnDoctor.addActionListener(evento1)
        var evento2  = ActionListener {
            var ventPaci  = GUIPaciente("PACIENTE")
            ventPaci.isVisible=true

        }
        btnPaciente.addActionListener(evento2)
    }
}