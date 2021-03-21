package proje2;

//Program arayuzu menusunde “Kelime Bul ve Değiştir” secimi yapildiginda acilan ekrandaki 
//“Bul ve Değiştir” butonuna basildigi zaman gerekli eylemleri gerceklesiterecek komut nesnesinin class'i.

public class KelimeyiDegistirKomutu implements UndoableCommand{

    public MetinEditoru editor;
    private String backup;

    public KelimeyiDegistirKomutu(MetinEditoru editor) {
        this.editor = editor;
    }

    @Override
    public boolean execute() {
        backup = editor.anaEkran.getText();

        String x = editor.anaEkran.getText(); // Ana ekrandaki yazi bir string degiskenine ataniyor.
        //Bu yazidaki belli kelimeler, yeni kelimeler ile degistiriliyor.
        x = x.replace(editor.bulunacakKelime.getText(), editor.yerineKoyulacakKelime.getText());
        editor.anaEkran.setText(x); // Guncellenen string degiskeni ana ekranda gozukuyor.
        return true;
    }

    @Override
    public boolean undo() {
        editor.anaEkran.setText(backup);
        return true;
    }
}
