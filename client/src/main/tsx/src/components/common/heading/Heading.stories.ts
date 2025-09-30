import type {Meta, StoryObj} from "@storybook/react-vite";
import {Heading} from "./Heading.tsx";

const meta: Meta<typeof Heading> = {
    component: Heading,
    title: 'Heading',
    tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof Heading>;

export const DefaultHeading: Story = {
    args: {
        align: 'start',
        children: 'My heading',
        color: 'black',
        size: 'medium',
        tag: 'h1',
        testId: 'my-test-id',
    },
};