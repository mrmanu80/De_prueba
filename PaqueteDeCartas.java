// Programa para barajar y repartir cartas.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PaqueteDeCartas extends JFrame {
   private Carta paquete[];
   private int cartaActual;
   private JButton repartirBoton, barajarBoton;
   private JTextField pantallaCampo;
   private JLabel estadoEtiqueta;

   // configurar paquete de cartas y GUI
   public PaqueteDeCartas()
   {
      super( "Programa para repartir cartas" );

      String caras[] = { "As", "Dos", "Tres", "Cuatro", "Cinco", "Seis", 
         "Siete", "Ocho", "Nueve", "Diez", "Joto", "Qüina", "Rey" };
      String palos[] = { "Corazones", "Diamantes", "Tréboles", "Espadas" };

      paquete = new Carta[ 52 ];
      cartaActual = -1;
      
      // llenar paquete con objetos Carta
      for ( int cuenta = 0; cuenta < paquete.length; cuenta++ ) 
         paquete[ cuenta ] = new Carta( caras[ cuenta % 13 ],
            palos[ cuenta / 13 ] );

      // configurar GUI y manejo de eventos
      Container contenedor = getContentPane();
      contenedor.setLayout( new FlowLayout() );     

      repartirBoton = new JButton( "Repartir carta" );
      repartirBoton.addActionListener(

         new ActionListener() {  // clase interna anónima

            // repartir una carta
            public void actionPerformed( ActionEvent eventoAccion )
            {
               Carta repartida = repartirCarta();

               if ( repartida != null ) {
                  pantallaCampo.setText( repartida.toString() );
                  estadoEtiqueta.setText( "Carta #: " + cartaActual );
               }
               else {
                  pantallaCampo.setText( "NO HAY MAS CARTAS QUE REPARTIR" );
                  estadoEtiqueta.setText( "Barajee las cartas para continuar" );
               }
            }

         } // fin de la clase interna anónima

      ); // fin de la llamada a addActionListener

      contenedor.add( repartirBoton );

      barajarBoton = new JButton( "Barajar cartas" );
      barajarBoton.addActionListener(

         new ActionListener() {  // clase interna anónima

            // barajar paquete
            public void actionPerformed( ActionEvent eventoAccion )
            {
               pantallaCampo.setText( "BARAJANDO ..." );
               barajar();
               pantallaCampo.setText( "SE BARAJO EL PAQUETE" );
            }

         } // fin de la clase interna anónima

      ); // fin de la llamada a addActionListener

      contenedor.add( barajarBoton );

      pantallaCampo = new JTextField( 20 );
      pantallaCampo.setEditable( false );
      contenedor.add( pantallaCampo );

      estadoEtiqueta = new JLabel();
      contenedor.add( estadoEtiqueta );

      setSize( 275, 120 );  // establecer tamaño de ventana
      setVisible( true );               // mostrar ventana
   }

   // barajar paquete de cartas con algoritmo de una pasada
   private void barajar()
   {
      cartaActual = -1;

      // para cada carta, elegir otra carta aleatoria e intercambiarlas
      for ( int primera = 0; primera < paquete.length; primera++ ) {
         int segunda =  ( int ) ( Math.random() * 52 );
         Carta temp = paquete[ primera ];
         paquete[ primera ] = paquete[ segunda ];
         paquete[ segunda ] = temp;
      }

      repartirBoton.setEnabled( true );
   }

   // repartir una carta
   private Carta repartirCarta()
   {
      if ( ++cartaActual < paquete.length )
         return paquete[ cartaActual ];
      else
      {        
         repartirBoton.setEnabled( false );
         return null;
      }
   }

   // ejecutar aplicación
   public static void main( String args[] )
   {
      PaqueteDeCartas aplicacion = new PaqueteDeCartas();

      aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   }  

} // fin de la clase PaqueteDeCartas

// clase para representar una carta
class Carta
{
   private String cara;
   private String palo;

   // constructor para inicializar una carta
   public Carta( String caraCarta, String paloCarta )
   {
      cara = caraCarta;
      palo = paloCarta;
   }

   // devolver representación String de Carta
   public String toString()
   { 
      return cara + " de " + palo; 
   }

} // fin de la clase Carta