package marathon3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SalesForceActions extends BaseClass{
	
	@Test(priority=1)
	public void runCreateLead() {

		driver.findElement(By.xpath("//div[contains(@class, 'slds-icon-waffle')]")).click();
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//p[text()='Sales']/ancestor::a")).click();
		WebElement acc = driver.findElement(By.xpath("//span[text()='Accounts']"));
		driver.executeScript("arguments[0].click()", acc);
		driver.findElement(By.xpath("//a[@title='New']")).click();
		String accountName = "Gokul";
		driver.findElement(By.xpath("//label[text()='Account Name']/following::input")).sendKeys(accountName);
		WebElement ownershipDD = driver.findElement(By.xpath("//button[@aria-label='Ownership']"));
		driver.executeScript("arguments[0].click()", ownershipDD);
		driver.findElement(By.xpath("(//lightning-base-combobox-item//span)[6]")).click();
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		String toastMessage = driver
				.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"))
				.getText();
		System.out.println(toastMessage);
		Assert.assertTrue(toastMessage.contains(accountName), "Verify the Account name");
	}
	
	
	@BeforeTest
	public void setValue()
	{
		filename="EditLeadData";
	}
	@Test(dataProvider="d",priority=2)
	 public void runEditLead(String name,String billing_addr,String shipping_addr) throws InterruptedException{
	        
			driver.findElement(By.xpath("//div[contains(@class, 'slds-icon-waffle')]")).click();
	        driver.findElement(By.xpath("//button[text()='View All']")).click();
	        driver.findElement(By.xpath("//p[text()='Sales']/ancestor::a")).click();
	        WebElement accountsTab = driver.findElement(By.xpath("//a[@title='Accounts']"));
	        driver.executeScript("arguments[0].click()",accountsTab);
	        driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).sendKeys(name+ Keys.ENTER);
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//table/tbody/tr[1]//td[6]")).click();
	        driver.findElement(By.xpath("//a[@title='Edit']")).click();
	        WebElement typeDD = driver.findElement(By.xpath("//button[@aria-label='Type']"));
	        driver.executeScript("arguments[0].click()",typeDD);
	        driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Technology Partner']")).click();
	        WebElement industryDD = driver.findElement(By.xpath("//button[@aria-label='Industry']"));
	        driver.executeScript("arguments[0].click()",industryDD);
	        driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Healthcare']")).click();
	        WebElement biilingStreet = driver.findElement(By.xpath("//label[text()='Billing Street']/following::textarea"));
	        biilingStreet.clear();
	        biilingStreet.sendKeys(billing_addr);
	        WebElement shippingStreet = driver.findElement(By.xpath("//label[text()='Shipping Street']/following::textarea"));
	        shippingStreet.clear();
	        shippingStreet.sendKeys(shipping_addr);
	        WebElement priorityDD = driver.findElement(By.xpath("//button[@aria-label='Customer Priority']"));
	        driver.executeScript("arguments[0].click()",priorityDD);
	        driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Low']")).click();
	        driver.findElement(By.xpath("//button[@aria-label='SLA']")).click();
	        driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Silver']")).click();
	        WebElement activeDD = driver.findElement(By.xpath("//button[@aria-label='Active']"));
	        driver.executeScript("arguments[0].click()",activeDD);
	        driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='No']")).click();
	        int randomNum1 = (int)(Math.random()*999999);
	        int randomNum2 = (int)(Math.random()*999999);
	        String phno = ""+randomNum1+randomNum2;
	        phno = phno.substring(0,10);
	        System.out.println(phno);
	        WebElement phnoField = driver.findElement(By.xpath("//input[@name='Phone']"));
	        phnoField.clear();
	        phnoField.sendKeys(phno);
	        WebElement upsellOpportunityDD = driver.findElement(By.xpath("//button[@aria-label='Upsell Opportunity']"));
	        driver.executeScript("arguments[0].click()",upsellOpportunityDD);
	        driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='No']")).click();
	        driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
	        Thread.sleep(2000);
	        String phnoStr = driver.findElement(By.xpath("//table/tbody/tr[1]/td[4]")).getText();
	        System.out.println(phnoStr);
	        Assert.assertTrue(phnoStr.contains(phno),"verify the mobileNumber");

	    }
	
	
	@Test(priority=3)
	public void runDeleteLead() throws InterruptedException {
	        
			driver.findElement(By.xpath("//div[contains(@class, 'slds-icon-waffle')]")).click();
			driver.findElement(By.xpath("//button[text()='View All']")).click();
	        driver.findElement(By.xpath("//p[text()='Sales']/ancestor::a")).click();
	        WebElement accountsTab = driver.findElement(By.xpath("//a[@title='Accounts']"));
	        driver.executeScript("arguments[0].click()",accountsTab);
	        driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).sendKeys("Gokul"+ Keys.ENTER);
	        Thread.sleep(2000);
	        String noOfItems = driver.findElement(By.xpath("//span[@aria-label='Recently Viewed']")).getText();
	        String[] s = noOfItems.split(" ");
	        int countBeforeDelete = Integer.parseInt(s[0]);
	        driver.findElement(By.xpath("//table/tbody/tr[1]//td[6]//span")).click();
	        driver.findElement(By.xpath("//a[@title='Delete']")).click();
	        driver.findElement(By.xpath("//button[@title='Delete']")).click();
	        Thread.sleep(2000);
	        noOfItems = driver.findElement(By.xpath("//span[@aria-label='Recently Viewed']")).getText();
	        s = noOfItems.split(" ");
	        int countAfterDelete = Integer.parseInt(s[0]);

	        Assert.assertTrue(countBeforeDelete==(countAfterDelete+1),"Verify the account has deleted");

	    }
	
}
