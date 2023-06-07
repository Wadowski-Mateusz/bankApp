import React from "react";
import { TransactionViewDTO } from "../../DTOs/TransactionDTOs";
import { format } from 'date-fns';

interface Props {
  transaction: TransactionViewDTO;
}

export default function TransactionView({ transaction }: Props) {
  return (
    <tr>
      <td>{format(new Date(transaction.timestamp), 'dd MMMM yyyy')}</td>
      <td>{transaction.type}</td>
      <td>{transaction.fullName}</td>
      <td>{transaction.title}</td>
      <td>{transaction.amount}</td>
    </tr>
  );
}
