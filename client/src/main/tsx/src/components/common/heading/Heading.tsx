import type { ReactNode } from "react";

const headingAligns = {
  inherit: "[text-align:inherit]",
  start: "text-start",
  center: "text-center",
  end: "text-end",
  justify: "text-justify",
};

export type HeadingAlign = keyof typeof headingAligns;

const headingColors = {
  inherit: "[color:inherit]",
  black: "text-black",
};

export type HeadingColor = keyof typeof headingColors;

const headingSizes = {
  inherit: "[font-size:inherit]",
  small: "text-sm",
  medium: "text-base",
  large: "text-xl",
  "x-large": "text-2xl",
  "xx-large": "text-3xl",
};

export type HeadingSize = keyof typeof headingSizes;

export type HeadingTag = "h1" | "h2" | "h3" | "h4" | "h5";

const tagToSize: Record<HeadingTag, HeadingSize> = {
  h1: "xx-large",
  h2: "x-large",
  h3: "large",
  h4: "medium",
  h5: "small",
};

export interface HeadingProps {
  align?: HeadingAlign;
  children?: ReactNode;
  color?: HeadingColor;
  size?: HeadingSize;
  tag?: HeadingTag;
  testId?: string;
}

export const Heading = ({
  align = "start",
  children,
  color = "black",
  size,
  tag = "h1",
  testId,
}: HeadingProps) => {
  const classNames = [
    "font-semibold",
    "leading-[normal]",
    headingAligns[align],
    headingColors[color],
    size ? headingSizes[size] : headingSizes[tagToSize[tag]],
    "tw-overflow-clip",
    "tw-m-0",
    "tw-p-0",
    "tw-list-none",
  ]
    .filter(Boolean)
    .join(" ");

  const Tag = tag;

  return (
    <Tag data-test={testId} className={classNames}>
      {children}
    </Tag>
  );
};
