export default function ReviewMain() {
  const fakeData = [
    {
      id: 1,
      photo: "",
      content: "Description 1",
      star: "_____",
      day: "2023-11-17",
    },
    {
      id: 2,
      photo: "",
      content: "Description 2",
      start: "___",
      day: "2023-11-17",
    },
    {
      id: 3,
      photo: "",
      content: "Description 1",
      star: "_____",
      day: "2023-11-17",
    },
    {
      id: 4,
      photo: "",
      content: "Description 2",
      start: "___",
      day: "2023-11-17",
    },
    {
      id: 5,
      photo: "",
      content: "Description 1",
      star: "_____",
      day: "2023-11-17",
    },
    {
      id: 6,
      photo: "",
      content: "Description 2",
      start: "___",
      day: "2023-11-17",
    },
  ];
  return (
    <>
      <div className="flex space-x-4">
        <div className="bg-mainwhite py-44">
          <div>리뷰</div>
          {fakeData.map((el) => (
            <div key={el.id} className="w-72 h-80 rounded-md shadow-xl">
              <div className="bg-mainrealblack h-1/2 rounded-md">
                {el.photo}
              </div>
              <div className="">
                <div>{el.content}</div>
                <div>{el.star}</div>
                <div>{el.day}</div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </>
  );
}
