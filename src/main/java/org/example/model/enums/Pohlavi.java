package org.example.model.enums;

public enum Pohlavi {
    MUZ('M') ,
    ZENA('Z');

    private char kod;

    //Konstruktor
    Pohlavi(char kod){
        this.kod = kod;
    }

    public static Pohlavi getEnumFromKod(Character kod) {
        if (kod.equals('M') || kod.equals('m')) {
            return MUZ;
        }

        if (kod.equals('Z') || kod.equals('z')) {
            return ZENA;
        }

        throw  new UnsupportedOperationException("Pro daný kód " + kod + " neexistuje enum typu Pohlaví");
    }

    //Getter
    public char getKod() {
        return kod;
    }
}
