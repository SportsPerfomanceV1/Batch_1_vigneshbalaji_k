console.log("testing whether js are connect with html file or not ")
const approvebtn = document.querySelector("#aprrovebtn");
const not_approvebtn = document.querySelector("#not_aprrovebtn")
let flag = 0;
const approvefun = (event)=>{
	const btn = event.target;
	const parentNode = event.target.parentNode;
	const rootNode = parentNode.parentNode;
	const eventId =  rootNode.querySelector("#eventIdValue").innerText;
	const spinner = rootNode.querySelector("#spinner");
	spinner.className = "spinner-border spinner-border-sm"
	fetch(`/event/permission/${eventId}`,{
		headers:{'Content-Type' : 'application/json'}
	})
	.then(response => response.text())
	.then((data) => {
	    
		btn.innerText = "approved"
		btn.className= "btn btn-success text-white w-100"
		const disapprove = rootNode.querySelector("#disapprove");
		disapprove.className = "btn btn-warning invisible text-white w-100"
		spinner.className = ""
			
	})
	.catch(error => console.log(error))
};
const disapprove = (event)=>{
    const parentNode = event.target.parentNode;
	const rootNode = parentNode.parentNode;
	const eventId =  rootNode.querySelector("#eventIdValue").innerText;
	console.log("the event id is :" + eventId);
	fetch(`/event/disapprove/${eventId}`,{
		headers:{'Content-Type' : 'application/json'}
	})
	.then(request => request.text())
	.then(data =>{
		rootNode.className = "invisible"
	})
	.catch(error => console.log(error));
};
