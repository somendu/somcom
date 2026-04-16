<script setup lang="ts">
import { computed, ref, onMounted } from 'vue'
import { backend } from '@/config/backend'

type PortfolioImage = {
  url: string
  title: string
}

type GalleryRow = {
  type: 'portrait' | 'landscape'
  items: PortfolioImage[]
}

const images = ref<PortfolioImage[]>([])
const loading = ref(true)
const selectedImage = ref<string | null>(null)

const portraitRows = 5
const landscapeRows = 3

function openImage(url: string) {
  selectedImage.value = url
}

function closeImage() {
  selectedImage.value = null
}

function isPortraitImage(url: string) {
  return !url.endsWith('-v.jpg')
}

const galleryRows = computed<GalleryRow[]>(() => {
  const portraitImages = images.value.filter((item) => isPortraitImage(item.url))
  const landscapeImages = images.value.filter((item) => !isPortraitImage(item.url))

  const rows: GalleryRow[] = []
  let nextRowType: GalleryRow['type'] = 'portrait'

  while (portraitImages.length > 0 || landscapeImages.length > 0) {
    if (nextRowType === 'portrait' && portraitImages.length > 0) {
      rows.push({
        type: 'portrait',
        items: portraitImages.splice(0, portraitRows),
      })
      nextRowType = 'landscape'
      continue
    }

    if (nextRowType === 'landscape' && landscapeImages.length > 0) {
      rows.push({
        type: 'landscape',
        items: landscapeImages.splice(0, landscapeRows),
      })
      nextRowType = 'portrait'
      continue
    }

    if (portraitImages.length > 0) {
      rows.push({
        type: 'portrait',
        items: portraitImages.splice(0, portraitRows),
      })
      continue
    }

    rows.push({
      type: 'landscape',
      items: landscapeImages.splice(0, landscapeRows),
    })
  }

  return rows
})

onMounted(async () => {
  try {
    const res = await fetch(backend.api.portfolio)

    if (!res.ok) throw new Error('API failed')

    images.value = await res.json() as PortfolioImage[]
  } catch (err) {
    console.error(err)
  } finally {
    loading.value = false
  }
})
</script>

<template>
  <div class="container">
    <section>
      <div v-if="loading" class="gallery-stack">
        <div class="gallery-row portrait">
          <div class="card portrait" v-for="n in 5" :key="`portrait-${n}`">
            <div class="skeleton-img portrait"></div>
          </div>
        </div>

        <div class="gallery-row landscape">
          <div class="card landscape" v-for="n in 3" :key="`landscape-${n}`">
            <div class="skeleton-img landscape"></div>
          </div>
        </div>
      </div>

      <div v-else class="gallery-stack">
        <div
          v-for="(row, rowIndex) in galleryRows"
          :key="`${row.type}-${rowIndex}`"
          class="gallery-row"
          :class="row.type"
        >
          <div
            v-for="item in row.items"
            :key="item.url"
            class="card"
            :class="row.type"
          >
            <img
              :src="backend.getImageUrl(item.url)"
              :alt="item.title"
              @click="openImage(item.url)"
            />
            <div v-if="item.title" class="overlay-text">{{ item.title }}</div>
          </div>
        </div>
      </div>

      <!-- Fullscreen -->
      <div v-if="selectedImage" class="overlay" @click="closeImage">
        <img
          :src="backend.getImageUrl(selectedImage)"
          class="fullscreen"
        />
      </div>

    </section>

  </div>
</template>

<style scoped>
.container {
  padding: 40px 40px 64px;
}

.gallery-stack {
  display: flex;
  flex-direction: column;
  gap: 28px;
  margin-top: 20px;
}

.gallery-row {
  display: grid;
  gap: 24px;
  align-items: start;
}

.gallery-row.portrait {
  grid-template-columns: repeat(5, minmax(0, 1fr));
}

.gallery-row.landscape {
  grid-template-columns: repeat(3, minmax(0, 1fr));
}

.card {
  position: relative;
  overflow: hidden;
  transition: transform 0.25s ease, box-shadow 0.25s ease;
  cursor: pointer;
  border-radius: 16px;
  background: white;
  box-shadow: 0 8px 20px rgba(0,0,0,0.08);
}

.card.portrait {
  aspect-ratio: 3 / 5;
}

.card.landscape {
  aspect-ratio: 7 / 5;
}

.card img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s ease;
  display: block;
}

.card:hover {
  transform: translateY(-6px);
  box-shadow: 0 16px 30px rgba(0,0,0,0.14);
}

.card:hover img {
  transform: scale(1.04);
}

.overlay-text {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 10px;
  background: rgba(0,0,0,0.6);
  color: white;
  font-size: 14px;
}

/* Skeleton */
.skeleton-img {
  width: 100%;
  background: #ddd;
  height: 100%;
  animation: pulse 1.5s infinite;
}

.skeleton-img.portrait {
  aspect-ratio: 3 / 5;
}

.skeleton-img.landscape {
  aspect-ratio: 7 / 5;
}

@keyframes pulse {
  0% { opacity: 0.6; }
  50% { opacity: 1; }
  100% { opacity: 0.6; }
}

/* Fullscreen overlay */
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0,0,0,0.8);
  display: flex;
  justify-content: center;
  align-items: center;
}

.fullscreen {
  max-width: 90%;
  max-height: 90%;
  border-radius: 10px;
}

@media (max-width: 1100px) {
  .gallery-row.portrait {
    grid-template-columns: repeat(3, minmax(0, 1fr));
  }

  .gallery-row.landscape {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }
}

@media (max-width: 720px) {
  .container {
    padding: 24px 20px 48px;
  }

  .gallery-row.portrait,
  .gallery-row.landscape {
    grid-template-columns: 1fr;
  }
}
</style>
