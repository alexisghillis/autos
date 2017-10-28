package services;

import dto.Model;
import dto.Version;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.SourceType;

public class TestSelenium {
  public static void main(String[] args) {
    System.setProperty("webdriver.gecko.driver", "C:/APPS/geckodriver.exe");

    FirefoxDriver driver = new FirefoxDriver();
    driver.get("https://www.auto-data.net/en/");

    driver.findElement(By.xpath("//*[@id=\"center\"]/div[2]/a[1]/img")).click();

    driver.findElement(By.xpath("/html/body/div/div[4]/div[4]/div[1]/a[1]/img")).click();

    Model model = new Model();
    String tableHeader = driver.findElement(By.xpath("//*[@id=\"center\"]/table[3]")).getText();
    model.setModelName(tableHeader);
    WebElement table = driver.findElement(By.xpath("//*[@id=\"center\"]/table[4]"));
    List<WebElement> allRows = table.findElements(By.tagName("tr"));
    allRows.remove(0);

    List<Version> versionList = new ArrayList<>();
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

    versionList.forEach(version ->{
      System.out.println(version.getModification()
          +" "+version.getDoors()
          +" "+version.getPower()
          +" "+version.getTypeCoupe()
          +" "+version.getSeats()
          +" "+version.getYearBegin()
          +" "+version.getYearEnd());
        }
    );


  }
}