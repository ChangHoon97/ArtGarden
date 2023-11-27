"use client";
import { Perform_Info } from "@/app/types";
import { useQuery } from "@tanstack/react-query";
import React from "react";

async function getPerForm() {
  return (await fetch(`http://13.125.213.95:8080/performances`).then((res) =>
    res.json()
  )) as Perform_Info[];
}

export default function PerformInfo() {
  const { data } = useQuery<Perform_Info[]>({
    queryKey: ["perform-info"],
    queryFn: () => getPerForm(),
  });
  return (
    <>
      <div className="bg-mainrealblack">
        <div className="flex flex-wrap justify-center">
          {data?.map((el) => (
            <div
              key={el.id}
              className="bg-mainwhite w-1/5 mx-2 mt-4 py-60 "
              // style={{ flex: "1 0 25%" }}
            >
              <img src={el.posterUrl}></img>
              {/* <div>{el.name}</div>
              <div>
                <div className="mt-15">{el.endDate}</div>
                <div className="">{el.price}</div>
                <div>{el.place}</div>
              </div> */}
            </div>
          ))}
        </div>
      </div>
    </>
  );
}
