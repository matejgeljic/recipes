import type { Meta, StoryObj } from "@storybook/react-vite";
import { Button } from "./Button.tsx";
import { UserPlus } from "lucide-react";

const meta: Meta<typeof Button> = {
  component: Button,
  title: "Button",
  tags: ["autodocs"],
} as Meta<typeof Button>;

export default meta;

type Story = StoryObj<typeof Button>;

export const PrimaryButton: Story = {
  args: {
    title: "My Button",
    isDisabled: false,
    icon: UserPlus,
    isLoading: false,
    variant: "blue",
    type: "button",
  },
};
