import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Sasha on 3/21/2017.
 */
public class GooglePage {
    @FindBy(css = "input[name='q']")
    private WebElement searchField;

    public void setValue(final String value){
        searchField.clear();
        searchField.sendKeys(value);
        searchField.sendKeys(Keys.ENTER);
    }

}
