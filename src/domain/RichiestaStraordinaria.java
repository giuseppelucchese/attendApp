package domain;

import java.time.LocalDate;

public class RichiestaStraordinaria {
    private LocalDate data;
    private int numOre;

    public RichiestaStraordinaria(LocalDate data, int numOre) {
        this.data = data;
        this.numOre = numOre;
    }
}
