/** @type {import('tailwindcss').Config} */
module.exports = {
  mode: 'jit',
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    fontFamily: {
        'classic': ['AktivGrotesk-Medium']
    },
    extend: {
      colors: {
        'nase-cerna': '#1C2227',
        'nase-tmave-seda': '#2A2F37',
        'nase-svetle-seda': '#818181',
        'nase-zluta': '#D3FC01',
        'nase-bila': '#FFFFFF',
      }
    },
  },
  plugins: [],
}

