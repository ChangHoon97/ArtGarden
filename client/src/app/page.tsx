import MainVision from "./components/MainVision";
import TalkVision from "./components/TalkVision";
import PerformIntro from "./components/perform/PerformIntro";
import PerformInfo from "./components/perform/PerformInfo";
import PerformRank from "./components/perform/PerfromRank";
import ReviewMain from "./components/review/ReviewMain";
import { Suspense } from "react";
export default function Home() {
  return (
    <>
      <Suspense>
        <MainVision />
        <TalkVision />
        <PerformIntro />
        <PerformInfo />
        <PerformRank />
        <ReviewMain />
      </Suspense>
    </>
  );
}
