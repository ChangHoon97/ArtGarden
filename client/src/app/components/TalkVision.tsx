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
              <div
                className="md:col-span-5 lg:col-span-4 laptop:col-span-3 md:!col-start-7 text-md lg:text-lg reset-last content-intro pr-20 is-complete opacity-100"
                data-animation="fade"
              >
                <p>
                  Symbols of our state, written with the best modern fonts by
                  Ukrainian designers.
                </p>
              </div>
            </div>
            <div className="max-w-[96rem] mx-auto mb-100 text-center">
              <div
                className="text-[2.8rem] lg:text-[4.6rem] mb-20 tracking-tighter leading-tight px-5 laptop:px-40 is-complete"
                data-animation="split"
              >
                We invite you to take a look at Ukrainian letters from two new
                perspectives - significant symbols for Ukraine and modern
                Ukrainian fonts
              </div>
              <p
                className="mb-0 is-complete"
                data-animation="split"
                data-delay="0.35"
              >
                So what is the - typographic Ukraine?
              </p>
            </div>
            <div className="grid md:grid-cols-12 md:gap-x-14 items-end">
              <div className="md:col-span-6 flex justify-center">
                <div
                  className="aspect-[270/374] w-[24rem] laptop:w-[27rem] pointer-events-none mb-50 md:mb-0"
                  data-intro-position="last"
                >
                  <div className="card card--simple aspect-[76/105] w-[24rem] laptop:w-[27rem] h-auto mx-auto laptop:hidden card--preview">
                    <div className="card__front">
                      <div className="card__headline">
                        <span>[е]</span>
                        <span>07</span>
                      </div>
                      <div className="card__svg bg-yellow">
                        {/* <img className="block w-full h-full object-cover" src="/assets/images/letters/e.svg" alt="Е" width="229" height="259"> */}
                      </div>
                      <p className="card__subtitle whitespace-nowrap overflow-ellipsis overflow-hidden">
                        Aeneid by Ivan Kotliarevsky{" "}
                      </p>
                    </div>
                    <div className="card__back">
                      <div className="card__headline">
                        <span>[е]</span>
                        <span>07</span>
                      </div>
                      <div className="card__image">
                        {/* <picture className="w-full h-auto flex">
                    <source media="(min-width: 1000px)" data-srcset="https://abetka-strapi.onrender.com/uploads/Mask_e_6f88f1d306.jpg" srcset="https://abetka-strapi.onrender.com/uploads/Mask_e_6f88f1d306.jpg">
                    <img className="block w-full h-full object-cover bg-gray-100 opacity-0 loaded" data-component="lazyload" alt="image description" width="1200" height="1472" loading="lazy" src="https://abetka-strapi.onrender.com/uploads/small_Mask_e_6f88f1d306.jpg" data-ll-status="loaded">
                  </picture> */}
                      </div>
                    </div>
                  </div>
                </div>
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
