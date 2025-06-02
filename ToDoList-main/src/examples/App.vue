<script setup>
import { ref } from 'vue';
import Tabs from './components/Tabs.vue';
import { RouterView } from 'vue-router';

//date 18 2025
 
</script>
<template>
<Tabs></Tabs>
<div class="container">
<section class="section">
    <Suspense>
        <RouterView></RouterView>
    </Suspense>
</section>
</div>
</template>

