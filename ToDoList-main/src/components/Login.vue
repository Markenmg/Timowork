<script setup>

const url = "https://demo2.z-bit.ee/users/get-token"

const emit = defineEmits(['auth']);

//prevent login button from working
const handleLogin = (e,) => {
    e.preventDefault(); // Stop page reload
    const password = document.getElementById("password").value;
    const username = document.getElementById("username").value;

    const data = {
        "username": username,
        "password": password,
    }

    //gets the users token
    fetch(url, {
    method: "POST", // or "POST"
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
})
  .then(res => res.json())
  .then(data => { 
    //send the access token upwards
    emit("auth", data.access_token);

   })
  .catch(err => { console.log(err);});

};

</script>
<template>
    <div class="login-container">
        <h1 class="title has-text-left">Login</h1>
        <form id="loginForm" class="is-flex is-flex-direction-column" @submit="handleLogin">
            <!-- Username Field -->
            <div class="field">
                <label class="label" for="username">Username</label>
                <div class="control">
                    <input class="input" type="text" id="username" placeholder="your.name@gmail.com">
                </div>
            </div>

            <!-- Password Field -->
            <div class="field">
                <label class="label" for="password">Password</label>
                <div class="control">
                    <input class="input" type="password" id="password" placeholder="password">
                </div>
            </div>

            <!-- Submit Button -->
            <div class="field is-grouped is-grouped-centered">
                <div class="control">
                    <button type="submit" class="button is-primary">Login</button>
                </div>
            </div>
        </form>
    </div>
</template>

<style scoped> 
.login-container {
  width: 100%;
  max-width: 500px;
}

</style>