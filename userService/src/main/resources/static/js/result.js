console.log("this is result page ");
console.log("the data is " + result)
const dashboard = document.querySelector("#dashboard");
/**
	 * <li class="text-white">AthleteId : <span id="athleteId-fp">abc</span></li>
                  <li class="text-white">coachId : <span id="coachId-fp">abc</span></li>
                  <li class="text-white">eventId : <span id="eventId-fp">abc</span></li>
                  <li class="text-white">eventTitle : <span id="eventMeetName-fp">abc</span></li>
                  <li class="text-white">eventDate : <span id="eventDate-fp">abc</span></li>
	 */
//first plater fields
const coachIdFp = document.querySelector("#coachId-fp");
const athleteIdFp = document.querySelector("#athleteId-fp");
const eventIdFp = document.querySelector("#eventId-fp");
const eventMeetNameFp = document.querySelector("#eventMeetName-fp");
const eventDateFp = document.querySelector("#eventDate-fp");
//second player fields
const coachIdSp = document.querySelector("#coachId-sp");
const athleteIdSp = document.querySelector("#athleteId-sp");
const eventIdSp = document.querySelector("#eventId-sp");
const eventMeetNameSp = document.querySelector("#eventMeetName-sp");
const eventDateSp = document.querySelector("#eventDate-sp");
/**
 *  <th scope="row" th:text="${itrstat.index + 1}"></th>
      <td id="eventId" th:text="${result.events.eventId}"></td>
      <td id="meetName" th:text="${result.events.meetName}"></td>
      <td id="category" th:text="${result.events.category}"></td>
      <td id="eventDate" th:text="${result.events.eventDate}"></td>
      <td id="location" th:text="${result.events.location}"></td>
 */
// event detail of selected event result fields
const eventid = document.querySelector("#eventId");
const eventMeetName = document.querySelector("#meetName");
const eventDate = document.querySelector("#eventDate");
const getProfileUrl =  (imageData) =>{
               
                const byteCharacters = atob(imageData); // Decode base64 string
                const byteNumbers = new Uint8Array(byteCharacters.length);
                for (let i = 0; i < byteCharacters.length; i++) {
                    byteNumbers[i] = byteCharacters.charCodeAt(i);
                }
                const blob = new Blob([byteNumbers], { type: 'image/png' });           
                const url = URL.createObjectURL(blob);
                return url;
};

const showWinner = (event) =>{
	 
	 const root = event.target.parentNode;
	 const eventId = root.parentNode.querySelector("#eventId").innerText;
	 fetchWinnersData(eventId);
};



const fetchWinnersData = (eventId) =>{
	fetch(`/result/getWinners/${eventId}` ,{
		headers:{'Content-Type':'application/json'}
	}).then(response => response.json())
	
	.then (data =>{
		 console.log("show time checking ")
		 const fpProfile = getProfileUrl(data[0].photo);
	     const spProfile = getProfileUrl(data[1].photo);
	     const fp_profile = document.querySelector("#fp-profile");
	     const sp_profile = document.querySelector("#sp-profile");
	     
	     // filling first player data on dashboard
	     fp_profile.src = fpProfile;
	     eventDateFp.innerText = eventDate.innerText;
	     eventMeetNameFp.innerText = eventMeetName.innerText;
	     eventIdFp.innerText = eventid.innerText;
	     athleteIdFp.innerText = data[0].athleteId;
	     coachIdFp.innerText = data[0].coachId;
	     console.log("the athleteid data is :" , data[0].athleteId);
	     
	     // filling second player data on dashboard
	     eventDateSp.innerText = eventDate.innerText;
	     eventMeetNameSp.innerText = eventMeetName.innerText;
	     eventIdSp.innerText = eventid.innerText;
	     athleteIdSp.innerText = data[1].athleteId;
	     coachIdSp.innerText = data[1].coachId;
	     sp_profile.src = spProfile;
	      
	     // end line of data filling on dashboard
	     console.log("this is image data that get from backend :" + fpProfile);
	     dashboard.className = "container rounded rounded-4 p-0 position-relative w-50  border border-white border-2 overflow-scroll overflow-x-hidden";
	})
};

