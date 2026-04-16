const BASE_URL = import.meta.env.VITE_API_BASE_URL || ''

export const backend = {
  api: {
    home: `${BASE_URL}/api/homeview`,
    portfolio: `${BASE_URL}/api/portfolio`,
    blog: `${BASE_URL}/api/blog`
  },

  getImageUrl(path: string) {
    return `${BASE_URL}${path}`
  }
}
