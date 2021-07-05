import java.awt.Color
import java.awt.Dimension
import java.awt.event.ActionListener
import javax.swing.*


class GUIPaciente (titulo:String): JFrame(titulo) {
    var Productos: ArrayList<Paciente> = ArrayList<Paciente>()
    var objArchivo = Archivos()


    init {
        val tam = Dimension(650, 500)
        this.size = tam
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        this.setLocation(300, 100)
        var contenedor = JPanel()
        var panel1 = JPanel()
        var panel3 = JPanel()

        //text
        var idPaciente = JLabel("ID PACIENTE:")
        var nombre = JLabel("NOMBRE:")
        var fechaNac = JLabel("FECHA DE NACIMIENTO:")
        var discapacidad = JLabel("DISCAPACIDAD:")
        var talla = JLabel("TALLA:")
        var peso = JLabel("PESO:")

        this.contentPane.add(contenedor)
        contenedor.add(panel1)
        contenedor.add(panel3)
        panel1.background = Color(199, 199, 199)
        panel3.background = Color(199, 199, 199)
        contenedor.layout = BoxLayout(contenedor, BoxLayout.Y_AXIS)

        panel1.add(idPaciente)
        panel1.add(nombre)
        panel1.add(fechaNac)
        panel1.add(discapacidad)
        panel1.add(talla)
        panel1.add(peso)
        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos."))
        panel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista"))
        panel1.setLayout(null)
        panel3.setLayout(null)
        idPaciente.setBounds(10, 20, 160, 20)
        nombre.setBounds(10, 50, 160, 20)
        fechaNac.setBounds(10, 80, 160, 20)
        discapacidad.setBounds(10, 110, 160, 20)
        talla.setBounds(10, 140, 160, 20)
        peso.setBounds(10, 170, 160, 20)
        //textfile
        var txtidPaciente = JTextField()
        var txtNombre = JTextField()
        var txtFechaNac = JTextField()
        var txtDiscapacidad = JTextField()
        var txttalla = JTextField()
        var txtPeso = JTextField()
        panel1.add(txtidPaciente)
        panel1.add(txtNombre)
        panel1.add(txtFechaNac)
        panel1.add(txtDiscapacidad)
        panel1.add(txttalla)
        panel1.add(txtPeso)
        txtidPaciente.setBounds(200, 20, 180, 20)
        txtNombre.setBounds(200, 50, 180, 20)
        txtFechaNac.setBounds(200, 80, 180, 20)
        txtDiscapacidad.setBounds(200, 110, 180, 20)
        txttalla.setBounds(200, 140, 180, 20)
        txtPeso.setBounds(200, 170, 180, 20)
        //Botones
        var btnLeer = JButton("VER")
        var btnCrear = JButton("CREAR")
        var btnEliminar = JButton("ELIMINAR")
        var btnActualizar = JButton("ACTUALIZAR")
        panel1.add(btnLeer)
        panel1.add(btnCrear)
        panel1.add(btnEliminar)
        panel1.add(btnActualizar)
        btnLeer.setBounds(450, 20, 160, 40)
        btnCrear.setBounds(450, 70, 160, 40)
        btnEliminar.setBounds(450, 120, 160, 40)
        btnActualizar.setBounds(450, 170, 160, 40)
        //textArea para mostrar informaciòn
        var txaMostrar = JTextArea()
        var scrol = JScrollPane(txaMostrar)
        panel3.add(scrol)
        scrol.setBounds(20, 20, 590, 200)
        var eventoCrearPaciente = ActionListener {
            var idPaciente_ = txtidPaciente.getText().toInt()
            var nombrePac_ = txtNombre.getText()
            var fechaNacim_ = txtFechaNac.getText()
            var discapacidad_ = txtDiscapacidad.getText().toBoolean()
            var talla_ = txttalla.getText().toFloat()
            var peso_ = txtPeso.getText().toFloat()
            var nuevoPaciente: Paciente = Paciente(idPaciente_, nombrePac_, fechaNacim_, discapacidad_, talla_, peso_)
            Productos.add(nuevoPaciente)

            JOptionPane.showMessageDialog(null, "El Paciente ha sido guardado")
            objArchivo.escribir(Productos)
        }
        btnCrear.addActionListener(eventoCrearPaciente)

        var eventoRead = ActionListener {

            var msj = ""
            for (i in Productos.indices) {
                val per: String = Productos[i].toString()
                msj += per + "\n"
            }
            txaMostrar.setText(msj)
        }
        btnLeer.addActionListener(eventoRead)

        var eventoDelete = ActionListener {
            val PacienteEliminar: Int = txtidPaciente.getText().toInt()
            for (i in Productos.indices) {
                if (PacienteEliminar == Productos[i].idPaciente) {
                    Productos.removeAt(i)
                    objArchivo.escribir(Productos)
                    JOptionPane.showMessageDialog(null, "El Paciente ha sido eliminado")
                }
            }
        }
        btnEliminar.addActionListener(eventoDelete)

        var eventoActualizarPaciente = ActionListener {
            val PacienteActualizar: Int = txtidPaciente.getText().toInt()
            for (i in Productos.indices) {
                if (PacienteActualizar == Productos[i].idPaciente) {

                }
            }
        }
        btnActualizar.addActionListener(eventoActualizarPaciente)

    }
}

