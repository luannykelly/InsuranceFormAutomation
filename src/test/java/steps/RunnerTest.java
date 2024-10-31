package steps;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature", // Caminho para os arquivos .feature
        glue = {"steps"}, // Pacote onde estão as definições dos steps
        plugin = {
                "pretty", // Exibe o output dos testes no console de forma legível
                "html:target/cucumber-reports.html", // Gera um relatório HTML
                "json:target/cucumber-reports/cucumber.json", // Gera um relatório JSON
                "junit:target/cucumber-reports/cucumber.xml" // Gera um relatório XML compatível com o JUnit
        },
        monochrome = true, // Exibe o console de forma legível
        tags = "@Test" // Utilize tags para filtrar quais cenários executar, se necessário
)

public class RunnerTest {
    // Esta classe é utilizada apenas para configurar e executar os testes
    // O @RunWith(Cucumber.class) faz com que o Cucumber execute os testes definidos nos arquivos .feature
}
