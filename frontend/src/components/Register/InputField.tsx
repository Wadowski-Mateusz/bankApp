import React, { ChangeEvent } from "react";


interface Props {
  name: string,
  type: string
  value: string,
  onChange: (e: ChangeEvent<HTMLInputElement>) => void,
  placeholder: string,
}

export default function InputField ({ name, type, value, onChange, placeholder } : Props) {
  return (
    <input
      name={name}
      type={type}
      value={value}
      onChange={onChange}
      placeholder={placeholder}
      className="rounded-2 m-1"
    />
  );
};

