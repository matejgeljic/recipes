import React, { type ReactNode } from "react";
import { Heading } from "../heading/Heading";
import { Text } from "../text/Text.tsx";

export interface CardListProps {
  children: ReactNode;
  title?: string;
  columns?: number; // e.g. 1, 2, 3, 4
  gap?: string; // e.g. "gap-4"
  testId?: string;
  ariaLabel?: string;
  additionalClasses?: string;
}

export const CardList = ({
  children,
  title,
  columns = 4,
  gap = "gap-6",
  testId,
  ariaLabel,
  additionalClasses = "",
}: CardListProps) => {
  // Responsive grid classes
  const gridCols =
    {
      1: "grid-cols-1",
      2: "sm:grid-cols-2",
      3: "sm:grid-cols-2 md:grid-cols-3",
      4: "sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4",
    }[columns] || "sm:grid-cols-2 md:grid-cols-3";

  const cardTitle = title && (
    <Heading tag="h2" size="large" additionalClasses={"mb-4"}>
      {title}
    </Heading>
  );

  const isEmpty = React.Children.count(children) === 0;
  if (isEmpty) {
    return (
      <>
        {cardTitle}
        <Text color="black" size="large" additionalClasses="italic">
          No items to display.
        </Text>
      </>
    );
  }

  const content = Array.isArray(children) ? (
    children.map((child, idx) => (
      <li key={idx} className="h-full">
        {child}
      </li>
    ))
  ) : (
    <li className="h-full">{children}</li>
  );

  return (
    <section
      data-testid={testId}
      aria-label={ariaLabel || title}
      className={["flex flex-col", additionalClasses].join(" ")}
    >
      {cardTitle}
      <ul
        className={["grid", gridCols, gap, "list-none", "p-0", "m-0"].join(" ")}
        role="list"
      >
        {content}
      </ul>
    </section>
  );
};
