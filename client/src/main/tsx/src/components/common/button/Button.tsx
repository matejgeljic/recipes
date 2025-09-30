import React from "react";
import { Loader2, type LucideProps } from "lucide-react";
import { Icon } from "../icon/Icon.tsx";
import {Text} from "../text/Text.tsx";

const buttonVariants = {
    yellow: "yellow",
    blue: "blue",
} as const;
export type ButtonVariant = keyof typeof buttonVariants;

const variantStyles = {
    yellow: {
        enabled: {
            classes: "bg-yellow text-black",
            iconColor: "text-black",
        },
        disabled: {
            classes: "bg-light text-black",
            iconColor: "text-black",
        },
    },
    blue: {
        enabled: {
            classes: "bg-blue text-white",
            iconColor: "text-white",
        },
        disabled: {
            classes: "bg-grey text-white",
            iconColor: "text-white",
        },
    },
} as const;

export type ButtonType = "button" | "submit" | "reset";

export interface ButtonProps {
    isDisabled?: boolean;
    title?: string;
    icon?: React.FC<LucideProps>;
    isLoading?: boolean;
    variant?: ButtonVariant;
    type?: ButtonType;
    onClick?: () => void;
}

export const Button = ({
                           isDisabled = false,
                           title,
                           icon,
                           isLoading = false,
                           variant = "yellow",
                           type = "button",
                           onClick,
                       }: ButtonProps) => {
    const state = isDisabled ? "disabled" : "enabled";
    const { classes, iconColor } = variantStyles[variant][state];

    const classNames = [
        "rounded-lg",
        "px-2",
        "py-1",
        "gap-2",
        "flex",
        "cursor-pointer",
        classes,
        isDisabled || isLoading ? "cursor-not-allowed" : "",
    ]
        .filter(Boolean)
        .join(" ");

    const content = (
        <>
            <Text color={'inherit'} tag={'span'}>{title}</Text>
            {icon && <Icon icon={icon} color={iconColor} />}
        </>
    );

    return (
        <button
            className={classNames}
            type={type}
            disabled={isDisabled}
            aria-disabled={isDisabled || isLoading ? true : undefined}
            aria-label={isLoading ? "Loading" : undefined}
            onClick={isDisabled || isLoading ? undefined : onClick}
        >
            {content}
            {isLoading &&  <Loader2 className={`${iconColor} animate-spin`} />}
        </button>
    );
};
