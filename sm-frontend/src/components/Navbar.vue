<template>
  <nav class="nav">
    <div class="logo">
      <img src="/som.png" alt="Somendu"/>
    </div>

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

.logo img {
  height: 40px;
  width: auto;
}

.links a {
  margin-left: 20px;
  text-decoration: none;
  color: black;
}
</style>
