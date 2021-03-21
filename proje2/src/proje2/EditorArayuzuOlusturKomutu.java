package proje2;

import javax.swing.*;

//Program calistirildiginde kullanicinin karsisina gelen arayuzu olusturan class.
public class EditorArayuzuOlusturKomutu implements Command{

    public MetinEditoru editor;

    public EditorArayuzuOlusturKomutu(MetinEditoru editor) {
        this.editor = editor;
    }

    @Override
    public boolean execute() {
        // Program icin arayuz cercevesi olusturuluyor.
        editor.cerceve = new JFrame("Metin Editörü");

        // Metin ekrani olusturuluyor.
        editor.anaEkran = new JTextArea(20, 50);

        // Menu cubugu olusturuluyor.
        JMenuBar menuCubugu = new JMenuBar();

        // Menuler olusturuluyor.
        JMenu menuDosya = new JMenu("Dosya");
        JMenu menuDuzen = new JMenu("Düzen");

        // Menu elemanlari olusturuluyor.
        JMenuItem menuItemYeni = new JMenuItem("Yeni");
        JMenuItem menuItemAc = new JMenuItem("Aç");
        JMenuItem menuItemKaydet = new JMenuItem("Kaydet");
        JMenuItem menuItemGeriAl = new JMenuItem("Geri Al");
        JMenuItem menuBulveDegistir = new JMenuItem("Kelime Bul ve Değiştir");
        JMenuItem menuYazimKontrolu = new JMenuItem("Yazım Hatası Kontrolü Yap");
        JMenuItem menuCikis = new JMenuItem("Çıkış");

        // Menu elemanlari menulere ekleniyor.
        menuDosya.add(menuItemYeni);
        menuDosya.add(menuItemAc);
        menuDosya.add(menuItemKaydet);
        menuDosya.add(menuCikis);
        menuDuzen.add(menuItemGeriAl);
        menuDuzen.add(menuBulveDegistir);
        menuDuzen.add(menuYazimKontrolu);

        // Menuler menu cubuguna ekleniyor.
        menuCubugu.add(menuDosya);
        menuCubugu.add(menuDuzen);

        // Kullanici eylemlerinin takip edilecegi alanlar belirleniyor.
        menuItemYeni.addActionListener(editor);
        menuItemAc.addActionListener(editor);
        menuItemKaydet.addActionListener(editor);
        menuItemGeriAl.addActionListener(editor);
        menuBulveDegistir.addActionListener(editor);
        menuYazimKontrolu.addActionListener(editor);
        menuCikis.addActionListener(editor);

        editor.anaEkran.addKeyListener(editor); // Klavyeye basilan tusun takibi yapiliyor.

        // Yazılar cerceveye sigmadiginda asagi-yukari ve saga-sola kaydirabilme islevi ekleniyor.
        JScrollPane scroll = new JScrollPane(editor.anaEkran, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        editor.cerceve.add(scroll);

        editor.cerceve.setJMenuBar(menuCubugu); // Menu cubugu arayuz cercevesine ekleniyor.
        editor.cerceve.pack(); // Cercevenin boyutunu icindeki elementlere gore belirliyor.
        editor.cerceve.setLocationRelativeTo(null); // Program calistirildiginda arayuz monitoru ortalayarak gozukuyor.

        EditorRengiSec.menuCubuguRengiSec(menuCubugu); // Menu cubugu renginin belirlendigi ve degistirildigi metotlar cagiriliyor.

        editor.cerceve.setVisible(true); // Program calistirildiginda arayuz cercevesi gorunur hale geliyor.
        editor.cerceve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerceve kapatildiginde program calismayi sonlandiriyor.
        return true;
    }
}
