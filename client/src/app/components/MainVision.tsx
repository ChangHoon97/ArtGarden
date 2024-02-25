import Button from "./Button";
export default function MainVision() {
  return (
    <>
      <div className="bg-mainred">
        <div className="ml-80 pl-10">
          <div>
            <div className="mb-0">
              <span className="text-mainyellow text-5xl mb-0 pb-0">
                THE FUTURE
              </span>
              <span className="text-mainbrown text-4xl"> of </span>
              <div />
              <span className="text-mainyellow text-5xl mt-0">
                DESIGN IS HERE
              </span>
            </div>

            <div className="">
              <span className="text-mainwhite">
                Human expertise+AItechnologies+Unlimited Designs
              </span>
            </div>
          </div>

          <div className="mt-10 pb-12">
            <Button color="black" size="lg">
              See how this works
            </Button>
            <Button color="white" size="md">
              Check our work
            </Button>
          </div>
        </div>
      </div>
      <div className="bg-mainnavy h-60 ">
        <div className="text-mainwhite text-xl px-80 ml-8 py-10 pr-80 mr-52">
          Harnessing the Synergy of Human Expertise and AI Mastery: With the
          dynamic fusion of skilled designers and cutting-edge AI tools such as
          Chat GPT, Midjourney, Dall-E, Figma, Webflow, and Framer, we deliver
          an unrivaled blend of speed, precision, and streamlined efficiency,
          ensuring design excellence beyond expectations.
        </div>
      </div>
    </>
  );
}
