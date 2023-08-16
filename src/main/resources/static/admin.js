const url = "http://localhost:8080/restAdmin";

//Блок загрузки таблицы

function getAllUsers() {
    setTimeout(() => {
        fetch(url + "/users")
            .then(res => res.json())
            .then(data => {
                loadUsers(data)
            })
    }, 200)
}

function getUserInfo() {
    fetch(url + "/user").then(response => response.json()).then(user =>
        loadUser(user))
}

const roleList = []
$(document).ready( function () {
    getAllUsers();
    fetch(url + "/roles")
        .then(response => response.json())
        .then(roles => {
            roles.forEach(role => {
                roleList.push(role)
            })
        })
})

function loadUsers(users) {

    let StringHtmlTable = '';
    for (let user of users) {
        StringHtmlTable +=
            `<tr>
                <th><p class="font-weight-normal">${user.id}</p></th>
                <th><p class="font-weight-normal">${user.username}</p></th>
                <th><p class="font-weight-normal">${user.lastName}</p></th>
                <th><p class="font-weight-normal">${user.age}</p></th>
                <th><p class="font-weight-normal">${user.email}</p></th>
                <th><p class="font-weight-normal">${user.roles.map(r => r.name).join(' ')}</p></th>
                <th><button type="button" class="btn btn-primary" data-toggle="modal"  onclick="showEditFunction(${user.id})">Edit</button></th>
                <th><button type="button" class="btn btn-danger" data-toggle="modal" onclick="showDeleteFunction(${user.id})" >Delete</button></th>
            </tr>`
    }
    document.getElementById('adminTableBody').innerHTML = StringHtmlTable;
}

function loadUser(user) {
    document.getElementById('infoUserAdminPage').innerHTML = `
    <p class="pl-4 pt-1 pb-1 m-0 font-weight-bold"">${user.email}</p>
    <p class="font-weight-normal pl-1 pt-1 pb-1 m-0">with role:</p>
    <p className="pt-1 pb-1 m-0 pl-1">${user.roles.map(role => role.name).join(' ')}</p>
    `;
}

getAllUsers();
getUserInfo();

//блок модал окна редактирования

function showEditFunction(id) {
    $('#editModal').modal({
        show: true
    })
    const form = $('#edit-form')[0];
    edit(id)
    form.addEventListener('submit', editFunction)
}

function editFunction(event) {
    event.preventDefault()
    let updateUser = JSON.stringify({
        id : $(`#editId`).val(),
        username: $(`#editFirstName`).val(),
        lastName : $(`#editLastName`).val(),
        age: $(`#editAge`).val(),
        email: $(`#emailEdit`).val(),
        password : $(`#passwordEdit`).val(),
        roles : getRoleEdit()
    })

    fetch(url + "/users/" + document.getElementById("editId").value, {
        method : 'PATCH',
        headers : {
            'Content-Type': 'application/json'
    },
        body : updateUser
    }).then(() => {
        $('#editModal').modal('hide');
        getAllUsers()
    })
}

function edit(id) {
    fetch(url + "/users" + `/${id}`).then(response => {
        response.json().then(user => {
            $(`#editId`).val(user.id)
            $(`#editFirstName`).val(user.username)
            $(`#editLastName`).val(user.lastName)
            $(`#editAge`).val(user.age)
            $(`#emailEdit`).val(user.email)
            $(`#passwordEdit`).val(user.password)
        })
    })
}

function getRoleEdit() {
    let role = []
    let options = $('#rolesEdit')[0].options
    for (let i = 0; i < options.length; i++) {
        if (options[i].selected) {
            role.push(roleList[i])
        }
    }
    return role
}

// блок модал окна удаления

function showDeleteFunction(id) {
    $('#deleteModal').modal({
        show: true
    })
    const form = document.getElementById('delete-form')
    deleteInput(id)
    form.addEventListener('submit', deleteEvent)
}

function deleteEvent(event) {
    event.preventDefault()
    fetch(url + "/users/" + document.getElementById("idDelete").value, {
      method : 'DELETE'
    }).then(() => {
        $('#deleteModal').modal('hide');
        getAllUsers()
    })
}

function deleteInput(id) {
    fetch(url +  "/users" + `/${id}`).then(response => {
        response.json().then(user => {
            $(`#idDelete`).val(user.id)
            $(`#FirstNameDelete`).val(user.username)
            $(`#LastNameDelete`).val(user.lastName)
            $(`#ageDelete`).val(user.age)
            $(`#emailDelete`).val(user.email)
            $(`#passwordDelete`).val(user.password)
        })
    })
}

function getRoleNew() {
    let role = []
    let options = $('#rolesNew')[0].options
    for (let i = 0; i < options.length; i++) {
        if (options[i].selected) {
            role.push(roleList[i])
        }
    }
    return role
}

function functionAddNewUser() {
    let newUserForm = document.getElementById('newUser')
    let newUserBody = JSON.stringify({
        username: $(`#firstNameNew`).val(),
        lastName : $(`#lastNameNew`).val(),
        age: $(`#ageNew`).val(),
        email: $(`#emailNew`).val(),
        password : $(`#passwordNew`).val(),
        roles : getRoleNew()
    })
    fetch("http://localhost:8080/restAdmin/userNew", {
        method : 'POST',
        headers : {
            'Content-Type': 'application/json',
        },
        body : newUserBody
    }).then(() => {
        newUserForm.reset()
        getAllUsers()
    })
}