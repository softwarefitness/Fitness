package fitnessAcceptanceTest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",  // تحديد المسار لملفات الـ feature
    glue = "stepdefinitions"  // تحديد المسار لملفات تعريفات الخطوات (step definitions)
)
public class RunCucumberTest {
    // هذه الفئة لا تحتاج إلى أي طريقة (method) لأنه سيتم تشغيلها بواسطة Cucumber
}


