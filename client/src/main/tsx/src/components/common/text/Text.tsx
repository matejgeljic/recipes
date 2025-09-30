import type {ReactNode} from "react";

const textAligns = {
    inherit: '[text-align:inherit]',
    start: 'text-start',
    center: 'text-center',
    end: 'text-end',
    justify: 'text-justify',
};

export type TextAlign = keyof typeof textAligns;

const textColors = {
    inherit: '[color:inherit]',
    black: 'text-black',
    white: 'text-white',
}

const textSizes = {
    inherit: '[font-size:inherit]',
    small: 'text-sm',
    default: 'text-base',
    large: 'text-lg',
    'x-large': 'text-xl',
}

export type TextSize = keyof typeof textSizes;

export type TextColor = keyof typeof textColors;

const textWeights = {
    inherit: '[font-weight:inherit]',
    regular: 'font-normal',
    'semi-bold': 'font-semibold',
    bold: 'font-bold',
};

export type TextWeight = keyof typeof textWeights;

export type TextTag = 'p' | 'span' | 'div' | 'address' | 'blockquote' | 'figcaption' | 'cite' | 'time' | 'legend';

export interface TextProps {
    align?: TextAlign;
    children?: ReactNode;
    color?: TextColor;
    testId?: string;
    size?: TextSize;
    tag?: TextTag;
    underline?: boolean;
    weight?: TextWeight;
}

export const Text = ({
                         align = 'start',
                         children,
    color = 'black',
    size = 'default',
    tag = 'div',
    testId,
    underline = false,
    weight = 'regular',
}: TextProps) => {
    const classNames = [
        textAligns[align],
        textColors[color],
        textSizes[size],
        underline ? 'underline' : 'no-underline',
        textWeights[weight],
        '[letter-spacing:normal]',
        '[text-size-adjust:none]',
        'm-0',
        'p-0',
        'break-words',
        'hyphens-auto',
        'list-none',
    ]
        .filter(Boolean)
        .join(' ');

    const Tag = tag;

    return (
        <Tag className={classNames} data-testid={testId}>
            {children}
        </Tag>
    );
}