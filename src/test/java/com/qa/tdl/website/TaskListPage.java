package com.qa.tdl.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TaskListPage {
	
	public final static String URL = "http://localhost:9092/TaskCollection.html";
	
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
		
		@FindBy(id = "taskListCreate")
		private WebElement createTaskListInput;
		
		@FindBy(id = "taskListCreateBtn")
		private WebElement createTaskListBtn;
		
		@FindBy(id = "readTaskListId")
		private WebElement readTaskListIdInput;
		
		@FindBy(id = "readTaskListIdBtn")
		private WebElement readTaskListIdBtn;
		
		@FindBy(id = "readAllTaskList")
		private WebElement readAllBtn;
		
		@FindBy(id = "taskListIdUpdate")
		private WebElement updateTaskListIdInput;
		
		@FindBy(id = "taskListNameUpdate")
		private WebElement updateTaskListNameInput;
		
		@FindBy(id = "updateTaskListBtn")
		private WebElement updateTaskListBtn;
		
		@FindBy(id = "taskListDelete")
		private WebElement deleteTaskListInput;
		
		@FindBy(id = "deleteTaskListBtn")
		private WebElement deleteTaskListBtn;
		
		//Create Task List
		public void addTaskList(String groupName) {
			createTaskListInput.sendKeys(groupName);
			createTaskListBtn.click();
		}
		
		//Read by id
		public void readIdTaskList(Long input) {
			String id = input.toString();
			readTaskListIdInput.sendKeys(id);
			readTaskListIdBtn.click();
		}
		
		//Clicking the read all btn
		public void readAllTaskList() {
			readAllBtn.click();
		}
		
		//Updating 
		public void updateTaskList(Long idInput, String updateName) {
			String id = idInput.toString();
			updateTaskListIdInput.sendKeys(id);
			updateTaskListNameInput.sendKeys(updateName);
		}
		
		//Delete
		
		public void deleteTaskList(Long input) {
			String id = input.toString();
			deleteTaskListInput.sendKeys(id);
			deleteTaskListBtn.click();
		}

}
