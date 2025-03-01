var socket = io('http://localhost:9092/', {
    transports: ['polling', 'websocket']
});
socket.on('connect', function () {
   console.log("Connected");
});

socket.on('disconnect', function () {
    console.log("Disconnected");
});

socket.on('setAdmin', function(msg) {
    let admin = document.getElementById("Administator");
    admin.innerHTML = msg;
});


const userData = new Map();
userData.set('Team', '');

const joinButton = document.getElementById("joinTeamButton");

const collapsibles = document.querySelectorAll(".collapsible");

document.addEventListener('DOMContentLoaded', function() {
  const collapsibles = document.querySelectorAll(".collapsible");

  collapsibles.forEach(collapsible => {
    const header = collapsible.querySelector(".collapsible-header");
    const content = collapsible.querySelector(".collapsible-content");
    const addItemButton = collapsible.querySelector(".add-item-button");
    const name = document.getElementById("user_holder");

    addItemButton.addEventListener("click", () => {
        let liList = content.querySelectorAll("li");
        let teamName = header.children[0].innerHTML;
        const userName = name.innerHTML; // Store name for consistency
        const team = teamName + userName;

        console.log('Team Data: ' + userData.get("Team"));
        console.log(teamName);
        console.log(teamName === userData.get('Team'))

        if (addItemButton.textContent === 'LEAVE' && teamName === userData.get('Team')) {
          console.log("LEAVING TEAM");
          // Remove the user from the list and update the button
          for (let i = 0; i < liList.length; i++) {  //Iterate with the right way
              if (userName === liList[i].innerHTML) {
                  liList[i].remove(); // Use remove() instead
                  break; // Important: Stop after removing the element
              }
          }
          addItemButton.textContent = 'JOIN';
          socket.emit("LeaveTeam", teamName + name);  //Also, you can send a leave event
          userData.set('Team', '');
        }
        else if (addItemButton.textContent === 'JOIN' && userData.get('Team') === ''){
           let alreadyJoined = false;
           for (let i = 0; i < liList.length; i++) {
              if (userName === liList[i].innerHTML) {
                    alreadyJoined = true;
                    break;
              }
           }
           if (!alreadyJoined) {
                console.log("JOINING TEAM");
                let element = document.createElement('li');
                element.innerHTML = userName;
                content.appendChild(element);
                addItemButton.textContent = 'LEAVE';
                socket.emit("JoinTeam", team);
                userData.set("Team", teamName);
           }
           else{
               console.log("Already in the team")
           }
        }
    });
})});



function sendDisconnect() {
    socket.disconnect();
}


// $(document).keydown(function (e) {
//     if (e.keyCode == 13) {
//         $('#send').click();
//     }
// });

