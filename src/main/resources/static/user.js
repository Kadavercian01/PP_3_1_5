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
    document.getElementById('userInfo').innerHTML = `
    <p class="pl-4 pt-1 pb-1 m-0 font-weight-bold"">${user.email}</p>
    <p class="font-weight-normal pl-1 pt-1 pb-1 m-0">with role:</p>
    <p class="pt-1 pb-1 m-0 pl-1">${user.roles.map(role => role.name).join(' ')}</p>
    `;
}

getUserPage();