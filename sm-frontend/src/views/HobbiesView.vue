<script setup>
import { ref, onMounted } from "vue";

const images = ref([]);

const selectedImage = ref(null);

const openImage = (img) => {
  selectedImage.value = img;
};

const closeImage = () => {
  selectedImage.value = null;
};

onMounted(async () => {
  const res = await fetch("http://localhost:8080/api/portfolio");
  const data = await res.json();

  images.value = data.map(item => item.url);
});
</script>

<template>
  <div class="container">
    <h1>Hobbies</h1>

    <!-- Photography -->
    <section>
      <h2>📸 Photography</h2>

    <div class="gallery">
      <img
        v-for="img in images"
        :key="img"
        :src="'http://localhost:8080' + img"
        @click="openImage('http://localhost:8080' + img)"
      />
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
  margin-top: 20px;
}

.gallery img {
  width: 100%;
  border-radius: 10px;
  transition: transform 0.3s ease;
  cursor: zoom-in;
}

.gallery img:hover {
  transform: scale(1.05);
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
