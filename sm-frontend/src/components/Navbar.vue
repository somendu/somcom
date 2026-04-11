<template>
  <nav class="nav">
    <div class="logo">Somendu</div>

    <div class="links">
      <router-link to="/">Home</router-link>
      <router-link to="/hobbies">Hobbies</router-link>
      <router-link to="/about">About</router-link>
    </div>
  </nav>
</template>

<style scoped>
.nav {
  display: flex;
  justify-content: space-between;
  padding: 20px 40px;
  border-bottom: 1px solid #ddd;
}

.logo {
  font-weight: bold;
}

.links a {
  margin-left: 20px;
  text-decoration: none;
  color: black;
}
</style>
