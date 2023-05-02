import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class crawler {
	
	final static String CAFETERIA = "CAFETERIA" ;
	final static String BATHROOM = "BATHROOM";
	final static String TERACE = "TERACE";
	final static String ENTRANCE = "ENTRANCE";
	final static String EXIT = "EMERGENCY EXIT";

	public crawler() {
		// TODO Auto-generated constructor stub
	}
	
	public static void crawl() {
		WebDriver driver = new ChromeDriver();
		
		login(driver);
		
		checkBoard(driver);
		
		//createMeetingPoint(driver, 3, EXIT);
		
		//createCard(driver);
		
		driver.close();
	}
	
	private static void login(WebDriver driver) {
		driver.get("https://trello.com/login");
		
		//Let's wait a little bit so everything can charge correctly
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		driver.findElement(By.id("user")).sendKeys("****");
		
		//Let's wait a little bit so everything can charge correctly
		try {s
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		driver.findElement(By.id("login")).click();
		
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		driver.findElement(By.id("password")).sendKeys("****");
		
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		driver.findElement(By.id("login-submit")).click();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return;

	}
	
	private static void checkBoard(WebDriver driver) {
		
		driver.get("https://trello.com/b/AtfseEng/prueba");
		
		String title = driver.getTitle();
		System.out.println("YES! We have reached the trello page: "+ title);
		
		//Let's wait a little bit so everything can charge correctly
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		List<WebElement> cards = driver.findElements(By.cssSelector("a.list-card.js-member-droppable.ui-droppable"));

		cards.forEach((WebElement card) -> {
			System.out.println(card.toString());
			
			card.click();
			
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			//At this point, we have clicked a card.
			List<WebElement> etiquetas= driver.findElements(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[1]/div[3]/div/div/div/button"));
			if(!etiquetas.isEmpty()) {
				etiquetas.forEach((WebElement etiqueta) -> {
					System.out.println(etiqueta.getText());
					System.out.println(etiqueta.getCssValue("background-color"));
					
					//System.out.println(driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[2]/div/div/div/div[2]/div/div/div[2]")).getAttribute("innerText"));
					
				});
			}

				//VAMOS A TRATAR DE VER EL HISTORICO DE LA TARJETA
				WebElement botonOcultar = driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[11]/div[1]/div/a[1]"));
				if (botonOcultar.getText().equals("Mostrar detalles")) {
					botonOcultar.click();
				}
				
				//ELEMENTOS DE LA CHECKLIST PARA COMPROBAR EL ORDEN
				List<WebElement> checkListItems = driver.findElements(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[10]/div/div[4]/div"));
				List<String> checklist = new ArrayList<>();
				
				if(!checkListItems.isEmpty()) {
					//Now we have to see the order, so later on we can figure out if there is a pattern
					checkListItems.forEach((WebElement listItem) -> {
						System.out.println(listItem.getText());
						checklist.add(listItem.getText());
					});
					
				}
				
				List<WebElement> historico = driver.findElements(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[11]/div[3]/div"));
				List<String> pattern = new ArrayList<>();
				
				if(!historico.isEmpty()) {
					historico.forEach((WebElement eventoPasado) -> {
						if(!eventoPasado.getAttribute("class").equals("phenom mod-comment-type")) {
							
							if(eventoPasado.getText().contains("ha completado")) {
								System.out.println(eventoPasado.getText());
								String task = eventoPasado.getText().substring(eventoPasado.getText().indexOf("ha completado") +14, eventoPasado.getText().indexOf(" en esta tarjeta"));
								pattern.add(task);
							} else if(eventoPasado.getText().contains("ha marcado")){
								System.out.println(eventoPasado.getText());
								String task =eventoPasado.getText().substring(eventoPasado.getText().indexOf("ha marcado") +11, eventoPasado.getText().indexOf(" como incompleto"));
								pattern.add(task);
							}
						}
					});
				}
				
				List<Integer> index = new ArrayList<>();
				
				pattern.forEach((item) -> {
					index.add(checklist.indexOf(item)+1);
				});
				
				if(index.size() >= 8) {
					for (int i = 0; i <= index.size() - 8; i++) {
					    int x = index.get(i);
					    int y = index.get(i + 1);
					    //Given that there is a FIFO order, the a will be the meeting place and the b will be the floor
					    int a = index.get(i + 2);
					    int b = index.get(i + 5);

					    if (x != y && x >= 1 && x <= 5 && y >= 1 && y <= 5 &&
					        a >= 1 && a <= 5 && b >= 1 && b <= 5 && a != b &&
					        x == index.get(i + 3) && y == index.get(i + 4) &&
					        x == index.get(i + 6) && y == index.get(i + 7)) {

					        System.out.println("The meeting point is: floor " + b + " location "+ returnMeetingPlace(a));
					        
					        break;
					    }
					}
				}
		
			
				//botonOcultar.click();

			driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/a")).click();
		
			return;
		});
		
	}
	
	private static String returnMeetingPlace(int number) {
		
		switch(number) {
		case 1:
			return CAFETERIA;
		case 2:
			return BATHROOM;
		case 3:
			return TERACE;
		case 4:
			return ENTRANCE;
		case 5:
			return EXIT;
		}
		
		return "none";
	}
	
	private static int returnMeetingPlaceInteger(String location) {
		
		switch(location) {
		case CAFETERIA:
			return 1;
		case BATHROOM:
			return 2;
		case TERACE:
			return 3;
		case ENTRANCE:
			return 4;
		case EXIT:
			return 5;
		}
		
		return -1;
	}
	
	public static void createMeetingPoint(WebDriver driver, int floor, String location) {
		
		driver.get("https://trello.com/b/AtfseEng/prueba");
		
		String title = driver.getTitle();
		System.out.println("YES! We have reached the trello page: "+ title);
		
		//Let's wait a little bit so everything can charge correctly
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		List<WebElement> cards = driver.findElements(By.cssSelector("a.list-card.js-member-droppable.ui-droppable"));

		cards.forEach((WebElement card) -> {
			System.out.println(card.toString());
			
			card.click();
			
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			System.out.println(driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[1]"))
					.getCssValue("background-color"));
			
			
			if(driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[1]"))
					.getCssValue("background-color").equals("rgba(109, 236, 169, 1)")
					|| driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[1]"))
					.getCssValue("background-color").equals("rgba(148, 199, 72, 1)")) {
				//This means that we are in the right color-coded card, and we can now create the meeting point
				
				//*[@id="chrome-container"]/div[3]/div/div/div/div[4]/div[10]/div
				if(driver.findElements(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[10]/div/div[4]/div")).isEmpty()) {
					//If it is empty, we will create a tasklist with 5 elements
					driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[5]/div[2]/div/a[3]")).click();
					driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[4]/div/div[2]/div/div/div/form/input[2]")).click();
					
					for(int i = 0; i < 5; i++) {
						driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[10]/div/div[5]/textarea"))
							.sendKeys("HOLA " + (i+1));
					
						driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[10]/div/div[5]/div/input")).click();
					}
					
					//We exit the list
					driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[10]/div/div[5]/div/a[1]")).click();
				}
				
				//Now we have to do the pattern
				Random random = new Random();
				int x;
				int y;
				System.out.println("The floor is: "+floor+" and the place is "+returnMeetingPlaceInteger(location));
				// Select a random number different from the floor and the location point
				do {
				     x = random.nextInt(5) + 1;
				     System.out.println("THE x number could be:" +x);
				} while (x == floor || x == returnMeetingPlaceInteger(location));
				System.out.println("------------------THE x number IS :" +x);

				// Select another random number different from floor and the location point and the first number
				do {
					 y = random.nextInt(5) + 1;
					 System.out.println("THE y number could be:" +y);
				} while (y == floor || y == returnMeetingPlaceInteger(location) || y == x);
				System.out.println("------------------THE y number IS:" +y);
				
				List<Integer> pattern = new ArrayList<>();
					
				for (int i = 0; i < 8; i++) {
				 	if (i == 0 || i== 3 || i == 6) {
				   		pattern.add(x);
				   		System.out.println("------WE HAVE SET THE PATTERN FOR 0, 3 OR 6");
				   	} else if(i == 2) {
				   		pattern.add(floor);
				   		System.out.println("------WE HAVE SET THE PATTERN FOR 2");
				   	} else if (i == 5) {
				   		pattern.add(returnMeetingPlaceInteger(location));
				    	System.out.println("------WE HAVE SET THE PATTERN FOR 5");
				    } else {
				    	pattern.add(y);
				    	System.out.println("------WE HAVE SET THE PATTERN FOR THE REST");
				    }
				}
				    
				//Now we select the pattern

				for(int i = 0; i < pattern.size(); i++) {
				   	driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[10]/div/div[4]/div["+ pattern.get(i) +"]/div[1]")).click();
				}
				
				driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/a")).click();
				
			} else {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div/div/a")).click();
				
			}
			
			
		});
		
		
	}

}
