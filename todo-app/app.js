
const API  = "https://demo2.z-bit.ee";
let token  = localStorage.getItem("todo_token");

async function request(path, opts = {}) {
  opts.headers = {
    "Content-Type": "application/json",
    ...(token ? { Authorization: `Bearer ${token}` } : {}),
    ...opts.headers,
  };
  const res = await fetch(API + path, opts);
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

/*  auth */
async function register() {
  const username = document.getElementById("username").value.trim();
  const password = document.getElementById("password").value.trim();
  if (!username || !password) return alert("Fill both username & password");
  try {
    const { access_token } = await request("/users", {
      method: "POST",
      body: JSON.stringify({ username, newPassword: password }),
    });
    token = access_token;
    localStorage.setItem("todo_token", token);
    switchToApp();
  } catch (e) {
    alert("Registration failed: " + e.message);
  }
}

async function login() {
  const username = document.getElementById("username").value.trim();
  const password = document.getElementById("password").value.trim();
  if (!username || !password) return alert("Fill both username & password");
  try {
    const { access_token } = await request("/users/get-token", {
      method: "POST",
      body: JSON.stringify({ username, password }),
    });
    token = access_token;
    localStorage.setItem("todo_token", token);
    switchToApp();
  } catch (e) {
    alert("Login failed: " + e.message);
  }
}

function logout() {
  token = null;
  localStorage.removeItem("todo_token");
  document.getElementById("auth").style.display = "";
  document.getElementById("todo").style.display = "none";
}

/* appstu/ui */
async function loadTasks() {
  try {
    const list = await request("/tasks");
    render(list);
  } catch (e) {
    alert("Could not load tasks: " + e.message);
  }
}

async function addTask(title) {
  await request("/tasks", { method: "POST", body: JSON.stringify({ title }) });
  await loadTasks();
}

async function toggleDone(id, done) {
  await request(`/tasks/${id}`, {
    method: "PUT",
    body: JSON.stringify({ marked_as_done: done }),
  });
  await loadTasks();
}

async function deleteTask(id) {
  await request(`/tasks/${id}`, { method: "DELETE" });
  await loadTasks();
}

function render(tasks) {
  const ul = document.getElementById("tasks");
  ul.innerHTML = "";
  tasks.forEach(t => {
    const li = document.createElement("li");
    if (t.marked_as_done) li.classList.add("done");
    li.innerHTML = `
      <label>
        <input type="checkbox" ${t.marked_as_done ? "checked" : ""}>
        <span>${t.title}</span>
      </label>
      <button title="Delete">🗑</button>
    `;
    li.querySelector("input").onchange = e => toggleDone(t.id, e.target.checked);
    li.querySelector("button").onclick   = () => deleteTask(t.id);
    ul.appendChild(li);
  });
}

function switchToApp() {
  document.getElementById("auth").style.display = "none";
  document.getElementById("todo").style.display = "";
  loadTasks();
}

/* auto-login /DOMC */
document.addEventListener("DOMContentLoaded", () => {
  document
    .getElementById("addForm")
    .addEventListener("submit", e => {
      e.preventDefault();
      const input = document.getElementById("taskTitle");
      const title = input.value.trim();
      if (title) addTask(title).then(() => (input.value = ""));
    });

  if (token) switchToApp(); // auto‑login if token already stored
});
