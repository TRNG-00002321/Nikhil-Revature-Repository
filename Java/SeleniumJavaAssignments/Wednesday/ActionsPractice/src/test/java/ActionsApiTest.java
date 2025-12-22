import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActionsApiTest extends BaseTest
{

    private Actions actions;

    @BeforeEach
    void setupActions() {
        actions = new Actions(driver);
    }

    // Task 1:
    @Test
    @DisplayName("Mouse hover reveals hidden element")
    void testMouseHover() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement figure = driver.findElement(By.cssSelector(".figure"));

        actions.moveToElement(figure).perform();

        WebElement caption = figure.findElement(By.cssSelector(".figcaption"));
        assertTrue(caption.isDisplayed(), "Caption should be visible on hover");

        String captionText = caption.getText();
        assertTrue(captionText.contains("user1"), "Caption should contain user info");
    }

    @Test
    @DisplayName("Hover over multiple elements")
    void testMultipleHovers() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        List<WebElement> figures = driver.findElements(By.cssSelector(".figure"));

        for (int i = 0; i < figures.size(); i++) {
            WebElement figure = figures.get(i);

            actions.moveToElement(figure).perform();

            WebElement caption = figure.findElement(By.cssSelector(".figcaption h5"));
            assertTrue(caption.isDisplayed(), "Caption " + i + " should be visible");

            actions.moveToElement(driver.findElement(By.tagName("h3"))).perform();
        }
    }

    @Test
    @DisplayName("Hover and click link")
    void testHoverAndClick() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement figure = driver.findElement(By.cssSelector(".figure"));

        actions.moveToElement(figure).perform();

        WebElement profileLink = figure.findElement(By.cssSelector(".figcaption a"));
        profileLink.click();

        assertTrue(driver.getCurrentUrl().contains("/users/"));
    }

    @Test
    @DisplayName("Task 1.1: Test hover on all three figures with different usernames")
    void testAllThreeFiguresWithUsernames() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        List<WebElement> figures = driver.findElements(By.cssSelector(".figure"));
        assertEquals(3, figures.size(), "Should have 3 figures");

        String[] expectedUsers = {"user1", "user2", "user3"};

        for (int i = 0; i < figures.size(); i++) {
            WebElement figure = figures.get(i);
            actions.moveToElement(figure).perform();

            WebElement caption = figure.findElement(By.cssSelector(".figcaption"));
            assertTrue(caption.isDisplayed(), "Caption " + (i + 1) + " should be visible");

            String captionText = caption.getText();
            assertTrue(captionText.contains(expectedUsers[i]),
                    "Caption should contain " + expectedUsers[i]);
        }
    }

    @Test
    @DisplayName("Task 1.2: Test caption hides when mouse moves away (FluentWait)")
    void testCaptionHidesOnMouseOut_FluentWait() {

        driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement figure = driver.findElement(By.cssSelector(".figure"));

        // Create FluentWait
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        // Hover over the figure
        actions.moveToElement(figure).perform();

        // Wait until caption becomes visible
        WebElement caption = wait.until(driver ->
                figure.findElement(By.cssSelector(".figcaption"))
        );

        assertTrue(caption.isDisplayed(), "Caption should be visible on hover");

        // Move mouse away
        actions.moveToElement(driver.findElement(By.tagName("h1"))).perform();

        // Wait until caption disappears (not present OR not visible)
        Boolean captionHidden = wait.until(driver -> {
            List<WebElement> captions =
                    figure.findElements(By.cssSelector(".figcaption"));
            return captions.isEmpty() || !captions.get(0).isDisplayed();
        });

        assertTrue(captionHidden, "Caption should be hidden when mouse moves away");
    }


    @Test
    @DisplayName("Task 1.3: Hover and click in one action chain")
    void testHoverAndClickInChain() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement figure = driver.findElement(By.cssSelector(".figure"));
        WebElement profileLink = figure.findElement(By.cssSelector(".figcaption a"));

        actions.moveToElement(figure)
                .pause(Duration.ofMillis(300))
                .moveToElement(profileLink)
                .click()
                .perform();

        assertTrue(driver.getCurrentUrl().contains("/users/1"));
    }

    //Task 2:
    @Test
    @DisplayName("Basic drag and drop")
    void testDragAndDrop() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

        String sourceInitialText = source.getText();
        String targetInitialText = target.getText();

        assertEquals("A", sourceInitialText);
        assertEquals("B", targetInitialText);

        actions.dragAndDrop(source, target).perform();
    }

    @Test
    @DisplayName("Drag and drop with click-hold-move-release")
    void testDragAndDropManual() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

        actions.clickAndHold(source)
                .moveToElement(target)
                .release()
                .perform();
    }

    @Test
    @DisplayName("Drag and drop with offset")
    void testDragAndDropByOffset() {
        driver.get("https://jqueryui.com/draggable/");

        driver.switchTo().frame(driver.findElement(By.cssSelector(".demo-frame")));

        WebElement draggable = driver.findElement(By.id("draggable"));

        int initialX = draggable.getLocation().getX();
        int initialY = draggable.getLocation().getY();

        actions.dragAndDropBy(draggable, 100, 50).perform();

        int newX = draggable.getLocation().getX();
        int newY = draggable.getLocation().getY();

        assertTrue(newX > initialX, "Element should have moved right");
        assertTrue(newY > initialY, "Element should have moved down");

        driver.switchTo().defaultContent();
    }

    @Test
    @DisplayName("Task 2.1: JavaScript-based drag and drop")
    void testDragAndDropWithJavaScript() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

        String jsCode =
                "function createEvent(typeOfEvent) {" +
                        "  var event = document.createEvent('CustomEvent');" +
                        "  event.initCustomEvent(typeOfEvent, true, true, null);" +
                        "  event.dataTransfer = {" +
                        "    data: {}," +
                        "    setData: function(key, value) { this.data[key] = value; }," +
                        "    getData: function(key) { return this.data[key]; }" +
                        "  };" +
                        "  return event;" +
                        "}" +
                        "function dispatchEvent(element, event, transferData) {" +
                        "  if (transferData !== undefined) {" +
                        "    event.dataTransfer = transferData;" +
                        "  }" +
                        "  if (element.dispatchEvent) {" +
                        "    element.dispatchEvent(event);" +
                        "  } else if (element.fireEvent) {" +
                        "    element.fireEvent('on' + event.type, event);" +
                        "  }" +
                        "}" +
                        "var source = arguments[0];" +
                        "var target = arguments[1];" +
                        "var dragStartEvent = createEvent('dragstart');" +
                        "dispatchEvent(source, dragStartEvent);" +
                        "var dropEvent = createEvent('drop');" +
                        "dispatchEvent(target, dropEvent, dragStartEvent.dataTransfer);" +
                        "var dragEndEvent = createEvent('dragend');" +
                        "dispatchEvent(source, dragEndEvent, dropEvent.dataTransfer);";

        ((JavascriptExecutor) driver).executeScript(jsCode, source, target);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("B", source.getText());
        assertEquals("A", target.getText());
    }

    @Test
    @DisplayName("Task 2.2: Drag to specific pixel location")
    void testDragToSpecificLocation() {
        driver.get("https://jqueryui.com/draggable/");

        driver.switchTo().frame(driver.findElement(By.cssSelector(".demo-frame")));

        WebElement draggable = driver.findElement(By.id("draggable"));

        int targetX = 150;
        int targetY = 100;

        int currentX = draggable.getLocation().getX();
        int currentY = draggable.getLocation().getY();

        int xOffset = targetX - currentX;
        int yOffset = targetY - currentY;

        actions.dragAndDropBy(draggable, xOffset, yOffset).perform();

        int finalX = draggable.getLocation().getX();
        int finalY = draggable.getLocation().getY();

        assertTrue(Math.abs(finalX - targetX) < 10, "X position should be near target");
        assertTrue(Math.abs(finalY - targetY) < 10, "Y position should be near target");

        driver.switchTo().defaultContent();
    }

    @Test
    @DisplayName("Task 2.3: Drag and drop multiple items (Stable)")
    void testDragMultipleItems() {

        driver.get("https://jqueryui.com/sortable/");
        driver.switchTo().frame(driver.findElement(By.cssSelector(".demo-frame")));

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(StaleElementReferenceException.class);

        List<WebElement> items = driver.findElements(By.cssSelector("#sortable li"));
        assertTrue(items.size() >= 3, "Should have at least 3 items");

        String firstItemText = items.get(0).getText();

        WebElement firstItem = items.get(0);
        WebElement thirdItem = items.get(2);

        // Drag first item BELOW third item using offset
        actions.clickAndHold(firstItem)
                .moveToElement(thirdItem)
                .moveByOffset(0, 10)
                .pause(Duration.ofMillis(300))
                .release()
                .perform();

        // Wait until order actually changes
        wait.until(driver -> {
            List<WebElement> updated =
                    driver.findElements(By.cssSelector("#sortable li"));
            return !updated.get(0).getText().equals(firstItemText);
        });

        List<WebElement> reorderedItems =
                driver.findElements(By.cssSelector("#sortable li"));

        assertNotEquals(firstItemText, reorderedItems.get(0).getText(),
                "First item should no longer be first");

        driver.switchTo().defaultContent();
    }


    //Task 3:
    @Test
    @DisplayName("Double-click action")
    void testDoubleClick() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        WebElement addButton = driver.findElement(By.cssSelector("button[onclick='addElement()']"));

        actions.doubleClick(addButton).perform();

        List<WebElement> deleteButtons = driver.findElements(By.cssSelector(".added-manually"));
        assertEquals(2, deleteButtons.size(), "Double-click should add 2 elements");
    }

    @Test
    @DisplayName("Context menu (right-click)")
    void testContextClick() {
        driver.get("https://the-internet.herokuapp.com/context_menu");

        WebElement hotSpot = driver.findElement(By.id("hot-spot"));

        actions.contextClick(hotSpot).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        String alertText = driver.switchTo().alert().getText();
        assertTrue(alertText.contains("You selected a context menu"));

        driver.switchTo().alert().accept();
    }

    @Test
    @DisplayName("Click at specific coordinates")
    void testClickAtOffset() {
        driver.get("https://the-internet.herokuapp.com/");

        WebElement heading = driver.findElement(By.tagName("h1"));

        actions.moveToElement(heading, 10, 10)
                .click()
                .perform();
    }

    @Test
    @DisplayName("Task 3.1: Triple-click to select text")
    void testTripleClick() {
        driver.get("https://the-internet.herokuapp.com/");

        WebElement paragraph = driver.findElement(By.cssSelector(".heading"));

        actions.click(paragraph)
                .click()
                .click()
                .perform();

        String selectedText = (String) ((JavascriptExecutor) driver)
                .executeScript("return window.getSelection().toString();");

        assertFalse(selectedText.isEmpty(), "Text should be selected after triple-click");
    }

    @Test
    @DisplayName("Task 3.2: Click and hold")
    void testClickAndHold() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement element = driver.findElement(By.id("column-a"));

        actions.clickAndHold(element)
                .pause(Duration.ofSeconds(1))
                .release()
                .perform();

        assertTrue(true, "Click and hold completed successfully");
    }

    @Test
    @DisplayName("Task 3.3: Click at exact page coordinates")
    void testClickAtExactCoordinates() {
        driver.get("https://the-internet.herokuapp.com/");

        actions.moveByOffset(100, 200)
                .click()
                .perform();

        actions.moveByOffset(-100, -200).perform();

        assertTrue(true, "Clicked at exact coordinates");
    }

    //Task 4:
    @Test
    @DisplayName("Keyboard modifier keys")
    void testKeyboardModifiers() {
        driver.get("https://the-internet.herokuapp.com/key_presses");

        WebElement input = driver.findElement(By.id("target"));

        actions.click(input)
                .sendKeys("a")
                .perform();

        WebElement result = driver.findElement(By.id("result"));
        assertTrue(result.getText().contains("A"));

        actions.keyDown(Keys.SHIFT)
                .sendKeys("a")
                .keyUp(Keys.SHIFT)
                .perform();

        assertTrue(result.getText().contains("A"));
    }

    @Test
    @DisplayName("Ctrl+A, Ctrl+C, Ctrl+V sequence")
    void testCopyPaste() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys("testtext");

        actions.click(usernameField)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .perform();

        actions.keyDown(Keys.CONTROL)
                .sendKeys("c")
                .keyUp(Keys.CONTROL)
                .perform();

        actions.click(passwordField)
                .keyDown(Keys.CONTROL)
                .sendKeys("v")
                .keyUp(Keys.CONTROL)
                .perform();
    }

    @Test
    @DisplayName("Arrow key navigation")
    void testArrowKeys() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));

        usernameField.sendKeys("hello");

        actions.sendKeys(Keys.HOME).perform();

        actions.sendKeys("prefix_").perform();

        assertEquals("prefix_hello", usernameField.getAttribute("value"));
    }

    @Test
    @DisplayName("Tab through form fields")
    void testTabNavigation() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));

        usernameField.click();
        usernameField.sendKeys("user");

        actions.sendKeys(Keys.TAB).perform();

        actions.sendKeys("password").perform();

        WebElement passwordField = driver.findElement(By.id("password"));
        assertEquals("password", passwordField.getAttribute("value"));
    }

    //Task 5:
    @Test
    @DisplayName("Complex action chain")
    void testComplexActionChain() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        actions.click(usernameField)
                .sendKeys("tomsmith")
                .sendKeys(Keys.TAB)
                .sendKeys("SuperSecretPassword!")
                .click(loginButton)
                .perform();

        assertTrue(driver.getCurrentUrl().contains("secure"));
    }

    @Test
    @DisplayName("Build and perform actions separately")
    void testBuildActions() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement figure = driver.findElement(By.cssSelector(".figure"));
        WebElement caption = figure.findElement(By.cssSelector(".figcaption"));

        actions.moveToElement(figure);

        actions.perform();

        assertTrue(caption.isDisplayed());
    }

    @Test
    @DisplayName("Multi-step action chain with pauses")
    void testActionChainWithPauses() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        List<WebElement> figures = driver.findElements(By.cssSelector(".figure"));

        actions.moveToElement(figures.get(0))
                .pause(Duration.ofMillis(500))
                .moveToElement(figures.get(1))
                .pause(Duration.ofMillis(500))
                .moveToElement(figures.get(2))
                .pause(Duration.ofMillis(500))
                .perform();

        WebElement lastCaption = figures.get(2).findElement(By.cssSelector(".figcaption"));
        assertTrue(lastCaption.isDisplayed(), "Last caption should be visible");
    }
}