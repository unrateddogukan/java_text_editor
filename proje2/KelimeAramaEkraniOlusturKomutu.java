package proje2;

import javax.swing.*;
import java.awt.*;

//Program arayüzü menusunde “Kelime Bul ve Değiştir” secimi yapildiginda acilan ekrani olusturan komut nesnesinin class'ı.
public class KelimeAramaEkraniOlusturKomutu implements Command{

    public MetinEditoru editor;

    public KelimeAramaEkraniOlusturKomutu(MetinEditoru editor) {
        this.editor = editor;
    }

    @Override
    public boolean execute() {
        JFrame aramaArayuzu = new JFrame("Kelime Bul ve Değiştir"); // Ana cerceveye ek yeni cerceve(arayuz) olusturuluyor.
        aramaArayuzu.setLayout(new FlowLayout()); // Arayuz duzeni saglaniyor.

        // Arayuze arama eyleminin gerceklesmesi komutunu alan buton ekleniyor.
        editor.buton = new JButton("Bul ve Değiştir");
        editor.buton.addActionListener(editor);

        // Bulunacak kelimenin kullanicidan alinacagi ekran olusturuluyor.
        editor.bulunacakKelime = new JTextField();
        editor.bulunacakKelime.setPreferredSize(new Dimension(200, 40));

        // Bulunan kelimenin yerine konulacak kelimenin kullanicidan alinacagi ekran olusturuluyor.
        editor.yerineKoyulacakKelime  = new JTextField();
        editor.yerineKoyulacakKelime.setPreferredSize(new Dimension(200, 40));

        // Ekran etiketleri olusturuluyor.
        JLabel bul = new JLabel("Bu Kelimeyi Bul:");
        bul.setSize(95,40);
        JLabel degistir = new JLabel("Bu Kelime İle Değiştir:");
        degistir.setSize(125,40);

        // Etiketler ve ekranlar arayuze ekleniyor.
        aramaArayuzu.add(bul);
        aramaArayuzu.add(editor.bulunacakKelime);
        aramaArayuzu.add(degistir);
        aramaArayuzu.add(editor.yerineKoyulacakKelime);
        aramaArayuzu.add(editor.buton);

        aramaArayuzu.pack(); // Arayuzun, arayuz ogelerinin cerceveye sigacagi sekilde boyutlanmasi saglaniyor.
        aramaArayuzu.setLocationRelativeTo(null);
        aramaArayuzu.setVisible(true); // Menude secildiginde arama yapilan arayuz gorunur hale geliyor.

        return true;
    }
}
