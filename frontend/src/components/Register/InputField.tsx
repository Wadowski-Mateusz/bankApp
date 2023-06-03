import React, { ChangeEvent } from "react";


interface Props {
  name: string,
  type: string,
  value: string | Date,
  onChange: (e: ChangeEvent<HTMLInputElement>) => void,
  placeholder: string,
}

export default function InputField ({ name, type, value, onChange, placeholder } : Props) {
  const convertedValue = typeof value === 'string' ? value : value.toISOString();

  return (
    <input
      name={name}
      type={type || ''}
      value={convertedValue}
      onChange={onChange}
      placeholder={placeholder}
      className="rounded-2 m-1"
    />
  );
};

