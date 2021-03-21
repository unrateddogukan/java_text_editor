package proje2;

import java.awt.*;

//RenkliEditor abstract class'indan extend edilerek metin editorunun
//menubar renginin siyah olmasini saglayan class.
public class SiyahRenkliEditor extends RenkliEditor {

    @Override
    public Color renkSecim() {
        return Color.black;
    }
}
