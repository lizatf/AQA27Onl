import org.testng.annotations.Listeners;
import utils.Listener;

@Listeners(Listener.class)

public class BaseTest {
    Calculator calculator = new Calculator();
}
