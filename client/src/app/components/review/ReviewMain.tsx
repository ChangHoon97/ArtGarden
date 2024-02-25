"use client";
import { Review_Info } from "@/app/types";
import { useQuery } from "@tanstack/react-query";
import React from "react";

async function getReview() {
  return (await fetch("http://13.125.213.95:8080/reviews").then((res) =>
    res.json()
  )) as Review_Info[];
}
export default function ReviewMain() {
  const { data } = useQuery<Review_Info[]>({
    queryKey: ["review"],
    queryFn: () => getReview(),
  });
  return (
    <>
      <div className="flex space-x-4">
        <div className="bg-mainwhite py-44">
          <div>리뷰</div>
          {data &&
            data.map((el) => (
              <div key={el.id} className="w-72 h-80 rounded-md shadow-xl">
                <div className="bg-mainrealblack h-1/2 rounded-md">
                  {el.perform_id}
                </div>
                <div className="">
                  <div>{el.content}</div>
                  <div>{el.rate}</div>
                  <div>{el.created_at}</div>
                </div>
              </div>
            ))}
        </div>
      </div>
    </>
  );
}
