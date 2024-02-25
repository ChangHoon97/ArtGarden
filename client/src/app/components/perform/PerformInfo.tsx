"use client";
import { Perform_Info } from "@/app/types";
import { useQuery } from "@tanstack/react-query";
import React from "react";
const data = [
  {
    id: "PF121682",
    name: "옥탑방 고양이 [대학로]",
    startDate: "2010-04-06",
    endDate: "오픈런",
    place: "틴틴홀 (틴틴홀)",
    price: "전석 35,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF121682_210322_143051.gif",
  },
  {
    id: "PF130891",
    name: "쉬어매드니스 [대학로]",
    startDate: "2016-04-05",
    endDate: "오픈런",
    place:
      "콘텐츠박스(구. 르메이에르 씨어터) (콘텐츠박스(구. 르메이에르 씨어터))",
    price: "전석 35,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF130891_221108_093357.gif",
  },
  {
    id: "PF132171",
    name: "크리미널",
    startDate: "2016-07-01",
    endDate: "오픈런",
    place: "봄날아트홀(구. 아리랑소극장) (1관(지하1층))",
    price: "전석 30,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF132171_200102_095006.jpg",
  },
  {
    id: "PF134308",
    name: "조각: 사라진 기억",
    startDate: "2016-11-20",
    endDate: "오픈런",
    place: "댕로홀 (댕로홀)",
    price: "전석 35,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF134308_200901_132842.gif",
  },
  {
    id: "PF136032",
    name: "난타 [제주]",
    startDate: "2012-04-01",
    endDate: "오픈런",
    place: "제주난타극장 (제주난타극장)",
    price: "VIP석 60,000원, S석 50,000원, A석 40,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF136032_170227_153022.jpg",
  },
  {
    id: "PF138268",
    name: "시간을 파는 상점",
    startDate: "2017-07-01",
    endDate: "오픈런",
    place:
      "파랑씨어터 (구. 도향아트홀, 뮤디스홀) (파랑씨어터 (구. 도향아트홀, 뮤디스홀))",
    price: "전석 35,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF138268_200102_102145.gif",
  },
  {
    id: "PF142007",
    name: "사춘기메들리",
    startDate: "2018-03-10",
    endDate: "오픈런",
    place: "아티스탄홀 (아티스탄홀)",
    price: "전석 35,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF142007_190520_135852.jpg",
  },
  {
    id: "PF144565",
    name: "아기돼지삼형제: 늑대숲 또옹돼지 원정대",
    startDate: "2018-04-07",
    endDate: "오픈런",
    place: "서연아트홀(구. 인아소극장) (서연아트홀(구. 인아소극장))",
    price: "전석 30,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF144565_220811_114818.jpg",
  },
  {
    id: "PF144982",
    name: "택시안에서 [서울]",
    startDate: "2018-10-12",
    endDate: "오픈런",
    place: "해바라기소극장(구. 훈아트홀) (해바라기소극장(구. 훈아트홀))",
    price: "전석 40,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF144982_181017_114924.jpg",
  },
  {
    id: "PF145801",
    name: "반짝반짝 라푼젤",
    startDate: "2018-08-01",
    endDate: "오픈런",
    place: "달밤엔씨어터 (달밤엔씨어터)",
    price: "전석 30,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF145801_181213_104716.jpg",
  },
  {
    id: "PF147207",
    name: "택시안에서 [부산]",
    startDate: "2019-03-15",
    endDate: "오픈런",
    place: "해바라기소극장 [부산] (해바라기소극장 [부산])",
    price: "전석 35,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF147207_190318_105324.jpg",
  },
  {
    id: "PF148765",
    name: "요리하는 마술사 [파주]",
    startDate: "2016-06-08",
    endDate: "오픈런",
    place: "파주 프로방스 마술극장 (파주 프로방스 마술극장)",
    price: "전석 30,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF148765_190607_105902.jpg",
  },
  {
    id: "PF150471",
    name: "빨간모자야 조심해! [청주, 세종]",
    startDate: "2018-01-26",
    endDate: "오픈런",
    place: "소극장 쇠내골 (소극장 쇠내골)",
    price: "전석 50,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF150471_200629_135159.gif",
  },
  {
    id: "PF150502",
    name: "1미터 마술공연 [강남]",
    startDate: "2018-12-15",
    endDate: "오픈런",
    place: "매지클 (매지클)",
    price: "전석 35,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF150502_190701_174122.gif",
  },
  {
    id: "PF151901",
    name: "요리하는 마술사 [대학로]",
    startDate: "2019-06-29",
    endDate: "오픈런",
    place: "맛있는 극장 (맛있는 극장)",
    price: "전석 30,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF151901_190719_134111.gif",
  },
  {
    id: "PF152048",
    name: "두잇아카펠라 금토공연",
    startDate: "2019-07-20",
    endDate: "오픈런",
    place: "두잇아카펠라 Abar (두잇아카펠라 Abar)",
    price: "전석 15,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF152048_190723_130715.jpg",
  },
  {
    id: "PF152141",
    name: "북극곰 예술여행",
    startDate: "2019-07-20",
    endDate: "오픈런",
    place: "북극곰소극장(구.아뮤스소극장) (북극곰소극장(구.아뮤스소극장))",
    price: "전석 35,000원, 돗자리석 25,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF152141_190724_140737.jpg",
  },
  {
    id: "PF155520",
    name: "라면에 파송송",
    startDate: "2019-10-05",
    endDate: "오픈런",
    place: "로즈아트홀 (로즈아트홀)",
    price: "전석 30,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF155520_200901_112228.gif",
  },
  {
    id: "PF157288",
    name: "미래상상마술쇼",
    startDate: "2019-11-02",
    endDate: "오픈런",
    place: "송파브이아트홀 (송파브이아트홀)",
    price: "전석 25,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF157288_191104_112351.gif",
  },
  {
    id: "PF159714",
    name: "세례요한",
    startDate: "2019-12-21",
    endDate: "오픈런",
    place: "북촌나래홀 (북촌나래홀)",
    price: "전석 40,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF159714_191216_125554.jpg",
  },
  {
    id: "PF160126",
    name: "끝까지 간다",
    startDate: "2019-12-24",
    endDate: "오픈런",
    place: "북극곰소극장(구.아뮤스소극장) (북극곰소극장(구.아뮤스소극장))",
    price: "전석 35,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF160126_220419_101543.gif",
  },
  {
    id: "PF160272",
    name: "해녀의 부엌: 해녀이야기",
    startDate: "2018-12-31",
    endDate: "오픈런",
    place: "해녀의부엌 (해녀의부엌)",
    price: "전석 63,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF160272_191231_111713.png",
  },
  {
    id: "PF161298",
    name: "자취",
    startDate: "2020-02-28",
    endDate: "오픈런",
    place: "댕로홀 (댕로홀)",
    price: "전석 35,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF161298_200911_090916.gif",
  },
  {
    id: "PF170941",
    name: "연극라면",
    startDate: "2021-01-14",
    endDate: "오픈런",
    place: "해피씨어터 (해피씨어터)",
    price: "전석 40,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF170941_210106_102633.gif",
  },
  {
    id: "PF172538",
    name: "한뼘사이 [라온아트홀]",
    startDate: "2021-03-19",
    endDate: "오픈런",
    place: "라온아트홀 (라온아트홀)",
    price: "전석 40,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF172538_210318_095642.jpg",
  },
  {
    id: "PF172547",
    name: "빌의 구둣방",
    startDate: "2021-03-20",
    endDate: "오픈런",
    place: "소극장 쇠내골 (소극장 쇠내골)",
    price: "전석 40,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF172547_210318_100837.PNG",
  },
  {
    id: "PF173180",
    name: "운빨로맨스 [컬쳐씨어터]",
    startDate: "2021-05-01",
    endDate: "오픈런",
    place: "컬쳐씨어터(구. 휴먼시어터) (컬쳐씨어터(구. 휴먼시어터))",
    price: "전석 40,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF173180_211231_171626.jpg",
  },
  {
    id: "PF173894",
    name: "프리즌 [H씨어터]",
    startDate: "2021-05-01",
    endDate: "오픈런",
    place: "에이치씨어터 (익스트림씨어터 2관)",
    price: "전석 50,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF173894_210423_105247.gif",
  },
  {
    id: "PF173963",
    name: "매지컬북: 앨리스 인 컬러랜드",
    startDate: "2021-05-01",
    endDate: "오픈런",
    place: "매지클 (매지클)",
    price: "전석 15,000원",
    posterUrl:
      "http://www.kopis.or.kr/upload/pfmPoster/PF_PF173963_210426_131624.png",
  },
];
// async function getPerForm() {
//   return (await fetch(`http://13.125.213.95:8080/performances`).then((res) =>
//     res.json()
//   )) as Perform_Info[];
// }

export default function PerformInfo() {
  // const { data, isLoading } = useQuery<Perform_Info[]>({
  //   queryKey: ["perform-info"], //캐시를 관리하기 위한 키값으로 배열형태로 사용. string 형태로 해쉬해 key와 data를 매핑시킴.
  //   queryFn: () => getPerForm(), //promise를 반환하는 함수 (fetch나 axios) . data를 resolve 하고 error를 던짐
  // });
  // if (isLoading || !data) return <div>loading...</div>;
  return (
    <>
      <div className="bg-white">
        <div className=" grid grid-cols-4 justify-items-center">
          {data.map((el) => (
            <div key={el.id} className="card w-96 bg-base-100 shadow-xl mt-8">
              <figure>
                <img src={el.posterUrl} alt="연극 사진" className="w-96 h-80" />
              </figure>
              <div className="card-body text-black">
                <h2 className="card-title text-black">{el.name}</h2>
                <p className="text-base">
                  <span className="font-semibold">가격:</span> {el.price}
                </p>
                <p className="text-base">
                  <span className="font-semibold">장소:</span> {el.place}
                </p>
                <p className="text-base">
                  <span className="font-semibold">마감날짜:</span> {el.endDate}
                </p>
                <div className="card-actions justify-end">
                  <button className="btn btn-primary">상세 보기</button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </>
  );
}
