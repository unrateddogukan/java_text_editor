package proje2;

import javax.swing.*;

// Program arayuzu menusunde “Yazım Hatası Kontrolü Yap” secimi yapildiginda
// uygulanan komutun gerceklestirecegi eylemleri barindiran ve 
// gerekli metotlari cagiran class.
public class YazimKontroluKomutu implements UndoableCommand{

    public MetinEditoru editor;
    private String backup;

    public YazimKontroluKomutu(MetinEditoru editor) {
        this.editor = editor;
    }

    @Override
    public boolean execute() {
        backup = editor.anaEkran.getText();

        YazimHatasiKontrolu yazimHatasiKontrolu = new YazimHatasiKontrolu(editor.anaEkran.getText()); // Ana ekrandaki yazi

        // Hatalari duzeltilen yazi ana ekranda gozukur.
        editor.anaEkran.setText(yazimHatasiKontrolu.getDuzeltilenYazi());

        // Acilir pencerede hatali kelimeler gosterilir.
        JOptionPane.showMessageDialog(editor.cerceve, yazimHatasiKontrolu.getDuzeltilenKelimeler());

        return true;
    }

    @Override
    public boolean undo() {
        editor.anaEkran.setText(backup);
        return true;
    }
}
