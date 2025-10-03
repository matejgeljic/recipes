import type { Meta, StoryObj } from "@storybook/react-vite";
import { RadioGroup } from "./RadioGroup.tsx";

const meta: Meta<typeof RadioGroup> = {
  component: RadioGroup,
  title: "RadioGroup",
  tags: ["autodocs"],
};

export default meta;

type Story = StoryObj<typeof RadioGroup>;

export const DefaultRadioGroup: Story = {
  args: {
    title: "My Radio Group",
    name: "my-radio-group",
    value: "option1",
    onChange: (value: string) => {
      console.log("Selected:", value);
    },
    options: [
      { label: "Option 1", value: "option1" },
      { label: "Option 2", value: "option2" },
      { label: "Option 3", value: "option3", disabled: true },
    ],
    collapsible: true,
    defaultCollapsed: false,
    testId: "radio-group-1",
  },
};
