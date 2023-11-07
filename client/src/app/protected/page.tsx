import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";

export default async function ProtectedRoute() {
  const session = await getServerSession();
  if (!session || !session.user) {
    redirect("api/auth/signin");
  }
  return <div>이 페이지는 로그인 된 사람만 들어오는 페이지</div>;
}
