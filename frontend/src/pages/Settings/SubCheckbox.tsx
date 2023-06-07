import React, { ChangeEvent, useEffect, useState } from "react";
import {FormCheck} from 'react-bootstrap'


interface Props {
  chckVal: boolean | undefined;
  onChange: (event: ChangeEvent<HTMLInputElement>) => void;
}


export default function SubCheckbox({ chckVal, onChange }: Props) {

  
return(
      <FormCheck className="form-check form-switch form-check-inline m-2">
      <label htmlFor="email-sub" className="form-check-label form-switch text-white" >Email subscription</label>
      <input 
        id="email-sub" 
        type="checkbox" 
        onChange={onChange}
        className="form-check-input"
        checked={chckVal}
        defaultChecked={chckVal}
        />
    </FormCheck>
)
}