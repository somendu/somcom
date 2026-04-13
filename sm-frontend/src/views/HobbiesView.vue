<script setup>
import { ref, onMounted } from "vue";

const images = ref([]);
const API_BASE = "https://sm-backend-1071867209793.us-central1.run.app";
const API_URL = "https://sm-backend-1071867209793.us-central1.run.app/api/portfolio";

onMounted(async () => {
  const res = await fetch(API_URL);
  images.value = await res.json();
});

const selectedImage = ref(null);

const openImage = (url) => {
  selectedImage.value = API_BASE + url;
};

const closeImage = () => {
  selectedImage.value = null;
};
</script>

<template>
  <div class="container">
    <h1>Hobbies</h1>

    <!-- Photography -->
    <section>
      <h2>📸 Photography</h2>

    <div class="gallery">
      <div v-for="img in images" :key="img.url" class="card">
        <img :src="API_BASE + img.url" @click="openImage(img.url)" />

      <div class="overlay-text">
        <span>{{ img.title }}</span>
      </div>
      </div>
    </div>


    <div v-if="selectedImage" class="overlay" @click="closeImage">
       <img :src="selectedImage" class="fullscreen" />
    </div>

    </section>

    <!-- Blog -->
    <section>
      <h2>✍️ Blog Archive</h2>
      <p>Coming soon...</p>
    </section>

    <!-- Badminton -->
    <section>
      <h2>🏸 Badminton</h2>
      <p>Coming soon...</p>
    </section>
  </div>
</template>

<style scoped>
.container {
  max-width: 1000px;
  margin: 60px auto;
}

.gallery {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
}

.card {
  position: relative;
  overflow: hidden;
  border-radius: 10px;
}

.card img {
  width: 100%;
  height: 300px;
  object-fit: cover;
  transition: transform 0.3s ease;
}

/* Hover zoom */
.card:hover img {
  transform: scale(1.1);
}

/* Overlay */
.overlay-text {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 15px;
  background: rgba(0,0,0,0.6);
  color: white;
  font-size: 14px;

  opacity: 0;
  transition: opacity 0.3s ease;
}

/* Show on hover */
.card:hover .overlay-text {
  opacity: 1;
}

.title {
  margin-top: 8px;
  font-size: 14px;
  color: #555;
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0,0,0,0.85);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.fullscreen {
  max-width: 90%;
  max-height: 90%;
  border-radius: 10px;
}
</style>
