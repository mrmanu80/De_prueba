// Demostración de los menús
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PruebaMenu extends JFrame
{
   private final Color valoresColor[]={Color.black,Color.blue,Color.red,Color.green };   
   private JRadioButtonMenuItem elementosColor[], tiposLetra[];
   private JCheckBoxMenuItem elementosEstilo[];
   private JLabel pantallaEtiqueta;
   private ButtonGroup grupoTiposLetra, grupoColores;
   private int estilo;

   // configurar GUI
   public PruebaMenu()
   {
      super( "Uso de objetos JMenu" );     

      // establecer menú Archivo y sus elementos de menú
      JMenu menuArchivo = new JMenu( "Archivo" );
      menuArchivo.setMnemonic( 'A' );

      // establecer elemento de menú Acerca de...
      JMenuItem elementoAcerca = new JMenuItem( "Acerca de..." );
      elementoAcerca.setMnemonic( 'c' );
      menuArchivo.add( elementoAcerca );
      elementoAcerca.addActionListener(

         new ActionListener() {  // clase interna anónima

            // mostrar cuadro de diálogo de mensaje cuando el usuario seleccione Acerca de...
            public void actionPerformed( ActionEvent evento )
            {
               JOptionPane.showMessageDialog( PruebaMenu.this,
                  "Éste es un ejemplo\ndel uso de menús",
                  "Acerca de", JOptionPane.PLAIN_MESSAGE );
            }

         }  // fin de la clase interna anónima

      ); // fin de la llamada a addActionListener
 
      // establecer elemento de menú Salir
      JMenuItem elementoSalir = new JMenuItem( "Salir" );
      elementoSalir.setMnemonic( 'S' );
      menuArchivo.add( elementoSalir );
      elementoSalir.addActionListener(

         new ActionListener() {  // clase interna anónima

            // terminar la aplicación cuando el usuario haga clic en elementoSalir
            public void actionPerformed( ActionEvent evento )
            {
               System.exit( 0 );
            }

         }  // fin de la clase interna anónima

      ); // fin de la llamada a addActionListener

      // crear barra de menús y adjuntarla a la ventana PruebaMenu
      JMenuBar barra = new JMenuBar();  
      setJMenuBar( barra );  
      barra.add( menuArchivo );    

      // crear menú Formato, con sus submenús y elementos de menú
      JMenu menuFormato = new JMenu( "Formato" );  
      menuFormato.setMnemonic( 'F' );

      // crear submenú Color
      String colores[] = { "Negro", "Azul", "Rojo", "Verde" };

      JMenu menuColor = new JMenu( "Color" );
      menuColor.setMnemonic( 'C' );

      elementosColor = new JRadioButtonMenuItem[ colores.length ];
      grupoColores = new ButtonGroup();
      ManejadorEventos manejadorEventos = new ManejadorEventos();

      // crear elementos de menú tipo botones de opción para el menú Color
      for ( int cuenta = 0; cuenta < colores.length; cuenta++ ) {
         elementosColor[ cuenta ] = 
            new JRadioButtonMenuItem( colores[ cuenta ] );
         menuColor.add( elementosColor[ cuenta ] );
         grupoColores.add( elementosColor[ cuenta ] );
         elementosColor[ cuenta ].addActionListener( manejadorEventos );
      }

      // seleccionar primer elemento del menú Color
      elementosColor[ 0 ].setSelected( true );  

      // agregar el menú Formato a la barra de menús
      menuFormato.add( menuColor );
      menuFormato.addSeparator();

      // crear submenú Tipo de letra
      String nombresTiposLetra[] = { "Serif", "Monospaced", "SansSerif" };

      JMenu menuTiposLetra = new JMenu( "Tipo de letra" );
      menuTiposLetra.setMnemonic( 'T' );

      tiposLetra = new JRadioButtonMenuItem[ nombresTiposLetra.length ];
      grupoTiposLetra = new ButtonGroup();

      // crear elementos de menú tipo botones de opción para el menú Tipos de letra
      for ( int cuenta = 0; cuenta < tiposLetra.length; cuenta++ ) {
         tiposLetra[ cuenta ] = new JRadioButtonMenuItem( nombresTiposLetra[ cuenta ] );
         menuTiposLetra.add( tiposLetra[ cuenta ] );
         grupoTiposLetra.add( tiposLetra[ cuenta ] );
         tiposLetra[ cuenta ].addActionListener( manejadorEventos );
      }

      // seleccionar el primer elemento del menú Tipo de letra
      tiposLetra[ 0 ].setSelected( true );

      menuTiposLetra.addSeparator();

      // establecer elementos del menú Estilo
      String nombresEstilo[] = { "Negrita", "Cursiva" };

      elementosEstilo = new JCheckBoxMenuItem[ nombresEstilo.length ];
      ManejadorEstilo manejadorEstilo = new ManejadorEstilo();

      // crear elementos de menú tipo casilla de verificación para el menú Estilo
      for ( int cuenta = 0; cuenta < nombresEstilo.length; cuenta++ ) {
         elementosEstilo[ cuenta ] = 
            new JCheckBoxMenuItem( nombresEstilo[ cuenta ] );
         menuTiposLetra.add( elementosEstilo[ cuenta ] );
         elementosEstilo[ cuenta ].addItemListener( manejadorEstilo );
      }

      // colocar menú Tipo de letra en el menú Formato
      menuFormato.add( menuTiposLetra );

      // agregar menú Formato a la barra de menús
      barra.add( menuFormato );  
     
      // establecer etiqueta para mostrar texto
      pantallaEtiqueta = new JLabel( "Texto de ejemplo", SwingConstants.CENTER );
      pantallaEtiqueta.setForeground( valoresColor[ 0 ] );
      pantallaEtiqueta.setFont( new Font( "Serif", Font.PLAIN, 72 ) );

      getContentPane().setBackground( Color.CYAN );
      getContentPane().add( pantallaEtiqueta, BorderLayout.CENTER );

      setSize( 550, 200 );
      setVisible( true );

   } // fin del constructor

   public static void main( String args[] )
   {
      PruebaMenu aplicacion = new PruebaMenu();
      aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   }

   // clase interna para manejar eventos de acción de los elementos de menú
   private class ManejadorEventos implements ActionListener {

      // procesar selecciones de color y tipo de letra
      public void actionPerformed( ActionEvent evento )
      {
         // procesar selección de color
         for ( int cuenta = 0; cuenta < elementosColor.length; cuenta++ )
  
            if ( elementosColor[ cuenta ].isSelected() ) {
               pantallaEtiqueta.setForeground( valoresColor[ cuenta ] );
               break;
            }
 
         // procesar selección de tipo de letra
         for ( int cuenta = 0; cuenta < tiposLetra.length; cuenta++ )

            if ( evento.getSource() == tiposLetra[ cuenta ] ) {
               pantallaEtiqueta.setFont( 
                  new Font( tiposLetra[ cuenta ].getText(), estilo, 72 ) );
               break;
            }

         repaint();  

      } // fin del método actionPerformed

   } // fin de la clase ManejadorEventos

   // clase interna para manejar eventos de los elementos de menú tipo casilla de verificación
   private class ManejadorEstilo implements ItemListener {

      // procesar selecciones de estilo de tipo de letra
      public void itemStateChanged( ItemEvent e )
      {
         estilo = 0;

         // checar selección de negrita
         if ( elementosEstilo[ 0 ].isSelected() )
            estilo += Font.BOLD;

         // checar selección de cursiva
         if ( elementosEstilo[ 1 ].isSelected() )
            estilo += Font.ITALIC;

         pantallaEtiqueta.setFont( 
            new Font( pantallaEtiqueta.getFont().getName(), estilo, 72 ) );

         repaint();
      }

   } // fin de la clase ManejadorEstilo

} // fin de la clase PruebaMenu
