<script setup>

import {ref} from 'vue';

const props = defineProps(['token']);
const emit = defineEmits(['created']);


let title = ref('');
let description= ref('');

function createTask(){
  //check if values are clean.
  if (!title.value.trim() || !description.value.trim()) return
  if(title.value && description.value){

  fetch("https://demo2.z-bit.ee/tasks", {
    method: "POST",
    headers: 
    {
      Authorization: `Bearer ${props.token}`,
      "Content-Type": "application/json"
     },
    body: JSON.stringify({ title: title.value, desc: description.value }),
  }).then(() => emit('created'))
  .catch(err => console.log(err));
  }
}

</script>

<template>
  <form @submit.prevent="createTask" class="is-flex is-align-items-flex-start is-align-items-center"  style="gap: 1rem;">
    <button class="button btn-primary">Add New Item</button>

    <div class="field mb-2">
      <label class="label" for="title">Title</label>
      <div class="control">
        <input class="input" type="text" id="title" v-model="title" placeholder="title" required>
      </div>
    </div>

    <div class="field mb-2">
      <label class="label" for="description">Description</label>
      <div class="control">
        <input class="input" type="text" id="description" v-model="description" placeholder="description" required>
      </div>
    </div>
  </form>
</template>
