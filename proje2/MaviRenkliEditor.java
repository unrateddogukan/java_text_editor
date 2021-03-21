package proje2;

import java.awt.*;

//RenkliEditor abstract class'indan extend edilerek metin editorunun
//menubar renginin mavi olmasini saglayan class.
public class MaviRenkliEditor extends RenkliEditor {

    @Override
    public Color renkSecim() {
        return Color.blue;
    }
}
