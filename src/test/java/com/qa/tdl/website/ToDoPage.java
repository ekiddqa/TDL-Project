package com.qa.tdl.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class ToDoPage {
	public final static String URL = "http://localhost:9092/ToDo.html";
	
	private WebDriver driver;
		
	 WebDriver getDriver() {
			return driver;
		}


		void setDriver(WebDriver driver) {
			this.driver = driver;
		}
		
		
		
		public void ToDoWebPage(WebDriver driver) {
			 this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		
		@FindBy(id = "toDoCreate")
		private WebElement createToDoInput;
		
		@FindBy(id = "taskListAssignmentCreate")
		private WebElement createTaskAssignmentInput;
		
		@FindBy(id = "createToDoBtn")
		private WebElement createToDoBtn;
		
		@FindBy(id = "readToDoId")
		private WebElement readToDoInput;
		
		@FindBy(id = "readToDoByIdBtn")
		private WebElement readToDoBtn;
		
		@FindBy(id = "readALLToDoBtn")
		private WebElement readAllToDoBtn;
		
		@FindBy(id = "toDoIdUpdate")
		private WebElement updateIdInput;
		
		@FindBy(id = "toDoUpdate")
		private WebElement updateToDoInput;
		
		@FindBy(id = "taskListUpdate")
		private WebElement updateListInput;
		
		@FindBy(id = "toDoUpdateBtn")
		private WebElement updateToDoBtn;
		
		@FindBy(id = "toDoDelete")
		private WebElement deleteToDoInput;
		
		@FindBy(id = "toDoDeleteBtn")
		private WebElement deleteToDoBtn;
		
		//Create
		public void createToDo(String task, Long idInput) {
			String id = idInput.toString();
			createToDoInput.sendKeys(task);
			createTaskAssignmentInput.sendKeys(id);
			createToDoBtn.click();
		}
		
		//Read by id
		public void readToDoId(Long input) {
			String id = input.toString();
			readToDoInput.sendKeys(id);
			readToDoBtn.click();
		}
		
		//Read All
		public void readAllToDo() {
			readAllToDoBtn.click();
		}
		
		//Update
		public void updateToDo(Long idInput, String name, String taskList) {
			String id = idInput.toString();
			updateIdInput.sendKeys(id);
			updateToDoInput.sendKeys(name);
			updateListInput.sendKeys(taskList);
			updateToDoBtn.click();
		}
		
		//Delete
		public void deleteToDo(Long input) {
			String id = input.toString();
			deleteToDoInput.sendKeys(id);
		}

}
