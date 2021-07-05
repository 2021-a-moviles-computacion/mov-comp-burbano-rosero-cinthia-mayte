import java.awt.Color
import java.awt.Dimension
import java.awt.event.ActionListener
import javax.swing.*


class GUIDoctor (titulo:String): JFrame(titulo){
        var Productos: ArrayList<_Doctor> = ArrayList<_Doctor>()
        var objArchivo = ArchivoDoctor()
    init{
            val tam = Dimension(700,550)
            this.size =tam
            defaultCloseOperation= WindowConstants.EXIT_ON_CLOSE
            this.setLocation(300,100)
            var contenedor= JPanel()
            var panel1= JPanel()
            var panel3= JPanel()

            //text
           // var idPaciente = JLabel("ID PACIENTE:")
            var idDoctor = JLabel("ID DOCTOR:")
            var nombre = JLabel("NOMBRE:")
            var fechaNac = JLabel("FECHA DE NACIMIENTO:")
            var discapacidad = JLabel("DISCAPACIDAD:")
            var especialidad = JLabel("ESPECIALIDAD:")
            var talla = JLabel("TALLA:")
            var peso = JLabel("PESO:")

            this.contentPane.add(contenedor)
            contenedor.add(panel1)
            contenedor.add(panel3)
            panel1.background= Color(199,199,199)
            panel3.background= Color(199,199,199)
            contenedor.layout= BoxLayout(contenedor, BoxLayout.Y_AXIS)

            //panel1.add(idPaciente)
            panel1.add(idDoctor)
            panel1.add(nombre)
            panel1.add(fechaNac)
            panel1.add(discapacidad)
            panel1.add(especialidad)
            panel1.add(talla)
            panel1.add(peso)
            panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos."))
            panel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista"))
            panel1.setLayout(null)
            panel3.setLayout(null)
            //idPaciente.setBounds(10,20,160,20)
            idDoctor.setBounds(10,50,160,20)
            nombre.setBounds(10,80,160,20)
            fechaNac.setBounds(10,110,160,20)
            discapacidad.setBounds(10,140,160,20)
            especialidad.setBounds(10,170,160,20)
            talla.setBounds(10,200,160,20)
            peso.setBounds(10,230,160,20)
            //textfile
            //var txtidPaciente= JTextField()
            var txtidDoctor= JTextField()
            var txtNombre= JTextField()
            var txtFechaNac= JTextField()
            var txtDiscapacidad= JTextField()
            var txtespecialidad= JTextField()
            var txttalla= JTextField()
            var txtPeso= JTextField()
            //panel1.add(txtidPaciente)
            panel1.add(txtidDoctor)
            panel1.add(txtNombre)
            panel1.add(txtFechaNac)
            panel1.add(txtDiscapacidad)
            panel1.add(txtespecialidad)
            panel1.add(txttalla)
            panel1.add(txtPeso)
           // txtidPaciente.setBounds(200,20,180,20)
            txtidDoctor.setBounds(200,50,180,20)
            txtNombre.setBounds(200,80,180,20)
            txtFechaNac.setBounds(200,110,180,20)
            txtDiscapacidad.setBounds(200,140,180,20)
            txtespecialidad.setBounds(200,170,180,20)
            txttalla.setBounds(200,200,180,20)
            txtPeso.setBounds(200,230,180,20)
            //Botones
            var btnLeer = JButton("VER")
            var btnCrear = JButton("CREAR")
            var btnEliminar = JButton("ELIMINAR")
            var btnActualizar = JButton("ACTUALIZAR")
            panel1.add(btnLeer)
            panel1.add(btnCrear)
            panel1.add(btnEliminar)
            panel1.add(btnActualizar)
            btnLeer.setBounds(450,20,160,40)
            btnCrear.setBounds(450,70,160,40)
            btnEliminar.setBounds(450,120,160,40)
            btnActualizar.setBounds(450,170,160,40)
            //textArea para mostrar informaciòn
            var txaMostrar = JTextArea()
            var scrol= JScrollPane(txaMostrar)
            //txaMostrar.append("ID       NOMBRE      FECHA NACIMIENTO        DISCAPACIDAD        TALLA        PESO")
            panel3.add(scrol)
            scrol.setBounds(20,20,590,200)

            var eventoCrearNuevoDoctor =ActionListener{
                var _idDoctor:Int =txtidDoctor.getText().toInt()
               // var _txtidPaciente: Int =txtidPaciente.getText().toInt()
                var _txtNombre=txtNombre.getText()
                var _txtFechaNac=txtFechaNac.getText()
                var _txtDiscapacidad=txtDiscapacidad.getText().toBoolean()
                var _txtespecialidad=txtespecialidad.getText()
                var _txttalla=txttalla.getText().toFloat()
                var _txtPeso=txtPeso.getText().toFloat()
                var objDoctor = _Doctor(_idDoctor,_txtNombre,_txtFechaNac,_txtDiscapacidad,_txtespecialidad,_txttalla,_txtPeso)
                    Productos.add(objDoctor)
                    println(objDoctor)
                JOptionPane.showMessageDialog(null, "El Doctor ha sido guardado");
                    objArchivo.escribir(Productos)
            }
            btnCrear.addActionListener(eventoCrearNuevoDoctor)

            var eventoReadDoctor = ActionListener {

                    var msj = ""
                    for (i in Productos.indices) {
                            val per: String = Productos[i].toString()
                            msj += per + "\n"
                    }
                    txaMostrar.setText(msj)
            }
            btnLeer.addActionListener(eventoReadDoctor)

            var eventoDeleteDoctor = ActionListener {
                    val PacienteEliminar: Int = txtidDoctor.getText().toInt()
                    for (i in Productos.indices) {
                            if (PacienteEliminar == Productos[i].idDoctor) {
                                    Productos.removeAt(i)
                                    objArchivo.escribir(Productos)
                                    JOptionPane.showMessageDialog(null, "El Doctor ha sido eliminado")
                            }
                    }
            }
            btnEliminar.addActionListener(eventoDeleteDoctor)
        }


    }




