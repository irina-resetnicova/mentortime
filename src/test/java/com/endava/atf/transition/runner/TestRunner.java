package com.endava.atf.transition.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.endava.atf.transition.definitions",
        plugin = {"pretty",
                "json:target/cucumber-reports/report.json",
                "html:target/cucumber-reports/report.html",
        }

//        ,tags = ("@RegistrationUI")

)
public class TestRunner {
}










//"pretty": Этот плагин выводит результаты выполнения тестов в удобочитаемом текстовом формате в консоли.
// "json:target/cucumber-reports/report.json": Этот плагин сохраняет результаты выполнения тестов в формате JSON в указанном файле.
//"html:target/cucumber-reports/report.html": Этот плагин создает HTML-отчет о выполнении тестов.
// "junit:target/cucumber-reports/cucumber.xml": Этот плагин генерирует отчеты в формате JUnit XML, что полезно,
// если вы хотите интегрировать тесты Cucumber в систему непрерывной интеграции.
