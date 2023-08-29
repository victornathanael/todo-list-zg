const name = document.getElementById('name')
const description = document.getElementById('description')
const date = document.getElementById('date')
const priority = document.getElementById('priority')
const category = document.getElementById('category')
const status = document.getElementById('status')
const categoryModal = document.getElementById('category-modal')

const createTaskBtn = document.getElementById('create-task')
const listTasksBtn = document.getElementById('list-tasks')
const addCategoryBtn = document.getElementById('add-category')

const today = new Date();
const formattedDate = today.toISOString().split('T')[0];
date.setAttribute('min', formattedDate);


window.onchange = () => {
    if (name.value.trim() !== '' && description.value.trim() !== '' && date.value && category.value) {
        createTaskBtn.removeAttribute('disabled')
    } else {
        createTaskBtn.setAttribute('disabled', '')
    }
}

const clearInput = () => {
    name.value = ''
    description.value = ''
    date.value = ''
    priority.value = '1'
    category.value = ''
    status.value = 'todo'
}

const updateTask = (taskEl, name, description, priority, category, status) => {
    const editTitle = taskEl.querySelector('.task-text h4');
    const editDescription = taskEl.querySelector('.task-text p');
    const editPriorityAndCategory = taskEl.querySelector('.task-text h6');

    editTitle.innerText = name;
    editDescription.innerText = description;
    editPriorityAndCategory.innerText = `${priority} - ${category}`
    editPriorityAndCategory.id = status
}

// modal
const modalContainer = document.getElementById('modal-container')
const closeModal = document.getElementById('close-modal')
const modalFade = document.getElementById('modal-fade')
const modalButton = document.getElementById('modal-button')

addCategoryBtn.onclick = () => {
    modalContainer.style.display = 'block'
    categoryModal.removeAttribute('disabled')
    modalButton.removeAttribute('disabled')
}

closeModal.onclick = () => {
    modalContainer.style.display = 'none'
}

window.onclick = (event) => {
    if (event.target === modalFade) {
        modalContainer.style.display = 'none'
    }
}

categoryModal.oninput = () => {
    modalButton.removeAttribute('disabled')
    if (!categoryModal.value) {
        modalButton.setAttribute('disabled', '')
    }
}

modalButton.onclick = () => {
    if (!categoryModal.value.trim()) {
        categoryModal.setAttribute('disabled', '')
        modalButton.setAttribute('disabled', '')
        return;
    }

    if (category[0].value === 'select') {
        category.innerHTML = ''
    }

    category.innerHTML += `<option value="${categoryModal.value.replace(' ', '-').toLowerCase()}" >${categoryModal.value}</option>`
    categoryModal.setAttribute('disabled', '')
    modalButton.setAttribute('disabled', '')

    if (name.value && description.value && date.value && category.value) {
        createTaskBtn.removeAttribute('disabled')
    } else {
        createTaskBtn.setAttribute('disabled', '')
    }
}

// create and list tasks

const taskContainer = document.getElementById('task-container')
const listOfTasks = document.getElementById('list-of-task')


const saveTask = () => {
    const task = document.createElement('div')
    task.classList.add('task')

    const taskText = document.createElement('div')
    taskText.classList.add('task-text')
    task.appendChild(taskText)

    const taskTitle = document.createElement('h4')
    taskTitle.innerText = name.value
    taskText.appendChild(taskTitle)

    const taskDescription = document.createElement('p')
    taskDescription.innerText = description.value
    taskText.appendChild(taskDescription)

    const taskStatus = document.createElement('h6')
    taskStatus.id = status.value
    taskStatus.innerText = `${priority.value} - ${category.value}`
    taskText.appendChild(taskStatus)

    const icons = document.createElement('div')
    icons.classList.add('icons')
    task.appendChild(icons)


    icons.innerHTML =
        `
        <img class="edit-icon" src="../assets/img/pencil-icon.png" alt="pencil icon to edit created task"> 
        <img class="delete-icon" src="../assets/img/garbage-icon.png" alt="garbage icon to delete created task">
    `


    return task
}

createTaskBtn.onclick = () => {
    listTasksBtn.removeAttribute('disabled')

    taskContainer.appendChild(saveTask())

    clearInput()

    name.focus()

    alert('Nova tarefa criada com sucesso')
    createTaskBtn.setAttribute('disabled', '')
}

listTasksBtn.onclick = () => {
    if (listOfTasks.style.display === 'flex') {
        listTasksBtn.innerText = 'Listar Tarefas'
        listOfTasks.style.display = 'none'
    } else {
        listOfTasks.style.display = 'flex'
        listTasksBtn.innerText = 'Esconder Tarefas'
    }
}

// edit and delete tasks

document.onclick = (e) => {
    const targetEl = e.target
    const taskEl = targetEl.closest('.task')
    let taskName
    let taskDescription

    if (taskEl && taskEl.querySelector('h4')) {
        taskName = taskEl.querySelector('h4').innerText
        taskDescription = taskEl.querySelector('p').innerText
    }

    // click edit icon
    if (targetEl.classList.contains('edit-icon')) {
        window.scrollTo({top: 0, behavior: 'smooth'});
        name.value = taskName
        description.value = taskDescription


        createTaskBtn.innerText = 'Editar Tarefa'
        createTaskBtn.id = 'edit-task'

        const editTaskBtn = document.getElementById('edit-task')

        editTaskBtn.onclick = () => {

            if (name.value && name.value.trim() !== '') {
                updateTask(taskEl, name.value, description.value, priority.value, category.value, status.value)
            }

            editTaskBtn.innerText = 'Criar Tarefa'
            editTaskBtn.id = 'create-task'
            createTaskBtn.setAttribute('disabled', '')
            clearInput()
        }
    }

    // click delete icon
    if (targetEl.classList.contains('delete-icon')) {
        taskEl.remove()
    }
}

// filter for status
const filterStatus = document.getElementById('filter-status');

filterStatus.onchange = () => {
    const tasks = document.querySelectorAll('.task');
    const selectedStatus = filterStatus.value;

    tasks.forEach(task => {
        const taskStatus = task.querySelector('h6').id;

        if (selectedStatus === 'all' || taskStatus === selectedStatus) {
            task.style.display = 'flex'
        } else {
            task.style.display = 'none'
        }

    });
};