import { type PropsWithChildren, useCallback, useState } from "react";
import { Text } from "../text/Text";
import { Heading } from "../heading/Heading";
import { ChevronDown, ChevronUp } from "lucide-react";
import { Icon } from "../icon/Icon.tsx";
import { motion, AnimatePresence } from "framer-motion";

export interface RadioOption {
  label: string;
  value: string;
  disabled?: boolean;
  testId?: string;
}

export interface RadioGroupProps {
  title: string;
  options: RadioOption[];
  name: string;
  value: string;
  onChange: (value: string) => void;
  collapsible?: boolean;
  defaultCollapsed?: boolean;
  testId?: string;
}

export const RadioGroup = ({
  title,
  options,
  name,
  value,
  onChange,
  collapsible = true,
  defaultCollapsed = false,
  testId,
}: RadioGroupProps) => {
  const [isCollapsed, setIsCollapsed] = useState(defaultCollapsed);

  const handleClick = useCallback(() => {
    setIsCollapsed((prevState) => !prevState);
  }, []);

  const radioList = options.map((option) => (
    <Radio
      key={option.value}
      name={name}
      label={option.label}
      value={option.value}
      checked={value === option.value}
      onChange={() => onChange(option.value)}
      disabled={option.disabled}
      testId={option.testId}
    />
  ));

  if (!options.length) {
    return null;
  }

  return (
    <section data-testid={testId} className="mb-4 pb-4">
      <button
        type="button"
        aria-expanded={!isCollapsed}
        aria-controls={`${name}-options`}
        className="flex items-center w-full justify-between mb-2 focus:outline-none"
        onClick={handleClick}
        tabIndex={collapsible ? 0 : -1}
        style={{ cursor: collapsible ? "pointer" : "default" }}
      >
        <Heading tag="h3" size="medium">
          {title}
        </Heading>
        <CollapseToggle collapsible={collapsible} isCollapsed={isCollapsed} />
      </button>
      <RadioGroupContent name={name} isCollapsed={isCollapsed}>
        {radioList}
      </RadioGroupContent>
    </section>
  );
};

const CollapseToggle = ({
  collapsible,
  isCollapsed,
}: {
  collapsible: boolean;
  isCollapsed: boolean;
}) => {
  if (!collapsible) {
    return null;
  }

  const collapsedIcon = isCollapsed ? (
    <Icon icon={ChevronDown} color={"black"} />
  ) : (
    <Icon icon={ChevronUp} color={"black"} />
  );

  return (
    <span aria-hidden="true" className="ml-2">
      {collapsedIcon}
    </span>
  );
};

const RadioGroupContent = ({
  isCollapsed,
  name,
  children,
}: PropsWithChildren<{ isCollapsed: boolean; name: string }>) => {

  return (
    <AnimatePresence initial={false}>
      {!isCollapsed && (
        <motion.div
          key={`${name}-options`}
          id={`${name}-options`}
          role="radiogroup"
          aria-labelledby={`${name}-label`}
          className="flex flex-col gap-2 overflow-hidden"
          initial={{ height: 0, opacity: 0 }}
          animate={{ height: "auto", opacity: 1 }}
          exit={{ height: 0, opacity: 0 }}
          transition={{ duration: 0.3, ease: "easeInOut" }}
        >
          {children}
        </motion.div>
      )}
    </AnimatePresence>
  );
};

interface RadioProps {
  name: string;
  label: string;
  value: string;
  checked: boolean;
  onChange: () => void;
  disabled?: boolean;
  testId?: string;
}

const Radio = ({
  name,
  label,
  value,
  checked,
  onChange,
  disabled = false,
  testId,
}: RadioProps) => {
  const id = `${name}-${value}`;
  return (
    <label
      htmlFor={id}
      className={`flex items-start gap-2 cursor-pointer ${disabled ? "opacity-60 cursor-not-allowed" : ""}`}
    >
      <input
        id={id}
        name={name}
        type="radio"
        value={value}
        checked={checked}
        onChange={onChange}
        disabled={disabled}
        aria-checked={checked}
        aria-disabled={disabled}
        data-testid={testId}
        className="mt-1 accent-orange-600"
      />
      <div>
        <Text tag="span" weight="regular">
          {label}
        </Text>
      </div>
    </label>
  );
};
