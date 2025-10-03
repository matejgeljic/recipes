import React from "react";
import { type LucideProps } from "lucide-react";

interface IconProps {
  icon: React.FC<LucideProps>;
  color: string;
}

export const Icon = (props: IconProps) => {
  const { icon: Icon, color } = props;

  return <Icon className={color} />;
};
