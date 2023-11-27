export default function TalkVision() {
  return (
    <>
      <section className="relative is-complete" data-header-theme="dark">
        <div className="left-0 top-0 w-full h-full absolute decoration-grid px-18 lg:px-14 flex justify-between pointer-events-none">
          <div className="w-10 lg:w-14 h-full border-l border-r border-current opacity-10"></div>
          <div className="w-10 lg:w-14 h-full border-l border-r border-current opacity-10"></div>
          <div className="w-10 lg:w-14 h-full border-l border-r border-current opacity-10"></div>
          <div className="w-10 lg:w-14 h-full border-l border-r border-current opacity-10 hidden lg:block"></div>
          <div className="w-10 lg:w-14 h-full border-l border-r border-current opacity-10 hidden lg:block"></div>
        </div>
        <div
          className="pt-150 laptop:pt-350 pb-150 lg:pb-180 bg-white"
          data-animation="intro-section"
          data-start="top bottom"
          data-end="bottom bottom"
        >
          <div
            className="w-200 pointer-events-none absolute left-1/2 top-0 -translate-x-1/2 -translate-y-1/2 aspect-[155/215]"
            data-intro-position="first"
          ></div>
          <div className="container">
            <div className="grid md:grid-cols-12 md:gap-x-14 mb-100">
              <div className="md:col-span-5 lg:col-span-4 laptop:col-span-3 md:!col-start-7 text-md lg:text-lg reset-last content-intro pr-20 is-complete opacity-100">
                <p>
                  Symbols of our state, written with the best modern fonts by
                  Ukrainian designers.
                </p>
              </div>
            </div>
            <div className="mb-100 text-center lg:ml-64">
              <div className="text-[2.8rem] lg:text-[3.3rem] mb-20 tracking-tighter leading-tight px-5 laptop:px-40 is-complete lg:ml-64">
                We invite you to take a look at Ukrainian letters from two new
                perspectives - significant symbols for Ukraine and modern
                Ukrainian fonts
              </div>
              <p className="mb-16 is-complete">
                So what is the - typographic Ukraine?
              </p>
            </div>
            <div className="grid md:grid-cols-12 md:gap-x-14 items-end">
              <div className="md:col-span-6 flex justify-center">
                <div
                  className="aspect-[270/374] w-[24rem] laptop:w-[27rem] pointer-events-none mb-50 md:mb-0"
                  data-intro-position="last"
                ></div>
              </div>
              <div
                className="md:col-span-5 lg:col-span-4 laptop:col-span-3 md:!col-start-7 text-md lg:text-lg reset-last content-intro md:pr-20 is-complete opacity-100"
                data-animation="fade"
              >
                <p>
                  Each letter of our alphabet is dedicated to a symbol without
                  which Ukraine would not be the country we know, love, and
                  stand for.
                </p>
                <p>
                  In the pages of this project, you will find 33 events,
                  figures, symbols, and signs that accompanied Ukraine on its
                  path to independence restoration in 1991 and support us in the
                  fight for it today.
                </p>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}
