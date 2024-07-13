import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParamTest {

    @Parameters({"numerator", "denominator"})

    @Test
    public void paramTest(@Optional("10") int num, @Optional("5") int denom){
        System.out.println(num/denom);
    }
}
