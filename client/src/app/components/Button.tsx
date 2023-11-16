import { ReactNode } from "react";

type Color = "black" | "white";
type Size = "sm" | "md" | "lg";

interface ButtonProps {
  type?: "submit" | undefined;
  color: Color;
  size: Size;
  className?: string;
  onClick?: () => void;
  children: ReactNode;
}

function Button({
  type,
  color,
  size,
  className,
  onClick,
  children,
}: ButtonProps) {
  let combinedClassName = "";

  switch (color) {
    case "black": {
      combinedClassName =
        "text-mainyellow rounded-2xl border-2 border-mainyellow bg-mainblack ml-40";
      break;
    }
    case "white": {
      combinedClassName =
        "text-mainwhite rounded-2xl border-2 border-mainwhite bg-mainred ml-40";
      break;
    }
  }
  switch (size) {
    case "sm": {
      combinedClassName += "w-58 h-12 text-xl font-semibold px-3";
      break;
    }
    case "md": {
      combinedClassName += "w-58 h-12 text-xl font-semibold px-3";
      break;
    }
    case "lg": {
      combinedClassName += "w-58 h-12 text-xl font-semibold px-3 mr-6";
      break;
    }
  }
  return (
    <button
      type={type ? type : "button"}
      className={`${combinedClassName} ${className}`}
      onClick={onClick}
    >
      {children}
    </button>
  );
}

export default Button;
