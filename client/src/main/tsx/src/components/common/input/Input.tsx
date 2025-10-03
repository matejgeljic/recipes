import type { ChangeEvent } from "react";
import { Text } from "../text/Text";

type InputState = "none" | "error" | "success";
type InputType = "text" | "search" | "time";

export interface InputProps {
  label: string;
  value: string;
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
  type?: InputType;
  state?: InputState;
  description?: string;
  hideLabel?: boolean;
  testId?: string;
  message?: string;
  placeholder?: string;
  required?: boolean;
}

const stateBorder = {
  none: "border-gray focus:border-blue-500",
  error: "border-error focus:border-red-600",
  success: "border-success focus:border-green-600",
};

export const Input = ({
  label,
  value,
  onChange,
  type = "text",
  state = "none",
  description,
  hideLabel = false,
  testId,
  message,
  placeholder,
  required = false,
}: InputProps) => {
  const inputId = `input-${label.replace(/\s+/g, "-").toLowerCase()}`;
  const describedBy =
    [description ? `${inputId}-desc` : null, message ? `${inputId}-msg` : null]
      .filter(Boolean)
      .join(" ") || undefined;

  return (
    <div className="flex flex-col gap-1">
      <InputLabel
        label={label}
        inputId={inputId}
        hideLabel={hideLabel}
        required={required}
      />
      <InputDescription inputId={inputId} description={description} />
      <input
        id={inputId}
        type={type}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
        required={required}
        aria-required={required}
        aria-invalid={state === "error"}
        aria-describedby={describedBy}
        data-testid={testId}
        className={[
          "block w-full rounded-md",
          "px-3 py-2",
          "bg-white",
          "outline-none",
          "transition-colors duration-150",
          "text-base",
          "placeholder-gray-",
          "border",
          stateBorder[state],
        ].join(" ")}
      />
      <InputMessage inputId={inputId} message={message} state={state} />
    </div>
  );
};

const InputLabel = ({
  inputId,
  hideLabel,
  label,
  required,
}: {
  inputId: string;
  hideLabel: boolean;
  label: string;
  required: boolean;
}) => {
  return (
    <label htmlFor={inputId} className={hideLabel ? "sr-only" : "mb-1"}>
      <Text tag="span" weight="regular">
        {label}
        {required && (
          <Text tag="span" color="error" aria-hidden="true">
            {" *"}
          </Text>
        )}
      </Text>
    </label>
  );
};

const InputDescription = ({
  description,
  inputId,
}: {
  description?: string;
  inputId: string;
}) => {
  if (!description) {
    return null;
  }

  return (
    <Text tag="div" size="small" color="grey" testId={`${inputId}-desc`}>
      {description}
    </Text>
  );
};

const InputMessage = ({
  state,
  inputId,
  message,
}: {
  state: InputState;
  inputId: string;
  message?: string;
}) => {
  if (!message) {
    return null;
  }

  return (
    <Text
      tag="div"
      size="small"
      color={state === "error" ? "error" : "black"}
      testId={`${inputId}-msg`}
    >
      {message}
    </Text>
  );
};
