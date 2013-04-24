package de.uni_hamburg.informatik.swt.se2.mediathek.services.vormerk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Vormerkkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.AbstractBeobachtbarerService;


public class VormerkService extends AbstractBeobachtbarerService
{
    public Map<Medium, List<Vormerkkarte>> _vormerkkarten;
    
    /**
     *  Konstruktor. Erzeugt einen neuen {@link VormerkService}.
     */
    public VormerkService()
    {
        _vormerkkarten = new HashMap<Medium, List<Vormerkkarte>>();
    }
    
    /**
     * Die Methode packt aus jeder Liste in der Map "_vormerkkarten" die Vormerkkarten in eine LinkedList und gibt diese zurück.
     * @return Alle Vormerkkarten, die gespeichert sind.
     **/
    public List<Vormerkkarte> getVormerkkarten()
    {
        List<Vormerkkarte> rueckgabe = new LinkedList<Vormerkkarte>();
        for(List<Vormerkkarte> vormerkkarten : _vormerkkarten.values())
        {
            rueckgabe.addAll(vormerkkarten);
        }
        return rueckgabe;
    }
    /**
     * Die Methode speichert in die Map an Vormerkkarten für jedes Medium eine Vormerkkarte, wenn das Vormerken für alle Medien möglich ist.
     * @param kunde Der Kunde, der sich Medien vormerken lassen möchte.
     * @param medien Die Medien, die der Kunde sich vormerken lassen möchte.
     * @require istFuerAlleVormerkenMoeglich(kunde, medien) == true;
     */
    public void merkeVor(Kunde kunde, List<Medium> medien)
    {
        assert istFuerAlleVormerkenMoeglich(kunde, medien)== true : "Vorbedingung verletzt: istFuerAlleVormerkenMoeglich(kunde, medien)== true";
        for (Medium medium : medien)
        {
            Vormerkkarte vormerkkarte = new Vormerkkarte(kunde, medium);
            if(_vormerkkarten.get(medium)==null)
            {
                _vormerkkarten.put(medium, new LinkedList<Vormerkkarte>());
                _vormerkkarten.get(medium).add(vormerkkarte);
            }
            else
            {
                _vormerkkarten.get(medium).add(vormerkkarte);
            }
            
            
            /*
            *TODO ?? Vormerkaktionen protokollieren? Dazu den Protokollierer anpassen? _protokollierer.protokolliere("Vorgemerkt", verleihkarte);
            */
        }
        informiereUeberAenderung();
    } 
 
    /**
     * Die Methode überprüft, ob der {@link Kunde} der erste ist der dieses {@link Medium} vorgemerkt hat oder das Medium garnicht vorgemerkt ist. (Gut zu gebrauchen um festzustellen, ob er es ausleihen darf.)
     * @param kunde Der {@link Kunde}, für den überprüft wird, ob er das Medium vorgemerkt hat.
     * @param medium Das Medium, dessen Vorgemerktheit überprüft wird.
     * @require kunde != null
     * @require medium != null
     * @return true, wenn das {@link Medium} von dem {@link Kunde}n vorgemerkt wurde und false, wenn nicht.
     */
        public boolean istLeihbar(Kunde kunde, Medium medium)
    {
       assert kunde != null : "Vorbedingung verletzt: kunde != null";
       assert medium != null : "Vorbedingung verletzt: medium != null";
       if((_vormerkkarten.get(medium)!=null)&&(_vormerkkarten.get(medium).isEmpty()==false))
       {
           return _vormerkkarten.get(medium).get(0).getVormerker().equals(kunde);
       }else
       {
           return true;
       }
    }
    /**
     * Überprüft, ob das Vormerken eines gegebenen Mediums für einen gegebenen Kunden
     * möglich ist.
     * @param kunde der Kunde, welcher vormerken möchte.
     * @param medium das Medium, welches der Kunde vormerken möchte.
     * @return die Antwort, ob ein Vormerken des Mediums für diesen Kunden möglich ist.
     */
    public boolean istVormerkenMoeglich(Kunde kunde, Medium medium)
    {
        List<Kunde> kunden = new ArrayList<Kunde>();
        kunden.addAll(getVormerkerFuer(medium));
        if(kunden.size() < 3)
        {
            for(Kunde i: kunden)
            {
                if(i.equals(kunde))
                {
                    return false;
                }
                
            }
            return true;
        }
        return false;
    }
    /**
     * Überprüft für eine Liste an Medien, ob der Kunde der erste ist, der sie vorgemerkt 
     * hat oder die Medien garnicht vorgemerkt sind. (Wieder gut für den Ausleihprozess)
     * @param kunde der Kunde, welcher vormerken möchte.
     * @param medien die Medien, die der Kunde vielleicht vorgemerkt haben könnte.
     * @return die Antwort, ob der sie alle vorgemerkt hat.
     */
    public boolean sindAlleLeihbar(Kunde kunde, List<Medium> medien)
    {
        for(Medium medium : medien)
        {
            if(!istLeihbar(kunde, medium))
                {
                return false;
                }
        }
        return true;
            
    }
    
    /**
     * Überprüft für eine gegebene Liste an Medien, ob das Vormerken dieser Medien 
     * für den gegebenen Kunden möglich ist.
     * @param kunde der Kunde, welcher Vormerken möchte
     * @param medien die Medien, welche der Kunde vormerken möchte.
     * @return die Antwort, ob der Kunde alle Medien vormerken kann, oder nicht.
     */
    public boolean istFuerAlleVormerkenMoeglich(Kunde kunde, List<Medium> medien)
    {
        for(Medium medium : medien)
        {
            if(!istVormerkenMoeglich(kunde, medium))
            {
                return false;
            }
        }
        return true;
    }
    /**
     * Die Methode gibt die Vormerker für ein spezifisches Medium zurück.(Gut für die GUI-Anzeige)
     * @param medium Das Medium, für das die Vormerker gelistet werden sollen.
     * @return Die Liste der Vormerker, sortiert in ihrer Reihenfolge.
     */
    public List<Kunde> getVormerkerFuer(Medium medium)
    {
        List<Vormerkkarte> vormerkkarten = getVormerkkartenFuer(medium);
        List<Kunde> kunden = new ArrayList<Kunde>();
        if(vormerkkarten!=null)
        {
            for(int n=0;n<vormerkkarten.size();n++)
            {
                kunden.add(n,vormerkkarten.get(n).getVormerker());
            }
        }
        return kunden;
    }
    /**
     * Die Methode gibt alle Vormerkkarten eines Kundens als Liste zurück.(gute Hilfsmethode) 
     * @param kunde Der Kunde, dessen Vormerkkarten gesucht werden.
     * @return Eine Liste an Vormerkkarten für den Kunden.
     */
    public List<Vormerkkarte> getVormerkkartenFuer(Kunde kunde)
    {
        List<Vormerkkarte> rueckgabe = new LinkedList<Vormerkkarte>();
        for(List<Vormerkkarte> vormerkkarten : _vormerkkarten.values())
        {
            for(Vormerkkarte vormerkkarte : vormerkkarten)
            {
                if(vormerkkarte.getVormerker().equals(kunde))
                {
                    rueckgabe.add(vormerkkarte);
                }
            }
        }
        return rueckgabe;
    }
    
    /**
     * Die Methode gibt alle Vormerkkarten eines Mediums als Liste zurück.(gute Hilfsmethode) 
     * @param medium Das Medium, dessen Vormerkkarten gesucht werden.
     * @return Eine Liste an Vormerkkarten für das Medium.
     */
    public List<Vormerkkarte> getVormerkkartenFuer(Medium medium)
    {
        return _vormerkkarten.get(medium);
    }
    
    /**
     *Diese Methode löscht den ersten Vormerker für ein Medium und wird gebraucht, wenn der betreffende Kunde dieses Medium ausleiht.
     *Um die Verleihkarte als Vormerkkarte mit zu nutzen müsste man einfach den Rückgabecharakter von remove nutzen um die Verleihkarte dann weiter zu nutzen. 
     * @param medium Das Medium, dessen erste Vormerkung entfernt werden soll.
     * @require !_vormerkkarten.get(medium).isEmpty()
     * @require _vormerkkarten.get(medium) != null
     */
    public void loescheErsteVormerktung(Medium medium)
    {
        assert _vormerkkarten.get(medium) != null : "Vorbedingung verletzt: _vormerkkarten.get(medium) != null";
        assert !_vormerkkarten.get(medium).isEmpty() : "Vorbedingung verletzt: !_vormerkkarten.get(medium).isEmpty()";
        
        _vormerkkarten.get(medium).remove(0);
        
        informiereUeberAenderung();
    }
    
    public Kunde getVormerker(Medium medium, int i)
    {
        if(_vormerkkarten.get(medium)!=null)
        {
            switch(_vormerkkarten.get(medium).size())
            {
            case 0: return null;
            case 1: if(i<1)
                {
                    return _vormerkkarten.get(medium).get(i).getVormerker();
                }
            case 2: if(i<2)
                {
                    return _vormerkkarten.get(medium).get(i).getVormerker();
                }
            case 3: return _vormerkkarten.get(medium).get(i).getVormerker();
            }
        }
        return null;
    }
}
