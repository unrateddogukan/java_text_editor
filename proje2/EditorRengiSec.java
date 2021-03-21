package proje2;

import javax.swing.*;

//EditorRengi class'indaki renkSec metodunu cagirip dondurulen nesneyi 
//RenkliEditor nesnesine atayan, parametre olarak “JMenuBar” turundeki 
//degiskeni alan ve bu degiskene gore RenkliEditor nesnesi araciligiyla 
//bu menubar üzerinde degisiklik gerceklestiren menuCubuguRengiSec 
//static metodunu bulunduran class.
public class EditorRengiSec {

    public static void menuCubuguRengiSec(JMenuBar menuBar) {

        RenkliEditor renkliEditor = EditorRengi.renkSec();

        if (renkliEditor != null) {
            renkliEditor.menubarDegistir(menuBar);
        }
    }
}
