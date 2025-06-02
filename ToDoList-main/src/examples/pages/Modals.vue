<script setup>

import {ref} from 'vue';
import Modal from '../components/Modal.vue';
import ImgModal from '../components/imgModal.vue';



let modalActive = ref(false);
let modalActive2 = ref(false);
let modalActive3 = ref(false);

document.body.addEventListener('keydown', event => {
  console.log(event);
  if(event.key === "Escape"){
    emit('close');
  }
})
</script>
<template>

<div class="container">
  <section class="section">
    <div class="buttons">
    <button class="button is-primary" @click="modalActive=true">Modal Active</button>
    <button class="button is-warning is-outlined" @click="modalActive2=true">Modal 2 Active</button>
    <button class="button is-danger is-rounded" @click="modalActive3=true">Modal 3 Active</button>
  </div>
  </section>

</div>
<ImgModal :active="modalActive" @close="modalActive=false" url="https://picsum.photos/200/300"></ImgModal>
<ImgModal :active="modalActive2" @close="modalActive2=false" url="https://picsum.photos/200/300"></ImgModal>
<Modal :active="modalActive3" @close="modalActive3=false">

  <div class="notification is-primary">
    <button @click="modalActive3 = false" class="delete"></button>
    Lorem ipsum dolor sit amet consectetur adipisicing elit. Quasi aliquid dolorum sapiente accusamus cupiditate iusto, neque provident ut eaque esse deleniti excepturi, modi debitis dignissimos voluptatem magni, accusantium dicta voluptates.
  </div>
</Modal>
</template>

<style>

</style>