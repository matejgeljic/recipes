import type {Meta, StoryObj} from "@storybook/react-vite";
import { Text } from './Text';

const meta: Meta<typeof Text> = {
    component: Text,
    title: 'Text',
    tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof Text>;

export const DefaultText: Story = {
    args: {
        align: 'start',
        children: 'My text',
        color: 'black',
        size: 'default',
        tag: 'div',
        testId: 'my-test-id',
        underline: false,
        weight: 'regular',
    },
};