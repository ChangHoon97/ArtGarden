import Providers from "@/utils/provider";
import React from "react";
import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import NavMenu from "./components/NavMenu";
import { getServerSession } from "next-auth";
import SessionProvider from "./components/SessionProvider";
const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "ArtGarden",
  description: "공연정보 웹사이트",
};

export default async function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const session = await getServerSession();
  return (
    <html lang="en">
      <body className={inter.className}>
        <SessionProvider session={session}>
          <main className="text-2xl flex-col gap-2 text-white">
            <NavMenu />
            <Providers>{children}</Providers>
          </main>
        </SessionProvider>
      </body>
    </html>
  );
}
