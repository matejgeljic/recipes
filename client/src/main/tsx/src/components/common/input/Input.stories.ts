import type { Meta, StoryObj } from "@storybook/react-vite";
import { Input } from "./Input.tsx";

const meta: Meta<typeof Input> = {
  component: Input,
  title: "Input",
  tags: ["autodocs"],
};

export default meta;

type Story = StoryObj<typeof Input>;

export const DefaultInput: Story = {
  args: {
    label: "My Input",
    value: "",
    onChange: () => {},
    placeholder: "Enter text...",
  },
};
