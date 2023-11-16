"use client";
import { useState } from "react";

export default function WhoAmIButton({
  whoAmIAction,
}: {
  whoAmIAction: () => Promise<string>;
}) {
  const [name, setName] = useState<string>();
  return (
    <div>
      <button
        onClick={async () => {
          setName(await whoAmIAction());
        }}
      >
        Who AM I?
      </button>
      {name && <div>너의 이름은 {name}</div>}
    </div>
  );
}
