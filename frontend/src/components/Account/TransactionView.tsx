import React from "react";
import { TransactionViewDTO } from "../DTOs/TransactionDTOs";

interface Props {
  transaction: TransactionViewDTO;
}

export default function TransactionView({ transaction }: Props) {
  return (
    <tr>
      <td>{transaction.timestamp.toLocaleString()}</td>
      <td>{transaction.type}</td>
      <td>{transaction.fullName}</td>
      <td>{transaction.title}</td>
      <td>{transaction.amount}</td>
    </tr>
  );
}
