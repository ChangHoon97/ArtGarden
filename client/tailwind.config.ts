import type { Config } from "tailwindcss";
import plugin from "tailwindcss/plugin";

const config: Config = {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    colors: {
      mainred: "#d73d55",
      mainyellow: "#fff200",
      mainblack: "#101824",
      mainnavy: "#090e24",
      mainbrown: "#511908",
      mainwhite: "#ffffff",
    },
    extend: {
      backgroundImage: {
        "gradient-radial": "radial-gradient(var(--tw-gradient-stops))",
        "gradient-conic":
          "conic-gradient(from 180deg at 50% 50%, var(--tw-gradient-stops))",
      },
    },
  },
  plugins: [
    plugin(function ({ addComponents }) {
      addComponents({
        ".btn-yellow": {
          textColors: "#fff200",
          fontWeight: "300",
          borderRadius: ".25rem",
          borderWidth: "200",
          borderColor: "#fff200",
          backgroundColor: "#090e24",
        },
      });
    }),
  ],
};
export default config;
