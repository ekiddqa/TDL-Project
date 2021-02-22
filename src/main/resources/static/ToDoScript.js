`use strict`

const toDo = {}

//Inputs
const toDoName = document.querySelector("#toDoCreate")
const toDoNameUpdate = document.getElementById("toDoUpdate")
const toDoGroup = document.getElementById("#taskListAssignmentCreate")
const toDoGroupUpdate = document.getElementById("taskListUpdate")

//Msgs
const toDisplayReadItem = document.querySelector("#displayDivReadItem")

const printOne = (record, display) => {
	let actualText = document.createTextNode(`${record.name}`);
	console.log(record.done);
	if (record.done == 1) {
		strike.appendChild(actualText);
	} else {
		display.append(actualText);
	}
}

const printAll = (set,display) => {
    for (let record of set) {
        display.appendChild(document.createElement("br"));
        printOne(record, display);
    }
    
}

const createToDo = () =>{
let formInfo = {
    task: toDoName.Value
}

fetch('http://localhost:9092/toDo/create', {
    method: 'post',
    headers: {
        "Content-type": "application/json"
    },
    body: JSON.stringify(formInfo)
    })
    .then((response) => response.json())
    .then((data) => console.info(`Response succeeded with json ${data}`))
    .catch((err)=> console.error(err));
}

const readAllToDo = () =>
fetch('http://localhost:9092/toDo/read')
.then((response) => {
    if (response.status !== 200) {
        console.log(`Issue has occured .Status Code: ${ response.status }`);
        return;
    }
response.json().
then((data) => console.log(data));     
printAll(data, toDisplayReadItem);    
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

const readToDoById = (id) => {
    fetch (`http://localhost:9092/toDo/read/${id}`)
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

const updateToDo = () => {

    fetch (`http://localhost:9092/toDo/update/${id}`, {
        method : `PUT`,
        headers : {
            "Content-Type" : "applcation/json"
    },
    body: JSON.stringify(readIdGarage, )
})
}

const deleteToDo = () => {
    fetch(`http://localhost:9092/toDo/delete/${id}/`, {
        method: 'delete',
      })
      .then((response) => {
        console.log(`Request succeeded with JSON response ${response.status}`);
        console.log(`Task successfully deleted.`)
      })
      .catch((err) => console.error(err));
    }
