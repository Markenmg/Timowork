<script setup>
import Modal from './Modal.vue';

defineProps(['active', 'url'])
</script>

<template>
<Modal :active="active" @close="$emit('close')">
  <p class="image is-4By3">
    <img :src="`${url}?t=${Date.now()}`" alt="">
  </p>
</Modal>
</template>