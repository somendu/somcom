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
const loading = ref(true)

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
  } finally {
    loading.value = false
  }
})
</script>

<template>
  <div class="home">
    <section v-if="loading" class="hero hero-loading" aria-busy="true">
      <div class="hero-media skeleton-frame">
        <div class="skeleton-block skeleton-image"></div>
      </div>

      <div class="hero-text hero-text-loading">
        <div class="skeleton-block skeleton-kicker"></div>
        <div class="skeleton-block skeleton-name"></div>
        <div class="skeleton-block skeleton-title"></div>
        <div class="skeleton-copy">
          <div class="skeleton-block skeleton-line"></div>
          <div class="skeleton-block skeleton-line short"></div>
          <div class="skeleton-block skeleton-line"></div>
          <div class="skeleton-block skeleton-line short"></div>
        </div>
      </div>

      <div class="hero-media skeleton-frame">
        <div class="skeleton-block skeleton-image"></div>
      </div>
    </section>

    <section v-else class="hero">
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

.hero-loading .hero-media,
.hero-loading .hero-text {
  pointer-events: none;
}

.hero-text-loading {
  gap: 16px;
}

.skeleton-frame {
  justify-content: center;
}

.skeleton-block {
  background: linear-gradient(90deg, rgba(225, 225, 225, 0.85), rgba(245, 245, 245, 0.98), rgba(225, 225, 225, 0.85));
  background-size: 200% 100%;
  animation: shimmer 1.4s ease-in-out infinite;
  border-radius: 16px;
}

.skeleton-image {
  width: min(100%, 520px);
  height: min(72vh, 820px);
  border-radius: 16px;
}

.skeleton-kicker {
  margin-left: auto;
  width: 48%;
  height: 48px;
}

.skeleton-name {
  margin-left: auto;
  width: 82%;
  height: 104px;
}

.skeleton-title {
  margin-left: auto;
  width: 68%;
  height: 56px;
}

.skeleton-copy {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-top: 12px;
}

.skeleton-line {
  margin-left: auto;
  width: 100%;
  height: 22px;
}

.skeleton-line.short {
  width: 72%;
}

@keyframes shimmer {
  0% {
    background-position: 200% 0;
  }

  100% {
    background-position: -200% 0;
  }
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

  .skeleton-kicker,
  .skeleton-name,
  .skeleton-title,
  .skeleton-line,
  .skeleton-line.short {
    margin-left: 0;
    margin-right: 0;
    width: 100%;
  }

  .hero-img {
    width: min(100%, 420px);
    height: auto;
  }

  .skeleton-image {
    width: min(100%, 420px);
    height: 420px;
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
