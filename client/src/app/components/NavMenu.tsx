"use client";
import Link from "next/link";
import { signIn, signOut, useSession } from "next-auth/react";
import { usePathname } from "next/navigation";

const ACTIVE_ROUTE = "py-1 px-2 text-white";
const INACTIVE_ROUTE =
  "py-1 px-2 text-white hover:text-white hover:bg-gray-700";
function AuthButton() {
  const { data: session } = useSession();

  if (session) {
    return (
      <>
        {session?.user?.name} <br />
        <button
          className="w-36 h-12 text-mainyellow rounded-2xl border-2 border-mainyellow bg-mainblack"
          onClick={() => signOut()}
        >
          Sign out
        </button>
      </>
    );
  }
  return (
    <>
      로그인 안됨 <br />
      <button
        className="w-36 h-12 text-mainyellow rounded-2xl border-2 border-mainyellow bg-mainblack"
        onClick={() => signIn()}
      >
        Sign in
      </button>
    </>
  );
}

export default function NavMenu() {
  const pathname = usePathname();
  return (
    <div className="flex  bg-mainred py-16 px-20">
      <div className="flex flex-row mx-60 px-8 ">
        <img src="logo.jpg" alt="ArtGarden 로고 이미지" className="w-44 h-8" />
        <ul className="flex space-x-4 ">
          <Link href="/">
            <li className={pathname === "/" ? ACTIVE_ROUTE : INACTIVE_ROUTE}>
              Home
            </li>
          </Link>
          <Link href="/protected">
            <li
              className={
                pathname === "/protected" ? ACTIVE_ROUTE : INACTIVE_ROUTE
              }
            >
              Protected page
            </li>
          </Link>
          <Link href="/serverAction">
            <li
              className={
                pathname === "/serveraction" ? ACTIVE_ROUTE : INACTIVE_ROUTE
              }
            >
              Server Action
            </li>
          </Link>
        </ul>
        <AuthButton />
      </div>
    </div>
  );
}
