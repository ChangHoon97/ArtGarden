import { getServerSession } from "next-auth";
import WhoAmIButton from "./WhoAmIButton";
export default async function ServerActionPage() {
  const whoAmI = async () => {
    "use server";
    const session = await getServerSession();
    return session?.user?.name || "로그인 되지 않았습니다.";
  };
  return (
    <div>
      <WhoAmIButton whoAmIAction={whoAmI} />
    </div>
  );
}
