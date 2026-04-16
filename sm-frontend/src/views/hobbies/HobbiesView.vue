<template>
<div class="hobbies-page">

<!-- SUB NAV -->
<div class="hobby-tabs">
  <router-link to="/hobbies" exact-active-class="active-tab">
    📸 Som's Captcha
  </router-link>

  <router-link to="/hobbies/writeup" active-class="active-tab">
    ✍️ Som's Writeup
  </router-link>

  <router-link to="/hobbies/playa" active-class="active-tab">
    🏸 Som's Playa
  </router-link>
</div>

<!-- CONTENT -->
<router-view />
</div>
</template>


<style scoped>
.hobby-tabs {
  display: flex;
  justify-content: center;
  gap: 40px;
  margin: 20px 0 5px;
}

.hobby-tabs a {
  text-decoration: none;
  color: #555;
  padding-bottom: 6px;
}

/* ONLY active tab */
.hobby-tabs a.active-tab {
  color: #111;
  border-bottom: 2px solid #111;
}

</style>
<script setup lang="ts">
</script>
