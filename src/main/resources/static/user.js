const userUrl = 'http://localhost:8080/restUser';


function getUserPage() {
    fetch(userUrl).then(response => response.json()).then(user =>
        getInformationAboutUser(user))
}

function getInformationAboutUser(user) {

    document.getElementById('UserInfoTable').innerHTML = `<tr>
    <th><p class="font-weight-normal">${user.id}</p></th>
    <th><p class="font-weight-normal">${user.firstName}</p></th>
    <th><p class="font-weight-normal">${user.lastName}</p></th>
    <th><p class="font-weight-normal">${user.age}</p></th>
    <th><p class="font-weight-normal">${user.email}</p></th>
    <th><p class="font-weight-normal">${user.roles.map(role => role.name).join(' ')}</p></th>   
    </tr>`;
}

getUserPage();