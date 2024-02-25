"use client";
import { useRouter } from "next/navigation";
export default function SignIn() {
  const googleServer =
    "https://accounts.google.com/o/oauth2/auth?" +
    "client_id=525826750508-0aihs14iifvcljqbdldvvsmua7erd7ct.apps.googleusercontent.com&" +
    "redirect_uri=http://artgarden.co.kr/signIn&" +
    "response_type=token&" +
    "scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";
  const router = useRouter();
  const googleRedirect = () => {
    router.push(`${googleServer}`);
  };
  return (
    <div>
      <button onClick={googleRedirect}>구글 로그인</button>
      <div>카카오 로그인</div>
      <div>네이버 로그인</div>
    </div>
  );
}
