<script setup>
const props = defineProps(['tabs', 'active']);
const emit = defineEmits(['update:active', 'update:eventKey']);

function setActive(key) {
  if (key == props.active) {
    emit('update:active', -1);
  } else {
    emit('update:active', key);
  }
}
</script>



<template>
  <div class="tabs is-centered">
    <ul>
      <li v-for="(item, key) in tabs" :class="{ 'is-active': key == active }" class="tab-button" @click="() => {setActive(key);emit('update:eventKey', key);}">
        <a>{{ item.name }}</a>
      </li>
    </ul>
  </div>
</template>

<style scoped>

.tab-button {
  transition: all ease 0.1s;
  font-size: 20px;
}

.tab-button:hover {
  font-weight: bold;
}

</style>

