`use strict`

const taskList = {}

//Inputs
const groupName = document.querySelector("#taskListCreate")
const groupNameUpdate = document.getElementById("taskListNameUpdate")

//Msgs
const toDisplayReadItem = document.querySelector("#displayDivReadItem")


const setTaskListId = (id) => {
    let taskListId = document.getElementById("taskListId")
    taskListId.value = id;
}

const createTaskList = () =>{
fetch('http://localhost:9092/taskList/create', {
    method: 'post',
    headers: {
        "Content-type": "application/json"
    },
    body: JSON.stringify(taskList)
    })
    .then((response) => response.json())
    .then((data) => console.info(`Response succeeded with json ${data}`))
    .catch((err)=> console.error(err));
}

const readAllTaskList = () =>
fetch('http://localhost:9092/taskList/read')
.then((response) => {
    if (response.status !== 200) {
        console.log(`Issue has occured .Status Code: ${ response.status }`);
        return;
    }
response.json().
then((data) => console.log(data));         
 })
.catch ((err) => console.log(`Fetch Error :-S ${err}`)
);

const getId = new URLSearchParams (window.location.search);
const printById = () => {
    console.log(getId);
    for (const id of getId){
        console.log(id);
    }
}

const readTaskListId = () => {
    let taskListId = getId.get("taskListId");
    console.log(`the id is ${taskListId}`);
    fetch (`http://localhost:9092/taskList/read/${id}`)
    .then((response) => {
        if (response.status !==200){
            console.log(`Issue has occurred ${response.status}`);
            return;
        }
        response.json()
        .then((data) => console.log(data));
    })
    .catch((err) => console.error(err));
}

const updateTaskList = () => {

    fetch (`http://localhost:9092/toDo/update/${id}`, {
        method : `PUT`,
        headers : {
            "Content-Type" : "applcation/json"
    },
    body: JSON.stringify(readIdGarage, )
})
}

const deleteTaskList = () => {
    fetch(`http://localhost:9092/toDo/delete/${id}/`, {
        method: 'delete',
      })
      .then((response) => {
        console.log(`Request succeeded with JSON response ${response.status}`);
        console.log(`Task successfully deleted.`)
      })
      .catch((err) => console.error(err));
}