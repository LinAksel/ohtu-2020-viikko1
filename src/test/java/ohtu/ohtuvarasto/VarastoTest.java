package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void virheTilavuusNolla() {
        varasto = new Varasto(-1);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void vaaraLisays() {
        double vanha = varasto.getSaldo();
        varasto.lisaaVarastoon(-1);
        assertEquals(vanha, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tuplaKonstruktoriSaldoIsompiTilavuutta() {
        varasto = new Varasto(7,8);
        assertEquals(7, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(7, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liikaOtto() {
        double maara = varasto.getSaldo();
        double tilavuus = varasto.getTilavuus();
        assertEquals(maara, varasto.otaVarastosta(tilavuus+1), vertailuTarkkuus);
    }

    @Test
    public void tuplaKonstruktoriSaldoVaara() {
        varasto = new Varasto(7,-1);
        assertEquals(7, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liikLisays() {
        double tilavuus = varasto.getTilavuus();
        varasto.lisaaVarastoon(tilavuus+3);
        assertEquals(tilavuus, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tuplaKonstruktoriTilavuusVaara() {
        varasto = new Varasto(0,7);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottoVaara() {
        double maara = varasto.getSaldo();
        assertEquals(maara, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }

    @Test
    public void tuplaKonstruktoriSaldoPienempiTilavuutta() {
        varasto = new Varasto(7,6);
        assertEquals(7, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(6, varasto.getSaldo(), vertailuTarkkuus);
        String tuloste = "saldo = 6.0, vielä tilaa 1.0";
        String vertailu = varasto.toString();
        assertEquals(tuloste, vertailu);
    }

    @Test
    public void tulostus() {
        double maara = varasto.getSaldo();
        double tilavuus = varasto.getTilavuus();
        String tuloste = "saldo = " + maara + ", vielä tilaa " + (tilavuus - maara);
        String vertailu = varasto.toString();
        assertEquals(tuloste, vertailu);
    }

}