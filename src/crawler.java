import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
		
	}
	
	public static void crawl() {
		WebDriver driver = new ChromeDriver();
		
		//First, we are going to log in Trello
		login(driver);
		
		//Second, we will create a meeting point which is part of the Tokio project
		createMeetingPoint(driver, 4, BATHROOM);
		
		//Third, we will create a tag related to the salary information
		createCasablancaTag(driver, "BAU", "03000000");
		
		//Finally, we will check if all of the information has been created correctly 
		//and can be retrieved from the board
		checkBoard(driver);
		
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
		try {
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
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return;

	}
	
	private static void checkBoard(WebDriver driver) {
		
		driver.get("https://trello.com/b/9ksmCVyn/strello-go");
		
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
		
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		cards.forEach((WebElement card) -> {
			//System.out.println(card.toString());
			
			card.click();
			
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			

			String color = driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[1]"))
			.getCssValue("background-color");
			
			//System.out.println(color);
			
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			switch(ProjectColors.getProject(color)) {
			//Depending on the color of the card, we will check certain information
			
			case "CASABLANCA":
				//TAGS INFORMATION
				System.out.println("-------WE ARE LOOKING THROUGH INFORMATION OF CASABLANCA PROJECT-------");
				informationCasablanca(driver);
				break;
			case "TOKIO":
				//MEETING POINT INFORMATION
				System.out.println("-------WE ARE LOOKING THROUGH INFORMATION OF TOKIO PROJECT-------");
				informationTokio(driver);
				break;
			case "MOON":
				//INFORMATION TO BE EXFILTRATED
				System.out.println("-------THERE IS STILL NO IMPLEMENTATION FOR THE MOON PROJECT-------");
				break;
			case "SPRING":
				//INFORMATION ABOUT PEERS
				System.out.println("-------THERE IS STILL NO IMPLEMENTATION FOR THE SPRING PROJECT-------");
				break;
			case "SEPTEMBER":
				//METADATA INFORMATION
				System.out.println("-------THERE IS STILL NO IMPLEMENTATION FOR THE SEPTEMBER PROJECT-------");
				break;
			default:
				break;

			}

			driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/a")).click();
		
			return;
		});
		
	}
	
	
	
	private static void informationCasablanca(WebDriver driver) {
		
		
		//VAMOS A TRATAR DE VER EL HISTORICO DE LA TARJETA
		//At this point, we have clicked a card.
		if (driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[11]/div[1]/div/a[1]")).getText().equals("Mostrar detalles")) {
			driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[11]/div[1]/div/a[1]")).click();
		}
		
		//Let's wait a little bit so everything can charge correctly
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				
		
		//We have to check the color codes in order to see the project of the card
		//We have to look thorugh the tags if we are in CASABLANCA
		List<WebElement> etiquetas= driver.findElements(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[1]/div[3]/div/div/div/button"));
		List<String> tagsPresent = new ArrayList<>();

		if(!etiquetas.isEmpty()) {
			etiquetas.forEach((WebElement etiqueta) -> {
				//Now we can process the etiquetas, put them on a list to later on see if they're present
				processTags(tagsPresent, etiqueta.getText(), etiqueta.getCssValue("background-color"));
				try {
					Thread.sleep(3500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			});
		}
		
		//we will need this for the time 
		List<WebElement> historico = driver.findElements(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[11]/div[3]/div"));
		
		//Let's wait a little bit so everything can charge correctly
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if(!historico.isEmpty()) {
			for(int i = 0; i < historico.size(); i++) {
				if(!historico.get(i).getAttribute("class").equals("phenom mod-comment-type")) {
					if(historico.get(i).getText().contains("fuera: ")) {
						String time = historico.get(i).getText().substring(historico.get(i).getText().indexOf("fuera:") + 6);
						processTime(tagsPresent, time);
						
						try {
							Thread.sleep(2500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}
				}
			}
			
		}
		
		if (driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[11]/div[1]/div/a[2]")).getText().equals("Ocultar detalles")) {
			driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[11]/div[1]/div/a[2]")).click();
		}
		
	}
	
	
	private static void processTime(List<String> tagsPresent, String time) {
		
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//DEPENDING ON THE TAG, THE TIME WILL BE TREATED DIFFERENTLY 
		if(time.contains("25 de dic.")) {
			if(tagsPresent.contains("BAU")) {
				//This means that it is the correct format for this tag
				//HERE TIME HAS TO BE TREATED INTO A SALARY
				String salary = time.substring(time.indexOf("25 de dic. de ") + 14, time.indexOf("\n"));
				if(salary.length() == 15) {
					System.out.println("The salary that has been found is: " 
							+ salary.charAt(0) + salary.charAt(1) + salary.charAt(2) + salary.charAt(3)
							+ salary.charAt(11) + salary.charAt(13) + salary.charAt(14)
							+ "€ per year ");
				} else {
					System.out.println("The salary that has been found is: " 
							+salary.charAt(0) + salary.charAt(1) + salary.charAt(2) + salary.charAt(3)
							+ salary.charAt(11) +salary.charAt(12) + salary.charAt(14) + salary.charAt(15)
							+ "€ per year ");
				}
				
			}
		}
		
		if(time.contains("1 de may. de 2000")) {
			if(tagsPresent.contains("BLOCKED")) {
				//This means that it is the correct format for this tag
				//HERE TIME IS TREATED AS A PIN-CODE
				String pinCode = time.substring(time.indexOf(" a las ") + 7, time.indexOf("\n"));
				System.out.println("The four number pin code that has been used to enter a "
						+" restricted zone is: " + pinCode.charAt(0) +" "+ pinCode.charAt(1) + " "
						+ pinCode.charAt(3) +" "+ pinCode.charAt(4));			
			}
		}
		
		if(time.contains("1 de ene.")) {
			//HERE TIME IS TREATED AS AN IDENTIFICATOR FOR A USER BETWEEN 6-8 DIGITS
			String idNumber = time.substring(time.indexOf("1 de ene. ") + 13, time.indexOf("\n"));
			 
			
			//tarjeta fuera: 1 de ene. de 1230 a las 11:48
			 
			
			if(tagsPresent.contains("REVIEW")){
				if(idNumber.length() == 15) {
					
					System.out.println("The person who is getting fire is the one with id number: " 
							+ idNumber.charAt(0) + idNumber.charAt(1) + idNumber.charAt(2) + idNumber.charAt(3)
							+ idNumber.charAt(11) + idNumber.charAt(13) + idNumber.charAt(14));
				} else {
					
					System.out.println("The person who is getting fire is the one with id number: " 
							+ idNumber.charAt(0) + idNumber.charAt(1) + idNumber.charAt(2) + idNumber.charAt(3)
							+ idNumber.charAt(11) + idNumber.charAt(12) + idNumber.charAt(14) + idNumber.charAt(15));
				}
			}
			
			if (tagsPresent.contains("PENDING")) {
				if(idNumber.length() == 15) {
					System.out.println("The person who is getting favoritism is the one with id number: " 
							+ idNumber.charAt(0) + idNumber.charAt(1) + idNumber.charAt(2) + idNumber.charAt(3)
							+ idNumber.charAt(11) + idNumber.charAt(13) + idNumber.charAt(14));
				} else {
					System.out.println("The person who is getting favoritism is the one with id number: " 
							+ idNumber.charAt(0) + idNumber.charAt(1) + idNumber.charAt(2) + idNumber.charAt(3)
							+ idNumber.charAt(11) +idNumber.charAt(12) + idNumber.charAt(14) + idNumber.charAt(15));
				}
			}
			
			if(tagsPresent.contains("ONTIME")) {
				if(idNumber.length() == 15) {
					System.out.println("The person who is getting a promotion is the one with id number: " 
							+ idNumber.charAt(0) + idNumber.charAt(1) + idNumber.charAt(2) + idNumber.charAt(3)
							+ idNumber.charAt(11) + idNumber.charAt(13) + idNumber.charAt(14));
				} else {
					System.out.println("The person who is getting a promotion is the one with id number: " 
							+ idNumber.charAt(0) + idNumber.charAt(1) + idNumber.charAt(2) + idNumber.charAt(3)
							+ idNumber.charAt(11) +idNumber.charAt(12) + idNumber.charAt(14) + idNumber.charAt(15));
				}
			}
		}
	}

	
	private static List<String> processTags(List<String> tagsPresent, String text, String color){
		
		
		String tag = casablancaTags.returnTag(text, color);
		
		if(!tag.equals("")) {
			tagsPresent.add(tag);
		}

		return tagsPresent;
		
	}
	
	private static void informationTokio(WebDriver driver) {
		
		//VAMOS A TRATAR DE VER EL HISTORICO DE LA TARJETA
		if (driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[11]/div[1]/div/a[1]")).getText().equals("Mostrar detalles")) {
			driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[11]/div[1]/div/a[1]")).click();
		}
		
		//Let's wait a little bit so everything can charge correctly
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//We have to check the color codes in order to see the project of the card
		
		//-------------------THIS IS ALL FOR TOKIO---------------------------------
		
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
		
		if (driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[11]/div[1]/div/a[2]")).getText().equals("Ocultar detalles")) {
			driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[11]/div[1]/div/a[2]")).click();
		}
		
		
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
		
		driver.get("https://trello.com/b/9ksmCVyn/strello-go");
		
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

		for(int j = 0; j < cards.size(); j++) {
			
			System.out.println(cards.get(j).toString());
			
			cards.get(j).click();
			
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			System.out.println(driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[1]"))
					.getCssValue("background-color"));	
			

			if(ProjectColors.getProject(driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[1]"))
					.getCssValue("background-color")).equals("TOKIO")){
				//This means that we are in the right color-coded card, and we can now create the meeting point
				
				//*[@id="chrome-container"]/div[3]/div/div/div/div[4]/div[10]/div
				if(driver.findElements(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[10]/div/div[4]/div")).isEmpty()) {
					//If it is empty, we will create a tasklist with 5 elements
					driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[5]/div[2]/div/a[3]")).click();
					driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[4]/div/div[2]/div/div/div/form/input[2]")).click();
					
					for(int i = 0; i < 5; i++) {
						driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[10]/div/div[5]/textarea"))
							.sendKeys("TASK " + (i+1));
						
						try {
							Thread.sleep(2500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					
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
				return;
				
			} else {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div/div/a")).click();
				
			}
					
			
		}
		
		
	}
	
	public static void createCasablancaTag(WebDriver driver, String tag, String number) {
		
		driver.get("https://trello.com/b/9ksmCVyn/strello-go");
		
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

		for(int j = 0; j < cards.size(); j++) {
			
			System.out.println(cards.get(j).toString());
			
			cards.get(j).click();
			
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			System.out.println(driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[1]"))
					.getCssValue("background-color"));	
			

			if(ProjectColors.getProject(driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[1]"))
					.getCssValue("background-color")).equals("CASABLANCA")){
				//This means that we are in the right color-coded card, and we can now create the meeting point		
				
				List<WebElement> etiquetas= driver.findElements(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[1]/div[3]/div/div/div/button"));
				
				if(etiquetas.isEmpty() || !etiquetas.contains(tag)) {
									
					//Then we have to select the tag that corresponds
					driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[5]/div[2]/div/a[2]")).click();
					try {
						Thread.sleep(3500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					//Then we loop through all of the tags
					List<WebElement> availableTags = driver.findElements(By.xpath("/html/body/div[6]/div/section/div/div/ul/li"));
					
					System.out.println(availableTags);
					try {
						Thread.sleep(3500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					for (int i = 0; i < availableTags.size(); i++) {
						WebElement possibleTag = driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/ul/li["+ (i+1) +"]/label/span[2]/div/div"));
						System.out.println("++++++++++++++"+possibleTag);
						
						try {
							Thread.sleep(3500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						
						System.out.println(possibleTag.getText());
						System.out.println(possibleTag.getCssValue("background-color"));
						
						if(casablancaTags.returnTag(possibleTag.getText(), possibleTag.getCssValue("background-color")).equals(tag)) {
							//then we select the tag
							driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/ul/li["+ (i+1) +"]/label/span[2]/div/div")).click();

							driver.findElement(By.xpath("/html/body/div[6]/div/section/header/button")).click();
							
							break;
						}
					}
				}
				
				
				//Now that we have the tag, we need the time
				driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[5]/div[2]/div/div[1]/div/button")).click();

				
				//First date, then hour
				switch(tag) {
				case "BAU":
					
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[1]/input"))
					.sendKeys(Keys.CONTROL + "a");
					
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[1]/input"))
					.sendKeys(Keys.DELETE);
					
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[1]/input"))
					.sendKeys("25/12/"+ number.substring(0, 4));
					
					try {
						Thread.sleep(3500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[2]/input"))
					.sendKeys(Keys.CONTROL + "a");
					
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[2]/input"))
					.sendKeys(Keys.DELETE);
					
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[2]/input"))
					.sendKeys(number.substring(4, 6) + ":" + number.substring(6, 8));
					
					try {
						Thread.sleep(3500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					break;
				case "BLOCKED":
					//01/05/2000
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[1]/input"))
					.sendKeys(Keys.CONTROL + "a");
					
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[1]/input"))
					.sendKeys(Keys.DELETE);
					
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[1]/input"))
					.sendKeys("01/05/2000");
					
					try {
						Thread.sleep(3500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[2]/input"))
					.sendKeys(Keys.CONTROL + "a");
					
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[2]/input"))
					.sendKeys(Keys.DELETE);
					
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[2]/input"))
					.sendKeys(number.substring(0, 2) + ":" + number.substring(2, 4));
					
					try {
						Thread.sleep(3500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					break;
				default:
					//01/01
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[1]/input"))
					.sendKeys(Keys.CONTROL + "a");
					
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[1]/input"))
					.sendKeys(Keys.DELETE);
					
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[1]/input"))
					.sendKeys("01/01/"+ number.substring(0, 4));
					
					try {
						Thread.sleep(3500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[2]/input"))
					.sendKeys(Keys.CONTROL + "a");
					
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[2]/input"))
					.sendKeys(Keys.DELETE);
					
					driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[2]/div[2]/div/div[2]/input"))
					.sendKeys(number.substring(4, 6) + ":" + number.substring(6, 8));
					
					try {
						Thread.sleep(3500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					break;
				}
				
				driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[4]/button[1]")).click();
				
				try {
					Thread.sleep(3500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				//Now we eliminate it
				driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[1]/div[6]/div/div/div/button")).click();
				
				try {
					Thread.sleep(3500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				driver.findElement(By.xpath("/html/body/div[6]/div/section/div/div/form/div[4]/button[2]")).click();
				
				try {
					Thread.sleep(3500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/a")).click();
				return;
				
			} else {
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div/div/a")).click();
				
			}
					
			
		}
		
		
	}
	

}
