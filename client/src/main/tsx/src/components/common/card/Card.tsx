import type { ReactNode } from "react";

export interface CardProps {
  imageSrc: string;
  imageAlt: string;
  footer: ReactNode;
  testId?: string;
  rounded?: boolean;
  shadow?: boolean;
}

export const Card = ({
  imageSrc,
  imageAlt,
  footer,
  testId,
  rounded = true,
  shadow = true,
}: CardProps) => {
  return (
    <figure
      data-testid={testId}
      className={[
        "bg-white",
        shadow ? "shadow-md" : "",
        rounded ? "rounded-lg overflow-hidden" : "",
        "flex flex-col",
        "transition-shadow duration-200",
        "border border-gray-100",
        "w-full",
        "hover:shadow-2xl",
      ].join(" ")}
      tabIndex={0}
      aria-label={imageAlt}
    >
      <div className="relative w-full aspect-[4/3] bg-gray-100">
        <img
          src={imageSrc}
          alt={imageAlt}
          className="object-cover w-full h-full"
          loading="lazy"
          draggable={false}
        />
      </div>
      <figcaption
        className="flex flex-col gap-1 px-4 py-3 border-t border-gray-200 bg-white"
        aria-live="polite"
      >
        {footer}
      </figcaption>
    </figure>
  );
};
