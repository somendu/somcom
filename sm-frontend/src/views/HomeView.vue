<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { backend } from '@/config/backend'

type HomeResponse = {
  imageUrls: string[]
  name: string
  title: string
  descriptions: string[]
}

const imageUrls = ref<string[]>([])
const name = ref('')
const title = ref('')
const descriptionLines = ref<string[]>([])

onMounted(async () => {
  try {
    const res = await fetch(backend.api.home)
    const data: HomeResponse = await res.json()

    imageUrls.value = data.imageUrls.map((path) => backend.getImageUrl(path))
    name.value = data.name
    title.value = data.title
    descriptionLines.value = data.descriptions
  } catch (err) {
    console.error('Failed to load home data', err)
  }
})
</script>

<template>
  <div class="home">
    <section class="hero">
      <div v-if="imageUrls.length > 0" class="hero-media left">
        <img
          :src="imageUrls[0]"
          class="hero-img"
          alt="Portrait of Somendu Maiti"
        />
      </div>

      <div class="hero-text">
        <p class="hero-kicker">Hey, I'm</p>
        <h1>{{ name }}</h1>
        <h2>{{ title }}</h2>
        <p
          v-for="(line, index) in descriptionLines"
          :key="index"
          class="extra"
        >
          {{ line }}
        </p>
      </div>

      <div v-if="imageUrls.length > 1" class="hero-media right">
        <img
          :src="imageUrls[1]"
          class="hero-img"
          alt="Portrait of Somendu Maiti in the mountains"
        />
      </div>
    </section>
  </div>
</template>

<style>
.home {
  min-height: calc(100vh - 81px);
  padding: 0 16px;
  background: #fafafa;
  overflow: hidden;
}

.hero {
  min-height: calc(100vh - 81px);
  display: grid;
  grid-template-columns: minmax(260px, 1fr) minmax(320px, 460px) minmax(260px, 1fr);
  align-items: center;
  gap: 32px;
  padding: 10px 16px 16px;
}

.hero-media {
  display: flex;
  align-items: center;
}

.hero-media.left {
  justify-content: flex-start;
}

.hero-media.right {
  justify-content: flex-end;
}

.hero-img {
  width: min(100%, 520px);
  height: min(72vh, 820px);
  object-fit: contain;
  border-radius: 16px;
  box-shadow: 0 18px 40px rgba(0, 0, 0, 0.12);
  background: #fff;
  opacity: 0.7;
}

.hero-media.left .hero-img {
  transform: rotate(-2deg);
}

.hero-media.right .hero-img {
  transform: rotate(2deg);
}

.hero-text {
  display: flex;
  flex-direction: column;
  gap: 14px;
  text-align: right;
  padding: 28px 12px;
  font-family: 'Times New Roman', Times, serif;
}

.hero-kicker {
  font-size: clamp(2.2rem, 3.2vw, 3.4rem);
  line-height: 1.05;
  font-weight: 700;
}

.hero-text h1 {
  font-size: clamp(2.1rem, 3.6vw, 4.8rem);
  line-height: 1.05;
  font-weight: 700;
}

.hero-text h2 {
  font-size: clamp(1.7rem, 2.4vw, 2.5rem);
  color: #888;
  line-height: 1.15;
  font-weight: 600;
  margin-top: 6px;
}

.hero-text .extra {
  font-size: clamp(1.1rem, 1.35vw, 1.45rem);
  color: #333;
  line-height: 1.6;
  margin-top: 10px;
}

@media (max-width: 1100px) {
  .home {
    min-height: auto;
    overflow: visible;
  }

  .hero {
    min-height: auto;
    grid-template-columns: 1fr;
    padding: 32px 20px 40px;
  }

  .hero-media.left,
  .hero-media.right {
    justify-content: center;
  }

  .hero-media {
    width: auto;
    height: auto;
  }

  .hero-img {
    width: min(100%, 420px);
    height: auto;
  }

  .hero-media.left .hero-img,
  .hero-media.right .hero-img {
    transform: none;
  }

  .hero-text {
    text-align: center;
    padding: 0;
  }
}
</style>
