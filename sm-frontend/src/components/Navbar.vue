<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'

const showDropdown = ref(false)

const toggleDropdown = () => {
  showDropdown.value = !showDropdown.value
}

const closeDropdown = () => {
  showDropdown.value = false
}

/* close when clicking outside */
const handleClickOutside = (e) => {
  if (!e.target.closest('.nav-item')) {
    showDropdown.value = false
  }
}

onMounted(() => {
  document.addEventListener('click', handleClickOutside)
})

onBeforeUnmount(() => {
  document.removeEventListener('click', handleClickOutside)
})
</script>

<template>
  <nav class="nav">
    <!-- Logo -->
    <div class="logo">
      <img src="/som.png" alt="logo" />
    </div>

    <!-- Links -->
    <div class="links">
      <router-link to="/">Home</router-link>

      <router-link to="/hobbies">Hobbies</router-link>

      <router-link to="/about">About</router-link>
    </div>
  </nav>
</template>

<style scoped>/* ===== NAVBAR ===== */
.nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 40px;
  border-bottom: 1px solid #ddd;
  background: #fff;
}

/* LOGO */
.logo img {
  height: 40px;
}

/* LINKS CONTAINER */
.links {
  display: flex;
  align-items: center;
  gap: 30px;
}

.links a.router-link-active {
  font-weight: 600;
  border-bottom: 2px solid #111;
}

/* RESET ALL LINKS */
.links a,
.nav-item {
  text-decoration: none;
  color: #111;
  font-weight: 500;
  cursor: pointer;

  /* 🚫 kill blue highlight */
  outline: none;
  background: transparent;
}

/* REMOVE VISITED PURPLE */
.links a:visited {
  color: #111;
}

/* REMOVE CLICK BLUE FLASH */
.links a:focus,
.nav-item:focus,
.links a:active,
.nav-item:active {
  outline: none;
  background: transparent;
}

/* HOVER EFFECT */
.links a:hover,
.nav-item:hover {
  opacity: 0.7;
}
.nav-item {
  position: relative;
}

/* DROPDOWN */
.dropdown {
  position: absolute;
  top: 35px;
  right: 0;   /* 👈 aligns nicely to right */
  background: #fff;
  padding: 10px 0;
  border-radius: 12px;
  box-shadow: 0 10px 25px rgba(0,0,0,0.15);
  min-width: 220px;
  z-index: 9999;
}

/* ITEMS */
.dropdown a {
  display: block;
  padding: 10px 20px;
  color: #111;
}

.dropdown a:hover {
  background: #f5f5f5;
}
</style>
