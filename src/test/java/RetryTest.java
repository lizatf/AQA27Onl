import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Retry;

public class RetryTest extends BaseTest {
    private int attempt = 1;

    @Test(retryAnalyzer = Retry.class)
    public void flakyTest() {
        if (attempt == 3) {
            Assert.assertEquals(calculator.div(5, 5), 1, "Неверное деление");
            Assert.assertEquals(calculator.div(10.5, 5), 2.1, "Неверное деление чисел с плавающей запятой");
        } else {
            System.out.println("Attempt is: " + attempt);
            attempt++;
            throw new NullPointerException();
        }
    }
}
