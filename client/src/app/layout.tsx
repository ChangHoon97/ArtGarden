import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import navBar from "./navBar";
const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "ArtGarden",
  description: "공연정보 웹사이트",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <navBar />
        {children}
        <div>footer</div>
      </body>
    </html>
  );
}
