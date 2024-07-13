import data.StaticProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalcTest extends BaseTest{


    @Test(priority = 1, groups = "smoke", alwaysRun = true)
    public void testDivision() {
        Assert.assertEquals(calculator.div(5, 5), 1, "Неверное деление");
        Assert.assertEquals(calculator.div(10.5, 5), 2.1, "Неверное деление чисел с плавающей запятой");
    }

    @Test(enabled = false)
    public void testDivisionfalse() {
        Assert.assertEquals(calculator.div(10, 3), 5);
        Assert.assertEquals(calculator.div(10, 2.5), 3.33);
    }

    @Test(description = "Описание для отчета...")
    public void testDivisionDescription() {
        Assert.assertEquals(calculator.div(5, 5), 1, "Неверное деление");
        Assert.assertEquals(calculator.div(10.4, 6), 2.5, "Неверное деление чисел с плавающей запятой");
    }

    @Test(testName = "Название теста для отчета...", dependsOnMethods = {"testDivisionDescription", "testDivision"}, alwaysRun = true)
    public void testDivisionWithName() {
        Assert.assertEquals(calculator.div(5, 5), 1, "Неверное деление");
        Assert.assertEquals(calculator.div(10.5, 5), 2.1, "Неверное деление чисел с плавающей запятой");
    }

    @Test(timeOut = 2000, invocationCount = 4, threadPoolSize = 2)
    public void waitLongTimeTest() throws InterruptedException {
        Thread.sleep(500);
        Assert.assertEquals(calculator.div(5, 5), 1, "Неверное деление");
        Assert.assertEquals(calculator.div(10.5, 5), 2.1, "Неверное деление чисел с плавающей запятой");
        System.out.println(Thread.currentThread().getName());
    }

    @Test(invocationCount = 2, invocationTimeOut = 1000)
    public void invocationCountTest() throws InterruptedException {
        Thread.sleep(100);
        Assert.assertEquals(calculator.div(5, 5), 1, "Неверное деление");
        Assert.assertEquals(calculator.div(10.5, 5), 2.1, "Неверное деление чисел с плавающей запятой");
        System.out.println(Thread.currentThread().getName());
    }

    @Test(expectedExceptions = AssertionError.class)
    public void exceptionTest() {
        Assert.assertEquals(calculator.div(15, 5), 22);
        Assert.assertEquals(calculator.div(20.22, 6), 3.18);
    }

    @Test(dataProvider = "dataForInt", dataProviderClass = StaticProvider.class, groups = "smoke", priority = 1, threadPoolSize = 2)
    public void dataProviderTest(int a, int b, int result) {
        System.out.println(Thread.currentThread().getName());
        Assert.assertEquals(calculator.div(a, b), result, "Неверное деление");
    }

    @Test(dataProvider = "dataForDouble", dataProviderClass = StaticProvider.class, groups = "smoke", priority = 1)
    public void dataProviderTestDouble(double a, double b, double result) {
        Assert.assertEquals(calculator.div(a, b), result, "Неверное деление чисел с плавающей запятой");
    }

    @Test(groups = "smoke", priority = 1, expectedExceptions = ArithmeticException.class)
    public void testDivisionZeroInt() {
        Assert.assertEquals(calculator.div(5, 0), 0);
        Assert.assertEquals(calculator.div(-5, 0), 0);
        Assert.assertEquals(calculator.div(0, 0), 0);
    }

    @Test(groups = "smoke", priority = 1)
    public void testDivisionZeroDouble() {
        Assert.assertEquals(calculator.div(10.2, 0), Double.POSITIVE_INFINITY);
        Assert.assertEquals(calculator.div(-10.2, 0), Double.NEGATIVE_INFINITY);
        Assert.assertEquals(calculator.div(0.0, 0.0), Double.NaN);
    }

    @Test(groups = "smoke", priority = 1)
    public void testDivisionZeroByNumber() {
        Assert.assertEquals(calculator.div(0, 5), 0);
        Assert.assertEquals(calculator.div(0, 5), 0);
    }

}
