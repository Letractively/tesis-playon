/**
 * 
 */
package tesis.playon.web.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "relojMB")
@ViewScoped
public class Reloj implements Runnable, Serializable {

    private static final long serialVersionUID = 6337573724767842535L;

    private Thread t;/*Crear variable privada de tipo Thread(hilo)*/
    private String formato;/*Crear variable privada de tipo String*/

    private String fechaHoraSistema;

    @PostConstruct
    private void init() {
	t = new Thread(this);/*Inicializamos t*/
        formato = "HH:mm:ss";/*Inicializamos formato HORAS:minutos:segundos*/
        t.start();/*Ponemos nuestro hilo en ejecucion*/
    }

    public void run() {/*Implementamos el metodo abstracto run*/
        while (true) {/*Ciclo infinito para que siempre se actualice la hora*/
            DateFormat D_Formato = new SimpleDateFormat(formato);/*Crear e inicializar variable tipo DateFormat*/
            Date date = new Date();/*Crear e inicializar variable tipo Date*/
            fechaHoraSistema = D_Formato.format(date);/*Ponemos la variable date en el JLabel*/
            try {
                Thread.sleep(1000);/*El hilo duerme 1000 miliseg, es decir 1 seg*/
            } catch (InterruptedException ex) {/*Excepciones*/
                Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
            }/*Fin de Excepciones*/
        }/*Fin del Ciclo*/
    }/*Fin de metodo run*/

    /**
     * @return the fechaHoraSistema
     */
    public String getFechaHoraSistema() {
        return fechaHoraSistema;
    }

    /**
     * @param fechaHoraSistema the fechaHoraSistema to set
     */
    public void setFechaHoraSistema(String fechaHoraSistema) {
        this.fechaHoraSistema = fechaHoraSistema;
    }

}
