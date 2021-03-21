package proje2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

// Programin butun islevselliklerinde icinde birlestiren, komut nesnelerinin olusturuldugu class.
public class MetinEditoru implements ActionListener, KeyListener {

    // Geri alinabilen komutlarin saklanacagi yapi olusturuluyor.
    private final CommandHistory history = new CommandHistory();

    // Arayuz cercevisini temsil eden nesne.
    JFrame cerceve;

    // Metin ekranini temsil eden nesne.
    JTextArea anaEkran;

    // Arayuzde butona basilmasinde gorev alan nesne.
    JButton buton;

    // Metinde yapilan aramalar icin olusturulacak arayuz ekranlari
    JTextField bulunacakKelime;
    JTextField yerineKoyulacakKelime;

    // Constructor metot
    public MetinEditoru() {
        executeCommand(new EditorArayuzuOlusturKomutu(this));
    }

    // ActionListener sinifini kullanabilmek icin olusturulmasi gereken,
    // programda gerceklestirilen kullanici eylemlerini takip eden metot.
    @Override
    public void actionPerformed(ActionEvent eylem){

        // Kullanicinin menulerden sececegi eylem kaydediliyor.
        String secilenEylem = eylem.getActionCommand();

        // Kullanicinin sectigi eyleme gore programin yapacagi eylem belirleniyor ve gerceklestiriliyor.
        switch (secilenEylem) {

            // Kullanici "Yeni" eylemi sectiginde yapilacak islemler yapiliyor.
            case "Yeni":
                anaEkran.setText(""); // Metin ekrani temizleniyor.
                break;

            // Kullanici "Aç" eylemi sectiginde yapilacak islemler yapiliyor.
            case "Aç":
                dosyaAc(); //Kod takibinin daha kolay olmasi icin buraya yazilacak kod dosyaAc() metodu icine yazilip orada gerceklestiriliyor.
                break;

            // Kullanici "Kaydet" eylemi sectiginde yapilacak islemler yapiliyor.
            case "Kaydet":
                dosyaKaydet(); //Kod takibinin daha kolay olmasi icin buraya yazilacak kod dosyaKaydet() metodu icine yazilip orada gerceklestiriliyor.
                break;

            // Kullanici "Çıkış" eylemi sectiginde yapilacak islemler yapiliyor.
            case "Çıkış":
                cerceve.setVisible(false); // Arayuz kapatiliyor.
                System.exit(0); // Program calismayi sonlandiriyor.

            // Kullanici "Geri Al" eylemi sectiginde yapilacak islemler yapiliyor.
            case "Geri Al":
                geriAl(); // Metin ekraninda yapilan degisiklikler, komut nesneleri araciligi ile bir adim geri aliniyor.
                break;

            // Kullanici "Kelime Bul ve Değiştir" eylemi sectiginde yapilacak islemler yapiliyor.
            case "Kelime Bul ve Değiştir":
                executeCommand(new KelimeAramaEkraniOlusturKomutu(this)); // Yeni bir KelimeAramaEkraniOlusturKomutu olusturuluyor.
                break;

            // Kullanici "Yazım Hatası Kontrolü Yap" eylemi sectiginde yapilacak islemler yapiliyor.
            case "Yazım Hatası Kontrolü Yap":
                executeCommand(new YazimKontroluKomutu(this)); // Yeni bir YazimKontroluKomutu olusturuluyor.
                break;
        }

        // Arama ekraninda "Kelime Bul ve Değiştir" butonuna basildiginda gerceklesitilecek islemler yapiliyor.
        if (eylem.getSource() == buton){
            executeCommand(new KelimeyiDegistirKomutu(this)); // Yeni bir KelimeyiDegistirKomutu olusturuluyor.
        }
    }

    private void dosyaAc() {
        // Acilacak dosyanin secilmesinde rol oynayan nesne.
        JFileChooser dosyaSecici = new JFileChooser("f:");

        // Kullanicinin dosya acma arayuzunde hangi eylemi sectiginin karsilastirilacagi degisken.
        int secim = dosyaSecici.showOpenDialog(null);

        // Kullanicinin acmak icin bir dosya acmasiyla tetiklenen if yapisi.
        if (secim == JFileChooser.APPROVE_OPTION) {

            // Secilen dosyanin bilgilerinin/konumunun atandigi File nesnesi olusturuluyor.
            File dosya = new File(dosyaSecici.getSelectedFile().getAbsolutePath());

            try {

                // Secilen dosyanın ve dosya iceriginin okunmasinda kullanilan nesneler olusturuluyor.
                FileReader dosyaOkuyucu = new FileReader(dosya);
                BufferedReader icerikOkuyucu = new BufferedReader(dosyaOkuyucu);

                // Dosya iceriginin aktarilacagi string ve stringBuilder yapilari olusturuluyor.
                String okunanSatir;
                StringBuilder gosterilecekIcerik = new StringBuilder(icerikOkuyucu.readLine());

                // Dosya icerigi okunuyor.
                while ((okunanSatir = icerikOkuyucu.readLine()) != null) {
                    gosterilecekIcerik.append("\n").append(okunanSatir);
                }

                anaEkran.setText(gosterilecekIcerik.toString()); // Metin ekranina secilen dosyanin icerigi aktariliyor.

                // Hata yakalaniyor.
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(cerceve, exception.getMessage());
            }

        } else {
            // Kullanicinin, acma islemi arayuzunu kapattiginina dair uyari yazisi ekrana veriliyor.
            JOptionPane.showMessageDialog(cerceve, "İşlem iptal edildi.");
        }
    }

    private void dosyaKaydet() {
        // Dosyanin kaydedilmesinde rol oynayan nesne.
        JFileChooser dosyaSecici2 = new JFileChooser("f:");

        // Kullanicinin dosya acma arayuzunde hangi eylemi sectiginin karsilastirilacagi degisken.
        int secim2 = dosyaSecici2.showSaveDialog(null);

        if (secim2 == JFileChooser.APPROVE_OPTION){

            // Secilen dosyanin bilgilerinin/konumunun atandigi File nesnesi olusturuluyor.
            File dosya = new File(dosyaSecici2.getSelectedFile().getAbsolutePath());

            try {
                // Dosya yazici nesnesi olusturuluyor.
                FileWriter dosyaYazici = new FileWriter(dosya, false);

                // Dosyaya icerik yazan nesne olusturuluyor.
                BufferedWriter icerikYazici = new BufferedWriter(dosyaYazici);

                // Metin ekranindaki icerik, icerik yazan nesneye aktariliyor.
                icerikYazici.write(anaEkran.getText());

                // Kaydetme islemi tamamlandiginda kaydetme ekrani kapaniyor ve icerik yazici nesnesinin islevselligi sonlandiriliyor.
                icerikYazici.flush();
                icerikYazici.close();

                // Hata yakalaniyor.
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(cerceve, exception.getMessage());
            }

        } else {
            // Kullanicinin, kaydetme islemi arayuzunu kapattiginina dair uyari yazisi ekrana veriliyor.
            JOptionPane.showMessageDialog(cerceve, "İşlem iptal edildi.");
        }
    }

    // KeyListener sinifini kullanabilmek icin olusturulmasi gereken, bu programda kullanilmayan metotlar.
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    // Kullanicinin klavye ile giris yaptigi tuslari takip edip, her giriste yeni bir TusaBasmaKomutu olusturan metot.
    @Override
    public void keyPressed(KeyEvent e) {
        executeCommand(new TusaBasmaKomutu(this));
    }

    // Her komut icin kapsayici nitelige sahip, komut olusturucu ve olusturulan geri alinabilir komutlari history'e kaydeden metot.
    private void executeCommand(Command command) {
        if (command.execute()) {
            if (command instanceof UndoableCommand)
            history.push((UndoableCommand) command);
        }
    }

    // Geri alinabilen komutlari history'den cagiran metot.
    private void geriAl() {
        if (history.isEmpty()) return;

        UndoableCommand undoableCommand = history.pop();
        if (undoableCommand != null) {
            undoableCommand.undo();
        }
    }
}
