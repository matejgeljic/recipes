import type { Meta, StoryObj } from "@storybook/react-vite";
import { Card } from "./Card.tsx";

const meta: Meta<typeof Card> = {
  component: Card,
  title: "Card",
  tags: ["autodocs"],
};

export default meta;

type Story = StoryObj<typeof Card>;

export const DefaultCard: Story = {
  args: {
    imageSrc: "https://via.placeholder.com/400x300",
    imageAlt: "Placeholder Image",
    testId: "default-card",
    footer: "Hi",
  },
};
