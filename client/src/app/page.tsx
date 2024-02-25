import { use } from "react";
import MainVision from "./components/MainVision";
import TalkVision from "./components/TalkVision";
import PerformIntro from "./components/perform/PerformIntro";
import PerformInfo from "./components/perform/PerformInfo";
import PerformRank from "./components/perform/PerfromRank";
import ReviewMain from "./components/review/ReviewMain";
export default function Home() {
  return (
    <>
      <MainVision />
      <TalkVision />
      <PerformIntro />
      <PerformInfo />
      <PerformRank />
      <ReviewMain />
    </>
  );
}
