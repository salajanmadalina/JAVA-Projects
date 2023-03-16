import junit.framework.TestCase;
import org.junit.*;
import java.util.ArrayList;

public class ModelTest extends TestCase {

    private static Polinom polinom1 = Model.convertStringIntoPolinom("x^3-4x^2+4x");
    private static Polinom polinom2 = Model.convertStringIntoPolinom(("x^2-2x"));
    private static Polinom polinom3 = Model.convertStringIntoPolinom("4x^3-4x+9");
    private static int nrTesteExecutate = 0;
    private static int nrTesteCuSucces = 0;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("S-au executat " + nrTesteExecutate + " teste din care "+ nrTesteCuSucces + " au avut succes!");
    }

    @Before
    public void setUp() throws Exception {
        nrTesteExecutate++;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAdunare() {
        Polinom result = Model.adunare(polinom1, polinom2);
        assertEquals("x^3-3x^2+2x", result.toString());
        nrTesteCuSucces ++;
    }

    @Test
    public void testScadere() {
        Polinom result = Model.scadere(polinom1, polinom2);
        assertEquals("x^3-5x^2+6x", result.toString());
        nrTesteCuSucces ++;
    }

    @Test
    public void testInmultire() {
        Polinom result = Model.inmultire(polinom1, polinom2);
        assertEquals("x^5-6x^4+12x^3-8x^2", result.toString());
        nrTesteCuSucces ++;
    }

    @Test
    public void testImpartire() {
        ArrayList<Polinom> result = Model.impartire(polinom1, polinom2);
        assertEquals("x-2", result.get(0).toString());
        assertEquals("0", result.get(1).toString());
        nrTesteCuSucces ++;
    }

    @Test
    public void testDerivare() {
        Polinom result = Model.derivare(polinom1);
        assertEquals("3x^2-8x+4", result.toString());
        nrTesteCuSucces ++;
    }

    @Test
    public void testIntegrare() {
        Polinom result = Model.integrare(polinom3);
        assertEquals("x^4-2x^2+9x", result.toString());
        nrTesteCuSucces ++;
    }
}