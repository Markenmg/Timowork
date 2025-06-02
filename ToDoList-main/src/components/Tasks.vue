<script setup>

import {watch, ref, Suspense} from 'vue';
import ListTasks from './Tasks/ListTasks.vue';
import NewTask from './pages/NewTask.vue';


const props = defineProps(['token']);
const tasks = ref([]);

let active = false;

//get tasks from user
function getTasks(){
  fetch("https://demo2.z-bit.ee/tasks", {
    method: "GET",
    headers: { Authorization: `Bearer ${props.token}` },
  })
  .then(res => res.json())
  .then(val => {
    tasks.value = val
  }).catch(err => console.log(err));
}

//show the tasks when the token exists in this scope.
watch(
  () => props.token,
  (token) => {
  if (!token) return;
  active = !active;

  getTasks();
});

function DeleteTask(index) {

const id = tasks.value[index].id
fetch(`https://demo2.z-bit.ee/tasks/${id}`, {
    method: "DELETE",
    headers: {
        Authorization: `Bearer ${props.token}`,
        "Content-Type": "application/json"
    },
}).then(() => {
    console.log(`item id: ${id} deleted`)
    tasks.value.splice(index, 1);
})
    .catch(err => console.log(err));
}

function editTask(el) {
    /*
        PUT https://demo2.z-bit.ee/tasks/${id}
          
        {
        "title": "First task",
        "marked_as_done": true // not currently chanign
        }
        
    */

      //editing the task code  
      console.log(el);      
      const id = el.id
          fetch(`https://demo2.z-bit.ee/tasks/${id}`, {
            method: "PUT",
            headers: 
            {
               Authorization: `Bearer ${props.token}`,
              "Content-Type": "application/json"
            },
            body: JSON.stringify(el)
          })
          .then(res => res.json())
          .then(val => {
            console.log(val);
          }).catch(err => console.log(err));
          
}

</script>
<template>

<NewTask
  v-if="active"
  :token="props.token"
  @created="getTasks"
/>

<ListTasks :tasks="tasks" @update:edit="el => editTask(el)" @delete="index => DeleteTask(index)"></ListTasks>


</template>