package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

public class Vormerkkarte
{
    // Eigenschaften einer Vormerkkarte
    private final Kunde _vormerker;
    private final Medium _medium;
       
    /**
     * Initialisert eine neue Vormerkkarte mit den gegebenen Daten.
     * 
     * @param vormerker
     *            Ein Kunde, der das Medium vorgemerkt hat.
     * @param medium
     *            Ein vorgemerktes Medium.
     * 
     * @require vormerker != null
     * @require medium != null
     * 
     * @ensure {@link #getVormerker()} == vormerker
     * @ensure {@link #getMedium()} == medium
     */
    public Vormerkkarte(Kunde vormerker, Medium medium)
    {
        _vormerker = vormerker;
        _medium = medium;
        
        assert vormerker != null : "Vorbedingung verletzt: kunde != null";
        assert medium != null : "Vorbedingung verletzt: medium != null";
    }


    /**
     * Gibt den Vormerker zurück.
     * 
     * @return den Kunden, der das Medium vorgemerkt hat.
     * 
     * @ensure result != null
     */
    public Kunde getVormerker()
    {
        return _vormerker;
    }

    /**
     * Gibt eine String-Darstellung der Vormerkkarte (enhält Zeilenumbrüche)
     * zurück.
     * 
     * @return Eine formatierte Stringrepäsentation der Vormerkkarte. Enthält
     *         Zeilenumbrüche.
     * 
     * @ensure result != null
     */
    public String getFormatiertenString()
    {
        return _medium.getFormatiertenString() + "wurde vorgemerkt fuer "
                + _vormerker.getFormatiertenString();
    }

    /**
     * Gibt das {@link Medium}, dessen Vormerke auf der Karte vermerkt ist,
     * zurück.
     * 
     * @return Das Medium, dessen Vormerke auf dieser Karte vermerkt ist.
     * 
     * @ensure result != null
     */
    public Medium getMedium()
    {
        return _medium;
    }


    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((_vormerker == null) ? 0 : _vormerker.hashCode());
        result = prime * result + ((_medium == null) ? 0 : _medium.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean result = false;
        if (obj instanceof Vormerkkarte)
        {
            Vormerkkarte other = (Vormerkkarte) obj;

            if (other.getVormerker().equals(_vormerker)
                    && other.getMedium().equals(_medium))

                result = true;
        }
        return result;
    }

    @Override
    public String toString()
    {
        return getFormatiertenString();
    }
}


