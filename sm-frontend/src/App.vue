<template>
  <div class="app-shell">
    <Navbar />
    <main class="app-content">
      <router-view />
    </main>
  </div>
</template>

<script setup>
import Navbar from "./components/Navbar.vue";
</script>

<style>
html, body {
  margin: 0;
  padding: 0;
  overflow-x: hidden;
}

body {
  font-family: 'Georgia', 'Times New Roman', serif;
  color: #111;
  margin: 0;
  overflow: hidden;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

#app {
  height: 100vh;
}

.app-shell {
  height: 100vh;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.app-content {
  flex: 1;
  min-height: 0;
  overflow-y: auto;
  overflow-x: hidden;
  scrollbar-gutter: stable;
  overscroll-behavior: contain;
  scrollbar-width: thin;
  scrollbar-color: rgba(120, 120, 120, 0.7) transparent;
}

.app-content::-webkit-scrollbar {
  width: 10px;
}

.app-content::-webkit-scrollbar-track {
  background: transparent;
}

.app-content::-webkit-scrollbar-thumb {
  background: linear-gradient(180deg, rgba(150, 150, 150, 0.85), rgba(105, 105, 105, 0.85));
  border-radius: 999px;
  border: 2px solid transparent;
  background-clip: padding-box;
}

.app-content::-webkit-scrollbar-thumb:hover {
  background: linear-gradient(180deg, rgba(120, 120, 120, 0.95), rgba(85, 85, 85, 0.95));
  background-clip: padding-box;
}
</style>
