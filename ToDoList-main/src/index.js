import './style.scss';
// Import Vue and the main App component
import { createApp } from 'vue';  // Importing createApp from Vue
import { createWebHashHistory, createRouter } from 'vue-router';

import App from './App.vue';
import NewTask from './components/pages/NewTask.vue';

const routes = [
  { path: '/NewTask', component: NewTask, name: 'New Task' }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})

const app = createApp(App);  // Creating the Vue app with the App component
app.use(router);  
app.mount('#app');  // Mounting Vue to the element with the id 'app'