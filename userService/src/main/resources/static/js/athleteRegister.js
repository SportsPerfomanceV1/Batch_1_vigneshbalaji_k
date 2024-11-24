console.log("this is js file for athlete registeration");
/**

const searchUserIdbtn = document.querySelector("#searchUserBtn");
const athleteRegBtn = document.querySelector("#athleteRegBtn");
const closebtn = document.querySelector("#closebtn");
const athleteRegModal = document.querySelector("#athleteRegistration");
console.log("i am stand in before ");

const firstName = document.querySelector("#firstname");
const lastname = document.querySelector("#lastname");
const birthdate = document.querySelector("#birthdate");
const gender = document.querySelector("#gender");
const coachId = document.querySelector("#coachId");
const athleteId = document.querySelector("#athleteId");
const height = document.querySelector("#height");
const weight = document.querySelector("#weight");
const photo = document.querySelector("#profile");
const athlete_form = document.querySelector("#athlete-form");

const getuserData = (event) =>{
	const userId = document.querySelector("#userId");
	const userid = userId.value;
	console.log("i am stand in getUserData method in js");
	fetch(`/athlete/getUser/${userid}`,{
		headers:{"Content-Type" : "application/json"},
		
	})
	.then((request)=>{
		if (request.ok){
			userId.setAttribute('readonly', true);
		    return request.json();
		}
		else {
		    confirm("please enter valid userId")
		}
	})	
	.then((data)=>{
		console.log("the user data " + data.firstName)
		athlete_form.className  = "card-body athlete-form"
		firstName.value = data.firstName;
		lastname.value = data.lastName;
		birthdate.value = data.dob;
		gender.value = data.gender;
	})
	.catch(error => console.log("error data print from getuser fetch catch block :" + error));
} ;
 
const athleteReg = (event)=>{
	const form  = new FormData();
	console.log("the user data is :" + user)
	
	// athlete data adding to FormData that will send to server
	form.append('firstName',firstName.value);
	form.append('lastName',lastname.value);
	form.append('dob',birthdate.value);
	form.append('gender',gender.value);
	form.append('coachId' ,coachId.value);
	form.append('athleteId',athleteId.value);
	form.append('height',height.value);
	form.append('weight',weight.value);
	form.append('profile',profile.files[0]);
    form.append('userId',document.querySelector("#userId").value)
	//fetch start
	console.log("form data is " + form.getAll);
		fetch("/athlete/register",{
			method:"POST",
			body: form
		})
		.then(response => response.text())
		.then((data) =>{
			  const modalInstance = bootstrap.Modal.getInstance(athleteRegModal);
			  modalInstance.hide();
		})
		.catch(error => console.log(error))
		// fetch end
	};

const eventReg = (event) =>{
	const events = event.target.parentNode.parentNode;
	const registerBtn = events.querySelector("#registerbtn");
	const eventNode = events.querySelector("#eventId");
	const eventId = eventNode.innerText;
	const loading = registerBtn.querySelector("#loading");
	loading.className = "spinner-border spinner-border-sm text-white me"
	console.log(" i am stand in fetch method to register the event id");
	console.log("the thymeleaf obj is :" + user);
	if (user !== null) {
	fetch(`/athlete/event/register/${eventId}`,{
		 headers:{"Content-Type" : 'application/json'}
	}).then(request => request.text())
	.then(data => {
		loading.className = ""
		confirm("event has registered successfully")
		
	}).catch(error => console.log(error));
	}
	else {
		alert("You Not a Athlete So You Can't Register Event")
	}
    }
	
searchUserIdbtn.addEventListener("click",getuserData);
athleteRegBtn.addEventListener("click",athleteReg);
*/