<script setup>
import { ref } from 'vue';
import Login from './components/Login.vue';
import Signin from './components/Signin.vue';
import Tasks from './components/Tasks.vue';
import Tabs from './components/Tabs.vue';

// State to control the visibility of the curriculum box
const isLoginBoxActive = ref(false);
const isSigninBoxActive = ref(false);

let token = ref(0);
let active = ref(-1);
let refreshTasks = ref(0);

// Toggle function to change visibility
const toggleLoginBox = () => {
    isLoginBoxActive.value = !isLoginBoxActive.value;
};

const toggleSigninBox = () => {
    isSigninBoxActive.value = !isSigninBoxActive.value;
};


let tabs = [
    { name: "Login", event: toggleLoginBox },
    { name: "Sign in", event: toggleSigninBox },
];

function updateActive(val) {
    active.value = val;
}

function startEvent(key) {
    tabs[key].event();
}

function setToken(val){
    token.value = val;  
}

</script>

<template>

    <Tabs :tabs="tabs" :active="active" @update:active="updateActive" @update:eventKey="startEvent"/>
    <div class="container mt-5">
        <!-- login area -->
        <div class="box" :class="{ 'is-hidden': active != 0 }">
            <!-- Item 1 -->
            <div class="collapsible-item is-flex is-justify-content-center is-align-items-center">
                <Login @auth="setToken"/>
            </div>
        </div>
        <div class="box" :class="{ 'is-hidden': active != 1 }">
            <!-- Item 1 -->
            <div class="collapsible-item is-flex is-justify-content-center is-align-items-center">
                <Signin @auth="setToken" />
            </div>
        </div>

        
    
        <!-- Tasks box -->
        <div class="box tasks">            
            <Tasks :token="token" :key="refreshTasks"></Tasks>
            
        </div>
    </div>
</template>

<style scoped>
/* Transition effect for box appearance */
.box {
    transition: opacity 0.5s ease, transform 0.5s ease;
    box-shadow: none !important;
}

.box.is-hidden {
    opacity: 0;
    transform: scale(0.95);
    /* Optionally scale it down when hidden */
}

/* Optional: Give the box a little shadow when it's visible */
.box:not(.is-hidden) {
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}
</style>

<style>
/* Transition effect for button */
.button {
    background-color: #1756ad;
    color: rgb(0, 0, 0);
    transition: all 0.3s ease;
    color: white;
    /* Smooth transition for all properties */
}

.button:hover {
    transform: scale(1.1);
    /* Make the button slightly bigger on hover */
    background-color: #66a5e9;
    /* Darker blue background on hover */
    }
</style>