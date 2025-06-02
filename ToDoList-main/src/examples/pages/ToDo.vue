<script setup>
import { ref, computed } from 'vue';
import ItemList from '../components/ItemList.vue';


let message = ref("Hello TA23A");

let i = 1;
let items = ref([
  {id: i++, name:'Piim', isDone: false },
  {id: i++, name:'Viin', isDone: false },
  {id: i++, name:'Õlu', isDone: false },
  {id: i++, name:'Krõpsud', isDone: false },
]);

function addItem(){
  if(message.value.trim() !== ''){
    items.value.push({id: i++, name: message.value.trim(), isDone: false});
  }
  message.value = '';
}

// Uncommented to filter done items
let doneItems = computed(() => {
  return items.value.filter(item => item.isDone);
});

let todoItems = computed(() => {
  return items.value.filter(item => item.isDone == false);
});

</script>

<template>
  <div class="container mt-2">
    <div class="field has-addons">
      <div class="control">
        <input type="text" class="input" v-model="message" @keypress.enter="addItem">
      </div>
      <div class="control">
        <button class="button is-info" @click="addItem">
          Add Item
        </button>
      </div>
    </div>
    <div class="content">
      <ItemList :items="items" title="All Items"></ItemList>
      <ItemList :items="doneItems" title="Done Items"></ItemList>
      <ItemList :items="todoItems" title="Todo Items"></ItemList>
  </div>
</div>
</template>

<style></style>
