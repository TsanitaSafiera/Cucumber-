package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // features we use to provide the path of all feature file
        features = "src/test/resources/features/",
        // glue keyword we use to provide the package where steps definitions are available
        glue = "steps",
        //  when dry run is true it stops actual execution, it quickly scan all the steps
        //  and will provide the missing definition
        dryRun = false,
        //tags = "@sprint1 or @sprint2"
        tags = "@Db",
        // it means sometime the console output for cucumber test is having some
        // irrelevant information, when you set to true, it removes aa that
        // irrelevant information from the console and will give you simple output
        monochrome = true,
        // it used to print all the steps in console
        // html plugin is generating report, this report will be generated under
        // target folder
        plugin = {"pretty","html:target/cucumber.html","json:target/cucumber.json",
        "rerun:target/failed.txt"}

)

public class RunnerClass {

}
