"use client";
import Link from "next/link";
import { signIn, signOut, useSession } from "next-auth/react";
import { usePathname } from "next/navigation";
import Button from "./Button";

const ACTIVE_ROUTE =
  "py-1 px-2 text-mainwhite hover:text-mainyellow  hover:bg-gray-700 ml-20";
const INACTIVE_ROUTE =
  "py-1 px-2 text-mainwhite hover:text-mainyellow hover:bg-gray-700  ml-20";
function AuthButton() {
  const { data: session } = useSession();

  if (session) {
    return (
      <>
        <div className="text-xl font-medium hover:text-mainyellow">
          {session?.user?.name}님
        </div>{" "}
        <br />
        <Button color="black" size="sm" onClick={() => signOut()}>
          Sign out
        </Button>
      </>
    );
  }
  return (
    <>
      <br />
      <Button color="black" size="sm" onClick={() => signIn()}>
        Sign in
      </Button>
    </>
  );
}

export default function NavMenu() {
  const pathname = usePathname();
  return (
    <div className="flex  bg-mainred pt-16 pb-6 px-20">
      <div className="flex flex-row mx-60 px-8 ">
        <img src="logo.jpg" alt="ArtGarden 로고 이미지" className="w-44 h-8" />
        <ul className="flex space-x-4 text-base ">
          <Link href="/">
            <li className={pathname === "/" ? ACTIVE_ROUTE : INACTIVE_ROUTE}>
              <p className="text-white">Home</p>
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
