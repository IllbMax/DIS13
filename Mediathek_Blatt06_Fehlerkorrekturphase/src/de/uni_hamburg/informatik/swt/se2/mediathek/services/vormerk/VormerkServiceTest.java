package de.uni_hamburg.informatik.swt.se2.mediathek.services.vormerk;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Vormerkkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

public class VormerkServiceTest
{
    VormerkService _vormerkService;
    Kunde _kunde;
    Kunde _kunde2;
    Medium _medium;
    List<Medium> _medien;

    @Before
    public void setUp() throws Exception
    {
        _vormerkService = new VormerkService();
        _medium = new CD("ich", "du", "er", 10);
        _kunde = new Kunde(new Kundennummer(429876), "Harry", "Trotter");
        _kunde2 = new Kunde(new Kundennummer(498765),"miau","wuff");
        _medien = new ArrayList<Medium>();
        _medien.add(_medium);
    }

    /**
     * Testet leider auch gleich das merkeVor()...
     */
    @Test
    public void testGetVormerkkarten()
    {
        //Am Anfang ist nichts vorgemerkt
        assertTrue(_vormerkService.getVormerkkarten().isEmpty());
        //wenn man Vormerkt enthält unser Speicher die Vormerkkarte zu dieser Vormerkung.
        _vormerkService.merkeVor(_kunde, _medien);
        assertTrue(_vormerkService.getVormerkkarten().contains(new Vormerkkarte(_kunde,_medium)));
    }

    @Test
    public void testIstLeihbar()
    {
        //Überprüfung, ob ein Medium zunächst leihbar ist.
        assertTrue(_vormerkService.istLeihbar(_kunde, _medium));
        //Überprüfung, ob man ein Medium leihen kann, dass man sich vorgemerkt hat.
        _vormerkService.merkeVor(_kunde, _medien);
        assertTrue(_vormerkService.istLeihbar(_kunde, _medium));
        //Überprüfung, ob man nicht leihen kann, was jemand anderer vorgemerkt hat.
        _vormerkService.merkeVor(_kunde2,_medien);
        _vormerkService.loescheErsteVormerktung(_medium);
        assertFalse(_vormerkService.istLeihbar(_kunde, _medium));
        //Überprüfung, ob ein wieder nichtmehr vorgemerktes Medium ausleihbar ist.
        _vormerkService.loescheErsteVormerktung(_medium);
        assertTrue(_vormerkService.istLeihbar(_kunde, _medium));
    }

    @Test
    public void testIstVormerkenMoeglich()
    {
        //Starttrue-Prüfung
        assertTrue(_vormerkService.istVormerkenMoeglich(_kunde, _medium));
        //Schon vormerkend-Prüfung
        _vormerkService.merkeVor(_kunde, _medien);
        assertFalse(_vormerkService.istVormerkenMoeglich(_kunde, _medium));
        //Schon 3 Vormerker-Prüfung
        _vormerkService.merkeVor(_kunde2, _medien);
        _vormerkService.merkeVor(new Kunde(new Kundennummer(889910),"egon","wald"), _medien);
        assertFalse(_vormerkService.istVormerkenMoeglich(new Kunde(new Kundennummer(779910),"egin","wäld"), _medium));

    }

    @Test
    public void testSindAlleLeihbar()
    {
        //TODO die scheiß Methode wird wohl ohne Test auskommen können, oder?
    }

    @Test
    public void testIstFuerAlleVorgemerkt()
    {
      //TODO die scheiß Methode wird wohl ohne Test auskommen können, oder?
    }

    @Test
    public void testGetVormerkerFuer()
    {
        
        _vormerkService.merkeVor(_kunde, _medien);
        assertEquals(_kunde,_vormerkService.getVormerkerFuer(_medium).get(0));
        assertNotSame(null, _kunde2, _vormerkService.getVormerkerFuer(_medium).get(0));
    }

    @Test
    public void testGetVormerkkartenFuerKunde()
    {
        assertTrue(_vormerkService.getVormerkkartenFuer(_kunde).isEmpty());
        _vormerkService.merkeVor(_kunde, _medien);
        List<Vormerkkarte> blub = new LinkedList<Vormerkkarte>();
        blub.add(new Vormerkkarte(_kunde,_medium));        
        assertEquals(blub,_vormerkService.getVormerkkartenFuer(_kunde));
    }

    @Test
    public void testGetVormerkkartenFuerMedium()
    {
         assertTrue(_vormerkService.getVormerkkartenFuer(_kunde).isEmpty());
         _vormerkService.merkeVor(_kunde, _medien);
         List<Vormerkkarte> blub = new LinkedList<Vormerkkarte>();
         blub.add(new Vormerkkarte(_kunde,_medium));        
         assertEquals(_vormerkService.getVormerkkartenFuer(_medium),blub);
         
         
    }

    @Test
    public void testLoescheErsteVormerktung()
    {
        _vormerkService.merkeVor(_kunde, _medien);
        _vormerkService.loescheErsteVormerktung(_medium);
        assertTrue(_vormerkService.getVormerkkarten().size()==0);
    }

}
