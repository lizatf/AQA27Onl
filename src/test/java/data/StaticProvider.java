package data;

import org.testng.annotations.DataProvider;

public class StaticProvider {

    @DataProvider(name = "dataForInt", parallel = true)
    public static Object[][] dataForDivTest() {
        return new Object[][]{
                {2, 2, 1},
                {-10, -2, 5},
                {-30, 5, -6}
        };
    }

    @DataProvider(name = "dataForDouble")
    public static Object[][] dataForDivTestDouble() {
        return new Object[][]{
                {10.5, 5, 2.1},
                {-10.5, -5, 2.1},
                {-10.5, 5, -2.1}
        };
    }
}

