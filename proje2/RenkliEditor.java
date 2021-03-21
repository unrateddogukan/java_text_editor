package proje2;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuBarUI;
import java.awt.*;

//Metin editorunun menubar rengini degistiren metodu iceren abstract class.
public abstract class RenkliEditor {

    public void menubarDegistir(JMenuBar menuBar) {

        menuBar.setUI(new BasicMenuBarUI() {

            @Override
            public void paint(Graphics g, JComponent c) {
                g.setColor(renkSecim());
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        });

        MenuElement[] menus = menuBar.getSubElements();

        for (MenuElement menuElement : menus) {

            JMenu menu = (JMenu) menuElement.getComponent();
            componentRengi(menu);
            menu.setOpaque(true);

            MenuElement[] menuElements = menu.getSubElements();

            for (MenuElement popupMenuElement : menuElements) {

                JPopupMenu popupMenu = (JPopupMenu) popupMenuElement.getComponent();
                popupMenu.setBorder(null);

                MenuElement[] menuItens = popupMenuElement.getSubElements();

                for (MenuElement menuItemElement : menuItens) {

                    JMenuItem menuItem = (JMenuItem) menuItemElement.getComponent();
                    componentRengi(menuItem);
                    menuItem.setOpaque(true);

                }
            }
        }
    }

    private void componentRengi(Component comp) {
        comp.setBackground(renkSecim());
        comp.setForeground(Color.white);
    }

    abstract Color renkSecim();
}

