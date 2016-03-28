package ru.leonsabr.allure.demo;

import org.apache.tika.io.IOUtils;
import org.junit.Test;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.events.AddParameterEvent;

import java.io.IOException;

import static org.junit.Assert.*;

@Title("Some tests for demo purposes")
public class SimpleTest {

    private static long version = 1L;

    @Title("Example of failed test")
    @Test
    public void failed() {
        fail("This is failed test");
    }

    @Title("Basic authentication test")
    @Test
    public void demo() throws IOException {
        dumpParameter("Base URL", "http://localhost:8080");
        dumpParameter("Browser", "chrome");

        step1();
        step2();
        authentication();
    }

    @Step("Fill in Login form")
    public void step1() {
        typeUsername("leonid.rudenko");
        typePassword("123456");
    }

    @Step("Type username '{0}'")
    public void typeUsername(String username) {
    }

    @Step("Type password '{0}'")
    public void typePassword(String password) {
    }

    @Step("Submit Login form")
    public void step2() {
    }

    @Step("Check authentication state")
    public void authentication() throws IOException {
        attach("Header", IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("screen.png")));
    }

    @Attachment(value = "{0}", type = "image/png")
    private static byte[] attach(String description, byte[] bytes) {
        return bytes;
    }

    private static void dumpParameter(String key, String value) {
        Allure.LIFECYCLE.fire(new AddParameterEvent(key, value));
    }
}
