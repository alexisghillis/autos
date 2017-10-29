package services;

import dto.Model;
import dto.Version;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestSelenium {
  public static void main(String[] args) {
    System.setProperty("webdriver.gecko.driver", "C:/APPS/geckodriver.exe");

    FirefoxDriver driver = new FirefoxDriver();
    driver.get("https://www.auto-data.net/en/");

    driver.findElement(By.xpath("//*[@id=\"center\"]/div[2]/a[2]/img")).click();
//*[@id="center"]/div[2]/a[2]/img
// *[@id="center"]/div[2]/a[6]/img
    driver.findElement(By.xpath("//*[@id=\"center\"]/div[1]/a[1]/img")).click();
    //*[@id="center"]/div[1]/a[2]/img
    createModels(driver);
  }

  private static void createModels(FirefoxDriver driver) {
    List<WebElement> tablesHeaders = driver.findElements(By.cssSelector("table[style='margin:10px 0 0 0']"));
    List<WebElement> tablesData = driver.findElements(By.className("carData"));

    List<Model> models = new ArrayList<>();
    for (int i = 0; i < tablesData.size(); i++) {
      Model model = new Model();
      List<Version> versionList = new ArrayList<>();
      List<WebElement> allRows = tablesData.get(i).findElements(By.tagName("tr"));
     //remove table header
      allRows.remove(0);

      allRows.forEach(row -> {
            Version version = new Version();
            List<WebElement> cells = row.findElements(By.xpath("./*"));
            version.setModification(cells.get(0).getText());
            version.setDoors(cells.get(1).getText());
            version.setPower(cells.get(2).getText());
            version.setTypeCoupe(cells.get(3).getText());
            version.setSeats(cells.get(4).getText());
            version.setYearBegin(cells.get(5).getText());
            version.setYearEnd(cells.get(6).getText());
            versionList.add(version);
          }
      );

      model.setModelName(tablesHeaders.get(i).getText());
      model.setVersionList(versionList);
      models.add(model);
    }
  }
}