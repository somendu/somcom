import { fileURLToPath, URL } from 'node:url'
import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import vueDevTools from 'vite-plugin-vue-devtools'

export default defineConfig(({ mode }) => {
  const env = loadEnv(mode, process.cwd())
  const target = env.VITE_API_TARGET

  return {
    base: './',

    plugins: [
      vue(),
      vueJsx(),
      vueDevTools(),
    ],

    resolve: {
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url))
      },
    },

    server: {
      proxy: {
        '/api': {
          target,
          changeOrigin: true
        },
        '/images': {
          target,
          changeOrigin: true
        }
      }
    }
  }
})
